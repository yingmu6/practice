package interview.offer_come.design_mode.strategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chensy
 * @date 2024/3/28
 */
public class Context2 {

//    private List<TravelStrategy> strategyList = new ArrayList<>();
    private Map<TravelModelEnum, TravelStrategy> strategyMap = new HashMap<>();

    public Context2() {
//        strategyList.add(new TravelByAirStrategy());
//        strategyList.add(new TravelByCarStrategy());

        strategyMap.put(TravelModelEnum.AIR, new TravelByAirStrategy());
        strategyMap.put(TravelModelEnum.CAR, new TravelByCarStrategy());
    }

    // 这种写法表达的语义有些问题，因为上下文只维护着策略的引用，并不维护具体业务
//    public void travelByAir() {
//        for (TravelStrategy strategy : strategyList) {
//            if (strategy instanceof TravelByAirStrategy) {
//                strategy.travelMode();
//            }
//        }
//    }
//
//    public void travelByCar() {
//        for (TravelStrategy strategy : strategyList) {
//            if (strategy instanceof TravelByCarStrategy) {
//                strategy.travelMode();
//            }
//        }
//    }

    public void travelModel(TravelModelEnum travelModelEnum) {
        TravelStrategy travelStrategy = strategyMap.get(travelModelEnum);
        if (travelStrategy == null) {
            return;
        }
        travelStrategy.travelMode();
    }
}
