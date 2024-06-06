package thinking.interface_relative.interfaceprocessor;

/**
 * @author orange
 * @date 2024/6/5
 */
public interface Processor {
    String name();
    Object process(Object input);
}