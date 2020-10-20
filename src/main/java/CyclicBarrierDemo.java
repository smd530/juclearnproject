import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 集齐七颗龙珠再召唤神龙
 * 做加法
 * @author shanmingda
 * @date 2020-10-13 15:52
 */
public class CyclicBarrierDemo {

    private static final int DRAGON_BALL = 7;

    public static void main(String[] args) {

//        CyclicBarrier(int parties, Runnable barrierAction)
        CyclicBarrier cyclicBarrier = new CyclicBarrier(DRAGON_BALL, () -> {
            System.out.println("召唤神龙");
        });

        for (int i = 1; i <= DRAGON_BALL; i++) {
            final int tempInt = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t收集到第："+tempInt+"颗龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
