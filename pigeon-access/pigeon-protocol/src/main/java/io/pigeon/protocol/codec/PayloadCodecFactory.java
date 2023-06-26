package io.pigeon.protocol.codec;

import io.pigeon.common.entity.Message;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/6/1
 **/
public class PayloadCodecFactory {
    private static final PayloadCodec<Message>[] CODEC_ARRAY;
    public static final PayloadCodecFactory INSTANCE = new PayloadCodecFactory();

    static {
        CODEC_ARRAY = new PayloadCodec[4];
        CODEC_ARRAY[1] = new ProtostuffPayloadCodec<>();
    }

    public PayloadCodec<Message> getCodec(int codecType) {
        return CODEC_ARRAY[codecType];
    }

}
