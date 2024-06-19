package thinking.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

/**
 * @author chensy
 * @date 2024/4/14
 */
public class WaxOMatic {
    static class Car {
        private boolean waxOn = false;
        public synchronized void waxed() {
            waxOn = true;
            notifyAll();
        }
        public synchronized void buffed() {
            waxOn = false;
            notifyAll();
        }
        public synchronized void waitForWaxing() throws InterruptedException {
            while (waxOn == true) {
                wait();
            }
        }
        public synchronized void waitForBuffing() throws InterruptedException {
            while (waxOn == true) {
                wait();
            }
        }
    }

    static class WaxOff implements Runnable {
        private Car car;
        public WaxOff(Car c) {
            car = c;
        }

        public void run() {
            try {
                while (!Thread.interrupted()) {
                    car.waitForWaxing();
                    printnb("Wax Off!");
                    TimeUnit.MILLISECONDS.sleep(200);
                    car.buffed();
                }
            } catch (InterruptedException e) {
                print("Exiting via interrupt");
            }
            print("Ending Wax Off task");
        }
    }

    static class WaxOn implements Runnable {
        private Car car;
        public WaxOn(Car c) {
            car = c;
        }
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    printnb("Wax On! ");
                    TimeUnit.MILLISECONDS.sleep(200);
                    car.waxed();
                    car.waitForBuffing();
                }
            } catch (InterruptedException e) {
                print("Exiting via interrupt");
            }
            print("Ending Wax On task");
        }
    }

    public static void main(String[] args) throws InterruptedException { //线程间协作
        Car car = new Car();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new WaxOff(car));
        exec.execute(new WaxOn(car));
        TimeUnit.SECONDS.sleep(5);
        exec.shutdown();
    }
}
