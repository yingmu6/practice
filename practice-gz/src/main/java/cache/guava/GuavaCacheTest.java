package cache.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;

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
    public static void main(String[] args) throws ExecutionException {

        CacheLoader<String, String> loader;
        loader = new CacheLoader<String, String>() {
            @Override
            public String load(String key) throws Exception {
                return key.toUpperCase();
            }
        };

        LoadingCache<String, String> cache = CacheBuilder.newBuilder().build(loader);
        System.out.println(cache.getUnchecked("hello"));

        cache.put("test", "123");
        System.out.println(cache.get("test") + ",,," + cache.get("HELLO"));
        System.out.println("缓存的个数:" + cache.size());
    }
}
