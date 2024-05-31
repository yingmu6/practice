package spring.ioc.handler;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author orange
 * @date 2024/5/31
 */
public class CommandDispatcherTest { //@GzY-Doing

    /**
     * 知识点：Spring通过注解注入集合 && Dispatcher派发模式
     *
     * 知识点概括：
     * 1）
     *
     * 参考链接：
     * 1）Spring-通过注解注入Bean的几种方式 https://cloud.tencent.com/developer/article/2146142
     * 2）深入理解Spring注解机制 https://www.cnblogs.com/Createsequence/p/16585516.html
     */

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/spring-ioc-constructor.xml");
        CommandDispatcher commandDispatcher = (CommandDispatcher) context.getBean("commandDispatcher");

        AbstractCommand userAdd = new UserAddCommand();
        commandDispatcher.dispather(userAdd);

        AbstractCommand userDel = new UserDelCommand();
        commandDispatcher.dispather(userDel);

        /**
         * 输出结果：
         * 用户添加
         * 用户删除
         *
         * 结果分析：
         * 1）CommandDispatcher的构造方法的参数是Set<ICommandHandler> iCommandHandlerSet，即Spring会在依赖注入时，传入所有ICommandHandler实例
         *    在该方法中将指令与ICommandHandler缓存起来，进行具体派发时会使用到
         *
         * 2）进行派发时commandDispatcher.dispather(AbstractCommand) 会根据传入的命令类型，选择缓存中handlerMap对应的ICommandHandler实例，
         *    实现具体的实例派发，即实现了多态处理
         */
    }
}
