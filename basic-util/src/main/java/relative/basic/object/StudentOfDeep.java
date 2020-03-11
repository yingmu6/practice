package relative.basic.object;

/**
 * 深复制：对象中的属性，包含对象类型
 * @author : chensy
 * Date : 2020-03-09 11:08
 */
public class StudentOfDeep implements Cloneable {
    private String name;
    private Address address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public Object clone() {
        StudentOfDeep deep = null;
        try {
            deep = (StudentOfDeep) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return deep;
    }

}

class Address {
    private String addressName;

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }
}
