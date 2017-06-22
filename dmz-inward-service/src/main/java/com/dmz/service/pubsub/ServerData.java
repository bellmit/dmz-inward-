package com.dmz.service.pubsub;

/**
 * @author dmz
 * @date 2017/3/21
 */
public class ServerData {
    private Integer ip;
    private String address;
    private String name;

    public Integer getIp() {
        return ip;
    }

    public void setIp(Integer ip) {
        this.ip = ip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ServerData{" +
                "ip=" + ip +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
