package mybatis.mysql.testV2.common;

/**
 * @Author chenSy
 * @Date 2023/02/19 20:20
 * @Description
 */
public interface IBaseDAO<T> {
    void add(T var1);

    int update(T var1);

    T getById(Object var1);
}
