package interview.offer_come.design_mode.adapter.object_pattern;

import interview.offer_come.design_mode.adapter.class_pattern.Source;
import interview.offer_come.design_mode.adapter.class_pattern.Targetable;

/**
 * @author chensy
 * @date 2024/3/14
 */
public class ObjectAdapter implements Targetable {

    private Source source;

    public ObjectAdapter(Source source) {
        super();
        this.source = source;
    }

    @Override
    public void editTextFile() {
        this.source.editTextFile();
    }

    @Override
    public void editWordFile() {
        System.out.println("a word file editing");
    }
}
