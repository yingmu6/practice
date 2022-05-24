package spring.aspectj;

/**
 * @author chensy
 * @date 2022/2/15
 */

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class LogAspect {

    @Pointcut("execution (* spring.aspectj..*.*(..))")
    public void logPointcut() {

    }

    @Before("logPointcut()")
    public void beforeDeal(JoinPoint joinPoint) {
        System.out.println("调用前处理：" + joinPoint.getSignature());
    }


    //    @Around("execution (* spring.aspectj..*.*(..))")  //可以直接指定切入点表达式
//    @Around("logPointcut()") //可以先声明切入点，再引入切入点
    public void around(JoinPoint point) {
        String methodName = point.getSignature().getName();
        Object[] args = point.getArgs();
        Class<?>[] argTypes = new Class[point.getArgs().length];
        for (int i = 0; i < args.length; i++) {
            argTypes[i] = args[i].getClass();
        }
        Method method = null;
        try {
            method = point.getTarget().getClass().getMethod(methodName, argTypes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //获取方法上的注解
        Log log = method.getAnnotation(Log.class);
        if (log != null) {
            //演示方法执行前，记录一行日志
            System.out.println("before:" + methodName);
        }
        try {
            //执行方法
            ((ProceedingJoinPoint) point).proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace(); //将异常捕获，不向上抛出
        } finally {
            if (log != null) {
                //演示方法执行后，记录一行日志
                System.out.println("after:" + methodName);
            }
        }
    }


//    @AfterThrowing(value = "logPointcut()", throwing = "e")
//    public void exceptionDeal(JoinPoint joinPoint, Exception e) {
//        System.out.println("捕获到异常信息=" + e.getMessage());
//    }

    @AfterThrowing(pointcut = "logPointcut()", throwing = "e")  //该注解是在异常抛出后处理，所以会先打error日志，所以需要权衡
    public void exceptionDeal(JoinPoint joinPoint, Exception e) {
//        if (e instanceof NumberFormatException) {
//            NumberFormatException numberFormatException = (NumberFormatException) e;
//            System.out.println("字符串异常：" + numberFormatException.getMessage());
//        }
    }

    @Around(value = "logPointcut()")
    public void aroundDeal(JoinPoint point) { //可以捕获异常，然后做相应的逻辑处理
        try {
            //执行方法
            ((ProceedingJoinPoint) point).proceed();
        } catch (Throwable throwable) {
            System.out.println("111uuuuuuuuuuuuu");
//            throwable.printStackTrace(); //将异常捕获，不向上抛出
        } finally {
            System.out.println("111");
        }
    }

    /**
     * 若有多个注解，先执行@Around中((ProceedingJoinPoint) point).proceed() 调用前的逻辑，然后再执行@Before，
     * 最后执行@Around中((ProceedingJoinPoint) point).proceed()  后面的逻辑
     */
}
