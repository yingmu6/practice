package spring.ioc;

/**
 * @Author chenSy
 * @Date 2023/05/15 13:00
 * @Description
 */
public class StoreV2 {
    private Item item;

    private String desc;

    public StoreV2() { //set方式注入依赖时，会调用无参的构造方法

    }

    public StoreV2(Item item) {
        this.item = item;
    }

    public StoreV2(String desc, Item item) {
        this.desc = desc;
        this.item = item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return this.item;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
