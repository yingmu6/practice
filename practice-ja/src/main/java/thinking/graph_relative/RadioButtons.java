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
public class RadioButtons extends JFrame {

    /**
     * 知识点（22.8.8）：单选按钮
     */
    private JTextField t = new JTextField(15);
    private ButtonGroup g = new ButtonGroup();
    private JRadioButton
            rb1 = new JRadioButton("one", false),
            rb2 = new JRadioButton("two", false),
            rb3 = new JRadioButton("three", false);
    private ActionListener al = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            t.setText("Radio button " + ((JRadioButton)e.getSource()).getText());
        }
    };

    public RadioButtons() {
        rb1.addActionListener(al);
        rb2.addActionListener(al);
        rb3.addActionListener(al);
        g.add(rb1);
        g.add(rb2);
        g.add(rb3);
        t.setEditable(false);
        setLayout(new FlowLayout());
        add(t);
        add(rb1);
        add(rb2);
        add(rb3);
    }

    public static void main(String[] args) {
        run(new RadioButtons(), 200, 125);
    }
}
