package relative.netty.test2;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author : chensy
 * Date : 2020-02-21 22:30
 *
 */
public class Server {
    public static final Integer PORT = 8080;

    public static void main(String[] args) throws Exception {

        //1.第一个线程组 是用于接收Client端连接的
        EventLoopGroup  bootGroup = new NioEventLoopGroup(); //客户端
        //2.第二个线程组 实际业务操作请求
        EventLoopGroup  workGroup = new NioEventLoopGroup(); //网络读写

        //3.服务器启动类,配置服务器
        ServerBootstrap bootstrap = new ServerBootstrap();

        //加入客户端线程和网络读写
        bootstrap.group(bootGroup, workGroup)
                //我要指定使用NioServerSocketChannel这种类型的通道 ,当我们是Http的时候，需要更换这个Channel的类型
                .channel(NioServerSocketChannel.class)
                // 指定处理SockerChannel 的处理器
                .childHandler(new ChannelInitializer<SocketChannel>(){ //一掉要注意这个 SocketChannel 是Netty封装的，不是NIO

                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        //将我们的服务器处理类传递进去
                        ch.pipeline().addLast(new ServerHandler());
                    }
                })
                //设定BackLog大小大小
                .option(ChannelOption.SO_BACKLOG, 128)  //生产环境中，最好配额制100多
                //保持连接
                .childOption(ChannelOption.SO_KEEPALIVE, true)
        ;

        System.out.println("服务器启动。。。。。。");
        //绑定指定的端口 进行监听
        ChannelFuture future = bootstrap.bind(PORT).sync();

        //如果不休眠 ，直接就结束了
//		Thread.sleep(1000000);

        //关闭Channel
        //这个是相当于程序是睡眠模式，线程阻塞在这个地方
        future.channel().closeFuture().sync();

        //关闭线程组
        bootGroup.shutdownGracefully();
        workGroup.shutdownGracefully();


    }
}


