import org.omg.CORBA.TIMEOUT;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile不保证原子性
 *
 * @author shanmingda
 * @date 2020-09-25 16:37
 */

class MyData2 { // MyData.java ==> Mydata.class ==> JVM字节码
    volatile int number = 0;

    public void addTO50 () {
        this.number = 50;
    }

    //此时number加了 valotile 不保证原子性
    public void addPlusPlus() {
        number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();
    public void addAtomic() {

        //此方法是原子性的i++
     atomicInteger.getAndIncrement();
    }

}

public class VolatileDemo2 {

    public static void main(String[] args) {
        MyData2 myData2 = new MyData2();

        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    // 不保证原子性
                    // myData2.addPlusPlus();

                    //保证原子性
                    myData2.addAtomic();
                }
            }, String.valueOf(i)).start();
        }

//        try {
//            TimeUnit.SECONDS.sleep(5);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        //需要等待上面的20个线程全部计算完成后 再用main线程取得最终的结果值是多少

        //默认后台有两个线程 一个是main 一个是GC线程
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(myData2.atomicInteger);


    }



}
