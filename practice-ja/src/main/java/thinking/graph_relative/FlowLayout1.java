package thinking.graph_relative;

import javax.swing.*;
import java.awt.*;

import static net.mindview.util.SwingConsole.run;

/**
 * @author orange
 * @date 2024/6/11
 */
public class FlowLayout1 extends JFrame {

    /**
     * 知识点（22.6）：控制布局
     */
    public FlowLayout1() {
        setLayout(new FlowLayout());
        for (int i = 0; i < 20; i++) {
            add(new JButton("Button " + i));
        }
    }

    public static void main(String[] args) {
        run(new FlowLayout1(), 300, 300);
    }
}
