
package com.example.mvcp.restclient.models.cart;

import java.util.HashMap;
import java.util.Map;

public class CartRest {

    private Boolean status;
    private String message;
    private Result result;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
