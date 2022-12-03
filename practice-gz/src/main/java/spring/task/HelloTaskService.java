package spring.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 定时任务类
 * @Author chenSy
 * @Date 2022/12/02 15:09
 * @Description
 */
public class HelloTaskService {

    private static final Map<String, Integer> staticMap = new HashMap<>(); //统计每个线程，各自调用的次数，以线程名称作为key（类似ThreadLocal）

    private static ThreadLocal<Integer> invokeNum = new ThreadLocal<>(); //线程的局部变量，每个线程私有（与staticMap处理方式和结果是一样的）

    private static AtomicInteger englishNum = new AtomicInteger(1);

    private Long startTime = new Date().getTime();

    public void sayHelloByEnglish() throws InterruptedException {

        int randomNum = (int)(Math.random() * 500) + 1;
        Thread.currentThread().sleep(3000 + randomNum); //加上一个随机数
        String currentThreadName = Thread.currentThread().getName();

        dealMap(currentThreadName);

        dealThreadLocal();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
        System.out.println("英文：hello, 当前时间:" + sdf.format(new Date()) + ", 当前线程名称：" + currentThreadName
                + ", 已经执行的次数：" + staticMap.get(currentThreadName)
                + ",ThreadLocal次数：" + invokeNum.get() + ", 该方法总调用次数" + englishNum.getAndIncrement()
                + ", 已经启动" + (System.currentTimeMillis() - startTime) / 1000 + "秒");
    }

    public void dealMap(String currentThreadName) {
        Integer curInteger = staticMap.get(currentThreadName);
        if (curInteger != null) {
            staticMap.put(currentThreadName, curInteger + 1);
        } else {
            staticMap.put(currentThreadName, 1);
        }
    }

    public void dealThreadLocal() {
        Integer num = invokeNum.get();
//        System.out.println("线程名：" + Thread.currentThread().getName() + ", 获取的值："+num + "");
        if (num != null) {
            invokeNum.set(num + 1);
        } else {
            invokeNum.set(1);
        }
    }

    public void sayHelloByChinese() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");

        String currentThreadName = Thread.currentThread().getName();
        dealMap(currentThreadName);
        dealThreadLocal();

        System.out.println("中文：你好, 当前时间:" + sdf.format(new Date()) + ", 当前线程名称：" + Thread.currentThread().getName()+ ", 已经执行的次数：" + staticMap.get(currentThreadName)  + ",ThreadLocal次数：" + invokeNum.get());
    }

    public void sayHelloByFrench() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");

        String currentThreadName = Thread.currentThread().getName();
        dealMap(currentThreadName);
        dealThreadLocal();

        System.out.println("法语：bonjour, 当前时间:" + sdf.format(new Date()) + ", 当前线程名称：" + Thread.currentThread().getName()+ ", 已经执行的次数：" + staticMap.get(currentThreadName)  + ",ThreadLocal次数：" + invokeNum.get());
    }


    /**
     * 打印的结果片段如：
     * 线程名：helloScheduler-2, 获取的值：null
     * 中文：你好, 当前时间:2022-12-336 16:04:27, 当前线程名称：helloScheduler-2, 已经执行的次数：1,ThreadLocal次数：1
     *
     * 线程名：helloScheduler-1, 获取的值：null
     * 英文：hello, 当前时间:2022-12-336 16:04:31, 当前线程名称：helloScheduler-1, 已经执行的次数：1,ThreadLocal次数：1
     *
     * 线程名：helloScheduler-2, 获取的值：1
     * 中文：你好, 当前时间:2022-12-336 16:04:32, 当前线程名称：helloScheduler-2, 已经执行的次数：2,ThreadLocal次数：2
     *
     * 线程名：helloScheduler-1, 获取的值：1
     * 中文：你好, 当前时间:2022-12-336 16:04:37, 当前线程名称：helloScheduler-1, 已经执行的次数：2,ThreadLocal次数：2
     */

    /**
     * 总结：目前跑下来， <task:scheduler>中的pool-size设置为1会一个线程，串行执行<task:scheduled>指定的任务，会慢些
     * 但是从跑的结果来看，只要数目超过1，所耗费的时间是差不多的
     * 具体说明
     * 线程数：    次数：      所耗费时间；
     * 10个       10次        51秒
     * 20个       10次        51秒
     * 1个        10次        52秒
     */

}
