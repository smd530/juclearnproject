import java.util.concurrent.TimeUnit;

class HoldLockThread implements Runnable {

    private String lockA;

    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "\t自己持有：" + lockA + "\t尝试获得：" + lockB);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "\t自己持有：" + lockB + "\t尝试获得：" + lockA);

            }
        }
    }
}

/**
 * 死锁
 *
 * 死锁是指两个或两个以上的进程在执行过程中
 * 因争夺资源而造成的一种互相等待的现象
 * 若无外力干涉那它们都将无法推进下去
 *
 * @author shanmingda
 * @date 2020-10-20 10:31
 */
public class DeadLockDemo {

    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new HoldLockThread(lockA, lockB), "ThreadA").start();
        new Thread(new HoldLockThread(lockB, lockA), "ThreadB").start();

        /**
         * jps java查看进程命令 jps -l
         *
         * jstack 1987 打当前进程的堆栈信息
         *
         */
    }
}
