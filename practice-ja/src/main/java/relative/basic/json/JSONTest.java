package relative.basic.json;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;

/**
 * @author chensy
 * @date 2022/2/14
 */
public class JSONTest {
    public static void main(String[] args) {
        String switchConfig = "{\"key1\":1}"; //HashMap的JSON格式，与JSON对象格式一样
        HashMap<String, Boolean> switchConfigMap = null;
        JSONObject obj = null;
        try {
            switchConfigMap = JSON.parseObject(switchConfig, HashMap.class); //解析HashMap
            obj = JSON.parseObject(switchConfig);
        } catch (Exception e) {
        }
        System.out.println(switchConfigMap);
        System.out.println(obj);
    }

    //复杂的JSON字符串与对象 建立数据模型
    static class PicInfo {

    }
}
