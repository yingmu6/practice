package com.csy.dao.test;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 * @Author chenSy
 * @Date 2023/04/01 15:52
 * @Description
 */
@ContextConfiguration("classpath:spring-database-test.xml")
public class AbstractDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

    private static final Logger logger = LoggerFactory.getLogger(AbstractDaoTest.class);

    private StopWatch stopWatch;

    public StopWatch getStopWatch() {
        return stopWatch;
    }

    public void setStopWatch(StopWatch stopWatch) {
        this.stopWatch = stopWatch;
    }

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
    }
}
