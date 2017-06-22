package com.dmz.service.distributeid;

/**
 * @author dmz
 * @date 2017/3/23
 */
public class ZkGeneratorRunning {
    public static void main(String[] args) {
        ZkGenerator zkGenerator = new ZkGenerator("192.168.23.113:2181");
        while (true) {
            zkGenerator.getSeq();

        }
    }
}
