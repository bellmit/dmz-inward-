package com.dmz.service.distributeid;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNoNodeException;
import org.I0Itec.zkclient.serialize.BytesPushThroughSerializer;
import org.springframework.beans.factory.InitializingBean;

import java.util.concurrent.*;

/**
 * @author dmz
 * @date 2017/3/23
 */
public abstract class IdMaker implements InitializingBean {

    private BlockingQueue<String> seqs = new ArrayBlockingQueue(100);
    private String seqPath = "/seq";
    private String preFix = "id-";

    private String connectAddress;
    private Integer timeout = 500000;
    private Integer sessionOut = 500000;
    private ZkClient zkClient;
    private ExecutorService executor = Executors.newFixedThreadPool(1);

    public IdMaker() {

    }

    public IdMaker(String connectAddress) {
        this.connectAddress = connectAddress;
        initIdMaker();
    }

    public String getSeq() {

        try {
            String seq = seqs.poll(2, TimeUnit.SECONDS);
            if (seq != null) {
                return generateId(seq);
            } else {
                return generateId(seq);
            }
        } catch (InterruptedException e) {
            // ignore
        }
        return null;
    }

    public abstract String generateId(String seq);

    private void fillingSeqs() {

        while (true) {
            if (seqs.isEmpty()) {
                for (int i = 0; i < 1000; ++i) {
                    this.seqs.offer(getZkSeq());
                }
            }
        }
    }

    private String getZkSeq() {

        try {

            String createNode = seqPath.concat("/").concat(preFix);
            // Ephemeral For Test.
            return zkClient.createEphemeralSequential(createNode, null);

        } catch (ZkNoNodeException e) {
            zkClient.createPersistent(seqPath, true);
            return getZkSeq();
        } catch (Exception e) {
            // ignore
        }
        return null;
    }

    public ZkClient getZkClient() {
        return zkClient;
    }

    public void setZkClient(ZkClient zkClient) {
        this.zkClient = zkClient;
    }

    private void initIdMaker() {
        zkClient = new ZkClient(connectAddress, timeout, sessionOut, new BytesPushThroughSerializer());
        executor.execute(() -> {
            fillingSeqs();
        });
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        initIdMaker();
    }

    public void setSessionOut(Integer sessionOut) {
        this.sessionOut = sessionOut;
    }

    public void setTimout(Integer timeout) {
        this.timeout = timeout;
    }

    public void setConnectAddress(String connectAddress) {
        this.connectAddress = connectAddress;
    }
}
