package thinking.exception;

/**
 * @author orange
 * @date 2024/6/4
 */
public class StormyInning implements StormyInningExt.Storm {

    /**
     * 知识点（12.9）：异常的限制
     *
     * 注明：用例有所改动，去掉StormyInning extends Inning，有报错
     */

    public StormyInning() throws StormyInningExt.RainedOut, StormyInningExt.BaseballException {}

    public StormyInning(String s) throws StormyInningExt.Foul, StormyInningExt.BaseballException {}

    public void randHard() throws StormyInningExt.RainedOut {}

    public void event() {}

//    @Override
    public void atBat() throws StormyInningExt.PopFoul {}

    @Override
    public void rainHard() throws StormyInningExt.RainedOut {}

    public static void main(String[] args) {
        try {
            StormyInning si = new StormyInning();
            si.atBat();
        } catch (StormyInningExt.PopFoul e) {
            System.out.println("Pop foul");
        } catch (StormyInningExt.RainedOut e) {
            System.out.println("Rained out");
        } catch (StormyInningExt.BaseballException e) {
            System.out.println("Generic baseball exception");
        }

        try {
            StormyInning i = new StormyInning();
            i.atBat();
        } catch (StormyInningExt.Strike e) {
            System.out.println("Strike");
        } catch (StormyInningExt.Foul e) {
            System.out.println("Foul");
        } catch (StormyInningExt.RainedOut e) {
            System.out.println("Rained out");
        } catch (StormyInningExt.BaseballException e) {
            System.out.println("Generic baseball exception");
        }
    }
}
