package com.example.cainiaoshop.bean;

import java.io.Serializable;

/**
 * Created by YNUpanpan on 20/11/27.
 */
public class BaseBean implements Serializable {


    protected   long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
