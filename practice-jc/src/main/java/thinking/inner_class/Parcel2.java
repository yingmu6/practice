package thinking.inner_class;

/**
 * @author chensy
 * @date 2024/3/15
 */
public class Parcel2 {

    class Contents { //此处默认为protected权限，即同一个包下的类可访问，若改为private，只有同一个类中能访问
        private int i = 11;
        public int value() {
            return i;
        }
    }

    class Destination {
        private String label;
        Destination(String whereTo) {
            label = whereTo;
        }
        String readLabel() {
            return label;
        }
    }

    public Destination to(String s) { //成员方法能访问成员内部类
        return new Destination(s);
    }

    public Contents contents() {
        return new Contents();
    }

    public void ship(String dest) {
        Contents c = contents();
        Destination d = to(dest);
        System.out.println(d.readLabel());
    }
}
