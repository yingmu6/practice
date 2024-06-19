package relative.basic.object;

/**
 * 浅复制
 * @author : chensy
 * Date : 2020-03-09 10:51
 */
public class StudentOfShallow implements Cloneable {
    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public Object clone() {
        StudentOfShallow shallow = null;
        try {
            shallow = (StudentOfShallow) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return shallow;
    }
}
