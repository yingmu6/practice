package interview.offer_come.basic.annotation;

import java.lang.reflect.Field;

/**
 * @author chensy
 * @date 2024/3/16
 */
public class FruitInfoUtil {

    public static void getFruitInfo(Class<?> clazz) {
        String strFruitProvider = "供应商信息";
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(FruitProvider.class)) { //判断字段上是否带有注解，若带有则通过反射机制获取到注解
                FruitProvider fruitProvider = field.getAnnotation(FruitProvider.class); //FruitProvider的实例是一个动态代理类，如：$Proxy1@539，对应的代理处理器为AnnotationInvocationHandler
                strFruitProvider = "供应商编号：" + fruitProvider.id() + "，供应商名称：" + fruitProvider.name()
                        + "，供应商地址：" + fruitProvider.address();
                System.out.println(strFruitProvider);
            }
        }
    }
}
