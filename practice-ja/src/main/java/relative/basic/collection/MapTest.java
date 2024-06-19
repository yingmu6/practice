package relative.basic.collection;

import org.junit.Test;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

/**
 * @author chensy
 * @date 2022/5/19
 */
public class MapTest {

    /**
     * Map_测试
     */

    /**
     * 场景1：Map基本使用
     */
    @Test
    public void basic_use() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "zhang");
        map.put("age", "11");
        System.out.println(map);

        Map<String, String> map1 = new HashMap<>();
        map1.put("version", "1.0.0");
        map1.put("application", "test");
        System.out.println(map1);

        /**
         * 输出结果：
         * {name=zhang, age=11}
         * {application=test, version=1.0.0}
         *
         * 结果分析：
         * HashMap并不会对key进行排序，而是根据计算的Hash值来插入元素的
         *
         * 参考链接：https://zhuanlan.zhihu.com/p/494172384
         */
    }

    /**
     * 场景2：IdentityHashMap使用 (identity: 特性、个性、身份，可以理解为：有特性的Map)
     * 1）内部实现：比较key使用 == ，而不是用equals
     * 2）应用场景：序列化已经深度拷贝
     * 3）提供了Map的所有操作，允许key、value都为null值
     *
     * 额外描述：
     * 1） As a result of its features, IdentityHashMap stands apart from other Map objects. However,
     *    it isn't used for general purposes, and therefore we need to be cautious while using this class.
     *    (IdentityHashMap是Map对象的一部分，但通常不使用它，要小心使用)
     *
     * 2）Though this class implements the Map interface, it violates（违反） the contract（契约） of the Map interface.
     *   it uses reference equality (==) on key search operations.
     *   （虽然IdentityHashMap实现了Map接口，但它违反了Map接口契约，因为它是使用 == 来搜索键key的，而不是像Map使用equals）
     *
     * 3）IdentityHashMap isn't threadsafe, the same as HashMap. So if we have multiple threads to access/modify IdentityHashMap entries
     *   in parallel, we should convert them to the synchronized map.
     *   （IdentityHashMap和HashMap一样也不是线程安全的，所以在多线程并发时，需要转换为线程安全的Map）
     *
     * 参考链接：
     * https://www.baeldung.com/java-identityhashmap  IdentityHashMap Guide
     */
    @Test
    public void test_IdentityHashMap_V1() {
        IdentityHashMap<String, Object> map = new IdentityHashMap();
        map.put("name", "zhang");
        map.put("age", 11);
        map.put(null, null);

        map.put("name", "li"); //相同的key，值对应覆盖
        System.out.println(map);

        // 转换为线程安全Map
        Map<String, String> synchronizedMap = Collections.synchronizedMap(new IdentityHashMap<String, String>());
    }

    /**
     * 验证IdentityHashMap比较key，使用 == 而不是用 equals
     * 两个对象比较时，==比较是引用是否相等，equals比较是内容是否相等
     */
    @Test
    public void test_IdentityHashMap_V2() {
        IdentityHashMap<String, String> identityHashMap = new IdentityHashMap<>();
        identityHashMap.put("title", "Harry");
        identityHashMap.put("author", "Jk");
        identityHashMap.put("language", "English");
        identityHashMap.put("genre", "Fantasy");

        HashMap<String, String> hashMap = new HashMap<>(identityHashMap);
        hashMap.put(new String("genre"), "Drama"); //因为key是根据equals来比较的，所以已经存在"genre"值，就不会创建新的key
        Assert.isTrue(hashMap.size() == 4, "Map数目错误");

        identityHashMap.put(new String("genre"), "Drama"); //IdentityHashMap中的key，是按==来比较的，new String(...)新的对象，引用不相等，所以认为是不同的key
        Assert.isTrue(identityHashMap.size() == 5, "Map数目错误");
    }
}
