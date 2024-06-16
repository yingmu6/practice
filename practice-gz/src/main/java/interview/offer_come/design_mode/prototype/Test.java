package interview.offer_come.design_mode.prototype;

/**
 * @author chensy
 * @date 2024/3/14
 */
public class Test {
    public static void main(String[] args) {
        // 浅复制
        Computer computer = new Computer("8core", "16G", "1TB");
        System.out.println("before simple clone:" + computer.toString());

        Computer computerClone = (Computer) computer.clone();
        System.out.println("after simple clone:" + computerClone.toString());

        // 深复制
        Disk disk = new Disk("208G", "2TB");
        ComputerDetail computerDetail = new ComputerDetail("12core", "64G", disk);
        System.out.println("before deep clone:" + computerDetail.toString());

        ComputerDetail computerDetailClone = (ComputerDetail) computerDetail.clone();
        System.out.println("after deep clone:" + computerDetailClone.toString());

        /**
         * 输出结果：
         *
         * 结果分析：
         *
         * 问题点答疑：
         * 1）为什么抛出java.lang.CloneNotSupportedException: com.csy.interview.offer_come.design_mode.prototype.ComputerDetail异常？
         */
    }
}
