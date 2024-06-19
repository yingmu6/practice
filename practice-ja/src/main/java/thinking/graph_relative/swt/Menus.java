package thinking.graph_relative.swt;

//import net.mindview.util.TextFile;
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.widgets.*;
//import thinking.graph_relative.swt.util.SWTApplication;
//import thinking.graph_relative.swt.util.SWTConsole;
//
//import java.util.Iterator;
//import java.util.Set;
//import java.util.TreeSet;

/**
 * @author orange
 * @date 2024/6/12
 */
public class Menus {
        //implements SWTApplication {

    /**
     * 知识点（22.14.4）：菜单
     */
//    private static Shell shell;
//    @Override
//    public void createContents(Composite parent) {
//        shell = parent.getShell();
//        Menu bar = new Menu(shell, SWT.BAR);
//        shell.setMenuBar(bar);
//        Set<String> words = new TreeSet<>(new TextFile("Menus.java", "\\w+"));
//        Iterator<String> it = words.iterator();
//        while (it.next().matches("[0-9]+"))
//            ;
//        MenuItem[] mItem = new MenuItem[7];
//        for (int i = 0; i < mItem.length; i++) {
//            mItem[i] = new MenuItem(bar, SWT.CASCADE);
//            mItem[i].setText(it.next());
//            Menu submenu = new Menu(shell, SWT.DROP_DOWN);
//            mItem[i].setMenu(submenu);
//        }
//
//        int i = 0;
//        while (it.hasNext()) {
//            addItem(bar, it, mItem[i]);
//            i = (i + 1) % mItem.length;
//        }
//
//    }
//
//    static Listener listener = new Listener() {
//
//        @Override
//        public void handleEvent(Event event) {
//            System.out.println(event.toString());
//        }
//    };
//
//    void addItem(Menu bar, Iterator<String> it, MenuItem mItem) {
//        MenuItem item = new MenuItem(mItem.getMenu(), SWT.PUSH);
//        item.addListener(SWT.Selection, listener);
//        item.setText(it.next());
//    }
//
//    public static void main(String[] args) {
//        SWTConsole.run(new Menus(), 600, 200);
//    }

}
