package com.example.cainiaoshop.widget;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by YNUpanpan on 20/11/27.
 */

public class GlideImageLoader extends ImageLoader {


    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context).load(path).into(imageView);
    }

    @Override
    public ImageView createImageView(Context context) {

        ImageView imageView = new ImageView(context);
        return imageView;
    }

}