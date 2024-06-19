package thinking.combinate_class;

import static net.mindview.util.Print.print;

/**
 * @author chensy
 * @date 2024/4/21
 */
public class OrcExt {

    /**
     * 知识点（7.6）：protected关键字
     */
    static class Villain {
        private String name;
        protected void set(String nm) { name = nm; }
        public Villain(String name) { this.name = name; }
        public String toString() {
            return "I'm a Villain and my name is " + name;
        }
    }

    static public class Orc extends Villain {
        private int orcNumber;
        public Orc(String name, int orcNumber) {
            super(name);
            this.orcNumber = orcNumber;
        }
        public void change(String name, int orcNumber) {
            set(name);
            this.orcNumber = orcNumber;
        }
        public String toString() {
            return "Orc " + orcNumber + "：" + super.toString();
        }

        public static void main(String[] args) {
            Orc orc = new Orc("Limburger", 12);
            print(orc);
            orc.change("Bob", 19);
            print(orc);
        }
    }
}
