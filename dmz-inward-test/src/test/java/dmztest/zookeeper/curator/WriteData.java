package dmztest.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.AuthInfo;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryUntilElapsed;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dmz
 * @date 2017/3/18
 */
public class WriteData {
    public static void main(String[] args) throws Exception {
        System.out.println(DigestAuthenticationProvider.generateDigest("dmz:0307"));
        RetryPolicy retryPolicy = new RetryUntilElapsed(5000, 1000);
        AuthInfo authInfo = new AuthInfo("digest", "dmz:0307".getBytes());
        List<AuthInfo> authInfoList = new ArrayList<>();
        authInfoList.add(authInfo);
        CuratorFramework curator = CuratorFrameworkFactory.builder()
                .connectString("192.168.23.113:2181")
                .sessionTimeoutMs(5000)
                .connectionTimeoutMs(1000)
                .authorization("digest", "dmz:0307".getBytes())
                .authorization(authInfoList)
                .retryPolicy(retryPolicy)
                .build();

        curator.start();
        Stat stat = new Stat();
        curator.getData().storingStatIn(stat).forPath("/dmz");
        curator.setData().withVersion(stat.getVersion()).forPath("/dmz", "2222".getBytes());
        System.out.println(stat);
    }
}
