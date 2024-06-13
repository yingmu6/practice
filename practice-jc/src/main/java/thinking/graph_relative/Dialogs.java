package thinking.graph_relative;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static net.mindview.util.SwingConsole.run;

/**
 * @author orange
 * @date 2024/6/11
 */
public class Dialogs extends JFrame {

    /**
     * 知识点（22.8.16）：对话框
     */
    class MyDialog extends JDialog {
        public MyDialog(JFrame parent) {
            super(parent, "My dialog", true);
            setLayout(new FlowLayout());
            add(new JLabel("Here is my dialog"));
            JButton ok = new JButton("OK");
            ok.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
            add(ok);
            setSize(150, 125);
        }
    }

    private JButton b1 = new JButton("Dialog Box");
    private MyDialog dlg = new MyDialog(null);
    public Dialogs() {
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dlg.setVisible(true);
            }
        });
        add(b1);
    }

    public static void main(String[] args) {
        run(new Dialogs(), 125, 75);
    }
}
