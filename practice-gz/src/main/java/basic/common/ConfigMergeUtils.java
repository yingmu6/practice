package basic.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Map合并工具
 *
 * @author chensy
 * @date 01/08/2022
 */
public class ConfigMergeUtils {

    public static String getMergeConfig(Map<String, List<String>> configMap) {
        String mergeConfig = StringUtils.EMPTY;
        JSONObject total = new JSONObject();
        JSONObject obj = new JSONObject();
        for (String key : configMap.keySet()) {
            if (!key.contains("=>")) {
//                total.put(filterKey(key), configMap.get(key));
                mergeConfig = mergeConfig(configMap.get(key), ",");
                continue;
            }
            String[] arr = key.split("=>");
            for (int i = 0; i < arr.length; i++) {
                String[] typeVal = arr[i].split("#");
                if (i == arr.length - 1) {
                    if (typeVal[0].contains("temp")) {
                        JSONObject last = new JSONObject();
                        if (typeVal[1].contains("Object")) {
//                            last = JSONObject.parseObject(configMap.get(key));
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
                        } else if (typeVal[1].contains("String")) {
                            obj.put(key, typeVal[1]);
                        }
                    }
                }
            }
        }
        return mergeConfig;
    }

    public static String filterKey(String key) {
        return key.substring(0, key.indexOf("$"));
    }
    // 分隔符

    public static String mergeConfig(List<String> configs, String delimiter) {
        String mergeConfig = StringUtils.EMPTY;
        for (int i = 0; i < configs.size(); i++) {
            if (i == configs.size() - 1) {
                mergeConfig = mergeConfig + configs.get(i);
            } else {
                mergeConfig = mergeConfig + configs.get(i) + delimiter;
            }
        }
        return mergeConfig;
    }

    public static void main(String[] args) {
//        System.out.println(scene1_common());
//        System.out.println(scene2_array());
//        System.out.println(scene3_object());
//        scene_001();
        scene_002();

//        test1();
    }

    public static void scene_001() {
        List<ConfigItem> configItems = new ArrayList<>();
        ConfigItem configItem = new ConfigItem();
        configItem.setConfigK("aa.pids#Array=>temp#Object");
        configItem.setConfigV("{\"name\":\"5\",\"type\":1,\"bb\":\"dj\"}");

        ConfigItem configItem2 = new ConfigItem();
        configItem2.setConfigK("aa.pids#Array=>temp#Object");
        configItem2.setConfigV("{\"name\":\"5\",\"type\":2,\"gg\":\"fs\"}");

        ConfigItem configItem3 = new ConfigItem();
        configItem3.setConfigK("aa.pids#Array=>temp#Object");
        configItem3.setConfigV("{\"name\":\"g\",\"type\":4,\"tt\":\"qlkg\"}");

        configItems.add(configItem);
        configItems.add(configItem2);
        configItems.add(configItem3);

        System.out.println(mergeConfig2(configItems));
    }

    public static void scene_002() {
        List<ConfigItem> configItems = new ArrayList<>();
        ConfigItem configItem = new ConfigItem();
        configItem.setConfigK("ccc.su#Object=>1#Array=>temp#Object");
        configItem.setConfigV("{\"code\":\"bic\",\"name\":\"22\"}");

        ConfigItem configItem3 = new ConfigItem();
        configItem3.setConfigK("ccc.su#Object=>1#Array=>temp#Object");
        configItem3.setConfigV("{\"code\":\"bic\",\"name33\":\"33\"}");

        ConfigItem configItem2 = new ConfigItem();
        configItem2.setConfigK("ccc.su#Object=>2#Array=>temp#Object");
        configItem2.setConfigV("{\"code\":\"bc2\",\"name\":\"222\"}");

        configItems.add(configItem);
        configItems.add(configItem2);
        configItems.add(configItem3);
        System.out.println(mergeConfig2(configItems));
    }

//    public static String mergeConfig(List<ConfigItem> configItems) {
//        JSONObject configObj = new JSONObject();
//        for (ConfigItem configItem : configItems) {
//            String[] keys = configItem.getConfigK().split("=>");
//            // 从左到右遍历
//            for (int i = 0; i < keys.length; i++) {
//                String[] tempKey = keys[i].split("#");
//                if (tempKey[0].equals("temp")) {
//                    continue;
//                }
//                if (tempKey[1].equals("Object")) {
//                    configObj.put(tempKey[0], new JSONObject());
//                } else if (tempKey[1].equals("Array")) {
//                    configObj.put(tempKey[0], new JSONArray());
//                } else if (tempKey[1].equals("String")) {
//                    configObj.put(tempKey[0], "");
//                }
//            }
//
//            for (int j = keys.length - 1; j >= 0; j--) {
//                String[] tempKey = keys[j].split("#");
//                Object obj = new Object();
//                if (tempKey[1].equals("Object")) {
//                    obj = JSON.parseObject(configItem.getConfigV());
//                } else if (tempKey[1].equals("Array")) {
//                    obj = JSON.parseArray(configItem.getConfigV());
//                } else if (tempKey[1].equals("String")) {
//                    obj = configItem.getConfigV() + "";
//                }
//            }
////            configObj.put(firstKey, configItem.getConfigV());
//        }
//        return configObj.toJSONString();
//    }

