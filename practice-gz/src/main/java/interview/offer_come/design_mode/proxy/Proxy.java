package interview.offer_come.design_mode.proxy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chensy
 * @date 2024/3/14
 */
public class Proxy implements Company {

    private HR hr;

    public Proxy() {
        super();
        this.hr = new HR();
    }

    @Override
    public void findWorker(String title) {
       hr.findWorker(title);
       String worker = getWorker(title);
        System.out.println("find a worker by proxy, worker name is:" + worker);
    }

    private String getWorker(String title) {
        Map<String, String> workList = new HashMap<String, String>() {
            {
                put("Java", "张三");
                put("Python", "李四");
                put("Php", "王五");
            }
        };
        return workList.get(title);
    }
}
