import java.util.concurrent.*;

/**
 * 阻塞队列
 *
 * ArrayBlockingQueue 由数组结构组成的有界阻塞队列
 * LinkedBlockingDeque 由链表结构组成的有界（integer.MAX_VALUE）阻塞队列 吞吐量一般高于上面那个
 * SynchronousQueue 一个不存储元素的队列 也即单个元素的队列 每个插入操作必须等待另一个线程调用移除操作 否则插入操作一直处于阻塞状态
 * PriorityBlockingQueue 优先队列
 *
 * @author shanmingda
 * @date 2020-10-13 17:34
 */
public class BlockingQueueDemo {

    public static void main(String[] args) throws Exception {

        // ArrayBlockingQueue 由数组结构组成的有界阻塞队列
        BlockingQueue<String> arrayblockingQueue = new ArrayBlockingQueue(2);
        // LinkedBlockingDeque 由链表结构组成的有界（integer.MAX_VALUE）阻塞队列
        BlockingQueue<String> linkedblockingQueue = new LinkedBlockingDeque(2);
        //
        BlockingQueue<String> synchronousQueue = new SynchronousQueue<>();

        // 抛出异常组
        System.out.println(arrayblockingQueue.add("a"));
        System.out.println(arrayblockingQueue.add("b"));
//        System.out.println(arrayblockingQueue.add("c"));

//        element是队列空不空 队首元素是谁
        System.out.println(arrayblockingQueue.element());

        System.out.println(arrayblockingQueue.remove());
        System.out.println(arrayblockingQueue.remove());
//        System.out.println(arrayblockingQueue.remove());

        // 特殊值 boolean
        arrayblockingQueue.offer("a");
        arrayblockingQueue.peek();
        arrayblockingQueue.poll();

        // 一直阻塞
        arrayblockingQueue.put("a");
        arrayblockingQueue.take();

        // 超时退出
        arrayblockingQueue.offer("a", 1L, TimeUnit.SECONDS);
        arrayblockingQueue.poll(1, TimeUnit.SECONDS);



    }
}
