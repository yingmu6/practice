package thinking.graph_relative;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

import static net.mindview.util.SwingConsole.run;

/**
 * @author orange
 * @date 2024/6/11
 */
public class Borders extends JFrame {

    /**
     * 知识点（22.8.5）：边框
     */
    static JPanel showBorder(Border b) {
       JPanel jp = new JPanel();
       jp.setLayout(new BorderLayout());
       String nm = b.getClass().toString();
       nm = nm.substring(nm.lastIndexOf('.') + 1);
       jp.add(new JLabel(nm, JLabel.CENTER), BorderLayout.CENTER);
       jp.setBorder(b);
       return jp;
    }

    public Borders() {
        setLayout(new GridLayout(2, 4));
        add(showBorder(new TitledBorder("Title")));
        add(showBorder(new EtchedBorder()));
        add(showBorder(new LineBorder(Color.BLUE)));
        add(showBorder(new MatteBorder(5, 5, 30, 30, Color.RED)));
        add(showBorder(new BevelBorder(BevelBorder.RAISED)));
        add(showBorder(new SoftBevelBorder(BevelBorder.LOWERED)));
        add(showBorder(new CompoundBorder(new EtchedBorder(), new LineBorder(Color.RED))));
    }

    public static void main(String[] args) {
        run(new Borders(), 500, 300);
    }
}
