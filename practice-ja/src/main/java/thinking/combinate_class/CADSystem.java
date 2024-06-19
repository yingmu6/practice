package thinking.combinate_class;

import static net.mindview.util.Print.print;

/**
 * @author chensy
 * @date 2024/5/15
 */
public class CADSystem extends CADSystemExt.Shape { //@TkY-Doing

    /**
     * 知识点（7.4.1）：确保正确清理
     *
     * 知识点概括：
     * 1）
     *
     */

    private CADSystemExt.Circle c;
    private CADSystemExt.Triangle t;
    private CADSystemExt.Line[] lines = new CADSystemExt.Line[3];
    public CADSystem(int i) {
        super(i + 1);
        for(int j = 0; j < lines.length; j++) {
            lines[j] = new CADSystemExt.Line(j, j * j);
        }
        c = new CADSystemExt.Circle(1);
        t = new CADSystemExt.Triangle(1);
        print("Combined constructor");
    }
    public void dispose() {
        print("CADSystem.dispose()");
        t.dispose();
        c.dispose();
        for (int i = lines.length -1; i >= 0; i--) {
            lines[i].dispose();
        }
        super.dispose();
    }

    public static void main(String[] args) {
        CADSystem x = new CADSystem(47);
        try {
            // 逻辑处理
        } finally {
            x.dispose();
        }

        /**
         * 输出结果：
         * Shape constructor
         * Shape constructor
         * Drawing Line：0，0
         * Shape constructor
         * Drawing Line：1，1
         * Shape constructor
         * Drawing Line：2，4
         * Shape constructor
         * Drawing Circle
         * Shape constructor
         * Combined constructor
         * CADSystem.dispose()
         * Erasing Triangle
         * Shape dispose
         * Erasing Circle
         * Shape dispose
         * Erasing Line：2，4
         * Shape dispose
         * Erasing Line：1，1
         * Shape dispose
         * Erasing Line：0，0
         * Shape dispose
         * Shape dispose
         *
         * 结果分析：
         *
         */
    }
}
