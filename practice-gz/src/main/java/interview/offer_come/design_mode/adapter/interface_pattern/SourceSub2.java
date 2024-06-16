package interview.offer_come.design_mode.adapter.interface_pattern;

/**
 * @author chensy
 * @date 2024/3/14
 */
public class SourceSub2 extends AbstractAdapter {

    @Override
    public void editWordFile() {
        System.out.println("a word file editing");
    }
}
