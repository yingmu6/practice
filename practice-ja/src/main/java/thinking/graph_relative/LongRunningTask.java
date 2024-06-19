package thinking.graph_relative;

import org.omg.CORBA.TIMEOUT;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.SwingConsole.run;

/**
 * @author orange
 * @date 2024/6/11
 */
public class LongRunningTask extends JFrame {

    /**
     * 知识点（22.10.1）：长期运行的任务
     */
    private JButton
            b1 = new JButton("Start Long Running Task"),
            b2 = new JButton("End Long Running Task");
    public LongRunningTask() {
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    System.out.println("Task interrupted");
                    return;
                }
                System.out.println("Task completed");
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread.currentThread().interrupt();
            }
        });
        setLayout(new FlowLayout());
        add(b1);
        add(b2);
    }

    public static void main(String[] args) {
        run(new LongRunningTask(), 200, 150);
    }

}
