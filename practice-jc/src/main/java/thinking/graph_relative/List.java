package thinking.graph_relative;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static net.mindview.util.SwingConsole.run;

/**
 * @author orange
 * @date 2024/6/11
 */
public class List extends JFrame {

    /**
     * 知识点（22.8.10）：列表框
     */
    private String[] flavors = {
            "Chocolate", "Strawberry", "Vanilla Fudge Swirl",
            "Mint Chip", "Mocha Almond Fudge", "Rum Raisin",
            "Praline Cream", "Mud Pie"
    };
    private DefaultListModel lItems = new DefaultListModel<>();
    private JList lst = new JList(lItems);
    private JTextArea t = new JTextArea(flavors.length, 20);
    private JButton b = new JButton("Add Item");
    private ActionListener bl = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (count < flavors.length) {
                lItems.add(0, flavors[count++]);
            } else {
                b.setEnabled(false);
            }
        }
    };

    private ListSelectionListener ll = new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (e.getValueIsAdjusting()) {
                return;
            }
            t.setText("");
            for (Object item : lst.getSelectedValues()) {
                t.append(item + "\n");
            }
        }
    };

    private int count = 0;
    public List() {
        t.setEnabled(false);
        setLayout(new FlowLayout());
        Border brd = BorderFactory.createMatteBorder(1, 1, 2, 2, Color.BLACK);
        lst.setBorder(brd);
        t.setBorder(brd);
        for (int i = 0; i < 4; i++) {
            lItems.addElement(flavors[count++]);
        }
        add(t);
        add(lst);
        add(b);
        lst.addListSelectionListener(ll);
        b.addActionListener(bl);
    }

    public static void main(String[] args) {
        run(new List(), 250, 375);
    }
}
