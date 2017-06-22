package com.dmz.service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dmz
 * @date 2016/12/29
 */
public abstract class LoadBalance<T> {

    protected List<String> candidates = new ArrayList<>();

    public abstract T elect();

    public abstract T electWithWeight();
}
