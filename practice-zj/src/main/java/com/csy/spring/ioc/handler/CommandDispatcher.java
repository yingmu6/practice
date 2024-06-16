package com.csy.spring.ioc.handler;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author orange
 * @date 2024/5/31
 */

@Service
public class CommandDispatcher {

    Map<Class, ICommandHandler> handlerMap; //将命令与关联的处理器缓存起来（Bean初始化时进行）

    public CommandDispatcher(Set<ICommandHandler> iCommandHandlerSet) { //在CommandDispatcher初始化时，会找到所有ICommandHandler的Bean实例进行依赖注入
        Map<Class, ICommandHandler> map = new HashMap<>();
        for (ICommandHandler handler : iCommandHandlerSet) {
            Class cls = handler.getCommand();
            if (map.containsKey(cls)) {
                throw new IllegalStateException("指令的类型重复");
            } else {
                map.put(cls, handler);
            }
        }
        this.handlerMap = Collections.unmodifiableMap(map);
    }

    public void dispather(AbstractCommand cmd) { //将收到的指令，找到对应的处理器，然后实现具体的派发
        ICommandHandler handler = handlerMap.get(cmd.getClass());
        handler.handler(cmd);
    }
}
