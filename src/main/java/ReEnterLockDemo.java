import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁
 *
 * @author shanmingda
 * @date 2020-10-21 23:34
 */
public class ReEnterLockDemo {

   static Object object = new Object();

   static Lock lock = new ReentrantLock();

    /**
     * 同步代码块可重入锁
     */
    public static void m1() {
        new Thread(() -> {
            synchronized (object) {
                System.out.println(Thread.currentThread().getName() + "\t外层锁");
                synchronized (object) {
                    System.out.println(Thread.currentThread().getName() + "\t中层锁");
                    synchronized (object) {
                        System.out.println(Thread.currentThread().getName() + "\t内层锁");
                    }
                }
            }
        }, "A").start();
    }

    /**
     * 同步方法可重入锁
     */
    public synchronized static void m2() {
        System.out.println(Thread.currentThread().getName() + "\t外层锁");
    }
    public synchronized static void m3() {
        m2();
        System.out.println(Thread.currentThread().getName() + "\t中层锁");
    }
    public synchronized static void m4() {
        m3();
        System.out.println(Thread.currentThread().getName() + "\t内层锁");
    }
    public static void main(String[] args) {

        //        m1();

//        new Thread(() -> {
//            m4();
//        },"B").start();

        // ReentrantLock重入
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\tReentrant外层");
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + "\tReentrant中层");
                    lock.lock();
                    try {
                        System.out.println(Thread.currentThread().getName() + "\tReentrant内层");
                    } finally {
                        lock.unlock();
                    }
                } finally {
                    lock.unlock();
                }
            } finally {
                lock.unlock();
            }
        }, "t1").start();
    }
}
