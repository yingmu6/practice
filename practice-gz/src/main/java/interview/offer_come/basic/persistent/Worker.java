package interview.offer_come.basic.persistent;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author chensy
 * @date 2024/3/16
 */
@Getter
@Setter
public class Worker implements Serializable {

    private static final long serialVersionUID = -5234233744028101156L;

    private String name;

    private transient int salary;

    static int age = 15;
}
