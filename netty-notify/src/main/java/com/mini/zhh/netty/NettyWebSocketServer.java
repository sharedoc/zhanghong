package com.mini.zhh.netty;

import com.mini.zhh.netty.handler.WebSocketHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

import java.net.InetSocketAddress;

/**
 * @Description web socket 服务
 * 功能：处理图片上传和下载
 * @Author zunqiaozhang
 * @Date 2021/11/01
 */
public class NettyWebSocketServer {

    public static void main(String[] args) {
        // 线程组：处理连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // 线程组：真正的执行工作
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            System.out.println("服务启动成功");
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            // 设置线程组
            serverBootstrap.group(bossGroup, workerGroup)
                    // 设置channel服务
                    .channel(NioServerSocketChannel.class)
                    // 设置线程队列连接数
                    .option(ChannelOption.SO_BACKLOG, 128)
                    // 设置保持活动连接状态
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    // 给workerGroup的EventLoop对应的管道设置处理器
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // 设置处理器
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new HttpServerCodec());
                            pipeline.addLast(new ChunkedWriteHandler());
                            pipeline.addLast(new HttpObjectAggregator(8192));
                            // 图片处理
                            pipeline.addLast(new WebSocketServerProtocolHandler("/imgOperator"));
                            pipeline.addLast(new WebSocketHandler());
                        }
                    });
            // 设置端口
            ChannelFuture channelFuture = serverBootstrap.bind(new InetSocketAddress(8001)).sync();
            // 对关闭通道进行监听
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
