package relative.basic.annotation;

/**
 * @author : chensy
 * Date : 2020/7/7 下午12:44
 */
@GeneralAnnotation( weight = 33.6) //注解中没有设置default值，声明注解时，就必须设置值
//@CustomerService(name = "liSi", age = 33, weight = 33.6) 此处会报出重复的注解
public class Student {

    private int sex;

    @MethodAnnotation(methodName = "getSex")
    public int getSex(){
        return this.sex;
    }
}
