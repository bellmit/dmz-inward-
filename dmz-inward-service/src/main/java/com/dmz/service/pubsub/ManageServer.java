package com.dmz.service.pubsub;

import com.alibaba.fastjson.JSON;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNoNodeException;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dmz
 * @date 2017/3/21
 */
public class ManageServer {

    private Map<String, ServerData> serverDataList = new HashMap<>();
    private ServerConfig serverConfig;
    private String serverPath;
    private String configPath;
    private String commandPath;
    private ZkClient zkClient;
    private IZkChildListener serverListener;
    private IZkDataListener commandListener;


    public ManageServer() {

        serverDataList = new HashMap<>();
    }

    public ManageServer(String serverPath, String configPath, String commandPath, ZkClient zkClient, ServerConfig serverConfig) {
        this.serverConfig = serverConfig;
        this.configPath = configPath;
        this.commandPath = commandPath;
        this.zkClient = zkClient;
        this.serverPath = serverPath;
        this.serverListener = (parentPath, currentChilds) -> {
            updateServerList(parentPath, currentChilds);
        };
        this.commandListener = new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                String cmd = new String((byte[]) data);
                System.out.println("CMD:" + cmd);
                exeCmd(cmd);
            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
            }
        };
        serverDataList = new HashMap<>();
    }

    public void start() {
        System.out.println("Manage server start...");
        zkClient.subscribeChildChanges(serverPath, serverListener);
        zkClient.subscribeDataChanges(commandPath, commandListener);
    }

    public void stop() {
        System.out.println("Manage server stop...");
        zkClient.unsubscribeDataChanges(commandPath, commandListener);
        zkClient.unsubscribeChildChanges(serverPath, serverListener);
    }

    private void exeList() {
        System.out.println("show server datas.");
        for (ServerData serverData : serverDataList.values()) {
            System.out.println(serverData.toString());
        }
    }

    private void exeCreate() {
        try {
            zkClient.createPersistent(configPath, JSON.toJSONString(serverConfig).getBytes());
        } catch (ZkNodeExistsException e) {
            zkClient.writeData(configPath, JSON.toJSONString(serverConfig).getBytes());
        } catch (ZkNoNodeException e) {
            zkClient.createPersistent(configPath, true);
            exeCreate();
        }
    }

    private void exeModify() {
        serverConfig.setName(serverConfig.getName() + "_Modified");
        try {
            zkClient.writeData(configPath, JSON.toJSONString(serverConfig).getBytes());
        } catch (ZkNoNodeException e) {
            exeCreate();
        }
    }

    private void exeCmd(final String cmd) {
        switch (cmd) {
            case "list":
                exeList();
                break;
            case "create":
                exeCreate();
                break;
            case "modify":
                exeModify();
                break;
            default:
                System.out.println("Error Command!");
                break;
        }
    }

    private void updateServerList(String parentPath, List<String> currentChilds) {
        System.out.println("Update serverList...");
        for (String child : currentChilds) {
            try {
                if (!serverDataList.containsKey(child)) {
                    String serverDataPath = parentPath.concat("/").concat(child);
                    Object serverDataB = zkClient.readData(serverDataPath);
                    ServerData serverData = JSON.parseObject(new String((byte[]) serverDataB), ServerData.class);
                    serverDataList.put(child, serverData);
                }
            } catch (ZkNoNodeException e) {
                continue;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        exeList();

    }

}
