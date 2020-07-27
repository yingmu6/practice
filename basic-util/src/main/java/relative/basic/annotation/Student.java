package relative.basic.annotation;

/**
 * @author : chensy
 * Date : 2020/7/7 下午12:44
 */
public class Student {
    @Service(sex = 2)
    private int sex;

    @Service(sex = 3)
    private int getSex(){
        return this.sex;
    }
}
