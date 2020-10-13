import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 互斥信号量
 * 抢车位
 * @author shanmingda
 * @date 2020-10-13 16:04
 */
public class SemaphoreDemo {

    public static void main(String[] args) {

        //Semaphore(int permits)

        //模拟资源类 有三个空车位
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                     // 占用
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"\t抢占了车位");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName()+"\t离开了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //释放
                    semaphore.release();
                }

            }, String.valueOf(i)).start();
        }
    }
}
