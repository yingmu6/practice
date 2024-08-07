package interview.written_exam.basic;

import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

/**
 * @author chensy
 * @date 2023/7/1
 */
public class CloneTest { //@MsY-Doing

    /**
     * 知识点：Clone_概述
     *
     * 知识点概述：（可查看Object#clone方法上的注释）
     * 1）In Java, cloning is the process of creating an exact（精确的） copy of the original object.
     *    It essentially（本质上） means the ability to create an object with a similar state as the original object.
     *   （在java中，克隆就是创建原始对象的精确副本的过程。它本质上意味着创建与原有对象相近的功能）
     *
     * 2）In java, if a class needs to support cloning, we must do the following things: (Clone需要做一下两件事)
     *    a）We must implement Cloneable interface.
     *    b）We must override clone() method from Object class.
     *
     * 3）深拷贝和浅拷贝
     *    a）浅拷贝：对基本数据类型进行值传递，对引用数据类型进行地址拷贝，不对对象内容拷贝，此为浅拷贝。
     *    b）深拷贝：对基本数据类型进行值传递，对引用数据类型，创建一个新的对象，并复制其内容，此为深拷贝。
     *
     * 问题点答疑：
     * 1）Clone都有哪些特性？
     *
     * 2）Clone都有哪些应用场景和用途？
     *
     * 参考链接：
     * 1）https://howtodoinjava.com/java/cloning/a-guide-to-object-cloning-in-java
     * 2）https://developer.aliyun.com/article/394579  深拷贝和浅拷贝
     */

    /**
     * 场景1：clone基本使用
     */
    @Test
    public void test_basic() throws CloneNotSupportedException { //Done
        Department dept = new Department(1, "Human Resource");
        Employee original = new Employee(3, "Admin", dept);

        //Lets create a clone of original object
        Employee cloned = (Employee) original.clone();

        //Let verify using employee id, if cloning actually workded
        System.out.println(cloned.getEmployeeId());

        //Must be true and objects must have different memory addresses
        System.out.println(original != cloned); //克隆出的对象与原对象有不同的内存地址

        //As we are returning same class; so it should be true
        System.out.println(original.getClass() == cloned.getClass()); //克隆出的对象与原对象有相同的Class对象

        //Default equals method checks for references so it should be false. If we want to make it true,
        //then we need to override equals method in Employee class. （需要重写equal方法）
        System.out.println(original.equals(cloned));

        /**
         * 结果输出：
         * 3
         * true
         * true
         * false
         *
         * 结果分析：
         * 1）(Employee) original.clone()只实现了浅拷贝，也就是对于Employee#department对象类型的成员属性，只拷贝了内存地址
         *    对于基本类型，会进行值拷贝，所以cloned.getEmployeeId()的值为3
         *
         * 2）原对象与clone产生的对象，内存地址是不一样的，所以original != cloned为true
         *
         * 3）original.getClass() == cloned.getClass()原始对象和clone对象属于同一个类型，所以class相同
         *
         * 4）original.equals(cloned) 两个对象相比内容默认是不相等的，除非重写了equals方法，然后再进行具体比较
         */
    }

    /**
     * 场景2：实现浅复制（Clone对象与原对象相互影响：Clone对象中的对象成员的改变，会影响原对象的改变，因为是地址复制，指向同一个内存地址）
     */
    @Test
    public void test_content_change() throws CloneNotSupportedException { //Done
        Department hr = new Department(1, "Human Resource");

        Employee original = new Employee(1, "Admin", hr);
        Employee cloned = (Employee) original.clone();

        //Let change the department name in cloned object and we will verify in original object
        cloned.getDepartment().setName("Finance");

        System.out.println(original.getDepartment().getName());
        System.out.println(cloned.getDepartment().getName());

        /**
         * 结果输出：
         * Finance
         * Finance
         *
         * 结果分析：
         * 1）因为original.clone()是浅复制，拷贝的是引用的内存地址。所以原对象和clone的对象的成员属性department指向同一块内存地址
         *    所以只要在一个对象中改变，另一个对象的department内容也会改变
         */
    }

    /**
     * 场景3：实现深复制（Clone对象不影响原对象，因为对象类型的成员变量，进行了内容的深度拷贝）
     */
    @Test
    public void test_deep_copying() throws CloneNotSupportedException { //Done
        Department hr = new Department(1, "Human Resource");

        EmployeeV2 original = new EmployeeV2(1, "Admin", hr);
        EmployeeV2 cloned = (EmployeeV2) original.clone();

        //Let change the department name in cloned object and we will verify in original object
        cloned.getDepartment().setName("Finance");

        System.out.println(original.getDepartment().getName());
        System.out.println(cloned.getDepartment().getName());

        /**
         * 输出结果：
         * Human Resource
         * Finance
         *
         * 结果分析：
         * 因为EmployeeV2中的成员属性Department已经实现了clone方法，会对其中的内容进行拷贝，而不仅仅是拷贝地址
         */
    }

    @Getter
    @Setter
    public class Employee implements Cloneable { //实现Clone操作，需要实现Cloneable接口，否则会抛出"java.lang.CloneNotSupportedException"异常

        private int employeeId;
        private String employeeName;
        private Department department;

        public Employee(int id, String name, Department dept) {
            this.employeeId = id;
            this.employeeName = name;
            this.department = dept;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone(); //浅拷贝，对于引用类型，只是拷贝了内存地址
        }

    }

    @Getter
    @Setter
    public class EmployeeV2 implements Cloneable {

        private int employeeId;
        private String employeeName;
        private Department department;

        public EmployeeV2(int id, String name, Department dept) {
            this.employeeId = id;
            this.employeeName = name;
            this.department = dept;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException { //实现深拷贝（即包含对象类型的成员对象，也要实现clone方法）
            EmployeeV2 cloned = (EmployeeV2) super.clone();
            cloned.setDepartment((Department) cloned.getDepartment().clone());
            return cloned;
        }

    }

    @Getter
    @Setter
    public class Department implements Cloneable{
        private int id;
        private String name;

        public Department(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

}
