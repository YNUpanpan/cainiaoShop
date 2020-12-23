package com.example.cainiaoshop.fragment;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.example.cainiaoshop.Contants;
import com.example.cainiaoshop.R;
import com.example.cainiaoshop.adapter.BaseAdapter;
import com.example.cainiaoshop.adapter.CategoryAdapter;
import com.example.cainiaoshop.adapter.DividerItemDecoration;
import com.example.cainiaoshop.adapter.WaresAdapter;
import com.example.cainiaoshop.adapter.decoration.DividerGridItemDecoration;
import com.example.cainiaoshop.bean.Category;
import com.example.cainiaoshop.bean.FBanner;
import com.example.cainiaoshop.bean.Page;
import com.example.cainiaoshop.bean.Wares;
import com.example.cainiaoshop.http.OkHttpHelper;
import com.example.cainiaoshop.http.SimpleCallback;
import com.example.cainiaoshop.http.SpotsCallBack;
import com.example.cainiaoshop.widget.GlideImageLoader;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import com.youth.banner.listener.OnBannerListener;

import com.lidroid.xutils.view.annotation.ViewInject;

import okhttp3.Request;
import okhttp3.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YNUpanpan on 20/12/5.
 */

public class CategoryFragment extends BaseFragment {

    private List<FBanner> mFBanner;

    Handler handler;
    View view;
    ArrayList<String> img;
    ArrayList<String> title;

    //com.youth.banner.Banner Banner;//banner组件
    @ViewInject(R.id.recyclerview_category)
    private RecyclerView mRecyclerView;


    @ViewInject(R.id.recyclerview_wares)
    private RecyclerView mRecyclerviewWares;

    @ViewInject(R.id.refresh_layout)
    private MaterialRefreshLayout mRefreshLaout;

    @ViewInject(R.id.banner_category)
    private  com.youth.banner.Banner Banner;

    private CategoryAdapter mCategoryAdapter;
    private WaresAdapter mWaresAdatper;


    private OkHttpHelper mHttpHelper = OkHttpHelper.getInstance();


    private int currPage=1;
    private int totalPage=1;
    private int pageSize=10;
    private long category_id=0;


    private  static final int STATE_NORMAL=0;
    private  static final int STATE_REFREH=1;
    private  static final int STATE_MORE=2;

    private int state=STATE_NORMAL;

    // 构建Runnable对象，在runnable中更新界面
    Runnable runnableBanner = new Runnable() {
        @Override
        public void run() {
            //更新界面
            Banner.start();
        }

    };


//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//
//        handler = new Handler();//用来处理线程
//        View view = inflater.inflate(R.layout.fragment_category,container,false);
//        ViewUtils.inject(this,view);
//
//
//        requestCategoryData();
//        requestBannerData();
//
//        initRefreshLayout();
//        return  view;
//    }
//
//
@Override
public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_category,container,false);
}

    @Override
    public void init() {
        handler = new Handler();//用来处理线程

        requestCategoryData();
        requestBannerData();

        initRefreshLayout();
    }



    private  void initRefreshLayout(){

        mRefreshLaout.setLoadMore(true);
        mRefreshLaout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {

                refreshData();

            }
            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {

                if(currPage <=totalPage)
                    loadMoreData();
                else{

                    mRefreshLaout.finishRefreshLoadMore();
                }
            }
        });
    }

    private  void refreshData(){

        currPage =1;

        state=STATE_REFREH;
        requestWares(category_id);

    }

    private void loadMoreData(){

        currPage = ++currPage;
        state = STATE_MORE;
        requestWares(category_id);

    }


    private  void requestCategoryData(){



        mHttpHelper.get(Contants.API.CATEGORY_LIST, new SpotsCallBack<List<Category>>(getContext()) {


            @Override
            public void onSuccess(Response response, List<Category> categories) {

                showCategoryData(categories);

                if(categories !=null && categories.size()>0)
                    category_id = categories.get(0).getId();
                    requestWares(category_id);
            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });

    }

    private  void showCategoryData(List<Category> categories){


        mCategoryAdapter = new CategoryAdapter(getContext(),categories);

        mCategoryAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Category category = mCategoryAdapter.getItem(position);

                category_id = category.getId();
                currPage=1;
                state=STATE_NORMAL;

                requestWares(category_id);


            }
        });

        mRecyclerView.setAdapter(mCategoryAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL_LIST));


    }





    private void requestBannerData( ) {



       String url = Contants.API.BANNER+"?type=1";

        mHttpHelper.get(url, new SpotsCallBack<List<FBanner>>(getContext()){


            @Override
            public void onSuccess(Response response, List<FBanner> banners) {

//                showSliderViews(banners);
                mFBanner = banners;
                showBannerViews();
            }


            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });

    }



    private void showBannerViews(){


        img = new ArrayList<>();
        title = new ArrayList<>();
        for (FBanner fBanner : mFBanner) {
            img.add(fBanner.getImgUrl());
            title.add(fBanner.getName());
        }

       // Banner = view.findViewById(R.id.banner_category);

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





    private void requestWares(long categoryId){

        String url = Contants.API.WARES_LIST+"?categoryId="+categoryId+"&curPage="+currPage+"&pageSize="+pageSize;

        mHttpHelper.get(url, new SimpleCallback<Page<Wares>>(getContext()) {
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
            public void onSuccess(Response response, Page<Wares> waresPage) {


                currPage = waresPage.getCurrentPage();
                totalPage =waresPage.getTotalPage();

                showWaresData(waresPage.getList());


            }


            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });

    }



    private  void showWaresData(List<Wares> wares){



        switch (state){

            case  STATE_NORMAL:

                if(mWaresAdatper ==null) {
                    mWaresAdatper = new WaresAdapter(getContext(), wares);

                    mRecyclerviewWares.setAdapter(mWaresAdatper);

                    mRecyclerviewWares.setLayoutManager(new GridLayoutManager(getContext(), 2));
                    mRecyclerviewWares.setItemAnimator(new DefaultItemAnimator());
                    mRecyclerviewWares.addItemDecoration(new DividerGridItemDecoration(getContext()));
                }
                else{
                    mWaresAdatper.clear();
                    mWaresAdatper.addData(wares);
                }




                break;

            case STATE_REFREH:
                mWaresAdatper.clear();
                mWaresAdatper.addData(wares);

                mRecyclerviewWares.scrollToPosition(0);
                mRefreshLaout.finishRefresh();
                break;

            case STATE_MORE:
                mWaresAdatper.addData(mWaresAdatper.getDatas().size(),wares);
                mRecyclerviewWares.scrollToPosition(mWaresAdatper.getDatas().size());
                mRefreshLaout.finishRefreshLoadMore();
                break;





        }



    }


}



