package mybatis.mysql.testV2.condition;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author chenSy
 * @Date 2023/04/03 19:32
 * @Description
 */
@Setter
@Getter
public class PageQueryStudentCondition {

    String enterpriseNo;

    String name;

    Integer start;

    Integer size;
}
