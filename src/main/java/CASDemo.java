import java.util.concurrent.atomic.AtomicInteger;

/**
 * CAS
 * compare and swap 比较并交换
 *
 * @author shanmingda
 * @date 2020-10-15 17:06
 */
public class CASDemo {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);

        // public final boolean compareAndSet(int expect, int update) {
        //        return unsafe.compareAndSwapInt(this, valueOffset, expect, update);
        // }
        System.out.println(atomicInteger.compareAndSet(5, 2020) + "\tcurrent value " + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 1024) + "\tcurrent value " + atomicInteger.get());
//        true	current value 2020
//        false	current value 2020

        atomicInteger.getAndIncrement();
    }
}
