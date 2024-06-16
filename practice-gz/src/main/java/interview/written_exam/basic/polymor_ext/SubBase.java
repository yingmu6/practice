package interview.written_exam.basic.polymor_ext;

/**
 * @author chensy
 * @date 2023/7/6
 */
public class SubBase extends Base {
    public SubBase() { //先执行父类构造方法
        add(2);
        System.out.println("SubBase()：i = " + i);
    }

    public void add(int v) {
        i += v * 2;
        System.out.println("SubBase.add()：i = " + i);
    }
}
