import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 阻塞队列
 *
 * @author shanmingda
 * @date 2020-10-13 17:34
 */
public class BlockingQueueDemo {

    public static void main(String[] args) {

        // ArrayBlockingQueue 由数组结构组成的有界阻塞队列
        BlockingQueue<String> arrayblockingQueue = new ArrayBlockingQueue(2);
        // LinkedBlockingDeque 由链表结构组成的有界（integer.MAX_VALUE）阻塞队列
        BlockingQueue<String> linkedblockingQueue = new LinkedBlockingDeque(2);
    }
}
