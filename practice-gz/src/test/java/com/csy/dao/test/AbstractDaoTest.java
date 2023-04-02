package com.csy.dao.test;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author chenSy
 * @Date 2023/04/01 15:52
 * @Description
 */
public class AbstractDaoTest {

    private static final Logger logger = LoggerFactory.getLogger(AbstractDaoTest.class);

    private StopWatch stopWatch;

    @Before
    public void invokeStart() {
        stopWatch = new StopWatch();
        stopWatch.start();
    }

    /**
     * 记录测试的DAO方法的调用耗时
     */
    @After
    public void invokeFinish() {
        logger.info("方法调用结束, 耗时:{} 毫秒", stopWatch.getTime());
        System.out.println("方法调用");
    }
}
