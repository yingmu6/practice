package thinking.concurrent.case4;

/**
 * @author chensy
 * @date 2024/4/14
 */
public class Joining {
    public static void main(String[] args) { //加入一个线程
        Sleeper
                sleepy = new Sleeper("sleepy", 1500),
                grumpy = new Sleeper("Grumpy", 1500);

        Joiner
                dopey = new Joiner("Dopey", sleepy),
                doc = new Joiner("Doc", grumpy);
        grumpy.interrupt();
    }
}
