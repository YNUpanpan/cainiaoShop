package com.example.cainiaoshop.msg;

/**
 * Created by YNUpanpan on 20/12/15.
 */


public class CreateOrderRespMsg extends BaseRespMsg {



    private OrderRespMsg data;

    public OrderRespMsg getData() {
        return data;
    }

    public void setData(OrderRespMsg data) {
        this.data = data;
    }



}


