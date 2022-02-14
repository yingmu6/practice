package relative.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author : chensy
 * todo https://qinzhaokun.github.io/2017/07/12/spring%20AOP%E5%8E%9F%E7%90%86/
 * Date : 2020-03-11 14:44
 */
public class Aspect {
    public void doAfter(JoinPoint jp) {
        System.out.println("log Ending method: " + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName());
    }

    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long time = System.currentTimeMillis();
        Object retVal = pjp.proceed();
        time = System.currentTimeMillis() - time;
        System.out.println("process time: " + time + " ms");
        return retVal;
    }

    public void doBefore(JoinPoint jp) {
        System.out.println("log Begining method: " + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName());
    }

    public void doThrowing(JoinPoint jp, Throwable ex) {
        System.out.println("method " + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName() + " throw exception");
        System.out.println(ex.getMessage());
    }
}
