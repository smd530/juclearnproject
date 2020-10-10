import java.util.concurrent.TimeUnit;

/**
 * volatile可见性
 *
 * @author shanmingda
 * @date 2020-09-25 16:16
 */

class MyData {
    volatile int number = 0;

    public void addTO50 (){
        this.number = 50;
    }
}

/**
 *
 */
public class VolatileDemo {

    public static void main(String[] args) {

        MyData myData = new MyData();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+" come in");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            myData.addTO50();
            System.out.println(Thread.currentThread().getName()+" update number value: " + myData.number);
        }, "A").start();

        //第二个线程就是我们的main线程
        while(myData.number == 0) {
            //main线程一直在这里等待循环 知道number不在等于0
        }

        System.out.println(Thread.currentThread().getName()+" mission is over main get number value: " + myData.number);
    }
}
