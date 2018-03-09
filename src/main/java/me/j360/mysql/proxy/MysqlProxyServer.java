package me.j360.mysql.proxy;

import java.util.concurrent.CountDownLatch;

/**
 * @author: min_xu
 * @date: 2018/3/9 下午6:37
 * 说明：
 */
public final class MysqlProxyServer {

    public void start(final int port) {

        CountDownLatch latch = new CountDownLatch(1);
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
