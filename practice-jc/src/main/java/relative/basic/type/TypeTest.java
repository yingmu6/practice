package relative.basic.type;

import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author chensy
 * @date 2023/7/27
 */
public class TypeTest {

    /**
     * 类型_测试
     * 1）ParameterizedType表示参数化的类型，例如 Collection<String>
     *
     * 2）Type是Java语言中所有类型的公共父接口，其从JDK5开始引入，引入的目的主要是为了支持泛型。
     *
     *
     * 参考链接：
     * https://www.cnblogs.com/baiqiantao/p/7460580.html Type类型介绍
     */

    /**
     * 场景1：Type基本使用
     */
    @Test
    public void test_basic_use() throws NoSuchMethodException {
        showType();

        /**
         * 输出结果：
         *
         * 结果分析：
         */
    }

    private void showType() throws NoSuchMethodException {
        // 注意 int.class 和 Integer.class 是不一样的(没有所谓的自动装箱、自动拆箱机制)，不能互用
        Class<?> clazz = List.class;
        Method method = TypeTest.class.getMethod("testType", int.class, Boolean.class, clazz, clazz, clazz, clazz, clazz, Map.class);
        Type[] genericParameterTypes = method.getGenericParameterTypes(); //按照方法参数声明顺序返回参数的 Type 数组
        for (Type type : genericParameterTypes) {
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                Type[] types = parameterizedType.getActualTypeArguments(); //返回表示此类型【实际类型参数】的 Type 数组
                for (int i = 0; i < types.length; i++) {
                    System.out.println(i + getTypeInfo(types[i]));
                }
            } else {
                System.out.println("  " + getTypeInfo(type));
            }
        }
    }

    private String getTypeInfo(Type type) {
        String typeName = type.getTypeName();
        Class<?> clazz = type.getClass();
        Class<?>[] interfaces = clazz.getInterfaces();
        StringBuilder typeInterface = new StringBuilder();
        for (Class<?> clazzType : interfaces) {
            typeInterface.append(clazzType.getSimpleName()).append(",");
        }
        return "【" + typeName + "】    【" + clazz.getSimpleName() + "】    【" + typeInterface + "】";
    }

    public <T> void testType(int i, Boolean b, List<String> a1, List<ArrayList<String>> a2, List<T> a3, //
                             List<? extends Number> a4, List<ArrayList<String>[]> a5, Map<Boolean, Integer> a6) {
    }


}
