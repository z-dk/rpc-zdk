package com.zdk.rpc.server.transport;

import com.zdk.rpc.core.codec.RpcDecoder;
import com.zdk.rpc.core.codec.RpcEncoder;
import com.zdk.rpc.server.handler.RpcRequestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;

/**
 * @author zdk 
 */
@Slf4j
public class NettyRpcServer implements RpcServer {

    @Override
    public void start(int port) {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();

        try {
            String serverAddress = InetAddress.getLocalHost().getHostAddress();
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) {
                            socketChannel.pipeline()
                                    // 协议编码
                                    .addLast(new RpcEncoder<>())
                                    // 协议解码
                                    .addLast(new RpcDecoder())
                                    // 请求处理器
                                    .addLast(new RpcRequestHandler());
                        }
                    })
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture channelFuture = bootstrap.bind(serverAddress, port).sync();
            log.info("server addr {} started on port {}", serverAddress, port);
            channelFuture.channel().closeFuture().sync();
        } catch (Exception ignored) {

        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
