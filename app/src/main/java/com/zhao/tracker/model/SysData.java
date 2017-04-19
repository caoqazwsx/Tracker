package com.zhao.tracker.model;

import java.io.Serializable;

/**
 * Created by zhao on 2017/4/19.
 */

public class SysData implements Serializable{
    private String id;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
