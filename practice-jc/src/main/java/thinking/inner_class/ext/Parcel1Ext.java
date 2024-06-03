package thinking.inner_class.ext;

import thinking.inner_class.Parcel1;

/**
 * @author orange
 * @date 2024/6/3
 */
public class Parcel1Ext {

    class Contents { //默认的访问权限：protected，即同一个包下可访问，不同包不可访问
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

    public void ship(String dest) {
        Contents c = new Contents();
        Destination d = new Destination(dest);
        System.out.println(d.readLabel());
    }
}
