package io.pigeon.access.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.pigeon.common.entity.Message;
import io.pigeon.common.enums.CodecType;
import io.pigeon.common.enums.Command;

/**
 * <description>
 *     https://asciiflow.cn/
 *
 *
 *
 * @author chaoxi
 * @since 3.0.0 2023/5/22
 **/
public class PigeonMessageEncoder extends MessageToByteEncoder<Message> {
    private final MessageCodecFactory messageCodecFactory;

    public PigeonMessageEncoder(MessageCodecFactory messageCodecFactory) {
        this.messageCodecFactory = messageCodecFactory;
    }


    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Message message, ByteBuf byteBuf) throws Exception {
        System.out.println("start encoding...");
        int startIndex = byteBuf.writerIndex();
        // -------- header ---------
        int header = 0;
        // 4 bit的command
        Command command = message.command();
        if (command == null) {
            System.out.println("command is null, stop");
            return;
        }
        header |= (command.getValue() << 12);
        // 3 bit的version
        header |= (1 << 9);
        // 2 bit的序列化方式
        int protocol = CodecType.PROTOBUF.getValue();
        header |= (protocol << 7);
        // 1 bit compress
        // 1 bit qos
        // 1 bit ack
        // 1 bit sequence number
        // 1 bit
        // 1 bit
        int end = message.hasPayload() ? 0 : 1;
        // 1 bit end
        header |= end;
        byteBuf.writeShort(header);
        System.out.printf("cmd %s %s header: %s / %s%n", command, command.getValue(), header, Integer.toBinaryString(header));
        if (!message.hasPayload()) {
            System.out.println("encode finish");
            return;
        }

        // 2 字节的 payload length，可选
        byteBuf.writeShort(0);

        // variable header(option)
        // TODO

        // ------ payload ----------
        PigeonMessageCodec<Message> codec = messageCodecFactory.getCodec(protocol);
        int bodyLength = codec.encodeTo(message, byteBuf);

        // 序列化完后回填body的长度
        byteBuf.setShort(startIndex + 2, bodyLength);
        System.out.println("encode finish. payload size: " + (bodyLength));
    }

}
