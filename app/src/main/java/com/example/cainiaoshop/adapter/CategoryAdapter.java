package com.example.cainiaoshop.adapter;

import android.content.Context;

import com.example.cainiaoshop.R;
import com.example.cainiaoshop.bean.Category;

import java.util.List;

/**
 * Created by YNUpanpan on 20/11/28.
 */

public class CategoryAdapter extends SimpleAdapter<Category> {


    public CategoryAdapter(Context context, List<Category> datas) {
        super(context, R.layout.template_single_text, datas);
    }

    @Override
    protected void convert(BaseViewHolder viewHoder, Category item) {


        viewHoder.getTextView(R.id.textView).setText(item.getName());

    }
}
