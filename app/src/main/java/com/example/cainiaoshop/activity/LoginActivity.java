package com.example.cainiaoshop.activity;

import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.example.cainiaoshop.CniaoApplication;
import com.example.cainiaoshop.Contants;
import com.example.cainiaoshop.R;
import com.example.cainiaoshop.bean.User;
import com.example.cainiaoshop.http.OkHttpHelper;
import com.example.cainiaoshop.http.SpotsCallBack;
import com.example.cainiaoshop.msg.LoginRespMsg;
import com.example.cainiaoshop.utils.DESUtil;
import com.example.cainiaoshop.utils.ToastUtils;
import com.example.cainiaoshop.widget.CNiaoToolBar;
import com.example.cainiaoshop.widget.ClearEditText;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;


import java.util.HashMap;
import java.util.Map;

import okhttp3.Response;

/**
 * Created by YNUpanpan on 20/12/15.
 */

public class LoginActivity extends AppCompatActivity {


    @ViewInject(R.id.toolbar)
    private CNiaoToolBar mToolBar;
    @ViewInject(R.id.etxt_phone)
    private ClearEditText mEtxtPhone;
    @ViewInject(R.id.etxt_pwd)
    private ClearEditText mEtxtPwd;



    @ViewInject(R.id.txt_toReg)
    private TextView txt_toReg;


    private OkHttpHelper okHttpHelper = OkHttpHelper.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ViewUtils.inject(this);

        initToolBar();
    }


    private void initToolBar(){

        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }


    @OnClick(R.id.txt_toReg)
    public void reg(View view){
        Intent intent=new Intent(LoginActivity.this,RegActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.btn_login)
    public void login(View view){


        String phone = mEtxtPhone.getText().toString().trim();
        if(TextUtils.isEmpty(phone)){
            ToastUtils.show(this, "请输入手机号码");
            return;
        }

        String pwd = mEtxtPwd.getText().toString().trim();
        if(TextUtils.isEmpty(pwd)){
            ToastUtils.show(this,"请输入密码");
            return;
        }


        Map<String,Object> params = new HashMap<>(2);
        params.put("phone",phone);
        params.put("password", DESUtil.encode(Contants.DES_KEY,pwd));

        okHttpHelper.post(Contants.API.LOGIN, params, new SpotsCallBack<LoginRespMsg<User>>(this) {


            @Override
            public void onSuccess(Response response, LoginRespMsg<User> userLoginRespMsg) {


               CniaoApplication application =  CniaoApplication.getInstance();
                application.putUser(userLoginRespMsg.getData(), userLoginRespMsg.getToken());

                if(application.getIntent() == null){
                    setResult(RESULT_OK);
                    finish();
                }else{

                    application.jumpToTargetActivity(LoginActivity.this);
                    finish();

                }



            }

            @Override
            public void onError(Response response, int code, Exception e) {
            }
        });
    }
}
