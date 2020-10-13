import java.util.concurrent.CountDownLatch;

/**
 * TODO
 *
 * @author shanmingda
 * @date 2020-10-13 15:19
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 0; i < 6; i++) {

            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t离开");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();


        System.out.println(Thread.currentThread().getName() + "\t关门");
    }

    public static void closedoor() {
        for (int i = 0; i < 6; i++) {

            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t离开");
            }, String.valueOf(i)).start();
        }

        System.out.println(Thread.currentThread().getName() + "\t关门");
    }
}
