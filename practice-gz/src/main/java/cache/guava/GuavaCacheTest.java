package cache.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Guava 缓存测试
 *
 * @Author chenSy
 * @Date 2023/03/23 21:27
 * @Description
 */
public class GuavaCacheTest {

    /**
     * 1）https://www.baeldung.com/guava-cache 使用指南
     * 2）https://github.com/google/guava/wiki/CachesExplained  guava的github地址
     *
     * 为什么要使用缓存Cache
     * A Cache is similar to ConcurrentMap, but not quite the same. The most fundamental（根本的） difference is that a ConcurrentMap persists（持续的）
     * all elements that are added to it until they are explicitly（显示地） removed. A Cache on the other hand is generally configured to evict（驱逐）
     * entries（条目） automatically, in order to constrain（限制） its memory footprint（占用空间）. In some cases a LoadingCache can be useful even if（即使）
     * it doesn't evict entries, due to its automatic cache loading.
     * （缓存类似ConcurrentMap，但是ConcurrentMap是持续的存储元素值，除非显示地移除元素。而Cache是可以自动驱逐条目的，也就是有驱逐策略，有效地管理了内部占用空间）
     *
     * Note: If you do not need the features of a Cache, ConcurrentHashMap is more memory-efficient -- but it is extremely difficult or impossible to duplicate most Cache features with any old ConcurrentMap.
     *（如果不需要Cache特性，用ConcurrentHashMap更高效的节省内存，但是要想用ConcurrentMap来实现Cache的特性是很困难的）
     */
    public static void main(String[] args) throws Exception {
//        basicUse();
        removalListenerUse();
    }

    /**
     * 场景1：缓存的基本set、get
     */
    private static void basicUse() throws Exception {
        CacheLoader<String, String> loader;
        loader = new CacheLoader<String, String>() {
            @Override
            public String load(String key) throws Exception {
                return key.toUpperCase() + "_yyy";
            }
        };

        /**
         * 此处LoadingCache的实例是什么？在哪里指定的？
         * 解答：在CacheBuilder#build方法中指定的LocalCache$LocalLoadingCache
         */
        LoadingCache<String, String> cache = CacheBuilder.newBuilder().build(loader);

        cache.put("test", "123");
        System.out.println(cache.get("test"));

        /**
         * 此处并没有显示的调用put方法设置，为什么能get到值？此处输出的为啥是 "HELLO_yyy"
         * 解答：github上文档上的描述
         * The canonical（标准的） way to query a LoadingCache is with the method get(K). This will either return an already cached value,
         * or else use the cache's CacheLoader to atomically load a new value into the cache.
         * （也就是通过get(K)的方式获取已存在的值，若没有值，就用CacheLoader中的loader方法产生新的值）
         *
         *
         */
        System.out.println(cache.get("HELLO"));
        System.out.println("缓存的个数:" + cache.size());
    }

    /**
     * 场景2：剔除策略_使用过期时间以及移除的监听器
     */
    private static void removalListenerUse() throws Exception {
        CacheLoader<String, String> cacheLoader = new CacheLoader<String, String>() {
            @Override
            public String load(String key) throws Exception {
                return key + "_ppp";
            }
        };

        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .removalListener(new RemovalListenerImpl())
                .build(cacheLoader);

        cache.put("name", "zhangsan");
        System.out.println("缓存值 value V1 = " + cache.get("name"));

        Thread.sleep(11000); //缓存获取以及 手动让缓存失效invalidate，都会回调RemovalListener#onRemoval方法，所以可以在缓存清除时，做一些清理工作
        System.out.println("缓存值 value V2 = " + cache.get("name"));
        cache.invalidate("name");
    }

    /**
     * 场景3：过期了加载指定方法，进行初始化
     */
    @Test
    public void testInit() {

    }

    /**
     * 场景4：剔除策略_指定缓存个数，超过对应剔除
     */

    /**
     * 场景5：刷新（手动+自动）以及重新加载
     */

    /**
     * 场景6：缓存命中统计
     */

    /**
     * 场景7：asMap使用
     */

    /**
     * 场景8：剔除策略_按权重清除缓存
     */

    /**
     * 场景9：预加载缓存
     */
}

