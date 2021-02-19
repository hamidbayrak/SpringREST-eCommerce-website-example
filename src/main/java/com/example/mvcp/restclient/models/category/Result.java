
package com.example.mvcp.restclient.models.category;

import java.util.HashMap;
import java.util.Map;

public class Result {

    private Integer cid;
    private String categoryName;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
