package me.j360.mysql.proxy;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import me.j360.mysql.proxy.constant.DatabaseType;
import me.j360.mysql.proxy.transport.common.codec.PacketCodecFactory;
import me.j360.mysql.proxy.transport.common.handler.DatabaseProxyHandlerFactory;

/**
 * @author: min_xu
 * @date: 2018/3/9 下午6:37
 * 说明：
 */
public final class MysqlProxyServer {

    public void start(final int port) throws InterruptedException {

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        public void initChannel(final SocketChannel socketChannel) {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            // TODO load database type from yaml or startup arguments
                            pipeline.addLast(PacketCodecFactory.createPacketCodecInstance(DatabaseType.MySQL));
                            pipeline.addLast(DatabaseProxyHandlerFactory.createDatabaseProxyHandlerInstance(DatabaseType.MySQL));
                        }
                    });
            ChannelFuture future = bootstrap.bind(port).sync();
            future.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

}
