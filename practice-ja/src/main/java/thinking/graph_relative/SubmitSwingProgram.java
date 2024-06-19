package thinking.graph_relative;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

/**
 * @author orange
 * @date 2024/6/11
 */
public class SubmitSwingProgram extends JFrame {

    /**
     * 知识点（22.2）：Swing基础
     */
    JLabel label;
    public SubmitSwingProgram() {
        super("Hello Swing");
        label = new JLabel("A Label");
        add(label);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 100);
        setVisible(true);
    }

    static SubmitSwingProgram ssp;

    public static void main(String[] args) throws InterruptedException {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ssp = new SubmitSwingProgram();
            }
        });

        TimeUnit.SECONDS.sleep(1);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ssp.label.setText("Hey! This is Different!");
            }
        });
    }
}
