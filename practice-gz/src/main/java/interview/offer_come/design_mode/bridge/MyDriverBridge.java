package interview.offer_come.design_mode.bridge;

/**
 * @author chensy
 * @date 2024/3/15
 */
public class MyDriverBridge extends DriverManagerBridge { //@MsY-Doing

    /**
     * 知识点：桥接模式
     *
     * 知识点概括：
     * 1）通过桥接器持有驱动类的引用，然后可设置不同的驱动，来产生不同的实现
     */

    public void execute() {
        getDriver().executeSQL();
    }

    public static void main(String[] args) { //Done
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
         * 1）抽象类DriverManagerBridge中持有Driver引用，而Driver对应着不同的实现
         *    可设置的不同的Driver实现类，从而产生不同的行为。
         *
         */
    }
}
