package com.csy.interview.offer_come.design_mode.strategy;

import org.junit.Test;

/**
 * @author chensy
 * @date 2024/3/28
 */
public class ZdTest {

    /**
     * 知识点：策略模式
     *
     * 总结概括：
     * 1）需要一个策略的上下文类，维护单个或多个策略引用，运行时选择对应的策略实例执行
     *
     * 参考链接：
     *
     */

    /**
     * 场景1：上下文中只维护一个策略引用
     */
    @Test
    public void singleStrategyRef() {
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
         * 1）上下文Context维护着策略的引用，然后设置怎样的策略实例，就会执行怎样的策略
         *
         * 总结概括：
         * 1）需要定义一个上下文类，用于保存策略的引用，并且提供设置策略实例的set方法
         * 2）此场景中Context只维护了一个策略引用TravelStrategy，在运行时，设置了哪个实例，就执行对应的策略
         */
    }

    /**
     * 场景2：上下文中维护多个策略引用
     */
    @Test
    public void multiStrategyReg() {
        Context2 context2 = new Context2();
        context2.travelByAir();
        System.out.println("change TravelStrategy to travelByCarStrategy......");
        context2.travelByCar();

        /**
         * 输出结果：
         * travel by air
         * change TravelStrategy to travelByCarStrategy......
         * travel by car
         *
         * 结果分析：
         * 1）Context2维护了策略TravelStrategy的引用列表，并在对象创建时将所有的策略实例加到列表中
         *
         * 总结概括：
         * 1）这种在工作场景中比较常用。就是在项目启动时，就把已知的策略，都加载到上下文维护的列表中，
         *    然后运行时根据某些条件，触发指定的策略，执行对应的逻辑。
         *
         */
    }
}
