package com.csy.interview.no2.aio_ext;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;

/**
 * @author chensy
 * @date 2023/8/17
 */
public class AioServer {

    private void listen(int port) {
        try {
            try(AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open()) {
                server.bind(new InetSocketAddress(port));
                System.out.println("服务端正在监听：" + port);

                ByteBuffer buff = ByteBuffer.allocateDirect(5);
                server.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
                    @Override
                    public void completed(AsynchronousSocketChannel result, Object attachment) {
                        try {
                            buff.clear();
                            result.read(buff).get();
                            buff.flip();
                            result.write(buff);
                            buff.flip();
                        }  catch (InterruptedException | ExecutionException e) {
                            throw new RuntimeException(e);
                        } finally {

                        }
                    }

                    @Override
                    public void failed(Throwable exc, Object attachment) {

                    }
                });
            }
        } catch (IOException e) {

        }
    }
}
