package relative.netty.test2;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author : chensy
 * Date : 2020-02-21 22:32
 */

//这个Handler需要继承 ChannelHandlerAdapter,这个是Netty实现的
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        //读取Message
        ByteBuf buff = (ByteBuf) msg;
        //建立一个数组用来存储读取的数据
        byte [] data = new byte[buff.readableBytes()];

        buff.readBytes(data);
        System.out.println("Server:\t"+new String(data));

        //回馈客户端
        ctx.writeAndFlush(Unpooled.copiedBuffer("Hello,Server get Data".getBytes()))
                //设定响应后就关闭程序
                .addListener(ChannelFutureListener.CLOSE);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //用来处理Netty执行过程中的异常
        cause.printStackTrace(); //打印错误
        ctx.close(); //关闭容器
    }
}


