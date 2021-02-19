package com.example.mvcp.restclient.models.wishlist;

import java.util.HashMap;
import java.util.Map;

public class WishlistPush {

    private Integer wid = null;
    private Integer uid;
    private Integer pid;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Integer getWid() {
        return wid;
    }

    public void setWid(Integer wid) {
        this.wid = wid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}