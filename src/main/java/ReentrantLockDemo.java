import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ipad implements Runnable{

    public synchronized void sendMsg() {
        System.out.println(Thread.currentThread().getId() + "\t invoked sendMsg()");
        sendEmail();
    }

    public synchronized void sendEmail() {
        System.out.println(Thread.currentThread().getId() + "\t ------invoked sendEmail()");
    }

    Lock lock = new ReentrantLock();
    @Override
    public void run() {
        get();
    }

    public void get() {

        // lock() unlock() 只要配对 就没问题 加锁几次 解锁几次
//        lock.lock();
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getId() + "\t invoked get()");
            set();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            lock.unlock();
            lock.unlock();
        }
    }

    public void set() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getId() + "\t ------invoked set()");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}


/**
 * 可重入锁
 * 指的是同一线程外层函数获得锁以后 内存递归函数仍然能获取该锁的代码 在同一线程在外层方法获取锁的时候 在进入内层方法会自动获得锁
 * 也就是说 线程可以进入任何一个它已经拥有的锁所同步的着的代码块
 * 10	 invoked sendMsg()
 * 10	 ------invoked sendEmail()
 * 11	 invoked sendMsg()
 * 11	 ------invoked sendEmail()
 *
 * @author shanmingda
 * @date 2020-10-16 17:36
 */
public class ReentrantLockDemo {

    public static void main(String[] args) {

        Ipad ipad = new Ipad();

        new Thread(() -> {
            ipad.sendMsg();
        }, "T1").start();

        new Thread(() -> {
            ipad.sendMsg();
        }, "T2").start();

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread t3 = new Thread(ipad);
        Thread t4 = new Thread(ipad);

        t3.start();
        t4.start();
    }
}
