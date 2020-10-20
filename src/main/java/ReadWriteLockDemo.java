import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache {

    private volatile Map<String, Object> map = new HashMap<>();

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {

        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t开始写入");
            // 暂停一会线程毫秒
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);
            System.out.println(Thread.currentThread().getName()+"\t写入成功");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }

    }

    public void get(String key) {

        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t开始读取");
            Object o = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t读取成功" + o);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }

    }
}



/**
 * 读写锁
 *
 * 多个线程同时读一个资源类没有任何问题 所以为了满足并发量 读取共享资源应该可以同时进行
 * 但是
 * 如果有一个线程想去写共享资源来 就不应该有其他线程可以对资源进行读或写
 * 总结
 *  读-读能共存
 *  读-写不能共存
 *  写-写不能共存
 *
 *  写操作 ：原子 + 独占 整个过程必须是一个完整的统一体 中间不允许被分割 被打断
 *
 *  凡事缓存的东西一定要用volatile修饰 必须要保证可见性
 * @author shanmingda
 * @date 2020-10-13 16:52
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        for (int i = 0; i < 5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                myCache.put(tempInt + "", tempInt + "");
            }, String.valueOf(i)).start();
        }

        for (int i = 0; i < 5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                myCache.get(tempInt + "");
            }, String.valueOf(i)).start();
        }

    }
}
