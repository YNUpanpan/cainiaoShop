package com.example.cainiaoshop.fragment;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//import androidx.appcompat.widget.ViewUtils;
import com.example.cainiaoshop.activity.WareDetailActivity;
import com.example.cainiaoshop.adapter.BaseAdapter;
import com.example.cainiaoshop.adapter.HWAdapter;
import com.example.cainiaoshop.utils.Pager;
import com.google.gson.reflect.TypeToken;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.cjj.MaterialRefreshLayout;
import com.example.cainiaoshop.Contants;
import com.example.cainiaoshop.R;
import com.example.cainiaoshop.bean.Page;
import com.example.cainiaoshop.bean.Wares;

//import org.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * Created by YNUpanpan on 20/11/25.
 */
public class HotFragment extends BaseFragment implements Pager.OnPageListener<Wares> {


    private HWAdapter mAdatper;

    @ViewInject(R.id.recyclerview)
    private RecyclerView mRecyclerView;

    @ViewInject(R.id.refresh_view)
    private MaterialRefreshLayout mRefreshLaout;



    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hot,container,false);
    }

    @Override
    public void init() {

        Pager pager = Pager.newBuilder()
                .setUrl(Contants.API.WARES_HOT)
                .setLoadMore(true)
                .setOnPageListener(this)
                .setPageSize(20)
                .setRefreshLayout(mRefreshLaout)
                .build(getContext(), new TypeToken<Page<Wares>>() {}.getType());


        pager.request();

    }


    @Override
    public void load(List<Wares> datas, int totalPage, int totalCount) {

        mAdatper = new HWAdapter(getContext(),datas);


        mAdatper.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Wares wares = mAdatper.getItem(position);

                Intent intent = new Intent(getActivity(), WareDetailActivity.class);

                intent.putExtra(Contants.WARE,wares);
                startActivity(intent);


            }
        });


        mRecyclerView.setAdapter(mAdatper);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    public void refresh(List<Wares> datas, int totalPage, int totalCount) {
        mAdatper.refreshData(datas);

        mRecyclerView.scrollToPosition(0);
    }

    @Override
    public void loadMore(List<Wares> datas, int totalPage, int totalCount) {

        mAdatper.loadMoreData(datas);
        mRecyclerView.scrollToPosition(mAdatper.getDatas().size());
    }
}