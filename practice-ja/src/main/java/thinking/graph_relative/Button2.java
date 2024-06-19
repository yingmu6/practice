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
public class Button2 extends JFrame {

    /**
     * 知识点（22.4）：捕获事件
     */
    private JButton
            b1 = new JButton("Button 1"),
            b2 = new JButton("Button 2");
    private JTextField txt = new JTextField(10);

    class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = ((JButton)e.getSource()).getText();
            txt.setName(name);
        }
    }

    private ButtonListener bl = new ButtonListener();
    public Button2() {
        b1.addActionListener(bl);
        b2.addActionListener(bl);
        setLayout(new FlowLayout());
        add(b1);
        add(b2);
        add(txt);
    }

    public static void main(String[] args) {
        run(new Button2(), 200, 150);
    }
}
