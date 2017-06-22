package dmztest.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.retry.RetryUntilElapsed;

/**
 * @author dmz
 * @date 2017/3/19
 */
public class Publish_Subscribe {
    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new RetryUntilElapsed(5000, 1000);
        CuratorFramework curator = CuratorFrameworkFactory.builder()
                .connectString("192.168.23.113:2181")
                .sessionTimeoutMs(5000)
                .connectionTimeoutMs(1000)
                .retryPolicy(retryPolicy)
                .build();
        curator.start();

        final NodeCache nodeCache = new NodeCache(curator, "/NewDmz"); // node self listener
        nodeCache.start();// start listener . Listener node self create , delete ,datachanged
        nodeCache.getListenable().addListener(() -> {
            byte[] data = nodeCache.getCurrentData().getData();
            if (data != null) {
                System.out.println(new String(data));
            }
        });

        final PathChildrenCache pathChildrenCache = new PathChildrenCache(curator, "/NewDmz", true);
        pathChildrenCache.start(); // start listener. Listener child node's create childNode,delete childNode,childNode data change.
        pathChildrenCache.getListenable().addListener((client, event) -> {
            switch (event.getType()) {
                case CHILD_ADDED:
                    System.out.println(event.getData());
                    break;
                case CHILD_UPDATED:
                    System.out.println(event.getData());
                    break;
                case CHILD_REMOVED:
                    System.out.println(event.getData());
                    break;
                default:
                    break;
            }
        });
        Thread.sleep(Integer.MAX_VALUE);

    }
}
