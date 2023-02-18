package mybatis.mysql;

import java.util.List;

/**
 * @Author chenSy
 * @Date 2023/02/16 18:25
 * @Description
 */
public interface IUserMapper {
    List<User> getUserList();

    User getUser(int id);
}
