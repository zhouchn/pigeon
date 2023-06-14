package io.pigeon.access.api;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/6/14
 **/
public interface PigeonClient {
    Future<Void> connect(String host, int port);

    Future<Void> connect(ClientOptions options);

    Future<Void> close();

    Future<Void> close(long timeout, TimeUnit unit);
}
