package com.example.cainiaoshop.activity;

import android.content.Context;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.appcompat.*;
import androidx.fragment.app.Fragment;

import com.example.cainiaoshop.R;
import com.example.cainiaoshop.bean.Tab;
import com.example.cainiaoshop.fragment.CartFragment;
import com.example.cainiaoshop.fragment.CategoryFragment;
import com.example.cainiaoshop.fragment.HomeFragment;
import com.example.cainiaoshop.fragment.HotFragment;
import com.example.cainiaoshop.fragment.MineFragment;
import com.example.cainiaoshop.widget.FragmentTabHost;
import com.mob.MobSDK;
import com.mob.OperationCallback;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

/**
 * Created by YNUpanpan on 20/11/25.
 */

public class MainActivity extends AppCompatActivity {



    private static final String TAG = "MainActivity";
    private LayoutInflater mInflater;

    private FragmentTabHost mTabhost;

   private CartFragment cartFragment;

    private List<Tab> mTabs = new ArrayList<>(5);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MobSDK.submitPolicyGrantResult(true, null);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        submitPrivacyGrantResult(true);
       // initToolBar();
        initTab();
       // sendCode(getApplicationContext());
    }

//    private void initToolBar() {
//
//        mToolbar = (CnToolbar) findViewById(R.id.toolbar);
//    }

    public void sendCode(Context context) {
        RegisterPage page = new RegisterPage();
        //如果使用我们的ui，没有申请模板编号的情况下需传null
        page.setTempCode(null);
        page.setRegisterCallback(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    // 处理成功的结果
                    HashMap<String,Object> phoneMap = (HashMap<String, Object>) data;
                    // 国家代码，如“86”
                    String country = (String) phoneMap.get("country");
                    // 手机号码，如“13800138000”
                    String phone = (String) phoneMap.get("phone");
                    // TODO 利用国家代码和手机号码进行后续的操作
                } else{
                    // TODO 处理错误的结果
                }
            }
        });
        page.show(context);
    }
    private void initTab() {


        Tab tab_home = new Tab(HomeFragment.class,R.string.home,R.drawable.selector_icon_home);
        Tab tab_hot = new Tab(HotFragment.class,R.string.hot,R.drawable.selector_icon_hot);
        Tab tab_category = new Tab(CategoryFragment.class,R.string.category,R.drawable.selector_icon_category);
        Tab tab_cart = new Tab(CartFragment.class,R.string.cart,R.drawable.selector_icon_cart);
        Tab tab_mine = new Tab(MineFragment.class,R.string.mine,R.drawable.selector_icon_mine);

        mTabs.add(tab_home);
        mTabs.add(tab_hot);
        mTabs.add(tab_category);
        mTabs.add(tab_cart);
        mTabs.add(tab_mine);



        mInflater = LayoutInflater.from(this);
        mTabhost = (FragmentTabHost) this.findViewById(android.R.id.tabhost);
        mTabhost.setup(this,getSupportFragmentManager(),R.id.realtabcontent);

        for (Tab tab : mTabs){

            TabHost.TabSpec tabSpec = mTabhost.newTabSpec(getString(tab.getTitle()));
            tabSpec.setIndicator(buildIndicator(tab));
            mTabhost.addTab(tabSpec,tab.getFragment(),null);

        }

        mTabhost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {

                if(tabId==getString(R.string.cart)){

                    refData();




                }
//                else{
//
//                    mToolbar.showSearchView();
//                    mToolbar.hideTitleView();
//                    mToolbar.getRightButton().setVisibility(View.GONE);
//
//                }
            }
        });

        mTabhost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        mTabhost.setCurrentTab(0);


    }


    private void refData(){

        if(cartFragment ==null) {

            Fragment fragment = getSupportFragmentManager().findFragmentByTag(getString(R.string.cart));

            if (fragment != null) {

                cartFragment = (CartFragment) fragment;

                cartFragment.refData();
                //cartFragment.changeToolbar();
            }
        }
        else
        {
            cartFragment.refData();
           // cartFragment.changeToolbar();
        }
    }


    private  View buildIndicator(Tab tab){


        View view =mInflater.inflate(R.layout.tab_indicator,null);
        ImageView img = (ImageView) view.findViewById(R.id.icon_tab);
        TextView text = (TextView) view.findViewById(R.id.txt_indicator);
        img.setBackgroundResource(tab.getIcon());
        text.setText(tab.getTitle());
        return  view;
    }
    private void submitPrivacyGrantResult(boolean granted) {
        MobSDK.submitPolicyGrantResult(granted, new OperationCallback<Void>() {
            @Override
            public void onComplete(Void data) {
                Log.d(TAG,"隐私协议授权结果提交：成功");
            }
            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "隐私协议授权结果提交：失败");
            }
        });
    }
}
