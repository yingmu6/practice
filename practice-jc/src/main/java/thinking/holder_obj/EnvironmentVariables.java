package thinking.holder_obj;

import java.util.Map;

/**
 * @author orange
 * @date 2024/6/5
 */
public class EnvironmentVariables {

    /**
     * 知识点（11.13）：Foreach与迭代器
     */
    public static void main(String[] args) {
        for (Map.Entry entry : System.getenv().entrySet()) {
            System.out.println(entry.getKey() + "：" + entry.getValue());
        }
    }
}
