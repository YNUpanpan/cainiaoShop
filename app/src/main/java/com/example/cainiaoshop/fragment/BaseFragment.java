package com.example.cainiaoshop.fragment;

import android.content.Intent;
import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cainiaoshop.CniaoApplication;
import com.example.cainiaoshop.activity.LoginActivity;
import com.example.cainiaoshop.bean.User;
import com.lidroid.xutils.ViewUtils;

/**
 * Created by YNUpanpan on 20/12/5.
 */


public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = createView(inflater,container,savedInstanceState);
        ViewUtils.inject(this, view);

        initToolBar();

        init();

        return view;

    }

    public void  initToolBar(){

    }


    public abstract View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    public abstract void init();

    public void startActivity(Intent intent,boolean isNeedLogin){


        if(isNeedLogin){

            User user = CniaoApplication.getInstance().getUser();
            if(user !=null){
                super.startActivity(intent);
            }
            else{

                CniaoApplication.getInstance().putIntent(intent);
                Intent loginIntent = new Intent(getActivity(), LoginActivity.class);
                super.startActivity(loginIntent);

            }

        }
        else{
            super.startActivity(intent);
        }

    }

}
