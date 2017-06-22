package com.dmz.service.pubsub;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.BytesPushThroughSerializer;

import java.io.IOException;

/**
 * @author dmz
 * @date 2017/3/21
 */
public class SubPubLoaderServer {
    private static String connectString = "192.168.23.113:2181";
    private static String serverPath = "/serverT";
    private static String configPath = "/configT";
    private static String commandPath = "/commandT";
    private static WorkeServer[] workeServers = new WorkeServer[5];

    public static void main(String[] args) throws IOException {
        ServerConfig serverConfig = new ServerConfig();
        serverConfig.setName("root");
        serverConfig.setUrl("mysql-url");
        serverConfig.setPwd("0307");
        ZkClient zkClient = new ZkClient(connectString, 500000, 5000, new BytesPushThroughSerializer());

        ManageServer manageServer = new ManageServer(serverPath, configPath, commandPath, zkClient, serverConfig);

        manageServer.start();
        for (int i = 0; i < 5; i++) {
            ZkClient zk = new ZkClient(connectString, 500000, 5000, new BytesPushThroughSerializer());
            ServerData serverData = new ServerData();
            serverData.setName("server_" + i);
            serverData.setIp(i);
            serverData.setAddress("192.168.23." + i);
            WorkeServer workeServer = new WorkeServer(serverConfig, serverData, configPath, serverPath, zk);
            workeServer.start();
            workeServers[i] = workeServer;
        }
        System.in.read();

        for (int i = 0; i < 5; i++) {
            workeServers[i].stop();
        }

    }
}
