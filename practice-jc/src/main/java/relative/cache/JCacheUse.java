package relative.cache;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;
import javax.cache.spi.CachingProvider;
import java.util.Date;

/**
 * @author : chensy
 * Date : 2020/9/9 上午10:35
 * https://www.dazhuanlan.com/2019/10/16/5da6032ce31a5/?__cf_chl_jschl_tk__=0b937db690104636b71bb5551c7db599362fa45d-1599620090-0-AWHyzUxL9zvZfQCt44hgAmPDU3L-y2duQVgf2H8eNw9zQWs6qz1e-GwgTcPnfp7xPpuMQ5kIga37r7ExWooU5kf61DtuGDbrFei_uxcPcERBXKerNwG4QJraTreOjWatouajNbLtgx2aU02k2bHXEID1nz8J7mjCNGBQuAX8zsJhxjWbGQGGY12K5tedqWK0eT99I_HqmK_ZoSIaKJ5BMji4sFGKBEjnwuXj8fLCgFy0a3YOdMtb5C_VA7oEQuaxx0RN12RoaLbUmdmAkDy2IaLhbF_jp4386efzfhd5B8fPidbZ9_PBE60xZDcfTZizcw
 */
public class JCacheUse {
    public static void main(String[] args) {
//        basicUse();
//        basicUse3();
        basicUse4();
    }

    private static void basicUse() {
        String cacheName = "sampleCache";
        CachingProvider provider = Caching.getCachingProvider();
        CacheManager manager = provider.getCacheManager();
        Cache<Integer, Date> cache = manager.getCache(cacheName, Integer.class, Date.class);
        Date value = new Date();
        Integer key = 100;
        cache.put(key, value);
        System.out.println(cache.get(key));
        /**
         * cache为空，抛出异常java.lang.NullPointerException
         */
    }

    private static void basicUse2() {
//        CacheManager cacheManager = Caching.getCachingProvider().getCacheManager();
//        MutableConfiguration<String, Date> config = new MutableConfiguration<String, Date>();
//
//        config.setExpiryPolicyFactory(AccessedExpiryPolicy.factoryOf(Duration.ONE_MINUTE)).setStatisticsEnabled(true);
//        cacheManager.configureCache("simpleCache",config);
//        cache = cacheManager.getCache("simpleCache");
    }

    private static void basicUse3() {
        CachingProvider cachingProvider = Caching. getCachingProvider();
        CacheManager cacheManager = cachingProvider. getCacheManager();
        MutableConfiguration<String, String> config = new MutableConfiguration();
        Cache<String, String> cache = cacheManager.createCache("JDKCodeNames",config);
        cache.put("JDK1.5","Tiger");
        cache.put("JDK1.6","Mustang");
        cache.put("JDK1.7","Dolphin");
        String jdk7CodeName = cache.get("JDK1.7");
        System.out.println(jdk7CodeName);
        /**
         * 抛出异常
         * Exception in thread "main" java.lang.AbstractMethodError:
         * org.jsr107.ri.RICacheManager.createCache(Ljava/lang/String;Ljavax/cache/configuration/Configuration;)Ljavax/cache/Cache;
         */
    }

    private static void basicUse4() {
        CachingProvider provider = Caching.getCachingProvider();
        CacheManager cacheManager = provider.getCacheManager();
        MutableConfiguration<Long, String> configuration =
                new MutableConfiguration<Long, String>()
                        .setTypes(Long.class, String.class)
                        .setStoreByValue(false)
                        .setExpiryPolicyFactory(CreatedExpiryPolicy.factoryOf(Duration.ONE_MINUTE));
        Cache<Long, String> cache = cacheManager.createCache("jCache", configuration);
        cache.put(1L, "one");
        String value = cache.get(1L);
        System.out.println(value);
    }
}
