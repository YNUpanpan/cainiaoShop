package com.example.cainiaoshop.adapter;

import android.content.Context;

import java.util.List;

/**
 * Created by YNUpanpan on 20/12/15.
 */


public abstract class SimpleAdapter<T> extends BaseAdapter<T,BaseViewHolder> {

    public SimpleAdapter(Context context, int layoutResId) {
        super(context, layoutResId);
    }

    public SimpleAdapter(Context context, int layoutResId, List<T> datas) {
        super(context, layoutResId, datas);
    }
}
