package mybatis.mysql.testV2.common;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @Author chenSy
 * @Date 2023/02/18 08:19
 * @Description
 */
public class BaseDAO<T extends BaseDO> extends SqlSessionDaoSupport implements IBaseDAO<T>{

    public String nameSpace;

    public SqlSessionFactory sqlSessionFactory; //使用@Resource这种方式，引入不了值，会是NPE

    public BaseDAO() {
        this.nameSpace = this.getClass().getCanonicalName() + ".";
    }

    @Resource(
            name = "sqlSessionFactory"
    )
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
        super.setSqlSessionFactory(sqlSessionFactory);
    }


    @Override
    public void add(T t) {
        this.executeInsert(nameSpace + "add", t);
    }

    @Override
    public int update(T t) {
        before(t);
        return this.executeUpdate(nameSpace + "update", t);
    }

    private void before(T t) {
        if (Objects.isNull(t.getId())) {
            System.out.println("执行异常");
        }
    }

    @Override
    public T getById(Object id) {
        return this.queryForObject(this.nameSpace + "getById", id);
    }

    @Override
    public int deleteById(Object id) {
        return this.executeUpdate(this.nameSpace + "deleteById", id);
    }

    protected T queryForObject(String statementName, Object parameterObject) {
        T returnObject = this.getSqlSession().selectOne(statementName, parameterObject);
        return returnObject;
    }

    protected int executeUpdate(String statementName, Object parameterObject) {
        int updateRows = this.getSqlSession().update(statementName, parameterObject);
        return updateRows;
    }

    protected int queryCount(String statementName, Object parameterObject) {
        Object obj = this.getSqlSession().selectOne(statementName, parameterObject);
        return obj != null ? Integer.valueOf(obj.toString()) : 0;
    }

    protected long queryCountSum(String statementName, Object parameterObject) {
        Object obj = this.getSqlSession().selectOne(statementName, parameterObject);
        return obj != null ? Long.valueOf(obj.toString()) : 0L;
    }

    protected Object executeInsert(String statementName, Object parameterObject) {
        Object back = null;
        back = this.getSqlSession().insert(statementName, parameterObject);
        return back;
    }

    protected int insert(String statementName, Object parameterObject) {
        int primaryKey = this.getSqlSession().insert(statementName, parameterObject);
        return primaryKey;
    }

    protected boolean executeBatch(String statementName, List<?> list) {
        if (CollectionUtils.isEmpty(list)) {
            return true;
        } else {
            SqlSession session = this.sqlSessionFactory.openSession(ExecutorType.BATCH, false);

            boolean isSuccess = false;
            try {
                session.insert(statementName, list);
                session.commit();
                session.clearCache();
                isSuccess = true;
            } catch (Exception var9) {
                session.rollback();
            } finally {
                session.close();
            }

            return isSuccess;
        }
    }

    protected int executeDelete(String statementName, Object parameterObject) {
        int updateRows = this.getSqlSession().delete(statementName, parameterObject);
        return updateRows;
    }

    public String wrapNamespace(String name) {
        return this.getClass().getCanonicalName() + "." + name;
    }
}
