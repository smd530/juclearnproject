import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport
 * park unPark不依赖锁块
 * 顺序可以颠倒
 * @author shanmingda
 * @date 2020-10-22 00:37
 */
public class LockSupportDemo {

   static Object object = new Object();

    public static void main(String[] args) {
        Thread a = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\tcome in");
            // 被阻塞 等待通知等待放行 它要通过需要许可证
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "\t被唤醒");
        }, "A");
        a.start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread b = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t通知了");
            LockSupport.unpark(a);
        }, "B");
        b.start();
    }
}
