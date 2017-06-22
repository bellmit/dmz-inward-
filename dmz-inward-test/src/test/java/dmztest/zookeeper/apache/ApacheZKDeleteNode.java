package dmztest.zookeeper.apache;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.List;

/**
 * @author dmz
 * @date 2017/3/15
 */
public class ApacheZKDeleteNode implements Watcher {

    private static ZooKeeper zooKeeper;

    public static void main(String[] args) throws IOException, InterruptedException {
        zooKeeper = new ZooKeeper("192.168.23.113:2181", 1000000, new ApacheZKDeleteNode());
        Thread.sleep(Integer.MAX_VALUE);
    }

    private void DeleteNodeAsyn() {
        zooKeeper.delete("/ym", -1, (rc, path, ctx) -> {
            System.out.println(path);
        }, "CTX");
    }
    private void DeleteNodeSyn() {
        try {
            zooKeeper.delete("/dmz/dmz_2", -1);  //-1 means that matchs any version
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    private void DeleteNodeAll(String path) {
        try {
            List<String> children = zooKeeper.getChildren(path, false);
            if (children.size() > 0) {
                for (String child : children) {
                    DeleteNodeAll(path + "/" + child);
                }
            }
             zooKeeper.delete(path, -1);
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
//                DeleteNodeSyn();
//                DeleteNodeAll("/dmz");
                DeleteNodeAsyn();
            }
        }
    }
}
