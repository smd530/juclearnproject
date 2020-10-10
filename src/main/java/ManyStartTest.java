/**
 * 同一个线程多次start 会报异常 java.lang.IllegalThreadStateException
 * Thread源码 start 方法 回答调用一个start0方法 start0是一个native方法
 *         public synchronized void start() {
 *
 *             if (threadStatus != 0)
 *                 throw new IllegalThreadStateException();
 *
 *             group.add(this);
 *
 *             boolean started = false;
 *             try {
 *                 start0();
 *                 started = true;
 *             } finally {
 *                 try {
 *                     if (!started) {
 *                         group.threadStartFailed(this);
 *                     }
 *                 } catch (Throwable ignore) {
 *
 *                 }
 *             }
 *         }
 *
 * @author shanmingda
 * @date 2020-09-22 21:37
 */
public class ManyStartTest {

    public static void main(String[] args) {
        Thread t1 = new Thread();
        t1.start();
        t1.start();

        //Exception in thread "main" java.lang.IllegalThreadStateException
        //	at java.lang.Thread.start(Thread.java:708)
        //	at ManyStartTest.main(ManyStartTest.java:12)

    }
}
