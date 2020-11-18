package relative.netty.test3.netty4;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Netty服务端
 * @author : chensy
 * Date : 2020/11/16 下午8:16
 */
public class Server {
    public static void main(String[] args) {
        basic();
    }

    public static void basic() {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(); //netty4 使用方式
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        System.out.println("server init channel");
                    }
                });
        serverBootstrap.bind(8000);
    }

    /**
     * NioEventLoopGroup：主要管理eventLoop的生命周期。
     * https://www.jianshu.com/p/e577803f0fb8
     */
}
