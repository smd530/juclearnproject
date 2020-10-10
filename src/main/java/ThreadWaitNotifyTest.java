/**
 * TODO
 *
 * @author shanmingda
 * @date 2020-09-30 17:34
 */


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 资源类
 */
class AirConditioner {

    private int num = 0;

    /**
     * 老版写法
     */
//    public synchronized void increment() throws InterruptedException{
//        // 1 判断
//        while (num != 0) {
//            this.wait();
//        }
//        // 2 干活
//        num ++;
//        System.out.println(Thread.currentThread().getName() + "\t" + num);
//
//        // 3 通知
//        this.notifyAll();
//    }
//
//    public synchronized void decrement() throws InterruptedException{
//        // 1 判断
//        while (num == 0) {
//            this.wait();
//        }
//        // 2 干活
//        num --;
//        System.out.println(Thread.currentThread().getName() + "\t" + num);
//
//        // 3 通知
//        this.notifyAll();
//    }

    /**
     * 新版写法
     */
    final Lock lock = new ReentrantLock();
    final Condition condition = lock.newCondition();
    public void increment() throws InterruptedException {
        lock.lock();
        try {
            // 1.判断
            while (num != 0) {
                condition.await();
            }
            // 2.干活
            num ++;
            System.out.println(Thread.currentThread().getName() + "\t" + num);
            // 3.通知
            condition.signalAll();

        } finally {
            lock.unlock();
        }
    }

    public void decrement() throws InterruptedException{
        lock.lock();
        try {
            // 1.判断
            while (num == 0) {
                condition.await();
            }
            // 2.干活
            num --;
            System.out.println(Thread.currentThread().getName() + "\t" + num);
            // 3.通知
            condition.signalAll();
        } finally {
            lock.unlock();
        }

    }
}

/**
 * 现在两个线程 可以操作初始值为零的一个变量
 * 实现一个线程对该变量加1 一个线程对该变量减1
 * 实现交替 10轮 变量初始值为0
 *
 * 1. 高内聚力低耦合前提下 线程操作资源类
 * 2. 判断/干活/通知
 * 3. 多线程交互中 必须要防止多线程的虚假唤醒 也即（判断使用while 不能用if）
 */
public class ThreadWaitNotifyTest {

    public static void main(String[] args) {
        AirConditioner airConditioner = new AirConditioner();
        new Thread(() -> {
            for (int i = 1; i <= 1000 ; i++) {
                try {
                    airConditioner.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }, "A").start();

        new Thread(() -> {
            try {
                for (int i = 1; i <= 1000; i++) {
                    airConditioner.decrement();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();
    }
}
