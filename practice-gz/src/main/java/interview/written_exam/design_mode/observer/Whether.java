package interview.written_exam.design_mode.observer;

import java.util.ArrayList;

/**
 * @author chensy
 * @date 2024/3/28
 */
public class Whether implements Subject { //主题

    private ArrayList<Observer> observers = new ArrayList<>(); //持有观察者引用列表

    private float temperature;

    @Override
    public void notifyObservers() {
        for (int i = 0; i < this.observers.size(); i++) {
            this.observers.get(i).update(temperature);
        }
    }

    @Override
    public void registerObserver(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        this.observers.remove(o);
    }

    public void whetherChange() {
        this.notifyObservers();
    }

    public float getTemperature() {
        return this.temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
        notifyObservers(); //主题的内容变更时，通知所有观察者
    }
}
