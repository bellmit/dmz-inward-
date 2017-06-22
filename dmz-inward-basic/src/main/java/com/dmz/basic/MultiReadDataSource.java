package com.dmz.basic;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author dmz
 * @date 2016/12/27
 */
public class MultiReadDataSource extends AbstractRoutingDataSource {

    private DataReadBalance dataReadBalance;

    @Override
    protected Object determineCurrentLookupKey() {
//        return null; 使用defaultTargetDataSource
        return dataReadBalance.getDataSourceKey();
    }

    public void setDataReadBalance(DataReadBalance dataReadBalance) {
        this.dataReadBalance = dataReadBalance;
    }
}
