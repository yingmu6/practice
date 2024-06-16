package interview.offer_come.design_mode.factory.common_factory;

/**
 * @author chensy
 * @date 2024/3/14
 */
public class Factory {
    public Phone createPhone(String phoneName) {
        if ("HuaWei".equals(phoneName)) {
            return new HuaWei();
        } else if ("Apple".equals(phoneName)) {
            return new Iphone();
        } else {
            return null;
        }
    }
}
