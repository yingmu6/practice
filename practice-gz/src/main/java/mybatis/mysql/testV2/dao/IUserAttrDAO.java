package mybatis.mysql.testV2.dao;

import mybatis.mysql.testV2.common.IBaseDAO;
import mybatis.mysql.testV2.entity.UserAttrDO;

import java.util.List;

/**
 * @Author chenSy
 * @Date 2023/04/06 23:17
 * @Description
 */
public interface IUserAttrDAO extends IBaseDAO<UserAttrDO> {
    void insertUserAttrInputList(UserAttrDO userAttrDO);

    void insertUserAttrInputMap(UserAttrDO userAttrDO);

    List<UserAttrDO> getAllUserAttr();
}
