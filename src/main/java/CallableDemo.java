import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyThread implements Runnable {
    @Override
    public void run() {

    }
}

class MyThread2 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {

        System.out.println("fuck it");
        return 1234;
    }
}

/**
 * 多线程中 第三种获得多线程的方法
 *
 * @author shanmingda
 * @date 2020-10-13 13:52
 */
public class CallableDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException{
        FutureTask futureTask = new FutureTask(new MyThread2());

        new Thread(futureTask, "A").start();

        System.out.println(futureTask.get());
    }
}
