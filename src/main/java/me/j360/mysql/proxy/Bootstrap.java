package me.j360.mysql.proxy;

/**
 * @author: min_xu
 * @date: 2018/3/9 下午6:18
 * 说明：
 */
public final class Bootstrap {

    private static final int DEFAULT_PORT = 3307;

    /**
     * Main Entrance.
     *
     * @param args startup arguments.
     * @throws InterruptedException interrupted exception
     */
    // CHECKSTYLE:OFF
    public static void main(final String[] args) throws InterruptedException {
        // CHECKSTYLE:ON
        new MysqlProxyServer().start(getPort(args));
    }

    private static int getPort(final String[] args) {
        if (0 == args.length) {
            return DEFAULT_PORT;
        }
        try {
            return Integer.parseInt(args[0]);
        } catch (final NumberFormatException ex) {
            return DEFAULT_PORT;
        }
    }

}
