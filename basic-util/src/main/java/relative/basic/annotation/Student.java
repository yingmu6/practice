package relative.basic.annotation;

/**
 * @author : chensy
 * Date : 2020/7/7 下午12:44
 */
@Service(name = "student")
public class Student {

    private int sex;

    @Method(methodName = "getSex")
    private int getSex(){
        return this.sex;
    }
}
