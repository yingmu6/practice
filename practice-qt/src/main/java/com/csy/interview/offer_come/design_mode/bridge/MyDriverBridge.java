package com.csy.interview.offer_come.design_mode.bridge;

/**
 * @author chensy
 * @date 2024/3/15
 */
public class MyDriverBridge extends DriverManagerBridge {
    public void execute() {
        getDriver().executeSQL();
    }

    public static void main(String[] args) {
        DriverManagerBridge driverManagerBridge = new MyDriverBridge();
        driverManagerBridge.setDriver(new MysqlDriver());
        driverManagerBridge.execute();

        driverManagerBridge.setDriver(new OracleDriver());
        driverManagerBridge.execute();

        /**
         * 输出结果：
         * execute sql by mysql driver
         * execute sql by oracle driver
         *
         * 结果分析：
         *
         * 问题点答疑：
         */
    }
}
