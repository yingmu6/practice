package thinking.interface_relative;

/**
 * @author orange
 * @date 2024/6/5
 */
public class Adventure {

    interface CanFight {
        void fight();
    }

    interface CanSwim {
        void swim();
    }

    interface CanFly {
        void fly();
    }

    static class ActionCharacter {
        public void fight() {}
    }

    static class Hero extends ActionCharacter implements CanFight, CanSwim, CanFly {
        public void swim() {}
        public void fly() {}
    }

    public static void t(CanFight x) { x.fight(); }
    public static void u(CanSwim x) { x.swim(); }
    public static void v(CanFly x) { x.fly(); }
    public static void w(ActionCharacter x) { x.fight(); }

    public static void main(String[] args) {
        Hero h = new Hero();
        t(h);
        u(h);
        v(h);
        w(h);
    }
}
