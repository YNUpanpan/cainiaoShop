
package com.example.cainiaoshop.msg;

import java.io.Serializable;

/**
 * Created by YNUpanpan on 20/12/15.
 */

public class BaseRespMsg implements Serializable {

    public final static int STATUS_SUCCESS=1;
    public final static int STATUS_ERROR=0;
    public final static String MSG_SUCCESS="success";

    protected  int status=STATUS_SUCCESS;
    protected  String message;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
