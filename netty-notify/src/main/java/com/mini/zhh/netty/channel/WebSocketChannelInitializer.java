package com.mini.zhh.netty.channel;

import com.mini.zhh.netty.handler.WebSocketHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @Description web socket channel
 * @Author zunqiaozhang
 * @Date 2021/11/01
 */
public class WebSocketChannelInitializer extends ChannelInitializer {

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new ChunkedWriteHandler());
        pipeline.addLast(new HttpObjectAggregator(8192));
        // 图片处理
        pipeline.addLast(new WebSocketServerProtocolHandler("/imgOperator"));
        pipeline.addLast(new WebSocketHandler());
    }

}
