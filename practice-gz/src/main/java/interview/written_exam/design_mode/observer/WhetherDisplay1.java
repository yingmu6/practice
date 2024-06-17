package interview.written_exam.design_mode.observer;

/**
 * @author chensy
 * @date 2024/3/28
 */
public class WhetherDisplay1 implements Observer {

    private float temprature;

    public WhetherDisplay1(Subject whether) {
        whether.registerObserver(this); //将观察者注册到主题中
    }

    @Override
    public void update(float temp) {
        this.temprature = temp;
        display();
    }

    public void display() {
        System.out.println("display1***:" + this.temprature);
    }
}
