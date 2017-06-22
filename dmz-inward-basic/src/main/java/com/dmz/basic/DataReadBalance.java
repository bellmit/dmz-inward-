package com.dmz.basic;

import com.dmz.service.LoadBalance;

/**
 * @author dmz
 * @date 2016/12/27
 */
public class DataReadBalance{

    public static ThreadLocal<String> dataSourceKey = new ThreadLocal<>();

    private  LoadBalance<String> loadBalance;

    public  String getDataSourceKey() {
        return dataSourceKey.get();
    }

    public  void setDataSourceKey() {
        dataSourceKey.set(loadBalance.electWithWeight());
    }

    public  void removeDataSourceKey() {
        dataSourceKey.remove();
    }

    public void setLoadBalance(LoadBalance<String> loadBalance) {
        this.loadBalance = loadBalance;
    }

}
