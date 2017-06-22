package com.dmz.service.masterslave;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkInterruptedException;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author dmz
 * @date 2017/3/20
 */
public class WorkServer {

    private volatile boolean running;
    private RunningData serverData;
    private ZkClient zkClient;
    private RunningData masterData;
    private String masterPath = "/master";
    private IZkDataListener zkDataListener;
    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
    private int delayTime = 5;

    public WorkServer() {
    }

    public WorkServer(RunningData data, ZkClient zkClient) {
        this.serverData = data;
        this.zkClient = zkClient;
        zkDataListener = new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {

            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {

//                takeMaster(); // 不检测网络抖动
                if (masterData == null || masterData.getName().equals(serverData.getName())) {
                    takeMaster();
                } else {
                    executorService.schedule(() -> takeMaster(), delayTime, TimeUnit.SECONDS);
                }
            }
        };
    }

    public void start() throws Exception {
        if (running) {
            throw new Exception("Work server has started");
        }
        running = true;
        zkClient.subscribeDataChanges(masterPath, zkDataListener);
        takeMaster();
    }

    public void takeMaster() {
        if (!running) {
            return;
        }
        try {
            String pathM = zkClient.create(masterPath, serverData, CreateMode.EPHEMERAL);
            masterData = serverData;
            System.out.println("Master is " + masterData.getName());
            executorService.schedule(() -> {
                releaseMaster();
            }, delayTime, TimeUnit.SECONDS);
        } catch (ZkNodeExistsException e) {
            RunningData runningData = zkClient.readData(masterPath);
            if (runningData == null) {
                takeMaster();
            } else {
                masterData = runningData;
            }
        } catch (Exception e) {
            // ignore
        }
    }

    public void releaseMaster() {
        if (needRelease()) {
            zkClient.delete(masterPath);
        }
    }

    public boolean needRelease() {
        if (masterData != null) {
            try {
                RunningData masterDataRemote = zkClient.readData(masterPath);
                masterData = masterDataRemote;
                return serverData.getName().equals(masterData.getName());
            } catch (ZkNodeExistsException e) {
                return false;
            } catch (ZkInterruptedException e) {
                needRelease();
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    public void stop() throws Exception {
        if (!running) {
            throw new Exception("Work server has stoped");
        }
        running = false;
        zkClient.unsubscribeDataChanges(masterPath, zkDataListener);
        releaseMaster();
    }


}
