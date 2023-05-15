package spring.ioc;

/**
 * @Author chenSy
 * @Date 2023/05/13 16:09
 * @Description
 */
public class Store {
    private Item item;

    public Store(Item item) {
        this.item = item;
    }

    public Store() { //传统方式：构造函数中设置依赖的实例
        this.item = new ItemImpl1();
    }

    public void setItem(Item item) { //传统方式：通过类中方法设置依赖的实例
        this.item = item;
    }

    public Item getItem() {
        return this.item;
    }
}
