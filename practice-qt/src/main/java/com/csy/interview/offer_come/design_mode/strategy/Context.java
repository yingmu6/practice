package com.csy.interview.offer_come.design_mode.strategy;

/**
 * @author chensy
 * @date 2024/3/15
 */
public class Context { //上下文

    private TravelStrategy travelStrategy; //维护着策略的引用

    public TravelStrategy getTravelStrategy() {
        return travelStrategy;
    }

    public void setTravelStrategy(TravelStrategy travelStrategy) {
        this.travelStrategy = travelStrategy;
    }

    public void travelModel() {
        this.travelStrategy.travelMode();
    }
}
