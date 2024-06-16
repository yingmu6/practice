package interview.offer_come.design_mode.strategy;

/**
 * @author chensy
 * @date 2024/3/15
 */
public class TravelByAirStrategy implements TravelStrategy {
    @Override
    public void travelMode() {
        System.out.println("travel by air");
    }
}
