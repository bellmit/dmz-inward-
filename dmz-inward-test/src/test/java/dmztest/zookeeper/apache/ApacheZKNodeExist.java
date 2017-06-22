package dmztest.zookeeper.apache;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * @author dmz
 * @date 2017/3/15
 */
public class ApacheZKNodeExist implements Watcher {

    private static ZooKeeper zooKeeper;

    public static void main(String[] args) throws IOException, InterruptedException {
        zooKeeper = new ZooKeeper("192.168.23.113:2181", 5000, new ApacheZKNodeExist());
        Thread.sleep(Integer.MAX_VALUE);
    }

    private void isNodeExistAsyn() {
        zooKeeper.exists("/dmz", true, (rc, path, ctx, stat) -> {
            System.out.println(stat);
        }, "CTX");

    }
    private void isNodeExsitSyn() {
        try {
            Stat isExist = zooKeeper.exists("/dmz", true);
            System.out.println(isExist);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void process(WatchedEvent event) {
        if (event.getState() == Event.KeeperState.SyncConnected) {
            if (event.getType() == Event.EventType.None && event.getPath() == null) {
                isNodeExistAsyn();
            } else if (event.getType() == Event.EventType.NodeDeleted) {
                System.out.println("NodeDeleted");
                isNodeExsitSyn();
            } else if (event.getType() == Event.EventType.NodeChildrenChanged) {
                System.out.println("NodeChildrenChanged");
                isNodeExsitSyn();
            } else if (event.getType() == Event.EventType.NodeCreated) {
                System.out.println("NodeCreated");
                isNodeExsitSyn();
            } else if (event.getType() == Event.EventType.NodeDataChanged) {
                System.out.println("NOdeDataChanged");
                isNodeExsitSyn();
            }
        }
    }
}
