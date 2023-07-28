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
     * 3）Type的所有已知子接口：GenericArrayType（泛型数组，如T[]）, ParameterizedType（参数化类型，即泛型，如List<T>，Map<String,String>）,
     *                      TypeVariable<D>（类型变量类型，如T）, WildcardType（通配符类型，如?）
     *    Type的实现类：Class
     *
     *
     * 参考链接：
     * https://www.cnblogs.com/baiqiantao/p/7460580.html Type类型介绍
     */

    /**
     * 场景1：Type基本使用
     * 用例来源：https://www.cnblogs.com/baiqiantao/p/7460580.html
     */
    @Test
    public void test_basic_use() throws NoSuchMethodException {
        showType();

        /**
         * 输出结果：
         *   类型名：【int】， 实现类的类名：【Class】
         *   类型名：【java.lang.Boolean】， 实现类的类名：【Class】
         * 参数序号：0 ，类型名：【java.lang.String】， 实现类的类名：【Class】
         * 参数序号：0 ，类型名：【java.util.ArrayList<java.lang.String>】， 实现类的类名：【ParameterizedTypeImpl】
         * 参数序号：0 ，类型名：【T】， 实现类的类名：【TypeVariableImpl】
         * 参数序号：0 ，类型名：【? extends java.lang.Number】， 实现类的类名：【WildcardTypeImpl】
         * 参数序号：0 ，类型名：【java.util.ArrayList<java.lang.String>[]】， 实现类的类名：【GenericArrayTypeImpl】
         * 参数序号：0 ，类型名：【java.lang.Boolean】， 实现类的类名：【Class】
         * 参数序号：1 ，类型名：【java.lang.Integer】， 实现类的类名：【Class】
         *
         * 结果分析：
         * 获取testType方法中的参数列表对应的参数类型
         * public <T> void testType(int i, Boolean b, List<String> a1, List<ArrayList<String>> a2, List<T> a3,
         *                              List<? extends Number> a4, List<ArrayList<String>[]> a5, Map<Boolean, Integer> a6)
         *
         * 1）int i, Boolean b属于基本类型，对应Class类型
         * 2）List<String> 是泛型参数，取实际类型getActualTypeArguments()，即为String，为原始类型，对应Class类型
         * 3）List<ArrayList<String>> 是泛型参数，取实际类型后为ArrayList<String>，还是参数类型，对应类型为ParameterizedType，实现类为ParameterizedTypeImpl
         * 4）List<T> 是泛型类型，取实际类型后为T，T为类型变量类型，对应的类型为TypeVariable，实现类为TypeVariableImpl
         * 5）List<? extends Number> 是泛型类型，取实际类型后为【? extends java.lang.Number】，对应为通配符类型WildcardType，实现类为WildcardTypeImpl
         * 6）List<ArrayList<String>[]> 是泛型类型，取实际类型为ArrayList<String>[]，对应为泛型数组类型GenericArrayType，对应实现类为GenericArrayTypeImpl
         * 7）Map<Boolean, Integer> 是泛型类型，取实际类型后有两个参数java.lang.Boolean、java.lang.Integer，两个都对应Class类型
         */
    }

    private void showType() throws NoSuchMethodException {
        Class<?> clazz = List.class;
        Method method = TypeTest.class.getMethod("testType", int.class, Boolean.class, clazz, clazz, clazz, clazz, clazz, Map.class);
        Type[] genericParameterTypes = method.getGenericParameterTypes(); //获取参数列表对应的Type数组
        for (Type type : genericParameterTypes) {
            if (type instanceof ParameterizedType) { //参数化类型，即泛型
                ParameterizedType parameterizedType = (ParameterizedType) type;
                Type[] types = parameterizedType.getActualTypeArguments(); //泛型对应的实际类型数组
                for (int i = 0; i < types.length; i++) {
                    System.out.println("参数序号：" + i + " ，" + getTypeInfo(types[i]));
                }
            } else {
                System.out.println("  " + getTypeInfo(type));
            }
        }
    }

    private String getTypeInfo(Type type) {
        String typeName = type.getTypeName();
        Class<?> clazz = type.getClass();
        return "类型名：【" + typeName + "】， 实现类的类名：【" + clazz.getSimpleName() + "】";
    }

    public <T> void testType(int i, Boolean b, List<String> a1, List<ArrayList<String>> a2, List<T> a3, //
                             List<? extends Number> a4, List<ArrayList<String>[]> a5, Map<Boolean, Integer> a6) {
    }

    /**
     * 场景2：逐级解析参数
     */
    @Test
    public void test_parse_v1() throws NoSuchMethodException {
        Class clazz = List.class;
        Method method = TypeTest.class.getMethod("testType", int.class, Boolean.class, clazz, clazz, clazz, clazz, clazz, Map.class);

        Type[] parameterTypes = method.getGenericParameterTypes();

        // 处理第4个参数，即List<ArrayList<String>> a2
        Type type = parameterTypes[3];
        if (type instanceof ParameterizedType) {
            Type[] actualTypes = ((ParameterizedType) type).getActualTypeArguments();
            for (Type type1 : actualTypes) {
                System.out.println("未处理前：" + "，参数名：" + type1.getTypeName() + "，参数实现类：" + type1.getClass().getSimpleName());
            }

            // 再次从泛型中提取类型
            for (Type type1 : actualTypes) {
                if (type1 instanceof ParameterizedType) {
                    Type[] actualTypes2 = ((ParameterizedType) type1).getActualTypeArguments();
                    for (Type type2 : actualTypes2) {
                        System.out.println("已处理后：" + "，参数名：" + type2.getTypeName() + "，参数实现类：" + type2.getClass().getSimpleName());
                    }
                }
            }
        }

        /**
         * 输出结果：
         * 未处理前：，参数名：java.util.ArrayList<java.lang.String>，参数实现类：ParameterizedTypeImpl
         * 已处理后：，参数名：java.lang.String，参数实现类：Class
         *
         * 结果分析：
         * List<ArrayList<String>>第一次提取 泛型对应的参数时，为ArrayList<String>，还是泛型类型。 再第二次提取实际类型时，就java.lang.String为基本类型了
         */

    }

}
