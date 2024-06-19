package thinking.io_relative;

import java.util.prefs.Preferences;

import static net.mindview.util.Print.print;

/**
 * @author orange
 * @date 2024/6/7
 */
public class PreferencesDemo {

    /**
     * 知识点（18.14）：Preferences
     */
    public static void main(String[] args) throws Exception {
        Preferences prefs = Preferences.userNodeForPackage(PreferencesDemo.class);
        prefs.put("Location", "Oz");
        prefs.put("Footwear", "Ruby Slippers");
        prefs.putBoolean("Are there witches?", true);
        int usageCount = prefs.getInt("UsageCount", 0);
        usageCount++;
        prefs.putInt("UsageCount", usageCount);
        for (String key : prefs.keys()) {
            print(key + "：" + prefs.get(key, null));
        }
        print("How many companions does Doroty have? " +
                prefs.getInt("Companions", 0));
    }
}
