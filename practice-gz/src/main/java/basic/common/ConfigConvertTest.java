package basic.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 配置转换测试
 *
 * @author chensy
 * @date 27/07/2022
 */
public class ConfigConvertTest {
    public static void main(String[] args) {
//        testParseMapForFilter();
        scene1();
//        scene2();
    }

    public static void scene1() {
        Map<String, List<User>> map = new HashMap<>();
        try {
            String energyStr = "{\"C1\":[{\"name\":\"张三\",\"age\":12,\"no\":\"111\"},{\"name\":\"李四\",\"age\":13,\"no\":\"222\"}],\"C2\":[{\"name\":\"王五\",\"age\":15,\"no\":\"444\"}],\"C3\":[{\"name\":\"刘六\",\"age\":14,\"no\":\"333\"}]}";
            map = JSONObject.parseObject(energyStr, new TypeReference<>() {
            });
        } catch (Exception e) {

        }
//        System.out.println("Map原始值：" + JSON.toJSONString(map));

        Map<String, List<User>> newMap = new HashMap<>();
        Map<String, String> dicMap = new HashMap<>();
        dicMap.put("user.info#Object=>C1#Array=>temp[0]#Object", "{\"name\":\"张三\",\"age\":12,\"no\":\"111\"}");
        dicMap.put("user.info#Object=>C1#Array=>temp[1]#Object", "{\"name\":\"李四\",\"age\":13,\"no\":\"222\"}");
        dicMap.put("user.info#Object=>C2#Array=>temp[0]#Object", "{\"name\":\"王五\",\"age\":15,\"no\":\"444\"}");
        dicMap.put("user.info#Object=>C3#Array=>temp[0]#Object", "{\"name\":\"刘六\",\"age\":14,\"no\":\"333\"}");

//        dicMap.put("user.info22#C2$Array[0]$Object", "22");
//        dicMap.put("user.info22#C3$Array[0]$Object", "33");
        //user.info#C1$Object

        Map<String, String> filterMap = new HashMap<>();
        filterMap = parseMapForFilter(dicMap, "user.info#");
        System.out.println("过滤map：" + filterMap);

        JSONObject total = new JSONObject();
        JSONObject obj = new JSONObject();
        for (String key : filterMap.keySet()) {
            String[] arr = key.split("=>");
            for (int i = 0; i < arr.length; i++) {
                String[] typeVal = arr[i].split("#");
                if (i == arr.length - 1) {
                    if (typeVal[0].contains("temp")) {
                        JSONObject last = new JSONObject();
                        if (typeVal[1].contains("Object")) {
                            last = JSONObject.parseObject(filterMap.get(key));
                        }
                        JSONArray array = new JSONArray();
                        array.add(last);
                        obj.put(arr[arr.length - 2].split("#")[0], array);
                        total.put(arr[0].split("#")[0], obj);
                    }
                    break;
                } else {
                    if (i == 0) {
                        total.put(typeVal[0], new JSONObject());
                    } else {
                        if (typeVal[1].contains("Object")) {
                            obj.put(typeVal[0], new JSONObject());
                        } else if (typeVal[1].contains("Array")) {
                            obj.put(typeVal[0], new JSONArray());
                        }
                    }
                }
            }
        }
        System.out.println("构建的对象obj：" + JSON.toJSONString(total));

//        for (String key : filterMap.keySet()) {
//            String[] arr = key.split("=>");
//            for (int i = arr.length - 1; i >= 0; i--) {
//                String[] arr2 = arr[i].split("$");
//                if (arr2[0].contains("temp")) {
//                    if (arr2[1].contains("$Object")) {
//                        JSONObject temp = JSONObject.parseObject(filterMap.get(key));
//                    }
//                } else {
//
//                }
//
//            }
//            System.out.println(key);
//        }


    }


    public static void scene2() {
        List<User> list = new ArrayList<>();
        try {
            String energyStr = "[{\"name\":\"张三\",\"age\":12,\"no\":\"111\"},{\"name\":\"李四\",\"age\":13,\"no\":\"222\"}]";
            list = JSONObject.parseArray(energyStr, User.class);
        } catch (Exception e) {
            System.out.println("异常信息:" + e.getMessage());
        }
        System.out.println("list原始值：" + JSON.toJSONString(list));

        //user.info$Array[0]$Object
    }


    public static void testParseMapForFilter() {
        Map<String, String> params = new HashMap<>(16);
        params.put("a-1", "1");
        params.put("a-2", "2");
        params.put("a-3", "3");
        params.put("b-1", "1");
        params.put("b-2", "2");
        params.put("b-3", "3");
        params.put("c-1", "1");
        params.put("c-2", "2");
        params.put("c-3", "3");

        Map<String, String> a = parseMapForFilter(params, "c");
        System.out.println(JSON.toJSONString(a));
    }

    /**
     * 从Map中模糊匹配key值
     */
    public static Map<String, String> parseMapForFilter(Map<String, String> map, String filters) {
        if (map == null) {
            return null;
        } else {
            map = map.entrySet().stream()
                    .filter((e) -> checkKey(e.getKey(), filters))
                    .collect(Collectors.toMap(
                            (e) -> (String) e.getKey(),
                            (e) -> e.getValue()
                    ));
        }
        return map;
    }


    /**
     * 通过indexof匹配想要查询的字符
     */
    private static boolean checkKey(String key, String filters) {
        if (key.startsWith(filters)) {
            return true;
        } else {
            return false;
        }
    }

}

@Getter
@Setter
class User {
    private String name;
    private Integer age;
    private String no;
}
