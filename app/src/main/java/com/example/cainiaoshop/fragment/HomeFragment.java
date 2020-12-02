package com.example.cainiaoshop.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cainiaoshop.R;
import com.example.cainiaoshop.adapter.DividerItemDecortion;
import com.example.cainiaoshop.adapter.HomeCategoryAdapter;
import com.example.cainiaoshop.bean.FBanner;
import com.example.cainiaoshop.bean.HomeCategory;
import com.example.cainiaoshop.widget.GlideImageLoader;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import com.youth.banner.listener.OnBannerListener;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by YNUpanpan on 20/11/25.
 */
public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    Handler handler;
    com.youth.banner.Banner Banner;//banner组件
    View view;
    RecyclerView mRecyclerView;
    HomeCategoryAdapter mAdatper;
    ArrayList<String> img;
    ArrayList<String> title;
    // 构建Runnable对象，在runnable中更新界面
    Runnable runnableBanner = new Runnable() {
        @Override
        public void run() {
            //更新界面
            Banner.start();
        }

    };
    private Gson mGson = new Gson();
    private List<FBanner> mFBanner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        handler = new Handler();//用来处理线程
        view = inflater.inflate(R.layout.fragment_home, container, false);


        initRecycleView();

        requestImages();

        return view;
    }

    private void requestImages() {
        //  String url ="http://112.124.22.238:8081/course_api/banner/query?type=1";
        String url = "http://112.124.22.238:8081/course_api/banner/query";
        OkHttpClient client = new OkHttpClient();

        FormBody body = new FormBody.Builder()
                .add("type", "1")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String json = response.body().string();
                    //Log.i("HomeFragment","json"+json);

                    Type type = new TypeToken<List<FBanner>>() {
                    }.getType();
                    mFBanner = mGson.fromJson(json, type);


                    initBanner();
                }
            }
        });


    }

    private void initRecycleView() {
        mRecyclerView = view.findViewById(R.id.recycleview);

        List<HomeCategory> datas = new ArrayList<>(15);


        HomeCategory category = new HomeCategory("热门活动", R.drawable.back4, R.drawable.back5, R.drawable.back6);
        datas.add(category);

        category = new HomeCategory("有利可图", R.drawable.back1, R.drawable.back2, R.drawable.back3);
        datas.add(category);


        category = new HomeCategory("品牌街", R.drawable.img_big_2, R.drawable.img_2_small1, R.drawable.img_2_small2);
        datas.add(category);

        category = new HomeCategory("金融街 包赚翻", R.drawable.img_big_1, R.drawable.img_3_small1, R.drawable.imag_3_small2);
        datas.add(category);

        category = new HomeCategory("超值购", R.drawable.img_big_0, R.drawable.img_0_small1, R.drawable.img_0_small2);
        datas.add(category);


        mAdatper = new HomeCategoryAdapter(datas);

        mRecyclerView.setAdapter(mAdatper);

        mRecyclerView.addItemDecoration(new DividerItemDecortion());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
    }

    private void initBanner() {

        img = new ArrayList<>();
        title = new ArrayList<>();
        for (FBanner fBanner : mFBanner) {
            img.add(fBanner.getImgUrl());
            title.add(fBanner.getName());
        }


        Banner = view.findViewById(R.id.banner);

        Banner.setImageLoader(new GlideImageLoader());   //设置图片加载器
        Banner.setImages(img);//设置图片源
        Banner.setBannerTitles(title);//设置标题源
        Banner.setDelayTime(2000);//设置轮播事件，单位毫秒
        Banner.setBannerAnimation(Transformer.ZoomOutSlide);//设置轮播动画，动画种类很多，有兴趣的去试试吧，我在这里用的是默认
//stack

/**
 *  轮播图的点击事件
 */
        Banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                // Toast.makeText(HomeFragment, "这是第" + position +"个效果", Toast.LENGTH_SHORT).show();
            }
        });
        Banner.setIndicatorGravity(BannerConfig.CENTER);//设置指示器的位置
        // Banner.start();//开始轮播，一定要调用此方法。通过调用runnableBanner来使用

        handler.post(runnableBanner);
    }
}


