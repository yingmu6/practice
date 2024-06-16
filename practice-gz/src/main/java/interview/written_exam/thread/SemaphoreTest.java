package interview.written_exam.thread;

import org.junit.Test;

import java.util.concurrent.Semaphore;

/**
 * @author chensy
 * @date 2024/1/19
 */
public class SemaphoreTest {

    /**
     * Semaphore：信号量测试
     * 1）We can use semaphores to limit the number of concurrent threads accessing a specific resource.
     * （我们可以使用信号量限制访问特定资源的并发线程数量）
     *
     * 2）Semaphore中的主要方法：
     *    a）ryAcquire()：return true if a permit（允许、许可） is available immediately and acquire it otherwise return false,
     *                     but acquire() acquires a permit and blocking until one is available
     *    b）release()：release a permit
     *    c）availablePermits()：return number of current permits available
     *
     * 参考链接：https://www.baeldung.com/java-semaphore
     */


    /**
     * 场景1：基本使用 todo @Ms-01/27
     */
    @Test
    public void basic_use() {

    }

    class LoginQueueUsingSemaphore {

        private Semaphore semaphore;

        public LoginQueueUsingSemaphore(int slotLimit) {
            this.semaphore = new Semaphore(slotLimit);
        }

        boolean tryLogin() {
            return semaphore.tryAcquire();
        }

        void logout() {
            semaphore.release();
        }

        int availableSlots() {
            return semaphore.availablePermits();
        }
    }
}
