package com.example.cainiaoshop.bean;


/**
 * Created by YNUpanpan on 20/11/27.
 */
public class Category extends BaseBean {


    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public Category(long id ,String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}
