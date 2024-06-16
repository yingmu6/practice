package interview.offer_come.design_mode.adapter.class_pattern;

/**
 * @author chensy
 * @date 2024/3/14
 */
public class Adapter extends Source implements Targetable {

    @Override
    public void editWordFile() {
        System.out.println("a word file editing");
    }
}
