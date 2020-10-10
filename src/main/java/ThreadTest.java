import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 资源类 = 实例变量 + 实例方法
 */
class Ticket {

    private int number = 5000;

    Lock lock = new ReentrantLock();

    public void saleTicket() {

        lock.lock();
        try {
            if (number > 0) {
                System.out.println("线程" + Thread.currentThread().getName()
                    + " 当前" + (number--) + "张，还剩下" + number + "张");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}


/**
 * 1.在高内聚低耦合的前提下 线程 操作 资源类
 *  1.1先写一个资源类
 * 2.几个线程要对资源类进行什么操作 这些操作都在资源类自身带着 低耦合
 *
 */
public class ThreadTest {

    public static void main(String[] args) {

        final Ticket ticket = new Ticket();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                for (int i = 1; i <= 5001; i++) {
//                    ticket.saleTicket();
//                }
//            }
//        }, "A").start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                for (int i = 1; i <= 5001; i++) {
//                    ticket.saleTicket();
//                }
//            }
//        }, "B").start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                for (int i = 1; i <= 5001; i++) {
//                    ticket.saleTicket();
//                }
//            }
//        }, "C").start();

        new Thread(() -> {for (int i = 0; i < 5001 ; i++){ ticket.saleTicket();}

        }, "A").start();

        new Thread(() -> {for (int i = 0; i < 5001 ; i++){ ticket.saleTicket();}

        }, "B").start();

        new Thread(() -> {for (int i = 0; i < 5001 ; i++){ ticket.saleTicket();}

        }, "C").start();


        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }, "D");



        new Thread(() -> {
            for (int i = 0; i < 5001; i++) {
                ticket.saleTicket();
            }
        }, "D").start();
    }


}
