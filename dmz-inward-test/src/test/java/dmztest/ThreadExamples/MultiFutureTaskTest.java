package dmztest.ThreadExamples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author dmz
 * @date 2017/12/19
 */
public class MultiFutureTaskTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {

        //ExecutorCompletionService();
        //CompletionFuture();
        //CompletableFuture();
        //ComposeDependentFutures();
        //CombineIndependentFutures();
        //CombineAllFutures();
        AnyofFuture();
        System.out.println("HHHH");
    }

    private static void AnyofFuture() throws ExecutionException, InterruptedException {
        AsyncService asyncService = new AsyncService();
        List<String> pageList = Arrays.asList("https://namibank.i8xiaoshi.com/community/home", "https://www.baidu.com/");
        List<CompletableFuture<String>> pageContentFutures = pageList.stream()
                .map(page -> asyncService.downLoadPage(page))
                .collect(Collectors.toList());

        CompletableFuture<Object> anyOfFuture = CompletableFuture.anyOf(pageContentFutures.toArray(new CompletableFuture[pageContentFutures.size()]));
        anyOfFuture.thenAccept(System.out::println);
    }
    private static void CombineAllFutures() throws ExecutionException, InterruptedException {
        AsyncService asyncService = new AsyncService();

        List<String> pageLinks = Arrays.asList("https://namibank.i8xiaoshi.com/community/home");

        List<CompletableFuture<String>> pageContentFutures = pageLinks.stream()
                .map(pageLink -> asyncService.downLoadPage(pageLink))
                .collect(Collectors.toList());

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(pageContentFutures.toArray(new CompletableFuture[pageLinks.size()]));

        // When all the Futures are completed, call `future.join()` to get their results and collect the results in a list -
        // The join() method is similar to get().
        // The only difference is that it throws an unchecked exception if the underlying CompletableFuture completes exceptionally.

        CompletableFuture<List<String>> allPageContents = allFutures.thenApply((v) -> pageContentFutures.stream()
                .map(pageContentFuture -> pageContentFuture.join())
                .collect(Collectors.toList()));

        CompletableFuture<Long> countFuture = allPageContents.thenApply(pageContents -> pageContents.stream()
                .filter(pageContent -> pageContent.contains("用户"))
                .count());

        System.out.println(countFuture.get());


    }

    private static void CombineIndependentFutures() throws ExecutionException, InterruptedException {
        AsyncService async = new AsyncService();
        async.height("hello")
                .thenCombine(async.width("world"), (a, b) -> a * b)
                .thenAccept(System.out::println);
    }

    private static void ComposeDependentFutures() throws ExecutionException, InterruptedException {
        AsyncService async = new AsyncService();
        CompletableFuture<String> name = async.getPersonId("hello").thenCompose(id -> async.getPersonNameById(id));
        System.out.println(name.get());
    }


    private static void CompletableFuture() {
        for (int i = 5; i > 0; i--) {
            int finalI = i;
            CompletableFuture.supplyAsync(() -> {
                try {
                    long startTime = System.currentTimeMillis();
                    TimeUnit.SECONDS.sleep(finalI);
                    System.out.format("Task %d,finished after %d \n", finalI, (System.currentTimeMillis() - startTime) / 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return finalI;
            }, Executors.newCachedThreadPool())
                    .thenApply(result -> "task result " + result)
                    //.thenRun(() -> System.out.println());
                    .thenAccept(System.out::println);
        }
    }

    private static void ExecutorCompletionService() throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newCachedThreadPool();
        ExecutorCompletionService<Integer> completionService = new ExecutorCompletionService<>(executor);

        for (int i = 0; i < 5; i++) {
            final int finalI = i;
            Future<Integer> future = completionService.submit(() -> {
                TimeUnit.SECONDS.sleep(finalI);
                System.out.println("pretend busy..." + finalI);
                return finalI;
            });
        }

        for (int i = 0; i < 5; i++) {
            System.out.println(completionService.take().get());
        }
    }

    private static void CompletionFuture() throws InterruptedException, ExecutionException {
        List<Future<Integer>> list = new ArrayList<>();
        ExecutorService executor = Executors.newCachedThreadPool();
        CompletionFuture<Integer> completionService = new CompletionFuture<>(executor);

        long startTime = System.currentTimeMillis();
        for (int i = 5; i > 0; i--) {
            int finalI = i;
            Future<Integer> future = completionService.submit(() -> {
                TimeUnit.SECONDS.sleep(finalI);
                System.out.println("pretend busy..." + finalI);
                return finalI;
            });
            list.add(future);
        }


        for (Future<Integer> future : list) {
            //System.out.println(future.get());
            System.out.println(completionService.getBlockingQueue().take().get());
        }


        System.out.format("Done after %d", System.currentTimeMillis() - startTime);
        executor.shutdown();
    }
}

class CompletionFuture<V> {

    private BlockingQueue<Future<V>> blockingQueue = new LinkedBlockingQueue<>();
    private Executor executor;

    CompletionFuture(Executor executor) {
        this.executor = executor;
    }

    public Future<V> submit(Callable<V> callable) {
        RunnableFuture<V> futureTask = new FutureTask<>(callable);
        executor.execute(new QueueingFuture(futureTask));
        return futureTask;
    }

    private class QueueingFuture extends FutureTask<Void> {

        private final Future<V> task;

        QueueingFuture(RunnableFuture<V> task) {
            super(task, null);
            this.task = task;
        }

        protected void done() {
            blockingQueue.add(task);
        }

    }

    public BlockingQueue<Future<V>> getBlockingQueue() {
        return blockingQueue;
    }

    public void setBlockingQueue(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }
}

class AsyncService {

    private ExecutorService executorService = Executors.newCachedThreadPool();

    public CompletableFuture<Integer> getPersonId(String name) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("getPersonId pretend busy....");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        }, executorService);
    }

    public CompletableFuture<String> getPersonNameById(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("getPersonNameById pretend busy...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Mark";
        }, executorService);
    }

    public CompletableFuture<Double> width(String name) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 12.4;
        }, executorService);
    }

    public CompletableFuture<Double> height(String name) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 20.3;
        }, executorService);
    }

    public CompletableFuture<String> downLoadPage(String url) {
        return CompletableFuture.supplyAsync(() -> downLoadPageUtil(url), executorService);
    }

    public String downLoadPageUtil(String url) {
        // 定义一个字符串用来存储网页内容
        String result = "";
        // 定义一个缓冲字符输入流
        BufferedReader in = null;
        try {
            // 将string转成url对象
            URL realUrl = new URL(url);
            // 初始化一个链接到那个url的连接
            URLConnection connection = realUrl.openConnection();
            // 开始实际的连接
            connection.connect();
            // 初始化 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            // 用来临时存储抓取到的每一行的数据
            String line;
            while ((line = in.readLine()) != null) {
                // 遍历抓取到的每一行并将其存储到result里面
                result += line + "\n";
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        } // 使用finally来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

}
