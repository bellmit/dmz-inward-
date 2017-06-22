package dmztest.zookeeper.apache;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;

/**
 * @author dmz
 * @date 2017/3/15
 */
public class ApacheZKGetChildren implements Watcher {
    private static ZooKeeper zooKeeper;

    public static void main(String[] args) throws IOException, InterruptedException {
        zooKeeper = new ZooKeeper("192.168.23.113:2181", 5000, new ApacheZKGetChildren());
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void process(WatchedEvent event) {
        if (event.getState() == Event.KeeperState.SyncConnected) {
            System.out.println("I am connected.");
            if (event.getType() == Event.EventType.None && null == event.getPath()) {
//                GetChildrenAsyn();
                GetChildrenSyn(); //在首次連接時候執行，建立鏈接后衹執行一次

            } else if (event.getType() == Event.EventType.NodeChildrenChanged) {
                GetChildrenAsyn();
//                GetChildrenSyn();
            } else if (event.getType() == Event.EventType.NodeDataChanged) {
                System.out.println(event);
            } else if (event.getType() == Event.EventType.NodeDeleted) {
                System.out.println(event);
            }
        } else {
            System.out.println(event);
        }
    }

    private void GetChildrenAsyn() {
        zooKeeper.getChildren("/dmz", true, (rc, path, ctx, children, stat) -> {
            System.out.println("Return Code:" + rc);
            System.out.println("Node Path:"+path);
            System.out.println("Context:" + ctx.toString());
            System.out.println("Stat:" + stat);
            for (String child : children) {
                System.out.println(child);
            }
        }, "ctx");
    }
    private void GetChildrenSyn() {
        try {
            List<String> children = zooKeeper.getChildren("/dmz", true);
            //為true則表示watcher對節點的變化感興趣，ApacheZK watcher衹會使用一次
            for (String child : children) {
                System.out.println(child);
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
