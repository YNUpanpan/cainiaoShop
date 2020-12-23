package com.example.cainiaoshop.bean;


import java.util.List;

/**
 * Created by YNUpanpan on 20/12/21.
 */

public class ChargeRefundCollection {


    String object;
    String url;
    Boolean hasMore;
    List<?> data;

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }

    public String getURL() {
        return url;
    }

    public void setURL(String url) {
        this.url = url;
    }
}