package com.dmz.service.masterslave;

import java.io.Serializable;

/**
 * @author dmz
 * @date 2017/3/20
 */
public class RunningData implements Serializable {
    private static final long serialVersionUID = 965800215650650205L;
    private Long id;
    private String name;

    public RunningData() {

    }

    public RunningData(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
