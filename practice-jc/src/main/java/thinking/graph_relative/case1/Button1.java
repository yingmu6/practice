package thinking.graph_relative.case1;

import javax.swing.*;
import java.awt.*;

import static net.mindview.util.SwingConsole.run;

/**
 * @author chensy
 * @date 2024/4/10
 */
public class Button1 extends JFrame {
    /**
     * 知识点：创建按钮
     */
    private JButton
      b1 = new JButton("Button 1"),
      b2 = new JButton("Button 2");

    public Button1() {
        setLayout(new FlowLayout());
        add(b1);
        add(b2);
    }

    public static void main(String[] args) {
        run(new Button1(), 200, 100); //run方法可以使用
    }
}
