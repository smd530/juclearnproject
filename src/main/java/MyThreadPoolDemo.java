import java.util.concurrent.*;

/**
 * 线程池
 *
 * @author shanmingda
 * @date 2020-10-13 22:39
 */
public class MyThreadPoolDemo {

    public static void main(String[] args) {

//        public ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
//                BlockingQueue<Runnable> workQueue,
//                RejectedExecutionHandler handler) {
//            this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
//                    Executors.defaultThreadFactory(), handler);
//        }

        // 获取当前系统CPU几核
        System.out.println(Runtime.getRuntime().availableProcessors());
        initPool();

        ExecutorService threadPool = new ThreadPoolExecutor(2, 5, 2L,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());



    }

//    ThreadPoolExecutor
//    ThreadPoolExecutor
//    ThreadPoolExecutor


    public static void initPool() {
        //    Executors 线程池工具类
        // 一池五个线程 固定线程数
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);
//
//        // 一池一个工作线程
//        ExecutorService threadPool2 = Executors.newSingleThreadExecutor();
//
//        // 一池N线程
//        ExecutorService threadPool3 = Executors.newCachedThreadPool();

        ExecutorService threadPool = new ThreadPoolExecutor(2, 5, 2L,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());


        // 拒绝策略 挨个试
        //ThreadPoolExecutor.AbortPolicy 默认
        //ThreadPoolExecutor.CallerRunsPolicy
        //ThreadPoolExecutor.DiscardOldestPolicy
        //ThreadPoolExecutor.DiscardPolicy
        try {
            for (int i = 1; i <= 2; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName()+"\t线程工作");
                });
//                TimeUnit.MILLISECONDS.sleep(300);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
