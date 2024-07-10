package interview.offer_come.design_mode.memento.ext;

/**
 * @author chensy
 * @date 2024/7/10
 */
public class Original2 {

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Original2(String value) {
        this.value = value;
    }

    public Memento2 createMemento2() {
        return new Memento2(value); //把值放到备忘对象中
    }

    public void restoreMemento2(Storage2 storage2, String version) {
        Memento2 memento2 = storage2.getMemeto2(version);
        this.value = memento2 != null ? memento2.getValue() : ""; //取备忘对象中的值更新当前值
    }
}
