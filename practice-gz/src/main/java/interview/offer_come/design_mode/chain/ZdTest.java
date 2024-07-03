package interview.offer_come.design_mode.chain;

import org.junit.Test;

/**
 * @author chensy
 * @date 2024/3/29
 */
public class ZdTest { //@MsY-Doing
    /**
     * 知识点：责任链模式
     *
     * 知识点概括：
     *
     */

    /**
     * 场景1：基本使用
     */
    @Test
    public void basicUse() { //Doing
        AuthHandler authHandler = new AuthHandler("user");
        BusinessHandler businessHandler = new BusinessHandler("business");
        ResponseHandler responseHandler = new ResponseHandler("response");

        System.out.println((authHandler instanceof AbstractHandler) + ", "
                + (businessHandler instanceof AbstractHandler) + ", "
                + (responseHandler instanceof AbstractHandler));

        System.out.println((authHandler instanceof Handler) + ", "
                + (businessHandler instanceof Handler) + ", "
                + (responseHandler instanceof Handler));

        authHandler.setHandler(businessHandler); //形成处理器链
        businessHandler.setHandler(responseHandler);
        authHandler.operator();

        /**
         * 输出结果：
         * true, true, true
         * true, true, true
         * user auth...
         * business info handler...
         * message response...
         *
         * 结果分析：
         * 1）AuthHandler、BusinessHandler、ResponseHandler都继承了AbstractHandler，所以都是AbstractHandler对象实例
         *    且这三个类也实现了Handler接口，也是Handler的实例对象
         *
         * 2）AuthHandler设置handler为BusinessHandler，BusinessHandler设置handler为ResponseHandler，就形成了处理器链
         *
         */
    }

}
