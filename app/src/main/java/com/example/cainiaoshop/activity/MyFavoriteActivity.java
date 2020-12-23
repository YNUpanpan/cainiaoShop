package com.example.cainiaoshop.activity;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cainiaoshop.CniaoApplication;
import com.example.cainiaoshop.Contants;
import com.example.cainiaoshop.R;
import com.example.cainiaoshop.adapter.BaseAdapter;
import com.example.cainiaoshop.adapter.FavoriteAdatper;
import com.example.cainiaoshop.adapter.decoration.CardViewtemDecortion;
import com.example.cainiaoshop.bean.Favorites;
import com.example.cainiaoshop.http.OkHttpHelper;
import com.example.cainiaoshop.http.SpotsCallBack;
import com.example.cainiaoshop.widget.CNiaoToolBar;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Response;

/**
 * Created by YNUpanpan on 20/12/20.
 */

public class MyFavoriteActivity extends Activity {


    @ViewInject(R.id.toolbar)
    private CNiaoToolBar mToolbar;


    @ViewInject(R.id.recycler_view)
    private RecyclerView mRecyclerview;


    private FavoriteAdatper mAdapter;

    private OkHttpHelper okHttpHelper = OkHttpHelper.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favorite);
        ViewUtils.inject(this);

        initToolBar();
        getFavorites();
    }



    private void initToolBar(){

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }




    private void getFavorites(){

        Long userId = CniaoApplication.getInstance().getUser().getId();

        Map<String, Object> params = new HashMap<>();
        params.put("user_id",userId);


        okHttpHelper.get(Contants.API.FAVORITE_LIST, params, new SpotsCallBack<List<Favorites>>(this) {
            @Override
            public void onSuccess(Response response, List<Favorites> favorites) {
                showFavorites(favorites);
            }

            @Override
            public void onError(Response response, int code, Exception e) {

                LogUtils.d("code:"+code);
            }
        });
    }

    private void showFavorites(List<Favorites> favorites) {


        mAdapter = new FavoriteAdatper(this,favorites);
        mRecyclerview.setAdapter(mAdapter);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.addItemDecoration(new CardViewtemDecortion());

        mAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
    }


}
