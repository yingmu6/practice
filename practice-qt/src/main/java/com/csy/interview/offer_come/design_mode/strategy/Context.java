package com.csy.interview.offer_come.design_mode.strategy;

/**
 * @author chensy
 * @date 2024/3/15
 */
public class Context {

    private TravelStrategy travelStrategy;

    public TravelStrategy getTravelStrategy() {
        return travelStrategy;
    }

    public void setTravelStrategy(TravelStrategy travelStrategy) {
        this.travelStrategy = travelStrategy;
    }

    public void travelModel() {
        this.travelStrategy.travelMode();
    }

    public static void main(String[] args) {
        Context context = new Context();
        TravelStrategy travelByAirStrategy = new TravelByAirStrategy();
        context.setTravelStrategy(travelByAirStrategy);
        context.travelModel();

        System.out.println("change TravelStrategy to travelByCarStrategy......");
        TravelStrategy travelByCarStrategy = new TravelByCarStrategy();
        context.setTravelStrategy(travelByCarStrategy);
        context.travelModel();

        /**
         * 输出结果：
         * travel by air
         * change TravelStrategy to travelByCarStrategy......
         * travel by car
         *
         * 结果分析：
         *
         * 问题点答疑：
         */
    }
}
