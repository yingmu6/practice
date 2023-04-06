package mybatis.mysql.testV2.entity;

import lombok.Getter;
import lombok.Setter;
import mybatis.mysql.testV2.common.BaseDO;

/**
 * @Author chenSy
 * @Date 2023/02/20 20:07
 * @Description
 */
@Setter
@Getter
public class StudentDO extends BaseDO<Integer> {

    private String enterpriseNo;

    private String name;

    private Integer age;

    // 学号
    private String studentId;

    private Integer score;

    private String extVal;
}
