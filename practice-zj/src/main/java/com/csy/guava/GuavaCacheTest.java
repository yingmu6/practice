package com.csy.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

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
     * 3）https://www.theartofverification.com/assertions-and-advantages-of-assertions/ 使用断言的好处
     * （可以从静态代码中看出预期的结果，而System是需要再运行时打印的，并且提供了@Before等注解，可以简化重复代码。还能结合Assert使用）
     * 4）https://wizardforcel.gitbooks.io/guava-tutorial/content/13.html guava的中文教程
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

    private static CacheLoader<String, String> CACHE_LOADER;

    private static CacheLoader<String, String> CACHE_LOADER_V2;

    private static int INDEX = 1;

    @Before
    public void initCacheLoader() {
        CACHE_LOADER = new CacheLoader<String, String>() {
            @Override
            public String load(String key) throws Exception {
                String value = key.toUpperCase() + (INDEX++);

                System.out.println("load方法加载：" + value);
                return value;
            }
        };

        CACHE_LOADER_V2 = new CacheLoader<String, String>() {
            @Override
            public String load(String key) {
                String value = key.toUpperCase();
                System.out.println("load_V2方法加载：" + value);
                return value;
            }
        };
    }

    /**
     * 场景1：缓存的基本set、get
     */
    @Test
    public void basicUse() throws Exception {
        /**
         * 此处LoadingCache的实例是什么？在哪里指定的？
         * 解答：在CacheBuilder#build方法中指定的LocalCache$LocalLoadingCache
         */
        LoadingCache<String, String> cache = CacheBuilder.newBuilder().build(CACHE_LOADER);

        cache.put("test", "123");
        Assert.isTrue(cache.get("test").equals("123"), "获取的值不正确");

        /**
         * 此处并没有显示的调用put方法设置，为什么能get到值？此处输出的为啥是 "HELLO"
         * 解答：github上文档上的描述
         * The canonical（标准的） way to query a LoadingCache is with the method get(K). This will either return an already cached value,
         * or else use the cache's CacheLoader to atomically load a new value into the cache.
         * （也就是通过get(K)的方式获取已存在的值，若没有值，就用CacheLoader中的loader方法产生新的值）
         */
        Assert.isTrue(cache.get("HELLO").equals("HELLO"), "值错误");
        Assert.isTrue(cache.size() == 2, "缓存个数不正确"); //使用Assert可以把预期的结果展示出来，而System只是输出值，要运行了才知道结果
    }

    /**
     * 场景2：剔除策略_使用过期时间以及移除的监听器
     */
    @Test
    public void removalListenerUse() throws Exception {
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .expireAfterWrite(3, TimeUnit.SECONDS)
                .removalListener(new RemovalListenerImpl())
                .build(CACHE_LOADER);

        cache.put("name", "zhangsan");
        Assert.isTrue(cache.get("name").equals("zhangsan"), "出现错误！");

        Thread.sleep(3100); //缓存获取以及 手动让缓存失效invalidate，都会回调RemovalListener#onRemoval方法，所以可以在缓存清除时，做一些清理工作
        Assert.isTrue(cache.get("name").equals("NAME"), "出现错误！"); //失效了，会调用CacheLoader的load方法

        cache.invalidate("name");
    }

    /**
     * 场景3：过期了加载指定方法，进行初始化
     */
    @Test
    public void testRefresh() throws Exception {
       LoadingCache<String, String> cache = CacheBuilder.newBuilder()
               .maximumSize(2)
               .refreshAfterWrite(3, TimeUnit.SECONDS) //定时刷新功能，即回调CacheLoader的load()方法（但要在检索时，才会真正刷新）
               .build(CACHE_LOADER);

       Assert.isTrue(cache.get("name").equals("NAME1"), "结果错误");
       Thread.sleep(3100);
       Assert.isTrue(cache.get("name").equals("NAME2"), "结果错误"); //此处若不检索，即使缓存设置的刷新时间到了，也不会执行load()方法
    }

    /**
     * 场景4：剔除策略_指定缓存个数，超过对应剔除
     */
    @Test
    public void testEvictWithSize() throws Exception{
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(2) //限制个数，超过指定个数后，会剔除最老的元素
                .build(CACHE_LOADER_V2);

        cache.get("Hello");
        Assert.isTrue(cache.size() == 1, "缓存个数错误"); //当超过2个元素时，该缓存会被踢除

        cache.get("Hi");
        Assert.isTrue(cache.size() == 2, "缓存个数错误");

        cache.get("Ok");
        Assert.isTrue(cache.size() == 2, "缓存个数错误");

        Assert.isTrue(cache.asMap().keySet()
                .containsAll(Lists.newArrayList("Hi", "Ok")), "缓存key不正确");
    }

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

