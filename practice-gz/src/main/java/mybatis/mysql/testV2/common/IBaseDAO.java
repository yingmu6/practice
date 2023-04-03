package mybatis.mysql.testV2.common;

import java.util.List;

/**
 * @Author chenSy
 * @Date 2023/02/19 20:20
 * @Description
 */
public interface IBaseDAO<T> {
    void save(T t);

    void saveBatch(List<T> list);

    int update(T t);

    int updateBatch(List<T> list);

    T get(Object id, String enterpriseNo);

    List<T> findList(Object t);

    T findModel(Object t);

    int remove(Object id, String enterpriseNo);
}
