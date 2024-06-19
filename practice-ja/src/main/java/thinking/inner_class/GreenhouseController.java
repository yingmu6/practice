package thinking.inner_class;

import thinking.inner_class.GreenhouseControls;

/**
 * @author chensy
 * @date 2024/3/29
 */
public class GreenhouseController {

    /**
     * 知识点：内部类与控制框架（内部类的具体应用）
     *
     * 知识点概括：（无）
     *
     * 问题点答疑：(无)
     */
    public static void main(String[] args) {

        GreenhouseControls gc = new GreenhouseControls();
        long curTime = System.currentTimeMillis() / 1000;
        System.out.println("开始执行事件，时间：" + curTime);

        gc.addEvent(gc.new ThermostatNight(curTime, 2)); //2秒后执行事件（时间转换时，要特别注意单位统一）
        gc.addEvent(gc.new LightOn(curTime, 3));
        gc.addEvent(gc.new LightOff(curTime, 4));
        gc.addEvent(gc.new WaterOff(curTime, 8));
        gc.addEvent(gc.new WaterOn(curTime, 6));
        gc.addEvent(gc.new ThermostatDay(curTime, 10));

        gc.run();

        /**
         * 输出结果：
         * 开始执行事件，时间：1711763710
         * Thermostat on night setting，时间：1711763712
         * Light is on，时间：1711763713
         * Light is off，时间：1711763714
         * Greenhouse water is on，时间：1711763716
         * Greenhouse water is off，时间：1711763718
         * Thermostat on day setting，时间：1711763720
         *
         * 结果分析：
         * 1）每个具体的事件都继承了Event，并设置了事件期望发生的时间，如LightOn、LightOff等，这些事件都是成员内部类
         * 2）在控制器GreenhouseControls调用run()时，会判断事件是否到达发生的时间，若到达则执行事件
         *
         * 总结概括：
         * 1）将多个事件作为成员内部类，聚合在控制类Control中，实现统一管理
         *
         */
    }
}
