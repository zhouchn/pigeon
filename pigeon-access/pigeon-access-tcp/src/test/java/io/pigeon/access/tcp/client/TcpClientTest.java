package io.pigeon.access.tcp.client;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/6/13
 **/
class TcpClientTest {

    @Test
    void connect() throws Exception {
        TcpClient tcpClient = new TcpClient("127.0.0.1", 8888);
        tcpClient.connect();
        CountDownLatch cdl = new CountDownLatch(1);
        cdl.await();
    }
}