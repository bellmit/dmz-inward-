package dmztest.zookeeper.apache;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * @author dmz
 * @date 2017/3/15
 */
class ApacheZKWatch implements Watcher {

    private static volatile boolean isConnected;

    @Override

    public void process(WatchedEvent watchedEvent) {

        if (!isConnected && watchedEvent.getState() == Event.KeeperState.SyncConnected) {
            System.out.println("I am connected.");
            isConnected = true;   // Avoid reconnecting for timeout.
        }
    }

}

public class ApacheZKCreateSession {

    private static ZooKeeper zookeeper = null;

    public static void main(String[] args) throws IOException, InterruptedException {
        zookeeper = new ZooKeeper("192.168.23.113:2181", 100, new ApacheZKWatch()); //非阻塞
        System.out.println("immediately:" + zookeeper.getState());
        Thread.sleep(Integer.MAX_VALUE);
    }
}
