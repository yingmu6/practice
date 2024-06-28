//package thinking.graph_relative.jnlp;
//
//import javax.jnlp.*;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.ByteArrayInputStream;
//
///**
// * @author orange
// * @date 2024/6/11
// */
//public class JnlpFileChooser extends JFrame {
//
//    /**
//     * 知识点（22.9）：JNLP与Java Web Start
//     */
//    private JTextField fileName = new JTextField();
//    private JButton
//            open = new JButton("Open"),
//            save = new JButton("Save");
//    private JEditorPane ep = new JEditorPane();
//    private JScrollPane jsp = new JScrollPane();
//    private FileContents fileContents;
//
//    public JnlpFileChooser() {
//        JPanel p = new JPanel();
//        open.addActionListener(new OpenL());
//        p.add(open);
//        save.addActionListener(new SaveL());
//        p.add(save);
//        jsp.getViewport().add(ep);
//        add(jsp, BorderLayout.SOUTH);
//        fileName.setEditable(false);
//        p = new JPanel();
//        p.setLayout(new GridLayout(2, 1));
//        p.add(fileName);
//        add(p, BorderLayout.NORTH);
//        ep.setContentType("text");
//        save.setEnabled(false);
//    }
//
//    class OpenL implements ActionListener {
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            FileOpenService fs = null;
//            try {
//                fs = (FileOpenService) ServiceManager.lookup("javax.jnlp.FileOpenService");
//            } catch (UnavailableServiceException use) {
//                throw new RuntimeException(use);
//            }
//            if (fs != null) {
//                try {
//                    fileContents = fs.openFileDialog(".",
//                            new String[] {"txt", "*"});
//                    if (fileContents == null) {
//                        return;
//                    }
//                    fileName.setText(fileContents.getName());
//                    ep.read(fileContents.getInputStream(), null);
//                } catch (Exception exc) {
//                    throw new RuntimeException(exc);
//                }
//                save.setEnabled(true);
//            }
//        }
//    }
//
//    class SaveL implements ActionListener {
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            FileSaveService fs = null;
//            try {
//                fs = (FileSaveService) ServiceManager.lookup("javax.jnlp.FileSaveService");
//            } catch (UnavailableServiceException use) {
//               throw new RuntimeException(use);
//            }
//            if (fs != null) {
//                try {
//                    fileContents = fs.saveFileDialog(".",
//                            new String[] {"txt"},
//                            new ByteArrayInputStream(
//                                    ep.getText().getBytes()
//                            ), fileContents.getName());
//                    if (fileContents == null) {
//                        return;
//                    }
//                    fileName.setText(fileContents.getName());
//                } catch (Exception exc) {
//                    throw new RuntimeException(exc);
//                }
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        JnlpFileChooser fc = new JnlpFileChooser();
//        fc.setSize(400, 300);
//        fc.setVisible(true);
//    }
//}
