package thinking.graph_relative;

import javax.swing.*;
import java.awt.*;

import static net.mindview.util.SwingConsole.run;

/**
 * @author orange
 * @date 2024/6/11
 */
public class GridLayout1 extends JFrame {

    /**
     * 知识点（22.6）：GridLayout
     */
    public GridLayout1() {
        setLayout(new GridLayout(7, 3));
        for (int i = 0; i < 20; i++) {
            add(new JButton("Button " + i));
        }
    }

    public static void main(String[] args) {
        run(new GridLayout1(), 300, 300);
    }
}
