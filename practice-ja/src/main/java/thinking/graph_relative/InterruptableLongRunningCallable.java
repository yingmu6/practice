package thinking.graph_relative;

import net.mindview.util.TaskItem;
import net.mindview.util.TaskManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Callable;

import static net.mindview.util.SwingConsole.run;

/**
 * @author orange
 * @date 2024/6/12
 */
public class InterruptableLongRunningCallable extends JFrame {

    /**
     * 知识点（22.10）：Swing与并发
     */
    class CallableTask extends InterruptableLongRunningTask.Task
            implements Callable<String> {

        @Override
        public String call() throws Exception {
            run();
            return "Return value of " + this;
        }
    }

    private JButton
            b1 = new JButton("Start Long Running Task"),
            b2 = new JButton("End Long Running Task"),
            b3 = new JButton("Get results");
    private TaskManager<String, CallableTask> manager = new TaskManager<>();
    public InterruptableLongRunningCallable() {
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CallableTask task = new CallableTask();
                manager.add(task);
                System.out.println(task + " added to the queue");
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (String result : manager.purge()) {
                    System.out.println(result);
                }
            }
        });

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (TaskItem<String, CallableTask> tt : manager){
                    tt.task.id();
                }
                for (String result : manager.getResults()) {
                    System.out.println(result);
                }
            }
        });
        setLayout(new FlowLayout());
        add(b1);
        add(b2);
        add(b3);
    }

    public static void main(String[] args) {
        run(new InterruptableLongRunningCallable(), 200, 150);
    }
}
