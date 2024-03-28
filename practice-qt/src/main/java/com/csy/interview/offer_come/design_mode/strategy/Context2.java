package com.csy.interview.offer_come.design_mode.strategy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chensy
 * @date 2024/3/28
 */
public class Context2 {

    private List<TravelStrategy> strategyList = new ArrayList<>();

    public Context2() {
        strategyList.add(new TravelByAirStrategy());
        strategyList.add(new TravelByCarStrategy());
    }

    public void travelByAir() {
        for (TravelStrategy strategy : strategyList) {
            if (strategy instanceof TravelByAirStrategy) {
                strategy.travelMode();
            }
        }
    }

    public void travelByCar() {
        for (TravelStrategy strategy : strategyList) {
            if (strategy instanceof TravelByCarStrategy) {
                strategy.travelMode();
            }
        }
    }
}
