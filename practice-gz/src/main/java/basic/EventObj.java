package basic;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author chensy
 * @date 2021/9/8
 */
public class EventObj implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        System.out.println("event:::" + applicationEvent.getTimestamp());
    }
}
