package com.example.cainiaoshop.http;

import android.content.Context;

import com.example.cainiaoshop.R;

import okhttp3.Request;
import okhttp3.Response;


import dmax.dialog.SpotsDialog;

/**
 * Created by YNUpanpan on 20/12/5.
 */

public abstract class SpotsCallBack<T> extends SimpleCallback<T> {


    private  Context mContext;

    private SpotsDialog mDialog;

    public SpotsCallBack(Context context){


        super(context);

        mContext=context;
       // mDialog = (SpotsDialog) new SpotsDialog.Builder().setContext(context).setMessage("?").setCancelable(false).build();
        initSpotsDialog();
    }

    void initSpotsDialog(){
//        mDialog = new SpotsDialog(mContext,"?",R.style.AppTheme,false,null);
        //mDialog = new SpotsDialog(mContext,"拼命加载中...");
//        mDialog = new SpotsDialog(mContext,
//                "?",
//                R.style.AppTheme,
//                false,null);
         mDialog = (SpotsDialog) new SpotsDialog.Builder().setContext(mContext).setMessage("拼命加载中...").setCancelable(false).build();

    }

    public  void showDialog(){
        mDialog.show();
    }

    public  void dismissDialog(){
        mDialog.dismiss();
    }


    public void setLoadMessage(int resId){
        mDialog.setMessage(mContext.getString(resId));
    }


    @Override
    public void onFailure(Request request, Exception e) {
        dismissDialog();
    }

    @Override
    public void onBeforeRequest(Request request) {

        showDialog();
    }

    @Override
    public void onResponse(Response response) {
        dismissDialog();
    }
}
