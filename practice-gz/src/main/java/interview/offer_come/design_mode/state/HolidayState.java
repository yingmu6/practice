package interview.offer_come.design_mode.state;

/**
 * @author chensy
 * @date 2024/3/15
 */
public class HolidayState extends AbstractState {
    @Override
    public void action(Context context) {
        System.out.println("state change to holiday state");
        System.out.println("holiday state action is travel, shopping, watch, television...");
    }
}
