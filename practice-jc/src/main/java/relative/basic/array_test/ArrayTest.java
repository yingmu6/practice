package relative.basic.array_test;

import com.alibaba.fastjson.JSON;

/**
 * 使用System拷贝数据元素
 *
 * @author chensy
 * @date 2022/5/8
 */
public class ArrayTest {
    public static void main(String[] args) {

        String[] sourceArr = {"11", "22", "33"};
        String[] targetArr = new String[4];

        System.arraycopy(sourceArr, 1, targetArr, 0, 2);
        System.out.println(JSON.toJSONString(targetArr)); //输出：["22","33",null,null]
    }
}
