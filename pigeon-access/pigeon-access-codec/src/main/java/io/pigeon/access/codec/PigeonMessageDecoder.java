package io.pigeon.access.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.pigeon.common.entity.Message;
import io.pigeon.common.entity.MessageFactory;
import io.pigeon.common.enums.Command;

import java.util.List;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/5/22
 **/
public class PigeonMessageDecoder extends ByteToMessageDecoder {
    private final MessageCodecFactory messageCodecFactory;

    public PigeonMessageDecoder(MessageCodecFactory messageCodecFactory) {
        this.messageCodecFactory = messageCodecFactory;
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        System.out.printf("start decode, readerIndex: %s readableBytes: %s%n", byteBuf.readerIndex(), byteBuf.readableBytes());
        if (byteBuf.readableBytes() > 2) {
            byteBuf.markReaderIndex();
            Message message = decodeMessage(byteBuf);
            if (message != null) {
                list.add(message);
            }
        }
        System.out.println("decode over, readerIndex: " + byteBuf.readerIndex());
    }

    private Message decodeMessage(ByteBuf byteBuf) {
        int header = byteBuf.readUnsignedShort();
        System.out.println("received: " + header + " / " + Integer.toBinaryString(header));
        int cmd = header >> 12;
        int ver = header >> 9 & 7;
        int protocol = header >> 7 & 3;
        int reachEnd = header & 1;
        Command command = Command.of(cmd);
        Message result = MessageFactory.newInstance(command);
        System.out.printf("cmd: %s %s version: %s codec %s end %s\r\n", command, cmd, ver, protocol, reachEnd);
        if (reachEnd == 1) {
            return result;
        }
        if (byteBuf.readableBytes() < 2) {
            byteBuf.resetReaderIndex();
            return null;
        }
        int length = byteBuf.readUnsignedShort();
        if (byteBuf.readableBytes() < length) {
            byteBuf.resetReaderIndex();
            return null;
        }
        int readerIndex = byteBuf.readerIndex();
        PigeonMessageCodec<Message> codec = messageCodecFactory.getCodec(protocol);
        codec.decodeFrom(byteBuf, length, result);
        // 更新读索引，避免重复读取相同内容
        byteBuf.readerIndex(readerIndex + length);
        System.out.printf("cmd: %s %s version: %s codec %s len: %s message: %s\r\n", command, cmd, ver, protocol, length, result);
        return result;
    }
}
