package mybatis.mysql.testV1;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author chenSy
 * @Date 2023/02/16 23:59
 * @Description
 */
@Getter
@Setter
public class User {
    private int id;
    private String name;
    private int noId;
    private Long gmtCreate;
    private Long gmtModified;
}
