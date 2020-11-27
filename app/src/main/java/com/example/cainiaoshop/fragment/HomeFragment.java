package com.example.cainiaoshop.fragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cainiaoshop.MainActivity;
import com.example.cainiaoshop.R;
import com.example.cainiaoshop.adapter.DividerItemDecortion;
import com.example.cainiaoshop.adapter.HomeCategoryAdapter;
import com.example.cainiaoshop.bean.HomeCategory;
import com.example.cainiaoshop.widget.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YNUpanpan on 20/11/25.
 */
public class HomeFragment extends Fragment {

    Banner banner;//banner组件
    List mlist;//图片资源
    List<String> mlist1;//轮播标题
    View view ;
    RecyclerView mRecyclerView;
    HomeCategoryAdapter mAdatper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_home,container,false);

        initBanner();

        initRecycleView();


        return  view;
    }

    private void initRecycleView() {
        mRecyclerView=view.findViewById(R.id.recycleview);

        List<HomeCategory> datas = new ArrayList<>(15);

        HomeCategory category = new HomeCategory("热门活动",R.drawable.img_big_1,R.drawable.img_1_small1,R.drawable.img_1_small2);
        datas.add(category);

        category = new HomeCategory("有利可图",R.drawable.img_big_4,R.drawable.img_4_small1,R.drawable.img_4_small2);
        datas.add(category);
        category = new HomeCategory("品牌街",R.drawable.img_big_2,R.drawable.img_2_small1,R.drawable.img_2_small2);
        datas.add(category);

        category = new HomeCategory("金融街 包赚翻",R.drawable.img_big_1,R.drawable.img_3_small1,R.drawable.imag_3_small2);
        datas.add(category);

        category = new HomeCategory("超值购",R.drawable.img_big_0,R.drawable.img_0_small1,R.drawable.img_0_small2);
        datas.add(category);


        mAdatper = new HomeCategoryAdapter(datas);

        mRecyclerView.setAdapter(mAdatper);

        mRecyclerView.addItemDecoration(new DividerItemDecortion());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
    }


    private void initBanner() {

        mlist = new ArrayList<>();
        mlist.add("http://pic0.iqiyipic.com/common/lego/20190504/5c7c889174894cd7aed96218320e1945.jpg");
        mlist.add("http://pic3.iqiyipic.com/common/lego/20190504/902898f2117c41ccaea5fa36eb4d0545.jpg");
        mlist.add("http://pic3.iqiyipic.com/common/lego/20190504/8245013abf2b44ce8736d7435d4567dc.jpg");
        mlist.add("http://pic2.iqiyipic.com/common/lego/20190501/9cdcc1a900a34c1497aeff9c5af610f2.jpg");
        mlist1 = new ArrayList<>();
        mlist1.add("这是一个美好的早晨");
        mlist1.add("但我们并不美好");
        mlist1.add("因为我是学人工智能的");
        mlist1.add("已经被学金融的虐得头破血流");

        banner = view.findViewById(R.id.banner);

        banner.setImageLoader(new GlideImageLoader());   //设置图片加载器
        banner.setImages(mlist);//设置图片源
        banner.setBannerTitles(mlist1);//设置标题源
        banner.setDelayTime(2000);//设置轮播事件，单位毫秒
        banner.setBannerAnimation(Transformer.ZoomOutSlide);//设置轮播动画，动画种类很多，有兴趣的去试试吧，我在这里用的是默认
//stack

/**
 *  轮播图的点击事件
 */
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                // Toast.makeText(HomeFragment, "这是第" + position +"个效果", Toast.LENGTH_SHORT).show();
            }
        });
        banner.setIndicatorGravity(BannerConfig.CENTER);//设置指示器的位置
        banner.start();//开始轮播，一定要调用此方法。

    }
}


