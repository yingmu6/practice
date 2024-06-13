package thinking.graph_relative.frogbean;

import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @author orange
 * @date 2024/6/12
 */
public class Frog {

    class Sports {}

    private int jumps;
    private Color color;
//    private Spots spots; //未找到Spots
    private boolean jmpr;
    public int getJumps() {
        return jumps;
    }
    public void setJumps(int jumps) {
        this.jumps = jumps;
    }
    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public boolean isJumper() {
        return jmpr;
    }
    public void setJumper(boolean j) {
       jmpr = j;
    }
    public void addActionListener(ActionListener l) {
       // ...
    }
    public void removeActionListener(ActionListener l) {
       // ...
    }
    public void removeKeyListener(ActionListener l) {
       // ...
    }
    public void croak() {
        System.out.println("Ribbel!");
    }
}
