package io.pigeon.delivery.upstream;

import io.pigeon.common.entity.Message;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/5/20
 **/
public class MessageEvent {
    private String source;
    private Message message;
    private String channelId;
    private long timestamp;
    private String threadName;
    private int threadPriority;
    private String data;

    long getTimeMillis() {
        return 1L;
    }

    public MessageEvent setData(String data) {
        this.data = data;
        return this;
    }

    public void clear() {
        System.out.println("clear event " + this);
    }

//    @Override
//    public String toString() {
//        return "LogEvent{" +
//                "data='" + data + '\'' +
//                '}';
//    }
}
