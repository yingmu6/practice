package mybatis.mysql.testV2.dao;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author chenSy
 * @Date 2023/02/18 08:19
 * @Description
 */
@Setter
@Getter
public class BaseDO<P> implements Serializable {

    private static final long serialVersionUID = -8612241350220864396L;

    private P id;

    private String enterpriseNo;

    private Long gmtCreate;

    private Long gmtModified;
}
