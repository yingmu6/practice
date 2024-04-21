package thinking.combinate_class;

/**
 * @author chensy
 * @date 2024/4/21
 */
public class Car {

    /**
     * 知识点（7.5）：在组合与继承之间选择
     */
    static class Engine {
        public void start() {}
        public void rev() {}
        public void stop() {}
    }

    static class Wheel {
        public void inflate(int psi) {}
    }

    static class Window {
        public void rollup() {}
        public void rolldown() {}
    }

    class Door {
        public Window window = new Window();
        public void open() {}
        public void close() {}
    }

    public Engine engine = new Engine();
    public Wheel[] wheels = new Wheel[4];
    public Door
       left = new Door(),
       right = new Door();
    public Car() {
        for (int i = 0; i < 4; i++) {
            wheels[i] = new Wheel();
        }
    }

    public static void main(String[] args) {
        Car car = new Car();
        car.left.window.rollup();
        car.wheels[0].inflate(72);
    }
}
