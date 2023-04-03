package com.consumer.basic.use;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author chenSy
 * @Date 2023/04/03 20:38
 * @Description
 */
public class BasicTest {

    Logger logger = LoggerFactory.getLogger(BasicTest.class);

    @Test
    public void test_log_print() {
        logger.debug("The provider debug level !");
        logger.info("The provider info level !");
        logger.warn("Ths provider warn level !");
        logger.error("The provider error level !");
    }

}
