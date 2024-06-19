package thinking.graph_relative;

import net.mindview.util.Generator;
import net.mindview.util.RandomGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static net.mindview.util.SwingConsole.run;

/**
 * @author orange
 * @date 2024/6/11
 */
public class TextPane extends JFrame {

    /**
     * 知识点（22.8.6）：一个迷你编辑器
     */
    private JButton b = new JButton("Add Text");
    private JTextPane tp = new JTextPane();
    private static Generator sg = new RandomGenerator.String(7);

    public TextPane() {
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 10; i++) {
                    tp.setText(tp.getText() + sg.next() + "\n");
                }
            }
        });
        add(new JScrollPane());
        add(BorderLayout.SOUTH, b);
    }

    public static void main(String[] args) {
        run(new TextPane(), 475,425);
    }
}
