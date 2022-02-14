package relative.basic.collection;

import java.util.Stack;

/**
 * @author : chensy
 * Date : 2020/8/3 上午11:02
 */
public class StackTest {
    public static void main(String[] args) {
        Stack<Student> stack = new Stack<>();
        Student student = new Student("张三", 10);
        Student student2 = new Student("李四", 11);
        stack.push(student);
        stack.push(student2);

        System.out.println(stack.pop().getName() + ", " + stack.peek().getName());
        System.out.println(stack.peek().getName());
        System.out.println(stack.pop().getName());

    }
}

class Student {
    private String name;
    private Integer age;

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}

/**
 * Stack class represents a last-in-first-out (LIFO 后进先出) stack of objects.
 * 1）底层数据结构
 *   1.1）Stack继承Vector，Vector继承AbstractList，底层是用数组Object[]存储对象
 *
 * 2）相关方法
 *  2.1）push（将元素推到栈中，没用到synchronized）：将元素添加到数组最后一个元素
 *  2.2）pop（将元素从栈中弹出，用到了synchronized）：取出数组中的最后一个元素（底层用peek()方法）
 *  2.3）peek（与pop功能类似，都可以取元素，用到了synchronized）
 *
 *  注意：pop获取元素后，会将最后一个元素置为null，对数组有变更
 *  而peek只是对元素进行读取
 */
