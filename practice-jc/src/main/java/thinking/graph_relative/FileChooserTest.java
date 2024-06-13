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
public class FileChooserTest extends JFrame {

    /**
     * 知识点（22.8.17）：文本对话框
     */
    private JTextField
            fileName = new JTextField(),
            dir = new JTextField();
    private JButton
            open = new JButton("Open"),
            save = new JButton("Save");

    public FileChooserTest() {
        JPanel p = new JPanel();
        open.addActionListener(new OpenL());
        p.add(open);
        save.addActionListener(new SaveL());
        p.add(save);
        add(p, BorderLayout.SOUTH);
        dir.setEditable(false);
        fileName.setEditable(false);
        p = new JPanel();
        p.setLayout(new GridLayout(2, 1));
        p.add(fileName);
        p.add(dir);
        add(p, BorderLayout.NORTH);
    }

    class OpenL implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser c = new JFileChooser();
            int rVal = c.showOpenDialog(FileChooserTest.this);
            if (rVal == JFileChooser.APPROVE_OPTION) {
                fileName.setText(c.getSelectedFile().getName());
                dir.setText(c.getCurrentDirectory().toString());
            }
            if (rVal == JFileChooser.CANCEL_OPTION) {
                fileName.setText("You pressed cancel");
                dir.setText("");
            }
        }
    }

    class SaveL implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser c = new JFileChooser();
            int rVal = c.showOpenDialog(FileChooserTest.this);
            if (rVal == JFileChooser.APPROVE_OPTION) {
                fileName.setText(c.getSelectedFile().getName());
                dir.setText(c.getCurrentDirectory().toString());
            }
            if (rVal == JFileChooser.CANCEL_OPTION) {
                fileName.setText("You pressed cancel");
                dir.setText("");
            }
        }
    }

    public static void main(String[] args) {
        run(new FileChooserTest(), 250, 150);
    }
}
