package dmztest.zookeeper.apache;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dmz
 * @date 2017/3/15
 */
public class ApacheZKACL implements Watcher {
    private static ZooKeeper zooKeeper;

    public static void main(String[] args) throws IOException, InterruptedException, NoSuchAlgorithmException {
        System.out.println(DigestAuthenticationProvider.generateDigest("dmz:0307"));
        zooKeeper = new ZooKeeper("192.168.23.113:2181", 5000, new ApacheZKACL());
        Thread.sleep(Integer.MAX_VALUE);
    }

    private void CreateNodeWithACL() {
        try {
            ACL ipAcl = new ACL(ZooDefs.Perms.READ, new Id("ip", "192.168.23.113"));
            ACL digestAcl = new ACL(ZooDefs.Perms.READ | ZooDefs.Perms.WRITE, new Id("digest",DigestAuthenticationProvider.generateDigest("dmz:0307")));
            List<ACL> acls = new ArrayList();
            acls.add(ipAcl);
            acls.add(digestAcl);
//            zooKeeper.addAuthInfo("digest","dmz".getBytes());
            String path_ = zooKeeper.create("/ACL", "acl".getBytes(), acls, CreateMode.PERSISTENT);
            System.out.println(path_);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent event) {
        if (event.getState() == Event.KeeperState.SyncConnected) {
            if (event.getType() == Event.EventType.None && event.getPath() == null) {
                CreateNodeWithACL();
            }
        }
    }
}
