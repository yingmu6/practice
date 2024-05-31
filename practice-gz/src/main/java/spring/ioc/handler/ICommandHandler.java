package spring.ioc.handler;

/**
 * @author orange
 * @date 2024/5/31
 */
public interface ICommandHandler<T extends AbstractCommand> {

    void handler(T cmd);

    Class<T> getCommand();
}