import enums.CountryEnum;

import java.util.concurrent.CountDownLatch;

/**
 * 用CountDownLatch 模拟一下六国被灭 最后秦国统一
 * 做减法
 * @author shanmingda
 * @date 2020-10-13 15:19
 */
public class CountDownLatchDemo {
    private static final int THREAD_NUM = 6;

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(THREAD_NUM);

        for (int i = 1; i <= THREAD_NUM; i++) {

            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t国 被灭");
                countDownLatch.countDown();
            }, CountryEnum.getEnum(i).getName()).start();
        }
        countDownLatch.await();

        Thread.currentThread().setName("秦国");
        System.out.println(Thread.currentThread().getName() + "\t 统一六国");
    }

    public static void closedoor() {
        for (int i = 1; i <= THREAD_NUM; i++) {

            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t离开");
            }, CountryEnum.getEnum(i).getName()).start();
        }

        System.out.println(Thread.currentThread().getName() + "\t关门");
    }
}
