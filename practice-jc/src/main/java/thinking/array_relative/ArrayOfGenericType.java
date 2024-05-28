package thinking.array_relative;

/**
 * @author orange
 * @date 2024/5/28
 */
public class ArrayOfGenericType<T> {

    /**
     * 知识点（16.5）：数组与泛型
     */

    T[] array;
    @SuppressWarnings("unchecked")
    public ArrayOfGenericType(int size) {
        // array = new T[size]; //编译错误：Type parameter 'T' cannot be instantiated directly （参数T不能被直接实例化）
        array = (T[]) new Object[size];
    }

//    public <U> U[] makeArray() {
//        return new U[10]; //编译错误：Type parameter 'U' cannot be instantiated directly
//    }


}
