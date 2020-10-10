import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程间的精确唤醒
 *
 * @author shanmingda
 * @date 2020-10-10 16:14
 */

class ShareResource {

    /**
     * 1:A 2:B 3:C
     */
    private int num = 1;

    /**
     * 一把锁
     */
    private Lock lock = new ReentrantLock();

    /**
     * 配多把钥匙
     */
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void print5() {
        lock.lock();
        try {

            // 1.判断
            while (num != 1) {
                condition1.await();
            }
            // 2.干活
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + num++);
            }
            // 3.通知 并且修改标志位
            num = 2;
            condition2.signal();
            // 4.标志位
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10() {
        lock.lock();
        try {

            // 1.判断
            while (num != 2) {
                condition2.await();
            }
            // 2.干活
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + num++);
            }
            // 3.通知 并且修改标志位
            num = 3;
            condition3.signal();
            // 4.标志位
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15() {
        lock.lock();
        try {

            // 1.判断
            while (num != 3) {
                condition3.await();
            }
            // 2.干活
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + num++);
            }
            // 3.通知 并且修改标志位
            num = 1;
            condition1.signal();
            // 4.标志位
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}

/**
 *
 * 多线程之间顺序调用 实现 A->B->C
 * 三个线程启动 要求如下
 *
 * A打印5次 B打印10次 C打印15次
 * 接着
 * A打印5次 B打印10次 C打印15次
 *
 * 1. 高内聚力低耦合前提下 线程操作资源类
 * 2. 判断/干活/通知
 * 3. 多线程交互中 必须要防止多线程的虚假唤醒 也即（判断使用while 不能用if）
 * 4. 注意标志位的修改和定位
 */
public class ThreadOrderAccess {

    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                    shareResource.print5();
            }, "A").start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                    shareResource.print10();
            }, "B").start();
        }
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                    shareResource.print15();
            }, "C").start();
        }

    }
}
