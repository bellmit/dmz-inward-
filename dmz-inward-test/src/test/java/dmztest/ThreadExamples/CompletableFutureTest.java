package dmztest.ThreadExamples;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author dmz
 * @date 2017/12/19
 */
public class CompletableFutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {

        //CompletableFuture<String> completableFuture = new CompletableFuture<>();
        //completableFuture.get();


        // attach callback
        //demoOne();

        //CompletableFuture<CompletableFuture<String>> future = getUsersDetail("userIs").thenApply(userId ->
        //        getCreditRating(userId)
        //);

        //System.out.println(future.get().get());

        //getUsersDetail("HB").thenCombine()

        //CompletableFuture.runAsync()
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        CompletableFuture.supplyAsync(() -> {
            // Code which might throw an exception
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int a = 1 / 0;
            System.out.println("Some result "+Thread.currentThread().getId());
            return "Some result";
        },cachedThreadPool).thenApply(result -> {
            System.out.println("processed result "+Thread.currentThread().getId());
            return "processed result";
        }).thenApplyAsync(result -> {
            System.out.println("result after further processing " +Thread.currentThread().getId());
            return "result after further processing";
        }).exceptionally(ex -> {
            System.out.println("Oops! We have an exception - " + Thread.currentThread().getId());
            return "Unknown!";
        }).thenRunAsync(()->{
            countDownLatch.countDown();
            System.out.println("after countDown processing " +Thread.currentThread().getId());
        });
        System.out.println(Thread.currentThread().getId()+" main");
        countDownLatch.await();
        cachedThreadPool.shutdown();
    }

    static void demoFive() throws ExecutionException, InterruptedException {
        List<String> list = Arrays.asList(new String[]{"A", "B", "C"});

        // Download contents of all the web pages asynchronously
        List<CompletableFuture<String>> pages = list.stream().map(pageLink -> downLoadPage(pageLink)).collect(Collectors.toList());

        // Create a combined Future using allOf() , 无法获得汇总结果
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(pages.toArray(new CompletableFuture[pages.size()]));

        // When all the Futures are completed, call `future.join()` to get their results and collect the results in a list -
        CompletableFuture<List<String>> pageAll = allFutures.thenApply(v -> pages.stream().map(page -> page.join()).collect(Collectors.toList()));

        //The join() method is similar to get(). The only difference is that it throws an unchecked exception if the underlying

        // Count the number of web pages having the "CompletableFuture" keyword.
        CompletableFuture<Long> countFuture = pageAll.thenApply(pageContents ->
                pageContents.stream()
                        .filter(pageContent -> pageContent.contains("CompletableFuture"))
                        .count()
        );

        System.out.println("Number of Web Pages having CompletableFuture keyword - " +
                countFuture.get());
    }

    static void demoFour() throws ExecutionException, InterruptedException {
        CompletableFuture<Double> future = getUsersDetail("H").thenCompose(userId -> getCreditRating(userId));
        // 整合依赖的服务 Compose
        System.out.println(future.get());

    }

    static CompletableFuture<String> downLoadPage(String pageLink) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return pageLink;
        });
    }

    static CompletableFuture<String> getUsersDetail(String userId) {
        return CompletableFuture.supplyAsync(() ->
                "UserId"
        );
    }

    static CompletableFuture<Double> getCreditRating(String userId) {
        return CompletableFuture.supplyAsync(() ->
                0.123
        );
    }

    private static void demoOne() throws ExecutionException, InterruptedException, IOException {

        CompletableFuture<String> first = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "World";
        });

        CompletableFuture<String> second =
                first.thenApply(name -> "Hello " + name) //callback methods transform the result of a CompletableFuture
                        .thenApply(fuck -> "Fuck " + fuck); //callback methods

        // 无法使用，必须是无返回值的任务
        //.thenRun(()->
        //    System.out.println("Future Completable ...")
        //);

        System.out.println(second.get()); // 阻塞

        CompletableFuture.runAsync(() -> {
            try {
                System.out.println("I am on working...." + " " + Thread.currentThread().getId());
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).thenRun(() -> System.out.println("I am done." + " " + Thread.currentThread().getId()))
                .thenRunAsync(() -> {
                });  // 使用不同线程池中的线程
        //如果I am on working瞬速执行完毕，则 I am done 在main线程中执行

        System.in.read();
    }

    private static void demoTwo() throws ExecutionException, InterruptedException {
        // runAsync 无返回值
        CompletableFuture<Void> runAsyncFuture = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "I'll run in a separate thread than the main thread.");
        }, Executors.newSingleThreadExecutor());   // 不知道则使用 ForkJoinPool.commonPool()
        runAsyncFuture.get();

        // supplyAsync 有返回值
        CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Completable";
        });
        String s = supplyAsync.get();
        System.out.println(s);
    }

}



























