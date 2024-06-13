package thinking.graph_relative;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

/**
 * @author orange
 * @date 2024/6/11
 */
public class SubmitLabelManipulationTask {

    /**
     * 知识点（22.2）：Swing基础
     */
    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Hello Swing");
        final JLabel label = new JLabel("A Label");
        frame.add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 100);
        frame.setVisible(true);

        TimeUnit.SECONDS.sleep(1);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                label.setText("Hey！This is Different!");
            }
        });
    }
}
