package thinking.interface_relative;

/**
 * @author orange
 * @date 2024/6/5
 */
public class HorrorShow {

    /**
     * 知识点（9.5）：通过继承来扩展接口
     */
    interface Monster {
        void menace();
    }

    interface DangerousMonster extends Monster {
        void destroy();
    }

    interface Lethal {
        void kill();
    }

    static class DragonZilla implements DangerousMonster {
        public void menace() {}
        public void destroy() {}
    }

    interface Vampire extends DangerousMonster, Lethal {
        void drinkBlood();
    }

    static class VeryBadVampire implements Vampire {
        public void menace() {}
        public void destroy() {}
        public void kill() {}
        public void drinkBlood() {}
    }

    static void u(Monster b) { b.menace(); }
    static void v(DangerousMonster d) {
        d.menace();
        d.destroy();
    }
    static void w(Lethal l) { l.kill(); }

    public static void main(String[] args) {
        DangerousMonster barney = new DragonZilla();
        u(barney);
        v(barney);
        Vampire vlad = new VeryBadVampire();
        u(vlad);
        v(vlad);
        w(vlad);
    }
}
