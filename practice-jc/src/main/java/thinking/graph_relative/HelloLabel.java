package thinking.graph_relative;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

/**
 * @author chensy
 * @date 2024/4/10
 */
public class HelloLabel {

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Hello Swing");
        JLabel label = new JLabel("A Label");
        frame.add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 100);
        frame.setVisible(true);
        TimeUnit.SECONDS.sleep(1);
        label.setText("Hey! This is Different");
    }
}
