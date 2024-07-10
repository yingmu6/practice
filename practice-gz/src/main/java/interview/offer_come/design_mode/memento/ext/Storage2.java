package interview.offer_come.design_mode.memento.ext;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chensy
 * @date 2024/7/10
 */
public class Storage2 {

    private Map<String, Memento2> versionMap = new HashMap<>(); //将版本与备忘对象缓存起来

    private static int COUNT = 0;

    private static final String VERSION_PREFIX = generatePrefix();

    public static String generatePrefix() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        return sdf.format(date) + "_";
    }

    public String storeMemeto2(Memento2 memento2) {
        String version = VERSION_PREFIX + (++ COUNT);
        versionMap.put(version, memento2);
        return version;
    }

    public Memento2 getMemeto2(String version) {
        return versionMap.get(version);
    }

    public static void main(String[] args) {
        System.out.println(generatePrefix());
    }
}
