package thinking.enum_type;

/**
 * @author chensy
 * @date 2024/4/8
 */
public class TrafficLight { //TrafficLight：交通灯，红绿灯（@thinking Done）

    /**
     * 知识点：switch语句中的enum
     */

    enum Signal {
        GREEN, YELLOW, RED
    }

    Signal color = Signal.RED;
    public void change() {
        switch (color) {
            case RED:
                color = Signal.GREEN;
                break;
            case GREEN:
                color = Signal.YELLOW;
                break;
            case YELLOW:
                color = Signal.RED;
                break;
        }
    }

    public String toString() {
        return "The traffic light is " + color;
    }

    public static void main(String[] args) {
        TrafficLight t = new TrafficLight();
        for (int i = 0; i < 7; i++) {
            System.out.println(t);
            t.change();
        }

        /**
         * 输出结果：
         * The traffic light is RED
         * The traffic light is GREEN
         * The traffic light is YELLOW
         * The traffic light is RED
         * The traffic light is GREEN
         * The traffic light is YELLOW
         * The traffic light is RED
         *
         * 结果分析：
         * 1）枚举值，可作为switch中的判断条件使用
         */
    }
}
