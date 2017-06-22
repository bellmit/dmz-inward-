package dmztest.zookeeper.apache;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * @author dmz
 * @date 2017/3/15
 */
public class ApacheZKGetData implements Watcher {

    private static ZooKeeper zooKeeper;
    private static Stat stat = new Stat();

    public static void main(String[] args) throws IOException, InterruptedException {
        zooKeeper = new ZooKeeper("192.168.23.113:2181", 5000, new ApacheZKGetData());
        Thread.sleep(Integer.MAX_VALUE);
    }

    private void GetNodeDataAsyn() {
        zooKeeper.addAuthInfo("digest", "dmz:0307".getBytes()); //ADD AUTH FOR /dmz NODE
        zooKeeper.getData("/dmz", true, (rc, path, ctx, data, stat1) -> {
            System.out.println(new String(data));
            System.out.println(stat1);
        }, "CTX");
    }

    private void GetNodeDataSyn() {
        try {
            System.out.println(new String(zooKeeper.getData("/dmz", true, stat)));
            System.out.println(stat);
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
//                GetNodeDataSyn();
                GetNodeDataAsyn();
            } else if (event.getType() == Event.EventType.NodeDataChanged) {
//                GetNodeDataSyn();
                GetNodeDataAsyn();
            }
        }
    }
}
