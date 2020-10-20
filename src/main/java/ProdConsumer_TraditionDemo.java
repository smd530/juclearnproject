import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareDate {

    private int number = 0;

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    public void increment() throws Exception {
        lock.lock();
        try {
            // 1. 判断
            while (number != 0) {
                // 等待 不能生产
                condition.await();
            }

            // 2. 干活
            number++;

            System.out.println(Thread.currentThread().getName() + "\t" + number);

            // 3. 通知唤醒
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decreament() throws Exception {
        lock.lock();
        try {

            // 1. 判断
            while (number == 0) {
                // 等待 不能生产
                condition.await();
            }

            // 2. 干活
            number--;

            System.out.println(Thread.currentThread().getName() + "\t" + number);

            // 3. 通知唤醒
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}


/**
 * 生产者消费者模式传统版
 * 一个初始值为0的变量 两个线程对其交替操作 一个加1 一个减1 来五轮
 *
 * 1. 线程 操作(方法) 资源类
 * 2. 判断 干活 通知
 * 3. 防止虚假唤醒
 *
 * @author shanmingda
 * @date 2020-10-19 14:56
 */
public class ProdConsumer_TraditionDemo {

    public static synchronized void main(String[] args) {

        ShareDate shareDate = new ShareDate();

        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    shareDate.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "AAAA").start();

        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    shareDate.decreament();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "BBBB").start();

    }
}
