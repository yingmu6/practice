package spring.task.annotation;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
@Service
public class HelloAnnotationService {

    private static final Map<String, Integer> staticMap = new HashMap<>(); //统计每个线程，各自调用的次数，以线程名称作为key（类似ThreadLocal）

    private static ThreadLocal<Integer> invokeNum = new ThreadLocal<>(); //线程的局部变量，每个线程私有（与staticMap处理方式和结果是一样的）

    private static AtomicInteger englishNum = new AtomicInteger(1);

    private Long startTime = new Date().getTime();

    @Async("executorTest")
    public void sayHelloByEnglish() throws InterruptedException {

        int randomNum = (int)(Math.random() * 500) + 1;
        Thread.currentThread().sleep(3000 + randomNum); //加上一个随机数
        String currentThreadName = Thread.currentThread().getName();

        dealMap(currentThreadName);

        dealThreadLocal();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
        System.out.println("Annotation英文：hello, 当前时间:" + sdf.format(new Date()) + ", 当前线程名称：" + currentThreadName
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

    @Async("poolTaskExecutor")
    public void sayHelloByChinese() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");

        String currentThreadName = Thread.currentThread().getName();
        dealMap(currentThreadName);
        dealThreadLocal();

        System.out.println("Annotation中文：你好, 当前时间:" + sdf.format(new Date()) + ", 当前线程名称：" + Thread.currentThread().getName()+ ", 已经执行的次数：" + staticMap.get(currentThreadName)  + ",ThreadLocal次数：" + invokeNum.get());
    }

    public void sayHelloByFrench() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");

        String currentThreadName = Thread.currentThread().getName();
        dealMap(currentThreadName);
        dealThreadLocal();

        System.out.println("Annotation法语：bonjour, 当前时间:" + sdf.format(new Date()) + ", 当前线程名称：" + Thread.currentThread().getName()+ ", 已经执行的次数：" + staticMap.get(currentThreadName)  + ",ThreadLocal次数：" + invokeNum.get());
    }


}
