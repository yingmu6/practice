package thinking.holder_obj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author orange
 * @date 2024/6/5
 */
public class AsListInference {

    /**
     * 知识点（11.3）：添加一组元素
     */
    static class Snow {}
    static class Powder extends Snow {}
    static class Light extends Powder {}
    static class Heavy extends Powder {}
    static class Crustry extends Snow {}
    static class Slush extends Snow {}

    public static void main(String[] args) {
        List<Snow> snow1 = Arrays.asList(new Crustry(), new Slush(), new Powder());

//        List<Snow> snow2 = Arrays.asList(new Light(), new Heavy());

        List<Snow> snow3 = new ArrayList<>();
        Collections.addAll(snow3, new Light(), new Heavy());

        List<Snow> snow4 = Arrays.<Snow>asList(new Light(), new Heavy());
    }
}
