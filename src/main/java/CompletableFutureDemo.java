import java.util.concurrent.CompletableFuture;

/**
 * 异步回调
 *
 * @author shanmingda
 * @date 2020-10-15 00:29
 */
public class CompletableFutureDemo {

    public static void main(String[] args) throws Exception{

        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t没有返回");
        });

        completableFuture.get();

        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName()+"\t有返回");
            int i = 10/0;
            return 1024;
        });
        System.out.println(completableFuture2.whenComplete((t, u) -> {
            System.out.println("t.." + t);
            System.out.println("u.." + u);
        }).exceptionally(f -> {
            System.out.println("exception.." + f.getMessage());
            return 1234;
        }).get());



    }
}
