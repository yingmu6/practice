package spring.ioc.handler;

import org.springframework.stereotype.Service;

/**
 * @author orange
 * @date 2024/5/31
 */
@Service
public class UserAddHandler implements ICommandHandler<UserAddCommand> {

    @Override
    public void handler(UserAddCommand cmd) {
        System.out.println("用户添加");
    }

    @Override
    public Class<UserAddCommand> getCommand() {
        return UserAddCommand.class;
    }
}
