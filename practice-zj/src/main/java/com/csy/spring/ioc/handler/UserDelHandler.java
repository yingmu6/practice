package com.csy.spring.ioc.handler;

import org.springframework.stereotype.Service;

/**
 * @author orange
 * @date 2024/5/31
 */

@Service
public class UserDelHandler implements ICommandHandler<UserDelCommand> {

    @Override
    public void handler(UserDelCommand cmd) {
        System.out.println("用户删除");
    }

    @Override
    public Class<UserDelCommand> getCommand() {
        return UserDelCommand.class;
    }
}
