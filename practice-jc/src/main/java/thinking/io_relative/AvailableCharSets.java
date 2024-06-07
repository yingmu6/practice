package thinking.io_relative;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.SortedMap;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

/**
 * @author orange
 * @date 2024/6/7
 */
public class AvailableCharSets {

    /**
     * 知识点（18.10.1）：转换数据
     */
    public static void main(String[] args) {
        SortedMap<String, Charset> charSets =
                Charset.availableCharsets();
        Iterator<String> it = charSets.keySet().iterator();
        while (it.hasNext()) {
            String csName = it.next();
            printnb(csName);
            Iterator alises =
                    charSets.get(csName).aliases().iterator();
            if (alises.hasNext()) {
                printnb("：");
            }
            while(alises.hasNext()) {
                printnb(alises.next());
                if (alises.hasNext()) {
                    printnb(",");
                }
            }
        }
        print();
    }
}
