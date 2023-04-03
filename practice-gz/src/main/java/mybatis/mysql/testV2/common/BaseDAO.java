package mybatis.mysql.testV2.common;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author chenSy
 * @Date 2023/02/18 08:19
 * @Description
 */
public class BaseDAO<T extends BaseDO> extends SqlSessionDaoSupport implements IBaseDAO<T>{

     public String nameSpace;

    public SqlSessionFactory sqlSessionFactory;

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
    public void save(T t) {
        before(t);
        this.executeInsert(nameSpace + "save", t);
    }

    @Override
    public void saveBatch(List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        this.executeBatch(nameSpace + "saveBatch", list);
    }

    @Override
    public int update(T t) {
        before(t); //在执行更新操作前，进行逻辑处理
        return this.executeUpdate(nameSpace + "update", t);
    }

    @Override
    public int updateBatch(List<T> list) {
        return this.executeUpdate(nameSpace + "updateBatch", list);
    }

    private void before(T t) {
//        if (StringUtils.isEmpty(t.getEnterpriseNo())) {
//            // 对企业编号进行通用处理 todo 此处逻辑待沉淀
//        }
    }

    @Override
    public T get(Object id, String enterpriseNo) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        paramMap.put("enterpriseNo", enterpriseNo);
        return this.queryForObject(this.nameSpace + "get", paramMap);
    }

    @Override
    public List<T> findList(Object t) {
        return this.queryForList(this.nameSpace + "findList", t);
    }

    @Override
    public T findModel(Object t) {
        return this.queryForObject(this.nameSpace + "findModel", t);
    }

    @Override
    public int remove(Object id, String enterpriseNo) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        paramMap.put("enterpriseNo", enterpriseNo);
        return this.executeDelete(this.nameSpace + "remove", paramMap);
    }

    protected T queryForObject(String statementName, Object parameterObject) {
        T object = this.getSqlSession().selectOne(statementName, parameterObject);
        return object;
    }


    protected List<T> queryForList(String statementName, Object parameterObject) {
        List<T> objectList = this.getSqlSession().selectList(statementName, parameterObject);
        return objectList;
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
