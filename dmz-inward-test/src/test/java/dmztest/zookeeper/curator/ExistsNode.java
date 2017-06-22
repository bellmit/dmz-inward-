package dmztest.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorEventType;
import org.apache.curator.retry.RetryUntilElapsed;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author dmz
 * @date 2017/3/18
 */
public class ExistsNode {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        RetryPolicy retryPolicy = new RetryUntilElapsed(5000, 1000);
        CuratorFramework curator = CuratorFrameworkFactory.builder()
                .connectString("192.168.23.113:2181")
                .sessionTimeoutMs(5000)
                .connectionTimeoutMs(1000)
                .retryPolicy(retryPolicy)
                .build();
        curator.start();
//        Stat isExists = curator.checkExists().forPath("/dmz");
        curator.checkExists().inBackground((curatorFramework, curatorEvent) -> {
            CuratorEventType eventType = curatorEvent.getType();
            int result = curatorEvent.getResultCode();// 0
            Object context = curatorEvent.getContext();
            curatorEvent.getPath();
            curatorEvent.getChildren();
            System.out.println(curatorEvent.getStat());
        },"Context",executor).forPath("/dmz");  //One asynchronize create a Thread in background
        Thread.sleep(Integer.MAX_VALUE);
    }
}
