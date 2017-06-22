package dmztest.zookeeper.zkclient;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.List;

/**
 * @author dmz
 * @date 2017/3/16
 */
public class CreateSessionCRUD {
    public static void main(String[] args) throws InterruptedException {
        ZkClient zkClient = new ZkClient("192.168.23.113:2181", 1000, 1000, new SerializableSerializer());
        // ADD NODE
        String path_ = zkClient.create("/zkclient", "Hello ZK", CreateMode.PERSISTENT);
        String path_2 = zkClient.create("/zkclient/zkclient_1", null, CreateMode.PERSISTENT);
        System.out.println(path_ + "-" + path_2);

        boolean existed = zkClient.exists("/zkclient");
        System.out.println(existed);

        List<String> list = zkClient.getChildren("/zkclient/zkclient_1");
        for (String child : list) {
            System.out.println(child);
        }
        Stat stat = new Stat();
        String data = zkClient.readData("/zkclient", stat);
        System.out.println(data + "-" + stat);

//        boolean delete_ = zkClient.delete("/zkclient");
        boolean delete_ = zkClient.deleteRecursive("/zkclient");
        System.out.println(delete_);

    }
}
