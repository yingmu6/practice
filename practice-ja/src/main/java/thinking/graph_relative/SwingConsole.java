package thinking.graph_relative;

import javax.swing.*;

/**
 * @author orange
 * @date 2024/6/11
 */
public class SwingConsole {

    /**
     * 知识点（22.2.1）：一个显示框架（可做工具类）
     */
    public static void run(final JFrame f, final int width, final int height) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                f.setTitle(f.getClass().getSimpleName());
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setSize(width, height);
                f.setVisible(true);
            }
        });
    }
}
