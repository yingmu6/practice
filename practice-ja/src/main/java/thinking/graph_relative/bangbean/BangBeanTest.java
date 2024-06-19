package thinking.graph_relative.bangbean;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TooManyListenersException;

import static net.mindview.util.SwingConsole.run;

/**
 * @author orange
 * @date 2024/6/12
 */
public class BangBeanTest extends JFrame {

    /**
     * 知识点（22.11.3）：一个更复杂的Bean
     */
    private JTextField txt = new JTextField(20);
    class BBL implements ActionListener {
        private int count = 0;

        @Override
        public void actionPerformed(ActionEvent e) {
            txt.setText("BangBean action " + count++);
        }
    }

    public BangBeanTest() {
        BangBean bb = new BangBean();
        try {
            bb.addActionListener(new BBL());
        } catch (TooManyListenersException e) {
            txt.setText("Too many listeners");
        }
        add(bb);
        add(BorderLayout.SOUTH, txt);
    }

    public static void main(String[] args) {
        run(new BangBeanTest(), 400, 500);
    }
}
