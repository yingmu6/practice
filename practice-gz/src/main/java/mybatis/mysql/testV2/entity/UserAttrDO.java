package mybatis.mysql.testV2.entity;

import lombok.Getter;
import lombok.Setter;
import mybatis.mysql.testV2.common.BaseDO;

/**
 * @Author chenSy
 * @Date 2023/04/06 23:15
 * @Description
 */
@Getter
@Setter
public class UserAttrDO extends BaseDO<Integer> {

    private String name;

    private String firstAttr1;

    private String firstAttr2;

    private String secondAttr1;

    private String secondAttr2;
}
