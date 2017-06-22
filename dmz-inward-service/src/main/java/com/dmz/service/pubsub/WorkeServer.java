package com.dmz.service.pubsub;

import com.alibaba.fastjson.JSON;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNoNodeException;
import org.apache.zookeeper.CreateMode;

/**
 * @author dmz
 * @date 2017/3/21
 */
public class WorkeServer {

    private ServerData serverData;
    private ServerConfig serverConfig;
    private String configPath;
    private String serverPath;
    private ZkClient zkClient;

    private IZkDataListener zkDataListener;

    public WorkeServer() {

    }

    public WorkeServer(ServerConfig initConfig, ServerData serverData, String configPath, String serverPath, ZkClient zkClient) {
        this.serverConfig = initConfig;
        this.serverData = serverData;
        this.configPath = configPath;
        this.serverPath = serverPath;
        this.zkClient = zkClient;
        this.zkDataListener = new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                String temData = new String((byte[]) data);
                ServerConfig sc = JSON.parseObject(temData, ServerConfig.class);
                updateConfig(sc);
            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {

            }
        };
    }

    public void start() {
        System.out.println("Work server "+serverData.getName()+" start...");
        initRunning();
    }

    private void initRunning() {
        registMe();
        zkClient.subscribeDataChanges(configPath, zkDataListener);
    }

    private void registMe() {

        String epNode = serverPath.concat("/").concat(serverData.getAddress());
        try {
            zkClient.create(epNode, JSON.toJSONString(serverData).getBytes(), CreateMode.EPHEMERAL);
        } catch (ZkNoNodeException e) {
            String parentPath = epNode.substring(0, epNode.lastIndexOf("/"));
            zkClient.createPersistent(parentPath, true);
            registMe();
        } catch (Exception e) {
            //ignore
        }

    }

    private void updateConfig(ServerConfig serverConfig) {
        System.out.println("Update server config...");
        this.serverConfig = serverConfig;
        System.out.println(this.serverConfig.toString());
    }

    public void stop() {
        System.out.println("Word server stop...");
        zkClient.unsubscribeDataChanges(configPath, zkDataListener);
        zkClient.close();
    }

}
