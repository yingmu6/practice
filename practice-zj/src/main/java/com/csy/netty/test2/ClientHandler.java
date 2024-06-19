package com.csy.netty.test2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * @author : chensy
 * Date : 2020-02-21 22:33
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //打印错误
        cause.printStackTrace();

        //关闭容器
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            //获取message
            ByteBuf buf = (ByteBuf) msg;

            //将数据写到字节数组中
            byte [] data = new byte[buf.readableBytes()];
            buf.readBytes(data);

            //打印数据
            System.out.println("Client:\t"+new String(data));

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            //释放消息
            ReferenceCountUtil.release(msg);
        }
    }
}


