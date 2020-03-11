package relative.basic.object;

/**
 * 深复制：对象中的属性，包含对象类型
 * @author : chensy
 * Date : 2020-03-09 11:08
 */
public class StudentOfDeepV2 implements Cloneable {
    private String name;
    private AddressV2 addressV2;

    public String getName() {
        return name;
    }

    public AddressV2 getAddressV2() {
        return addressV2;
    }

    public void setAddressV2(AddressV2 addressV2) {
        this.addressV2 = addressV2;
    }

    public void setName(String name) {
        this.name = name;
    }



    @Override
    public Object clone() {
        StudentOfDeepV2 deep = null;
        try {
            deep = (StudentOfDeepV2) super.clone(); // 浅复制
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        deep.addressV2 = (AddressV2) addressV2.clone(); // 深复制（对属性对象进行lone）
        return deep;
    }

}

class AddressV2 implements Cloneable {
    private String addressName;

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    @Override
    public Object clone() {
        AddressV2 addressV2 = null;
        try {
            addressV2 = (AddressV2) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return addressV2;
    }
}
