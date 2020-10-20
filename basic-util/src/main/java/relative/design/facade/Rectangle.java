package relative.design.facade;

/**
 * 长方形
 * @author : chensy
 * Date : 2020/10/19 下午10:34
 */
public class Rectangle implements Shape{
    @Override
    public void draw() {
        System.out.println("长方形");
    }
}
