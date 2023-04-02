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

    // 一般考虑软删除，通过update实现，不提供硬删除的接口
    int deleteById(Object var1);
}
