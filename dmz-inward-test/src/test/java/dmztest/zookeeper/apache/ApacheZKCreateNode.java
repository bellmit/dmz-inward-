package dmztest.zookeeper.apache;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * @author dmz
 * @date 2017/3/15
 */

public class ApacheZKCreateNode implements Watcher {

    private static ZooKeeper zookeeper = null;

    public static void main(String[] args) throws IOException, InterruptedException {
        zookeeper = new ZooKeeper("192.168.23.113:2181", 5000, new ApacheZKCreateNode()); //非阻塞
        System.out.println("immediately:"+zookeeper.getState());
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (watchedEvent.getState()==Event.KeeperState.SyncConnected) {
            System.out.println("i am connected.");
            CreateNodeSyn();
            CreateNodeAsyn();
        }
    }
    private class IStringCallBack implements AsyncCallback.StringCallback{

        @Override
        public void processResult(int i, String s, Object o, String s1) {
            // i 異步調用返回碼 成功為0
            // s 需要創建的節點的完整路徑
            // o 異步調用上下文
            // s1 已創建節點的真實路徑，若順序節點則與s值不同
            System.out.println("Return Code:" + i);
            System.out.println("Create Node Name:"+s);
            System.out.println("Context:" + o.toString());
            System.out.println("Real Node Name:" + s1);

        }
    }
    private void CreateNodeAsyn() {
            zookeeper.create("/dmz_1", "ym_1".getBytes()
                    , ZooDefs.Ids.OPEN_ACL_UNSAFE
                    , CreateMode.PERSISTENT,new IStringCallBack(),"CreateNodeAsyn");
    }
    private void CreateNodeSyn() {
        try {
            String path_ = zookeeper.create("/dmz_2", "ym_2".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            System.out.println(path_);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
