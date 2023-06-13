package io.pigeon.access.tcp.server;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/6/13
 **/
class TcpServerTest {

    @Test
    void listenAndServe() throws InterruptedException {
        TcpServer tcpServer = new TcpServer();
        tcpServer.listenAndServe(8888);
    }
}