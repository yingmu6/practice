package thinking.operator;

import static net.mindview.util.Print.print;

/**
 * @author orange
 * @date 2024/6/7
 */
public class Assignment {

    /**
     * 知识点（3.4）：赋值
     */
    static class Tank {
        int level;
    }

    public static void main(String[] args) {
        Tank t1 = new Tank();
        Tank t2 = new Tank();
        t1.level = 9;
        t2.level = 47;
        print("1：t1.level：" + t1.level +
                "，t2.level：" + t2.level);
        t1 = t2;
        print("2：t1.level：" + t1.level +
                "，t2.level：" + t2.level);
        t1.level = 27;
        print("3：t1.level：" + t1.level +
                "，t2.level：" + t2.level);
    }
}
