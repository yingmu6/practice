package interview.offer_come.design_mode.memento;

/**
 * @author chensy
 * @date 2024/3/15
 */
public class Original {

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Original(String value) {
        this.value = value;
    }

    public Memento createMemento() {
        return new Memento(value); //把值放到备忘对象中
    }

    public void restoreMemento(Memento memento) {
        this.value = memento.getValue(); //取备忘对象中的值更新当前值
    }
}
