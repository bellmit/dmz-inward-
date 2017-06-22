package dmztest.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author dmz
 * @date 2017/2/15
 */
public class InvokeAllTask {
    private static class BlockTest implements Callable<String> {

        private boolean block;

        public BlockTest(boolean block) {
            this.block = block;
        }

        @Override
        public String call() throws Exception {

            if (block) {
                Thread.sleep(10000);
            }
            return "Block? " + block;
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        BlockTest block1 = new BlockTest(false);
        BlockTest block2 = new BlockTest(true);
        BlockTest block3 = new BlockTest(false);
        BlockTest block4 = new BlockTest(false);
        BlockTest block5 = new BlockTest(false);
        BlockTest block6 = new BlockTest(false);

        // 使用invokeAll,invokeAll會阻塞, future.get()不會阻塞
//        List<Future<String>> futures = executor.invokeAll(Arrays.asList(block1, block2, block3, block4, block5, block6));
        List<Future<String>> futures = new ArrayList<>();
        Future<String> future1 = executor.submit(block1);
        Future<String> future2 = executor.submit(block2); //使用submit的時候，submit不會阻塞,future.get()會阻塞
        futures.add(future2);
        futures.add(future1);
        System.out.println("=========================");
        for (Future future : futures) {
            System.out.println(future.get());
        }
    }
}
