package relative.thread.pool;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 定时任务测试
 * @author : chensy
 * Date : 2020-02-29 20:31
 */
public class ScheduledExecutorTest {
    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd HH:ss:mm");
        System.out.println("调用前时间：" + simpleDateFormat.format(new Date()));
        delayUse();
    }

    public static void delayUse() {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(3);
        scheduledThreadPoolExecutor.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd HH:ss:mm");
                System.out.println("调用后时间：" + simpleDateFormat.format(new Date()));
            }
        }, 2000, 3000, TimeUnit.MILLISECONDS);


    }

    /**
     * A ThreadPoolExecutor that can additionally schedule commands to run after a given delay, or to execute periodically.
     * 可定期执行或延迟执行的的线程池
     */

    /**
     * 延时任务中initialDelay, delay,两个参数的含义研究
     * 解：initialDelay： 第一次执行时，延后多少时间执行
     * delay：第一次执行以后，按相同的延迟时间执行，
     * 比如initialDelay=2000，delay=3000 时间单位TimeUnit.MILLISECONDS、第一次延迟2秒，后面每次延迟3秒 ，如
     * 调用前时间：20200229 21:36:08
     * 调用后时间：20200229 21:38:08
     * 调用后时间：20200229 21:41:08
     * 调用后时间：20200229 21:44:08
     */
}
