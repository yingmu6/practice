package interview.offer_come.design_mode.command;

/**
 * @author chensy
 * @date 2024/3/15
 */
public class Receiver {

    public void action(String command) {
        System.out.println("command received, now execute command");
    }
}
