package io.pigeon.message.codec;

import io.pigeon.common.entity.Message;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/6/1
 **/
public class MessageCodecFactory {
    private static final PigeonMessageCodec<Message>[] CODEC_ARRAY;

    static {
        CODEC_ARRAY = new PigeonMessageCodec[4];
        CODEC_ARRAY[1] = new ProtostuffMessageCodec<>();
    }

    public PigeonMessageCodec<Message> getCodec(int codecType) {
        return CODEC_ARRAY[codecType];
    }

}
