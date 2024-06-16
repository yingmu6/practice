package interview.written_exam.design_mode.observer;

/**
 * @author chensy
 * @date 2024/3/28
 */
public class Test {

    public static void main(String[] args) {
        Whether whether = new Whether();
        WhetherDisplay1 d1 = new WhetherDisplay1(whether);
        WhetherDisplay2 d2 = new WhetherDisplay2(whether);
        whether.setTemperature(27);
        whether.setTemperature(26);
    }
}
