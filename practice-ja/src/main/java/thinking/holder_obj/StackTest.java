package thinking.holder_obj;

import net.mindview.util.Stack;

/**
 * @author orange
 * @date 2024/6/5
 */
public class StackTest {

    /**
     * 知识点：
     */
    public static void main(String[] args) {

        Stack<String> stack =
                new Stack<>();
        for (String s : "My dog has fleas".split(" ")) {
            stack.push(s);
        }

        while (!stack.empty()) {
            System.out.println(stack.pop() + " ");
        }
        System.out.println();
        java.util.Stack<String> stack2 =
                new java.util.Stack<String>();
        for (String s : "My dog has fleas".split(" ")) {
            stack2.push(s);
            while (!stack2.empty()) {
                System.out.println(stack2.pop() + " ");
            }
        }

    }
}
