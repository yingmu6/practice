package thinking.graph_relative.swt;

//import org.eclipse.swt.SWT;
//import org.eclipse.swt.events.PaintEvent;
//import org.eclipse.swt.events.PaintListener;
//import org.eclipse.swt.events.SelectionAdapter;
//import org.eclipse.swt.events.SelectionEvent;
//import org.eclipse.swt.layout.GridData;
//import org.eclipse.swt.layout.GridLayout;
//import org.eclipse.swt.widgets.Canvas;
//import org.eclipse.swt.widgets.Composite;
//import org.eclipse.swt.widgets.Slider;
import thinking.graph_relative.swt.util.SWTApplication;
import thinking.graph_relative.swt.util.SWTConsole;

/**
 * @author orange
 * @date 2024/6/12
 */
public class SineWave implements SWTApplication {

    /**
     * 知识点（22.14.6）：图形
     */

//    class SineDraw extends Canvas {
//        private static final int SCALEFACTOR = 200;
//        private int cycles;
//        private int points;
//        private double[] sines;
//        private int[] pts;
//        public SineDraw (Composite parent, int style) {
//            super(parent, style);
//            addPaintListener(new PaintListener() {
//                @Override
//                public void paintControl(PaintEvent paintEvent) {
//                    int maxWidth = getSize().x;
//                    double hstep = (double) maxWidth / (double) points;
//                    int maxHeight = getSize().y;
//                    pts = new int[points];
//                    for (int i = 0; i < points; i++) {
//                        pts[i] = (int) ((sines[i] * maxHeight / 2 * .95) + (maxHeight / 2));
//                    }
//                    paintEvent.gc.setForeground(paintEvent.display.getSystemColor(SWT.COLOR_RED));
//                    for (int i = 0; i < points; i++) {
//                        int x1 = (int) ((i - 1) * hstep);
//                        int x2 = (int) (i * hstep);
//                        int y1 = pts[i - 1];
//                        int y2 = pts[i];
//                        paintEvent.gc.drawLine(x1, y1, x2, y2);
//                    }
//                }
//            });
//            setCycles(5);
//        }
//        public void setCycles(int newCycles) {
//            cycles = newCycles;
//            points = SCALEFACTOR * cycles * 2;
//            sines = new double[points];
//            for (int i = 0; i < points; i++) {
//                double radians = (Math.PI / SCALEFACTOR) * i;
//                sines[i] = Math.sin(radians);
//            }
//            redraw();
//        }
//    }
//
//    private SineDraw sines;
//    private Slider slider;
//
//    @Override
//    public void createContents(Composite parent) {
//        parent.setLayout(new GridLayout(1, true));
//        sines = new SineDraw(parent, SWT.NONE);
//        // sines.setLayoutData(SWT.FILL, SWT.FILL, true, true); //报编译错误
//        sines.setFocus();
//        slider = new Slider(parent, SWT.HORIZONTAL);
//        slider.setValues(5, 1, 30, 1, 1, 1);
//        slider.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false));
//        slider.addSelectionListener(new SelectionAdapter() {
//            @Override
//            public void widgetSelected(SelectionEvent e) {
//               sines.setCycles(slider.getSelection());
//            }
//        });
//    }
//
//    public static void main(String[] args) {
//        SWTConsole.run(new SineWave(), 700, 400);
//    }


}
