package thinking.access_control;

import net.mindview.simple.List;
import net.mindview.simple.Vector;

/**
 * @author chensy
 * @date 2024/4/30
 */
public class LibTest {

    /**
     * 知识点（6.1.2）：创建独一无二的包名
     */
    public static void main(String[] args) {
        Vector v = new Vector();
        List l = new List(); //使用import导入

        /**
         * 结果输出：
         * net.mindview.simple.Vector
         * net.mindview.simple.List
         */
    }
}
