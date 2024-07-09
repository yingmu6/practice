package interview.offer_come.design_mode.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chensy
 * @date 2024/3/15
 */
public abstract class Subject { //抽象主题

    protected List<Observer> observerList = new ArrayList<>();

    public void add(Observer observer) {
        observerList.add(observer);
    }

    public void remove(Observer observer) {
        observerList.remove(observer);
    }

    public abstract void notifyObserver(String message);
}
