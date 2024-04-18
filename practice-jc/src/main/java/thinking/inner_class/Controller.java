package thinking.inner_class;

import thinking.inner_class.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chensy
 * @date 2024/3/26
 */
public class Controller {

    private List<Event> eventList = new ArrayList<>(); //维护事件列表

    public void addEvent(Event c) {
        eventList.add(c);
    }

    public void run() {
        while (eventList.size() > 0 ) { //一直轮询，判断是否有可用事件
            for (Event e : new ArrayList<Event>(eventList)) {
                if (e.ready()) { //若事件准备好，则对应执行
                    System.out.println(e);
                    e.action();
                    eventList.remove(e);
                }
            }
        }
    }
}
