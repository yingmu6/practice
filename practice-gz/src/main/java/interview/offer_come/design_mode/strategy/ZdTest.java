package interview.offer_come.design_mode.strategy;

import org.junit.Test;

/**
 * @author chensy
 * @date 2024/3/28
 */
public class ZdTest { //@MsY-Doing

    /**
     * 知识点：策略模式
     *
     * 知识点概括：
     * 1）需要一个策略的上下文类，维护单个或多个策略引用，运行时选择对应的策略实例执行
     *
     * 问题点答疑：
     * 1）策略模式与观察者用什么差异？
     *
     * 2）多态的"动态绑定"原理是怎样的？
     *
     * 3）设计模式的本质以及目标是怎样的？
     *
     */

    /**
     * 场景1：上下文中只维护一个策略引用
     */
    @Test
    public void singleStrategyRef() { //@Done
        Context context = new Context();
        TravelStrategy travelByAirStrategy = new TravelByAirStrategy();
        context.setTravelStrategy(travelByAirStrategy); //主动将策略的实例设置到上下文中
        context.travelModel(); //上下文使用维护的策略实例，执行具体的逻辑

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
         * 2）此场景中Context只维护了一个策略引用TravelStrategy，在运行时，设置了哪个实例，就执行对应的策略
         */
    }

    /**
     * 场景2：上下文中维护多个策略引用
     */
    @Test
    public void multiStrategyReg() { //Done
        Context2 context2 = new Context2();
        context2.travelModel(TravelModelEnum.AIR);
        System.out.println("change TravelStrategy to travelByCarStrategy......");
        context2.travelModel(TravelModelEnum.CAR);

        /**
         * 输出结果：
         * travel by air
         * change TravelStrategy to travelByCarStrategy......
         * travel by car
         *
         * 结果分析：
         * 1）Context2维护了策略TravelStrategy的引用列表，并在对象创建时将所有的策略实例加到列表中
         *
         * 2）这种在工作场景中比较常用。就是在项目启动时，就把已知的策略，都加载到上下文维护的变量中，
         *    然后运行时根据某些条件，触发指定的策略，执行对应的逻辑。
         *
         */
    }
}
