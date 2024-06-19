package thinking.inner_class;

/**
 * @author chensy
 * @date 2024/3/26
 */
public class LocalInnerClass {

    /**
     * 知识点：局部内部类
     */

    interface Counter {
        int next();
    }

    private int count = 0;

    Counter getCounter(final String name) {
        class LocalCounter implements Counter {
            public LocalCounter() {
                System.out.println("LocalCounter");
            }

            @Override
            public int next() {
                System.out.print(name);
                return count++;
            }
        }

        return new LocalCounter();
    }

    Counter getCounter2(final String name) {
        return new Counter() {
            {
                System.out.println("Counter()");
            }

            @Override
            public int next() {
                System.out.print(name);
                return count++;
            }
        };
    }

    public static void main(String[] args) {
        LocalInnerClass lic = new LocalInnerClass();
        Counter
                c1 = lic.getCounter("Local inner "),
                c2 =lic.getCounter2("Anonymous inner ");
        for (int i = 0; i < 5; i++) {
            System.out.println(c1.next());
        }

        for (int i = 0; i< 5; i++) {
            System.out.println(c2.next());
        }
    }

    /**
     * （场景：局部内部类）
     *
     * 输出结果：
     * LocalCounter
     * Counter()
     * Local inner 0
     * Local inner 1
     * Local inner 2
     * Local inner 3
     * Local inner 4
     * Anonymous inner 5
     * Anonymous inner 6
     * Anonymous inner 7
     * Anonymous inner 8
     * Anonymous inner 9
     *
     * 结果分析：
     *
     * 总结概括：
     */
}
