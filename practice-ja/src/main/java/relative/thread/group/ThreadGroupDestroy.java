package relative.thread.group;

/**
 * @author chensy
 * @date 2019-05-29 23:59
 */
public class ThreadGroupDestroy {
    public static void main(String[] args) {
        ThreadGroup group = new ThreadGroup("TestGroup");
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        //before destroy
        System.out.println("group.isDestroyed=" + group.isDestroyed());
        mainGroup.list();

        group.destroy();
        //after destroy
        System.out.println("group.isDestroyed=" + group.isDestroyed());
        mainGroup.list();

        /**
         * 输出结果：
         * group.isDestroyed=false
         * java.lang.ThreadGroup[name=main,maxpri=10]
         *     Thread[main,5,main]
         *     java.lang.ThreadGroup[name=TestGroup,maxpri=10]
         * group.isDestroyed=true
         * java.lang.ThreadGroup[name=main,maxpri=10]
         *     Thread[main,5,main]
         *
         * 结果分析：
         */
    }
}
