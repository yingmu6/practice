package spring.task.annotation.V2;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
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
public class HelloAnnotationServiceV2 {

    private static final Map<String, Integer> staticMap = new HashMap<>(); //统计每个线程，各自调用的次数，以线程名称作为key（类似ThreadLocal）

    private static ThreadLocal<Integer> invokeNum = new ThreadLocal<>(); //线程的局部变量，每个线程私有（与staticMap处理方式和结果是一样的）

    private static AtomicInteger englishNum = new AtomicInteger(1);

    private Long startTime = new Date().getTime();

//    @Async("executorTest")
//    @Scheduled(initialDelay = 2000, fixedDelay = 2000)
    public void sayHelloByEnglish() throws InterruptedException {

        int randomNum = (int)(Math.random() * 500) + 1;
        Thread current = Thread.currentThread();
        current.sleep(3000 + randomNum); //加上一个随机数
        String currentThreadName = current.getName();

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

    private  final String VAR = "2000";

//    private static final String VAR = getVar();
//
//    private static String getVar() { //通过方法赋值的，就编程变量了，不能作为属性值
//        return "3000";
//    }

    @Async("executorTest")
//    @Scheduled(initialDelay = 2000, fixedDelay = 2000) //若没有指定定时任务执行的规则，定时任务是不会被启动的（也就是xml和注解方式都没有指定）
//    @Scheduled(initialDelayString = "${file.init.delay}" , fixedDelayString = "${file.fixed.delay}" + 2)
    @Scheduled(initialDelayString = VAR , fixedDelayString = VAR)  //此处的属性值：必须是常量，不然会报错 Attribute value must be constant
    public void sayHelloByChinese() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");

        String currentThreadName = Thread.currentThread().getName();
        dealMap(currentThreadName);
        dealThreadLocal();

        String var= String.valueOf(Integer.parseInt("333") + 2000);
        System.out.println("变量值：" + var);
        System.out.println("Annotation中文：你好, 当前时间:" + sdf.format(new Date()) + ", 当前线程名称：" + Thread.currentThread().getName()+ ", 已经执行的次数：" + staticMap.get(currentThreadName)  + ",ThreadLocal次数：" + invokeNum.get());
    }

    public void sayHelloByFrench() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");

        String currentThreadName = Thread.currentThread().getName();
        dealMap(currentThreadName);
        dealThreadLocal();

        System.out.println("Annotation法语：bonjour, 当前时间:" + sdf.format(new Date()) + ", 当前线程名称：" + Thread.currentThread().getName()+ ", 已经执行的次数：" + staticMap.get(currentThreadName)  + ",ThreadLocal次数：" + invokeNum.get());
    }

    /**
     * 问题点：
     * 1） initialDelayString表达式的值，是否能做到字符串值相加的？若不能相加，每个任务都得提前计算好这个值了，就不通用了
     *    解答：不能的，因为这个是属性值，只能是常量，只能启动时，加载配置文件（要么全局都设置同一个初始化时间，要么每个任务就写一个变量，差异化获取服务启动时间）
     *          其实，启动时间可以按照同一标准设置随机值，然后固定延迟保持业务属性值即可，全局的使用初始化延迟的随机值，可以选择使用以及不使用
     *
     * 2）能否获取到spring的定时任务列表，并区分出是否cron表达式的任务？
     *    解答：
     *
     * 3）怎么在tomcat容器启动时，指定执行第一个类？web.xml的执行顺序是怎样的？
     *    解答：从启动日志以及debug调试观察出，web.xml的执行顺序为，先执行listener -》filter -》servlet（启动好listener后才会加载spring的xml配置文件以及属性properties文件的，其中filter和servlet都是在容器加载好以后，然后进行请求时拦截处理的）
     *         可参见：http://t.zoukankan.com/foohack-p-5033608.html（自定义监听器，在容器启动时，指定执行的java类）
     *    注明：web.xml中执行顺序与标签位置无关，比如 <listener>可前、可后，最终还是listener在filter、servlet之前之前
     *
     * 4）initialDelayString、fixedDelayString表达时的值，除了从配置文件获取，能否动态计算值的？
     *    解答：
     *
     * 5）想要任务随机执行，用怎样的算法实现负载均衡的？
     *    解答：
     */


}
