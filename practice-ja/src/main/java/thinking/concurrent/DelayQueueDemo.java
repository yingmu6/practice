package thinking.concurrent;//: concurrency/DelayQueueDemo.java
import org.junit.Test;

import java.util.concurrent.*;
import java.util.*;
import static java.util.concurrent.TimeUnit.*;
import static net.mindview.util.Print.*;

/**
 * 知识点：延迟队列
 *
 * 知识点概括：
 * 1）为队列中的任务设置延迟时间，在获取元素时，要等待指定的延迟时间（队列插入元素时不用等待）
 *
 * 关联点学习：
 * 1）PriorityQueue中的堆排序算法学习：DelayQueue中使用PriorityQueue做存储（Doing）
 * 2）Condition的功能学习：如何使用其await和notify的功能（Doing）
 *
 */

class DelayedTask implements Runnable, Delayed { //@TkY-Doing
  private static int counter = 0;
  private final int id = counter++; //每次对象创建初始化时，id值都会+1，值依次为0、1、...
  private final int delta;
  private final long trigger; //任务触发时间
  protected static List<DelayedTask> sequence =
    new ArrayList<DelayedTask>();
  public DelayedTask(int delayInMilliseconds) {
    delta = delayInMilliseconds;
    trigger = System.nanoTime() +
      NANOSECONDS.convert(delta, MILLISECONDS); //时间转换
    sequence.add(this); //把延迟任务加到列表中
  }
  public long getDelay(TimeUnit unit) { //获取延迟时间
    return unit.convert(
      trigger - System.nanoTime(), NANOSECONDS);
  }
  public int compareTo(Delayed arg) {
    DelayedTask that = (DelayedTask)arg;
    if(trigger < that.trigger) return -1;
    if(trigger > that.trigger) return 1;
    return 0;
  }
  public void run() { printnb(this + " "); }
  public String toString() {
    return String.format("[%1$-4d]", delta) +
      " Task " + id;
  }
  public String summary() {
    return "(" + id + ":" + delta + ")";
  }
  public static class EndSentinel extends DelayedTask {
    private ExecutorService exec;
    public EndSentinel(int delay, ExecutorService e) {
      super(delay);
      exec = e;
    }
    public void run() {
      for(DelayedTask pt : sequence) {
        printnb(pt.summary() + " ");
      }
      print();
      print(this + " Calling shutdownNow()");
      exec.shutdownNow();
    }
  }
}

class DelayedTaskConsumer implements Runnable {
  private DelayQueue<DelayedTask> q;
  public DelayedTaskConsumer(DelayQueue<DelayedTask> q) {
    this.q = q;
  }
  public void run() {
    try {
      while(!Thread.interrupted()) //依次取出队列中的延迟任务并执行对应的run方法（此处的run是手动调用，不是系统调用）
        q.take().run(); // Run task with the current thread
    } catch(InterruptedException e) {
      // Acceptable way to exit
    }
    print("Finished DelayedTaskConsumer");
  }
}

public class DelayQueueDemo {
  public static void main(String[] args) { //Doing
    Random rand = new Random(47);
    ExecutorService exec = Executors.newCachedThreadPool();
    DelayQueue<DelayedTask> queue =
      new DelayQueue<DelayedTask>();
    // Fill with tasks that have random delays:
    for(int i = 0; i < 20; i++) //将延迟任务放入队列中
      queue.put(new DelayedTask(rand.nextInt(5000)));
    // Set the stopping point
    queue.add(new DelayedTask.EndSentinel(5000, exec)); //设置最后一个延迟的任务（因为延迟时间为5000，为最后一个）
    exec.execute(new DelayedTaskConsumer(queue)); //此处不用线程池也可以，因为主线程中没有其它任务了

    /**
     * 输出结果：
     * [128 ] Task 11 [200 ] Task 7 [429 ] Task 5 [520 ] Task 18 [555 ] Task 1 [961 ] Task 4 [998 ] Task 16 [1207] Task 9 [1693] Task 2 [1809] Task 14 [1861] Task 3 [2278] Task 15 [3288] Task 10 [3551] Task 12 [4258] Task 0 [4258] Task 19 [4522] Task 8 [4589] Task 13 [4861] Task 17 [4868] Task 6 (0:4258) (1:555) (2:1693) (3:1861) (4:961) (5:429) (6:4868) (7:200) (8:4522) (9:1207) (10:3288) (11:128) (12:3551) (13:4589) (14:1809) (15:2278) (16:998) (17:4861) (18:520) (19:4258) (20:5000)
     * [5000] Task 20 Calling shutdownNow()
     * Finished DelayedTaskConsumer
     *
     * 结果分析：
     * 1）将延迟任务put添加到队列中以后（任务中设置的延迟时间），再调用take从队列中获取到元素，延迟队列中阻塞等待指定的时间，
     *    然后执行任务中run逻辑，执行完以后，队列中的元素对应移除。
     * 2）延迟队列内部，是使用PriorityQueue存储的，并且在插入和获取队列元素时，都会使用ReentrantLock进行加锁处理的。
     */
  }


  /**
   * 场景1：不使用线程池（主动调用线程池任务的run方法）
   */
  @Test
  public void test_without_executor() throws InterruptedException { //Doing
    DelayQueue<DelayedTask> queue = new DelayQueue<>();
    Random random = new Random(47);
    for (int i = 0; i < 5; i++) {
      queue.put(new DelayedTask(random.nextInt(5000)));
    }

    for (int i = 0; i < 5; i++) {
      queue.take().run(); //主动调用run方法
    }

    /**
     * 输出结果：
     * [555 ] Task 1 [961 ] Task 4 [1693] Task 2 [1861] Task 3 [4258] Task 0
     *
     * 结果分析：
     * 1）在获取队列中元素的时候，会延迟指定的时间（延迟时间 <= 0，表明已经过期，则不会延迟等待）
     *
     * 问题点答疑：
     * 1）为什么延迟队列使用的PriorityQueue中，插入的时候按堆排序插入，而获取的时候，直接从队首开始获取
     *    插入和获取不保持相同的规则吗？
     */
  }

  /**
   * 场景2：使用线程池（将延迟任务提交到线程池执行）
   */
  @Test
  public void test_with_executor() throws InterruptedException { //Doing
    DelayQueue<DelayedTask> queue = new DelayQueue<>();
    ExecutorService exec = Executors.newCachedThreadPool();
    Random random = new Random(47);
    for (int i = 0; i < 5; i++) {
      queue.put(new DelayedTask(random.nextInt(5000)));
    }

    for (int i = 0; i < 5; i++) {
      exec.submit(queue.take()); //将任务提交给线程池执行
    }

    /**
     * 输出结果：
     * [555 ] Task 1 [961 ] Task 4 [1693] Task 2 [1861] Task 3 [4258] Task 0
     *
     * 结果分析：
     * 1）
     *
     *
     */
  }
}
