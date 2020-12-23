package com.example.cainiaoshop.adapter;

import android.content.Context;
import android.net.Uri;

import com.example.cainiaoshop.R;
import com.example.cainiaoshop.bean.Wares;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.List;


/**
 * Created by YNUpanpan on 20/12/20.
 */
public class WaresAdapter extends SimpleAdapter<Wares> {



    public WaresAdapter(Context context, List<Wares> datas) {
        super(context, R.layout.template_grid_wares, datas);
    }

    @Override
    protected void convert(BaseViewHolder viewHoder, Wares item) {

        viewHoder.getTextView(R.id.text_title).setText(item.getName());
        viewHoder.getTextView(R.id.text_price).setText("￥"+item.getPrice());
        SimpleDraweeView draweeView = (SimpleDraweeView) viewHoder.getView(R.id.drawee_view);
        draweeView.setImageURI(Uri.parse(item.getImgUrl()));
    }



}
