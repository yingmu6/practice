package com.basic.use.wrapper;

import org.apache.dubbo.common.bytecode.Wrapper;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * @Author chenSy
 * @Date 2023/04/03 20:22
 * @Description
 */
public class WrapperTest {

    Logger logger = LoggerFactory.getLogger(WrapperTest.class);

    private static final String DEFAULT_ERROR_DESCRIBE = "获取的信息异常";

    @Test
    public void test_log_print() {
        logger.debug("The provider debug level !");
        logger.info("The provider info level !");
        logger.warn("Ths provider warn level !");
        logger.error("The provider error level !");
    }

    /**
     * Wrapper使用场景1：基本使用/包含创建Wrapper、获取声明的方法、获取属性值，设置属性值、调用目标方法（可通过Wrapper类对目标类进行操作）
     * 注意点：
     * 1）类或接口都创建Wrapper类
     */
    @Test
    public void test_wrapper_basic() {

        Wrapper wrapper = Wrapper.getWrapper(Car.class); //创建接口对应的Wrapper类
        String[] declaredMethodNames = wrapper.getDeclaredMethodNames(); //获取被封装的类中声明的方法
        Assert.isTrue(declaredMethodNames.length == 2, DEFAULT_ERROR_DESCRIBE);

        String[] methodNames = wrapper.getMethodNames(); //获取被封装的类中的方法（包含继承的方法）
        Assert.isTrue(methodNames.length == 3, DEFAULT_ERROR_DESCRIBE);

        Wrapper wrapperForClass = Wrapper.getWrapper(Suv.class); //类也可以有封装Wrapper类
        String[] ms = wrapperForClass.getDeclaredMethodNames();
        Assert.isTrue(ms.length == 6, DEFAULT_ERROR_DESCRIBE);

        Object suvObj = new Suv();
        wrapper.setPropertyValue(suvObj, "carName" , "越野车");

        String propertyValue = (String) wrapper.getPropertyValue(suvObj, "carName");
        Assert.isTrue(propertyValue.equals("越野车"), DEFAULT_ERROR_DESCRIBE);
    }

    /**
     * Wrapper使用场景2：测试Wrapper中的缓存内容Wrapper#WRAPPER_MAP
     */
    public void test_wrapper_cache() {

    }

    public interface Price {
        Double setCarPrice(Double carPrice);
    }

    public interface Car extends Price {
        String setCarName(String carName);

        String setCarColor(String carColor);
    }

    public class Suv implements Car {

        private String carName;

        private Double carPrice;

        private String carColor;

        @Override
        public Double setCarPrice(Double carPrice) {
            return this.carPrice = carPrice;
        }

        @Override
        public String setCarName(String carName) {
            return this.carName = carName;
        }

        @Override
        public String setCarColor(String carColor) {
            return this.carColor = carColor;
        }
    }
}
