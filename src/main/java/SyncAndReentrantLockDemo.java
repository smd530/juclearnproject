import java.util.concurrent.locks.ReentrantLock;

/**
 * synchornized 和 lock 的区别
 *
 * @author shanmingda
 * @date 2020-10-19 16:20
 */
public class SyncAndReentrantLockDemo {

    public static void main(String[] args) {

        synchronized (new Object()) {

        }

        new ReentrantLock();

// javap -c 之后
//        public static void main(java.lang.String[]);
//            Code:
//                0: new           #2                  // class java/lang/Object
//                3: dup
//                4: invokespecial #1                  // Method java/lang/Object."<init>":()V
//                7: dup
//                8: astore_1
//                9: monitorenter
//                10: aload_1
//                11: monitorexit
//                12: goto          20
//                15: astore_2
//                16: aload_1
//                17: monitorexit
//                18: aload_2
//                19: athrow
//                20: new           #3                  // class java/util/concurrent/locks/ReentrantLock
//                23: dup
//                24: invokespecial #4                  // Method java/util/concurrent/locks/ReentrantLock."<init>":()V
//                27: pop
//                28: return


    }
}