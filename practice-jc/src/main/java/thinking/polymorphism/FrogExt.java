package thinking.polymorphism;

/**
 * @author chensy
 * @date 2024/3/29
 */
public class FrogExt {

    static class Characteristic {
        private String s;
        Characteristic(String s) {
            this.s = s;
            System.out.println("Creating Characteristic " + s);
        }
        protected void dispose() {
            System.out.println("disposing Characteristic " + s);
        }
    }

    static class Description {
        private String s;
        Description(String s) {
            this.s = s;
            System.out.println("Creating Description " + s);
        }
        protected void dispose() {
            System.out.println("disposing Description " + s);
        }
    }

    static class LivingCreature {
        private Characteristic p = new Characteristic("is alive");
        private Description t = new Description("Basic Living Creature");
        LivingCreature() {
            System.out.println("LivingCreature()");
        }
        protected void dispose() {
            System.out.println("LivingCreature dispose");
            t.dispose();;
            p.dispose();
        }
    }

    static class Animal extends LivingCreature {
        private Characteristic p = new Characteristic("has heart");
        private Description t = new Description("Animal not Vegetable");
        Animal() {
            System.out.println("Animal()");
        }
        protected void dispose() {
            System.out.println("Animal dispose");
            t.dispose();
            p.dispose();
            super.dispose();
        }
    }

    static class Amphibian extends Animal {
        private Characteristic p = new Characteristic("can live in water");
        private Description t = new Description("Both water and land");
        Amphibian() {
            System.out.println("Amphibian()");
        }
        @Override
        protected void dispose() {
            System.out.println("Amphibian dispose");
            t.dispose();
            p.dispose();
            super.dispose();
        }
    }
}
