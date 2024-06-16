package com.csy.mybatis.mysql.testV2.dao;

import com.csy.mybatis.mysql.testV2.entity.TestDataBase;
import com.csy.mybatis.mysql.testV2.entity.TestDataBaseFactory;
import com.csy.mybatis.mysql.testV2.entity.TestTable;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

/**
 * @Author chenSy
 * @Date 2023/02/18 08:19
 * @Description
 */
public class BaseDAO<T extends BaseDO> extends SqlSessionDaoSupport implements IBaseDAO<T>{
    private static final Logger logger = LoggerFactory.getLogger(BaseDAO.class);

     public String nameSpace;

    private Boolean checkEnterpriseNo = true;

    public SqlSessionFactory sqlSessionFactory;

    public BaseDAO() {
        this.nameSpace = this.getClass().getCanonicalName() + ".";
    }

    @Resource(name = "sqlSessionFactory")
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) { //@Resource(...)修饰方法时，会将对应名称的实例注入到方法中
        this.sqlSessionFactory = sqlSessionFactory;
        super.setSqlSessionFactory(sqlSessionFactory);
    }


    @Override
    public void save(T t) {
        before(t);
        this.executeInsert(nameSpace + "save", t);
    }

    @Override
    public void saveBatch(List<T> list) { //只开放IBaseDAO的接口给子类使用，其它方法都改为private，进行权限控制
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        this.executeBatchInsert(nameSpace + "saveBatchInsert", list);
    }

    @Override
    public int update(T t) {
        before(t); //在执行更新操作前，进行逻辑处理
        return this.executeUpdate(nameSpace + "update", t);
    }

    @Override
    public int updateBatch(List<T> list) {
//        return this.executeUpdate(nameSpace + "updateBatch", list);

        return this.executeBatchUpdate(nameSpace + "updateBatch", list);
    }

    private void before(T t) {
        checkEnterpriseNo_way1(t);
//        checkEnterpriseNo_way2(t);
//        checkEnterprise_way3(t);
    }

    private void checkEnterpriseNo_way1(T t) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        if (this.checkEnterpriseNo && StringUtils.isEmpty(t.getEnterpriseNo())) {
            logger.info("方式一: 耗时:{} 毫秒", stopWatch.getTime());
            throw new RuntimeException("企业编号不为空");
        }
    }

    private void checkEnterprise_way3(T t) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        SqlSession session = this.getSqlSession();
        Connection connection = session.getConnection();

        String catalogFilter = null;
        String schemaFilter = null;
        try {
            TestDataBase db = TestDataBaseFactory.newDatabase(connection, catalogFilter, schemaFilter);
            String[] tableNames = db.getTableNames();
            for (String name : tableNames) {
                TestTable table = db.getTable(name);
                String[] columnNames = table.getColumns();
                logger.info("表的名称：{}, 列表名称：{}", tableNames, columnNames);
            }

            logger.info("当前类名：tableNames:{}, 耗时:{} 毫秒", tableNames, stopWatch.getTime());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void checkEnterpriseNo_way2(T t) {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        // 获取xxxMapper.xml中的内容
        SqlSession session = this.getSqlSession();
        Configuration configuration = session.getConfiguration();

        List<String> resultMapNames = new ArrayList<>();
        String currentClassName = this.getClass().getCanonicalName();
        Collection<String> names = configuration.getResultMapNames();
        for (String name : names) {
            if (name.startsWith(currentClassName)) {
               resultMapNames.add(name);
            }
        }

        if (CollectionUtils.isEmpty(resultMapNames)) {
            return;
        }

        Set<String> columns = new HashSet<>();
        // 获取到<resultMap> 中映射的column
        for (String name : resultMapNames) {
            ResultMap resultMap = configuration.getResultMap(name);
            columns.addAll(resultMap.getMappedColumns());
        }

        logger.info("方式二：class:{}, columns:{}, 耗时:{} 毫秒", currentClassName, columns, stopWatch.getTime());

        // 判断是否包含“企业编号”，若包含传入enterprise_no不为空
        if (columns.contains("ENTERPRISE_NO") && StringUtils.isEmpty(t.getEnterpriseNo())) {
           throw new RuntimeException("企业编号不为空");
        }

        // 可以拿到<sql/>标签的内容
//        Map<String, XNode> map = configuration.getSqlFragments();
//        for (String key : map.keySet()) {
//            if (key.startsWith(currentClassName)) {
//                XNode xNode = map.get(key);
//                Node node = xNode.getNode();
//                logger.info("node信息, {}" , JSON.toJSONString(node));
//            }
//        }

        // 通过configuration的mappedStatements可以拿到所有的语句
//        Collection<MappedStatement> mappedStatements = configuration.getMappedStatements();
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

    protected boolean executeBatchInsert(String statementName, List<?> list) {
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
            } catch (Exception e) {
                session.rollback();
                logger.error("execute batch insert error ", e);
            } finally {
                session.close();
            }

            return isSuccess;
        }
    }

    protected int executeBatchUpdate(String statementName, List<?> list) {
        if (CollectionUtils.isEmpty(list)) {
            return 0;
        } else {
            SqlSession session = this.sqlSessionFactory.openSession(ExecutorType.BATCH, false);

            int num = 0;
            try {
                num = session.update(statementName, list);
                session.commit();
                session.clearCache();
            } catch (Exception e) {
                session.rollback();
                logger.error("execute batch update error ", e);
            } finally {
                session.close();
            }

            return num;
        }
    }

    protected int executeDelete(String statementName, Object parameterObject) {
        int updateRows = this.getSqlSession().delete(statementName, parameterObject);
        return updateRows;
    }

    public String wrapNamespace(String name) {
        return this.getClass().getCanonicalName() + "." + name;
    }

    public Boolean getCheckEnterpriseNo() {
        return checkEnterpriseNo;
    }

    public void setCheckEnterpriseNo(Boolean checkEnterpriseNo) {
        this.checkEnterpriseNo = checkEnterpriseNo;
    }
}
