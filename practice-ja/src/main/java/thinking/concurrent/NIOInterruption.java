package thinking.concurrent;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousCloseException;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;

/**
 * @author chensy
 * @date 2024/4/14
 */
public class NIOInterruption {

    static class NIOBlocked implements Runnable {
        private final SocketChannel sc;
        public NIOBlocked(SocketChannel sc) {
            this.sc = sc;
        }
        public void run() {
            try {
                print("Waiting for read() in " + this);
                sc.read(ByteBuffer.allocate(1));
            } catch (ClosedByInterruptException e) {
                print("ClosedByInterruptException");
            } catch (AsynchronousCloseException e) {
                print("AsynchronousCloseException");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            print("Exiting NIOBlocked.run() " + this);
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        ServerSocket server = new ServerSocket(8080);
        InetSocketAddress isa =
                new InetSocketAddress("localhost", 8080);
        SocketChannel sc1 = SocketChannel.open(isa);
        SocketChannel sc2 = SocketChannel.open(isa);
        Future<?> f = exec.submit(new NIOBlocked(sc1));
        exec.execute(new NIOBlocked(sc2));
        exec.shutdown();
        TimeUnit.SECONDS.sleep(1);
        f.cancel(true);
        TimeUnit.SECONDS.sleep(1);
        sc2.close();
    }
}
