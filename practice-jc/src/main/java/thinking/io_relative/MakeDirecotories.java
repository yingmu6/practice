package thinking.io_relative;

import java.io.File;

/**
 * @author orange
 * @date 2024/6/6
 */
public class MakeDirecotories {

    /**
     * 知识点（18.1.3）：目录检查即创建
     */
    private static void usage() {
        System.err.println(
                "Usage:MakeDirectories path1 ...\n" +
                        "Creates each path\n" +
                        "Usage:MakeDirectories -d path1 ...\n" +
                        "Deletes each path\n" +
                        "Usage:MakeDirectories -r path1 path2\n" +
                        "Renames from path1 to path2"
        );
        System.exit(1);
    }

    private static void fileData(File f) {
        System.out.println(
                "Absoulte path：" + f.getAbsolutePath() +
                        "\n Can read：" + f.canRead() +
                        "\n Can write：" + f.canWrite() +
                        "\n getName：" + f.getName() +
                        "\n getParent：" + f.getParent() +
                        "\n getPath：" + f.getParent() +
                        "\n length：" + f.length() +
                        "\n lastModified：" + f.lastModified()
        );
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            usage();
        }

        if (args[0].equals("-r")) {
            File
                    old = new File(args[1]),
                    rname = new File(args[2]);
            fileData(old);
            fileData(rname);
            return;
        }

        int count = 0;
        boolean del = false;
        if (args[0].equals("-d")) {
            count++;
            del = true;
        }
        count--;
        while (++count < args.length) {
            File f = new File(args[count]);
            if(f.exists()) {
                System.out.println(f + " exists");
                if (del) {
                    System.out.println("deleting..." + f);
                    f.delete();
                }
            } else {
                if(!del) {
                    f.mkdirs();
                    System.out.println("created " + f);
                }
            }
        }
    }
}
