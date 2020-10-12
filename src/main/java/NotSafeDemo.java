import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 题目：请举例说明集合类是不安全的
 *
 * 1. 故障现象
 *      java.util.ConcurrentModificationException
 * 2. 导致原因
 *
 * 3. 解决方案
 * ArrayList
 *  3.1 Vector (不让用 用就完了)
 *  3.2 Collections.synchronizedList(new ArrayList<>()); (小数据量可以用)
 *  3.3 new CopyOnWriteArrayList<>(); 牛逼..... 读写分离
 * HashSet
 *  3.4 Collections.synchronizedSet(new HashSet<>()); (小数据量可以用)
 *  3.5 new CopyOnWriteArraySet<>(); 牛逼
 * HashMap
 *  3.6 Collections.synchronizedMap(new HashMap<>(16)); (小数据量可以用)
 *  3.7 new ConcurrentHashMap<>(16); 牛逼
 * 4. 优化建议（同样的错误 不出现第二次）
 * @author shanmingda
 * @date 2020-10-12 16:19
 */
public class NotSafeDemo {

    public static void main(String[] args) {
        ListNotSafe();
        SetNodeSafe();
        MapNodeSafe();

    }

    public static void ListNotSafe() {
//        List<String> list = new ArrayList<>();
//        List<String> list = Collections.synchronizedList(new ArrayList<>());

        List<String> list = new CopyOnWriteArrayList<>();
//      add 源码 利用Arrays.copyOf扩容 每次写加锁
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, "线程 "+i).start();
        }
    }

    public static void SetNodeSafe() {
//        Set<String> set = new HashSet<>();
//        Collections.synchronizedSet(new HashSet<>());
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, "线程 "+i).start();
        }
    }

    public static void MapNodeSafe() {
//        Map<String, Object> map = new HashMap<>(16);
//        Collections.synchronizedMap(new HashMap<>(16));
        Map<String, Object> map = new ConcurrentHashMap<>(16);
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, "线程 "+i).start();
        }
    }
}