    public static String mergeConfig2(List<ConfigItem> configItems) {
        List<Node> nodes = new ArrayList<>();
        for (ConfigItem configItem : configItems) {
            String[] keys = configItem.getConfigK().split("=>");
            // 从左到右遍历
            for (int i = 0; i < keys.length; i++) {
                String[] tempKey = keys[i].split("#");
                boolean isExist = nodes.stream().anyMatch(x -> x.getKey().equals(tempKey[0]));
                if (isExist && !tempKey[0].equals("temp")) {
                    continue;
                }
                Node node = new Node();
                node.setKey(tempKey[0]);
                node.setType(tempKey[1]);
                node.setValue(null);
                nodes.add(node);
            }

            Node lastNode = nodes.get(nodes.size() - 1);
            if (lastNode.getType().equals("Object")) {
                JSONObject obj = JSON.parseObject(configItem.getConfigV());
                lastNode.setValue(obj);
            } else if (lastNode.getType().equals("Array")) {
                JSONArray array = JSON.parseArray(configItem.getConfigV());
                lastNode.setValue(array);
            } else if (lastNode.getType().equals("String")) {
                lastNode.setValue(configItem.getConfigV() + "");
            }
        }

        return null;
    }

    @Data
    static class Node<T> {
        private String key;
        private T value;
        private String type;

        public Node() {
        }

        public Node(String key, T value, String type) {
            this.key = key;
            this.value = value;
            this.type = type;
        }
    }

    @Data
    static class ConfigItem {
        private String configK;
        private String configV;
    }

    public static void test1() {
        JSONObject keyObj = new JSONObject();
        keyObj.put("ho", new JSONArray());

        JSONObject valueObj = new JSONObject();
        JSONArray array = new JSONArray();
        array.add("ww");

        keyObj.put("ho", array);
        System.out.println(keyObj.toJSONString());

    }

    public static String scene1_common() {
        Map<String, List<String>> dicMap = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add("wt11");
        list.add("ecj22");
        dicMap.put("d.aa$String", list);
        return getMergeConfig(dicMap);
    }

    /**
     * 配置合并的算法逻辑（存储叶子节点，支持的类型为）
     * 1）判断是否是普通的配置（按是否包含=>判断）
     * 2）若是普通类型配置，按默认分隔符","进行拼接多个配置值
     * 3）若是复杂类型（总共分析的类型为：Object、Array、String）
     * (看存到哪一级，然后类型是啥，key作为类型)
     * <p>
     * 正向、、、设置key
     * 逆向、、、设置value
     */
    public static String scene11_common() {
        Map<String, String> dicMap = new HashMap<>();
//        return getMergeConfig(dicMap);
        return null;
    }

    public static String scene12_array() {
        Map<String, String> dicMap = new HashMap<>();
        dicMap.put("user.info#Object=>C1#Array=>temp#Object", "{\"name\":\"张三\",\"age\":12,\"no\":\"111\"}");
        dicMap.put("user.info#Object=>C1#Array=>temp#Object", "{\"name\":\"李四\",\"age\":13,\"no\":\"222\"}");
        dicMap.put("user.info#Object=>C2#Array=>temp#Object", "{\"name\":\"王五\",\"age\":15,\"no\":\"444\"}");
        dicMap.put("user.info#Object=>C3#Array=>temp#Object", "{\"name\":\"刘六\",\"age\":14,\"no\":\"333\"}");
//        return getMergeConfig(dicMap);
        return null;
    }

    public static String scene13_object() {
        Map<String, String> dicMap = new HashMap<>();
        dicMap.put("user.info#Object=>C1#Object", "{\"name\":\"张三\",\"age\":12,\"no\":\"111\"}");
//        return getMergeConfig(dicMap);
        return null;
    }

}