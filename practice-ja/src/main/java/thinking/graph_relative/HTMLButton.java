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
public class HTMLButton extends JFrame {

    /**
     * 知识点（22.8.18：Swing组件上
     */

    private JButton b = new JButton(
            "<html><b><font size=+2>" +
                    "<center>Hello!<br><i> Press me now!");
    public HTMLButton() {
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                add(new JLabel("<html>" +
                        "<i><font size=+4>Kapow!"));
                validate();
            }
        });
        setLayout(new FlowLayout());
        add(b);
    }

    public static void main(String[] args) {
        run(new HTMLButton(), 200, 500);
    }
}
