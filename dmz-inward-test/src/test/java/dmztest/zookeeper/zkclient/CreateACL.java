package dmztest.zookeeper.zkclient;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dmz
 * @date 2017/3/18
 */
public class CreateACL {
    public static void main(String[] args) throws NoSuchAlgorithmException, InterruptedException {
        ZkClient zkClient = new ZkClient("192.168.23.113:2181", 5000, 1000, new SerializableSerializer());

        List<ACL> aclList = new ArrayList<>();
        ACL ipAcl = new ACL(ZooDefs.Perms.READ, new Id("ip", "192.168.23.113"));
        ACL digestAcl = new ACL(ZooDefs.Perms.READ | ZooDefs.Perms.WRITE, new Id("digest", DigestAuthenticationProvider.generateDigest("dmz:0307")));
        aclList.add(ipAcl);
        aclList.add(digestAcl);

        zkClient.create("/zkClientWithACL", 123, aclList, CreateMode.PERSISTENT);
        boolean isExists = zkClient.exists("/zkClientWithACL");
        System.out.println(isExists);
        Thread.sleep(Integer.MAX_VALUE);
    }
}
