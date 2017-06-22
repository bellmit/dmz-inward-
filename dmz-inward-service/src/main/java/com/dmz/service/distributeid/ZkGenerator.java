package com.dmz.service.distributeid;

/**
 * @author dmz
 * @date 2017/3/23
 */
public class ZkGenerator extends IdMaker {

    public ZkGenerator(String address) {
        super(address);
    }

    @Override
    public String generateId(String seq) {
        System.out.println("SEQ#" + seq);
        return null;
    }
}
