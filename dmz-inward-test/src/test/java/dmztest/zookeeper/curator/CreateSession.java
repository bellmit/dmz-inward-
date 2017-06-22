package dmztest.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryUntilElapsed;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dmz
 * @date 2017/3/18
 */
public class CreateSession {
    public static void main(String[] args) throws Exception {
//        RetryPolicy retryPolicy = new RetryNTimes(3, 1000);
//        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
        RetryPolicy retryPolicy = new RetryUntilElapsed(5000, 1000);
//        CuratorFramework curator = CuratorFrameworkFactory.newClient("192.168.23.113:2181", 5999, 5000, retryPolicy);
//        curator.start();
        CuratorFramework curator = CuratorFrameworkFactory.builder()
                .connectString("192.168.23.113:2181")
                .sessionTimeoutMs(5000)
                .connectionTimeoutMs(1000)
                .retryPolicy(retryPolicy)
                .build();
        curator.start();

        // Create Node
        List<ACL> aclList = new ArrayList<>();
        ACL ipAcl = new ACL(ZooDefs.Perms.READ, new Id("ip", "192.168.23.113"));
        ACL digestAcl = new ACL(ZooDefs.Perms.READ | ZooDefs.Perms.WRITE, new Id("digest", DigestAuthenticationProvider.generateDigest("dmz:0307")));
        aclList.add(ipAcl);
        aclList.add(digestAcl);
        String path_ = curator.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .withACL(aclList)
                .forPath("/curatorNode", "321".getBytes());

        System.out.println(path_);
        // GetChildNode
        List<String> childNodes = curator.getChildren().forPath("/");
        for (String child : childNodes) {
            System.out.println(child);
        }
        // GetNodeData
        Stat stat = new Stat();
        byte[] nodeData = curator.getData().storingStatIn(stat).forPath("/curatorNode");
        System.out.println(stat + "|" + new String(nodeData));
        //Delete Node
        curator.delete().guaranteed().deletingChildrenIfNeeded().withVersion(-1).forPath("/curatorNode");
        Thread.sleep(Integer.MAX_VALUE);
    }
}
