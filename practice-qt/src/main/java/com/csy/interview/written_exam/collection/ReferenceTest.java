package com.csy.interview.written_exam.collection;

import org.junit.Test;

import java.lang.ref.*;

/**
 * @author chensy
 * @date 2023/8/25
 */
public class ReferenceTest {

    /**
     * 引用_测试
     * 1）A hard (or strong) reference is the default type of reference,
     *   and most of the time, we may not even think about when and how referenced objects are garbage collected
     *
     * 2）All soft references to objects reachable only by soft reference should be cleared out before the OutOfMemoryError exception is thrown
     *
     * 3）Objects referenced only by weak references aren't prevented（阻止） from being collected.
     *
     * 4）弱引用的作用和使用场景：
     *    除了ThreadLocal，还有一些高速缓存场景；因为缓存数据时用来加快执行速度，但是又不能无限制的将数据存入缓存，
     *    因为内存容量是有限的，这时可以使用弱引用，GC时及时清理缓存对象。
     *
     * 5）四种类型的回收场景：
     *    a）强：A a=new A(); 此时引用a强引用对象A；不会被GC
     *    b）软：SoftReference.java，在内存不够时引用对象会被GC；
     *    c）弱：WeakReference.java，每次GC都会被回收；
     *    d）虚：PhantomReference.java，每次GC都会被回收；
     *
     * 参考链接：
     * a）https://www.baeldung.com/java-reference-types
     * b）https://medium.com/@ramtop/weak-soft-and-phantom-references-in-java-and-why-they-matter-c04bfc9dc792
     * c）https://blog.csdn.net/csdn_20150804/article/details/103748869 弱引用WeakReference作用与使用场景
     */

    /**
     * 场景1：弱引用、虚引用、软引用基本使用
     */
    @Test
    public void test_reference_v1() {
        ReferenceQueue<Ref> queue = new ReferenceQueue<>();

        // 创建一个弱引用（指定引用的对象，以及引用对象要注册的队列）
        WeakReference<Ref> weak = new WeakReference<>(new Ref("Weak"), queue);
        // 创建一个虚引用
        PhantomReference<Ref> phantom = new PhantomReference<>(new Ref("Phantom"), queue);
        // 创建一个软引用
        SoftReference<Ref> soft = new SoftReference<>(new Ref("Soft"), queue);

        System.out.println("引用内容：");
        System.out.println(weak.get());
        System.out.println(phantom.get()); //看源码，phantom.get()始终返回null
        System.out.println(soft.get());

        System.out.println("被回收的引用：");
        for (Reference r = null; (r = queue.poll()) != null;) {
            System.out.println(r);
        }

        /**
         * 输出结果：
         *
         * 引用内容：
         * Weak
         * null
         * Soft
         * 被回收的引用：
         *
         * 结果分析：
         * 1）弱引用对象和软引用对象都是可达的，但是虚引用对象不可点，phantom.get()调用时总是为null
         * 2）多个引用说明：
         *    a）HardReference：强引用（注：没有这个类，只是形象说明），类似String str = new String()建立起来的引用，都是强引用。在str指向另一个对象或null之前
         *      都不会被GC回收（指向另一个对象，或str=null才会被GC回收）
         *    b）WeakReference：弱引用，当GC要求回收对象时，不会阻止对象被回收，即使有弱引用存在
         *    c）SoftReference：软引用，当GC要求回收对象时，也不会阻止对象被回收，但回收过程会有延迟，必须要等到JVM heap内存不够用，接近产生OutOfMemory错误时，才会被回收
         *    d）PhantomReference：虚引用，这种类型的引用比较特别，在大多数时间里，无法通过它拿到其引用的对象（即phantom.get()总是为null），但是，在这个对象消失的时候，
         *       该引用还是会进入ReferenceQueue队列中的
         * 3）创建弱引用、软引用、虚引用时，需要执行引用的的对象、引用对象注册的队列，如：new WeakReference<>(new Ref("Weak"), queue)
         */
    }

    @Test
    public void test_reference_v2() {
        ReferenceQueue<Ref> queue = new ReferenceQueue<>();

        WeakReference<Ref> weak = new WeakReference<>(new Ref("WeakV2"), queue); //注册：此处的Ref对象在外部没有任何引用，所以在某个时间点，GC应当回收这个对象
        PhantomReference<Ref> phantom = new PhantomReference<>(new Ref("PhantomV2"), queue);
        SoftReference<Ref> soft = new SoftReference<>(new Ref("SoftV2"), queue);

        System.out.println("引用内容V2：");
        System.out.println(weak.get());
        System.out.println(phantom.get()); //看源码，phantom.get()始终返回null
        System.out.println(soft.get());

        use_gc();
        System.out.println("被回收的引用V2：");
        for (Reference r = null; (r = queue.poll()) != null; ) {
            System.out.println(r);
        }

        /**
         * 输出结果：
         *
         * 引用内容V2：
         * WeakV2
         * null
         * SoftV2
         * 被回收的引用V2：
         * java.lang.ref.WeakReference@1b701da1
         * java.lang.ref.PhantomReference@726f3b58
         *
         * 结果分析：
         * 1）弱引用和虚引用都会回收了，软引用要在接近OOM异常时回收
         */
    }


    @Test
    public void test_reference_v3() {
        ReferenceQueue<Ref> queue = new ReferenceQueue<>();

        Ref wr = new Ref("Hard"); //强引用
        WeakReference<Ref> weak = new WeakReference<>(wr, queue); //引用的对象wr是强引用
        PhantomReference<Ref> phantom = new PhantomReference<>(wr, queue);
        SoftReference<Ref> soft = new SoftReference<>(new Ref("Soft"), queue);

        System.out.println("引用内容V3：");
        System.out.println(weak.get());
        System.out.println(phantom.get());
        System.out.println(soft.get());

        use_gc();
        System.out.println("被回收的引用V3：");
        for (Reference r = null; (r = queue.poll()) != null; ) {
            System.out.println(r);
        }

        wr = null; //将强引用置为null，可被GC回收
        use_gc();
        System.out.println("被回收的引用V333：");
        for (Reference r = null; (r = queue.poll()) != null; ) {
            System.out.println(r);
        }

        /**
         * 输出结果：
         *
         * 引用内容V3：
         * Hard
         * null
         * Soft
         * 被回收的引用V3：
         * 被回收的引用V333：
         * java.lang.ref.WeakReference@1b701da1
         * java.lang.ref.PhantomReference@726f3b58
         *
         * 结果分析：
         * 1）弱引用、虚引用在创建时，若关联了强引用，在强引用可达时不会被回收
         * 2）在强引用置为null，如wr=null，表明强引用可被回收，此时关联的弱引用、虚引用都可被回收
         */
    }

    // 出发gc，通知jvm进行垃圾回收
    private void use_gc() {
        System.gc();
        try {
            Thread.sleep(100); //给GC留点时间，保证GC执行完成
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    class Ref {

        Object v;

        Ref(Object v) {
            this.v = v;
        }

        public String toString() {
            return this.v.toString();
        }
    }
}
