package com.csy.netty.test2;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author : chensy
 * Date : 2020-02-21 22:32
 */
public class Client {

    public static void main(String[] args) throws InterruptedException {

        EventLoopGroup workGroup = new NioEventLoopGroup();

        //客户端启动
        Bootstrap boot  = new Bootstrap();

        boot.group(workGroup)
                .channel(NioSocketChannel.class) //客户端的Socker
                .handler(new ChannelInitializer<SocketChannel>(){ //一掉要注意这个 SocketChannel 是Netty封装的，不是NIO

                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        //将我们的服务器处理类传递进去
                        ch.pipeline().addLast(new ClientHandler());
                    }
                });

        //绑定指定的端口 进行监听
        ChannelFuture channel = boot.connect("127.0.0.1", 8080).sync();

        //发送数据
        channel.channel().writeAndFlush(Unpooled.copiedBuffer("hello Client".getBytes()));

        //这个相当于没有关闭Channel，注释
        channel.channel().closeFuture().sync();

        //关闭线程组
        workGroup.shutdownGracefully();
    }
}


