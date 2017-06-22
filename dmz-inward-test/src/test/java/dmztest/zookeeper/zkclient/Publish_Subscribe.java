package dmztest.zookeeper.zkclient;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.BytesPushThroughSerializer;

import java.util.List;

/**
 * @author dmz
 * @date 2017/3/18
 */
public class Publish_Subscribe {
    private static class ZKListernData implements IZkDataListener {

        @Override
        public void handleDataChange(String dataPath, Object data) throws Exception {
            System.out.println(dataPath);
            System.out.println(new String((byte[]) data));
        }

        @Override
        public void handleDataDeleted(String dataPath) throws Exception {
            System.out.println(dataPath);
        }
    }
    private static class ZKListernChinld implements IZkChildListener {

        @Override
        public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
            System.out.println(parentPath);
            for (String child : currentChilds) {
                System.out.println(child);
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        ZkClient zkClient = new ZkClient("192.168.23.113:2181,192.168.23.114:2181", 5000, 5000,
                new BytesPushThroughSerializer());
        zkClient.subscribeChildChanges("/subscribe", new ZKListernChinld());
        // 子节点的创建删除，本身节点的创建删除都会监听

        zkClient.subscribeDataChanges("/subscribe", new ZKListernData());
        // delete node self OR change node data

        Thread.sleep(Integer.MAX_VALUE);
    }
}
