package com.example.cainiaoshop.http;

import android.content.Context;
import android.content.Intent;

import com.example.cainiaoshop.CniaoApplication;
import com.example.cainiaoshop.activity.LoginActivity;
import com.example.cainiaoshop.R;
import com.example.cainiaoshop.utils.ToastUtils;

import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by YNUpanpan on 20/12/5.
 */
public abstract class SimpleCallback<T> extends BaseCallback<T> {

    protected Context mContext;

    public SimpleCallback(Context context){
        mContext = context;
    }

    @Override
    public void onBeforeRequest(Request request) {
    }

    @Override
    public void onFailure(Request request, Exception e) {
    }

    @Override
    public void onResponse(Response response) {
    }

    @Override
    public void onTokenError(Response response, int code) {
        ToastUtils.show(mContext, mContext.getString(R.string.token_error));
        Intent intent = new Intent();
        intent.setClass(mContext, LoginActivity.class);
        mContext.startActivity(intent);
        CniaoApplication.getInstance().clearUser();
    }

}
