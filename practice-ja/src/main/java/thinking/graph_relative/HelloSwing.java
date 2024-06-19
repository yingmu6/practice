package thinking.graph_relative;

import javax.swing.*;

/**
 * @author chensy
 * @date 2024/4/10
 */
public class HelloSwing {
    public static void main(String[] args) {
//        JFrame frame = new JFrame("Hello Swing");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(300, 100);
//        frame.setVisible(true);

        Object[] obj = new Object[] {
          Integer.valueOf(3), String.valueOf("aa"), Double.valueOf(6.0)
        };

        for (int i = 0; i < obj.length; i++) {
            System.out.println(obj[i]);
        }
    }
}
