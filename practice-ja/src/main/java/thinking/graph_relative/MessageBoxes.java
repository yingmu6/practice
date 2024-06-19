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
public class MessageBoxes extends JFrame {

    /**
     * 知识点（22.8.12）：消息框
     */
    private JButton[] b = {
            new JButton("Alert"), new JButton("Yes/No"),
            new JButton("Color"), new JButton("Input"),
            new JButton("3 Vals")
    };

    private JTextField txt = new JTextField(15);
    private ActionListener al = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String id = ((JButton)e.getSource()).getText();
            if (id.equals("Alert")) {
                JOptionPane.showConfirmDialog(null,
                        "There's a bug on you!", "Hey!",
                        JOptionPane.ERROR_MESSAGE);
            } else if (id.equals("Yes/No")) {
                JOptionPane.showConfirmDialog(null,
                        "or no", "choose yes",
                        JOptionPane.YES_NO_OPTION);
            } else if (id.equals("Color")) {
                Object[] options = {"Rea", "Green"};
                int sel = JOptionPane.showOptionDialog(
                        null, "Choose a Color!", "Warning",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.WARNING_MESSAGE, null, options, options[0]);
                if (sel != JOptionPane.CLOSED_OPTION) {
                    txt.setText("Color Selected：" + options[sel]);
                }
            } else if (id.equals("Input")) {
                String val = JOptionPane.showInputDialog(
                        "How many fingers do you see?");
                txt.setText(val);
            } else if (id.equals("3 Vals")) {
                Object[] selections = {"First", "Second", "Third"};
                Object val = JOptionPane.showInputDialog(
                        null, "Choose one", "Input",
                        JOptionPane.INFORMATION_MESSAGE, null, selections, selections[0]);
                if (val != null) {
                    txt.setText(val.toString());
                }
            }
        }
    };

    public MessageBoxes() {
        setLayout(new FlowLayout());
        for (int i = 0; i < b.length; i++) {
            b[i].addActionListener(al);
            add(b[i]);
        }
        add(txt);
    }

    public static void main(String[] args) {
        run(new MessageBoxes(), 200, 200);
    }
}
