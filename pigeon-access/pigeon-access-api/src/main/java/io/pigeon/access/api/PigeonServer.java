package io.pigeon.access.api;

import io.pigeon.delivery.api.MessageSubscriber;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/6/14
 **/
public interface PigeonServer {
    PigeonServer options(ServerOptions options);

    MessageSubscriber subscriber();

    Future<Void> listenAndServe() throws InterruptedException;

    Future<Void> listenAndServe(int port) throws InterruptedException;

    Future<Void> shutdownNow();

    Future<Void> shutdown(long timeout, TimeUnit unit);

}
