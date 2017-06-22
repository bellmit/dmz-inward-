package dmztest.zookeeper.zkclient;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.apache.zookeeper.CreateMode;

/**
 * @author dmz
 * @date 2017/3/18
 */
public class WriteData {
    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient("192.168.23.113:2181", 1000, 1000, new SerializableSerializer());
        String zkPath = zkClient.create("/zkClient_", new User("dmz"), CreateMode.PERSISTENT);
        zkClient.writeData("/zkClient_", new User("ym"), -1);
        User user = zkClient.readData("/zkClient_");
        System.out.println(user.getName());
    }
}
