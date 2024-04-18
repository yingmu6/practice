package thinking.inner_class;

import thinking.inner_class.Controller;
import thinking.inner_class.Event;

/**
 * @author chensy
 * @date 2024/3/26
 */
public class GreenhouseControls extends Controller {

    private boolean light = false;

    public class LightOn extends Event { //开灯事件（成员内部类）
        public LightOn(long startTime, long delayTime) {
            super(startTime, delayTime);
        }

        @Override
        public void action() { //执行的动作
            light = true;
        }

        public String toString() {
            return super.appendTime("Light is on");
        }
    }

    public class LightOff extends Event { //关灯事件
        public LightOff(long startTime, long delayTime) {
            super(startTime, delayTime);
        }

        @Override
        public void action() {
            light = false;
        }

        public String toString() {
            return super.appendTime("Light is off");
        }
    }

    private boolean water = false;

    public class WaterOn extends Event { //打开水的事件
        public WaterOn(long startTime, long delayTime) {
            super(startTime, delayTime);
        }

        @Override
        public void action() {
            water = true;
        }

        public String toString() {
            return super.appendTime("Greenhouse water is on");
        }
    }

    public class WaterOff extends Event { //关闭水的事件
        public WaterOff(long startTime, long delayTime) {
            super(startTime, delayTime);
        }

        @Override
        public void action() {
            water = false;
        }

        public String toString() {
            return super.appendTime("Greenhouse water is off");
        }
    }

    private String thermostat = "Day"; //恒温器
    public class ThermostatNight extends Event {
        public ThermostatNight(long start, long delayTime) {
            super(start, delayTime);
        }

        @Override
        public void action() {
            thermostat = "Night";
        }

        public String toString() {
            return super.appendTime("Thermostat on night setting");
        }
    }

    public class ThermostatDay extends Event {
        public ThermostatDay(long startTime, long delayTime) {
            super(startTime, delayTime);
        }

        @Override
        public void action() {
            thermostat = "Day";
        }

        public String toString() {
            return super.appendTime("Thermostat on day setting");
        }
    }
}
