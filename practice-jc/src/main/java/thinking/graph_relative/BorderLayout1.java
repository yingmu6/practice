package thinking.graph_relative;

import javax.swing.*;
import java.awt.*;

/**
 * @author orange
 * @date 2024/6/11
 */
public class BorderLayout1  extends JFrame {

    /**
     * 知识点（22.6）：控制布局
     */
    public BorderLayout1() {
        add(BorderLayout.NORTH, new JButton("North"));
        add(BorderLayout.SOUTH, new JButton("South"));
        add(BorderLayout.EAST, new JButton("East"));
        add(BorderLayout.WEST, new JButton("West"));
        add(BorderLayout.CENTER, new JButton("Center"));
    }
}
