package interview.offer_come.design_mode.bridge;

/**
 * @author chensy
 * @date 2024/3/15
 */
public class OracleDriver implements Driver {
    @Override
    public void executeSQL() {
        System.out.println("execute sql by oracle driver");
    }
}
