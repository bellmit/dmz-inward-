package com.dmz.service.masterslave;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

import java.io.IOException;

/**
 * @author dmz
 * @date 2017/3/20
 */
public class LoadWorkServer {

    private static int CLIENT_NUM = 10;
    private static String CONNECT_STRING = "192.168.23.113:2181";

    public static void main(String[] args) {
        WorkServer[] workServers = new WorkServer[CLIENT_NUM];
        ZkClient[] zkClients = new ZkClient[CLIENT_NUM];
        for (int i = 0; i < CLIENT_NUM; i++) {

            ZkClient zkClient = new ZkClient(CONNECT_STRING, 5000, 5000, new SerializableSerializer());
            RunningData runningData = new RunningData();
            runningData.setName("Client#" + i);
            runningData.setId(Long.valueOf(i));

            WorkServer workserver = new WorkServer(runningData, zkClient);
            try {
                workserver.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
            workServers[i] = workserver;
            zkClients[i] = zkClient;

        }

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Work server shutting down...");
            for (int i = 0; i < CLIENT_NUM; i++) {
                try {

                    workServers[i].stop();
                    zkClients[i].close();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

    }
}
