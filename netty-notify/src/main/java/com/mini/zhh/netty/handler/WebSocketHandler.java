package com.mini.zhh.netty.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description web socket handler
 * 处理图片等信息
 * @Author zunqiaozhang
 * @Date 2021/11/01
 */
public class WebSocketHandler extends SimpleChannelInboundHandler<BinaryWebSocketFrame> {

    /**
     * 活跃的channel
     */
    private static Map<ChannelId, Channel> activeChannelMap = new HashMap<>(16);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, BinaryWebSocketFrame msg) throws Exception {
        ctx.channel().writeAndFlush(new TextWebSocketFrame("上传成功"));
        for (Map.Entry<ChannelId, Channel> entry : activeChannelMap.entrySet()) {
            if (ctx.channel().id().equals(entry.getKey())) {
                continue;
            }
            System.out.println(entry.getKey());
            entry.getValue().writeAndFlush(msg.copy());
        }
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerAdded");
        System.out.println(ctx.channel().id());
        activeChannelMap.put(ctx.channel().id(), ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerRemoved");
        System.out.println(ctx.channel().id());
        activeChannelMap.remove(ctx.channel().id());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("exceptionCaught");
        System.out.println(ctx.channel().id());
    }

}
