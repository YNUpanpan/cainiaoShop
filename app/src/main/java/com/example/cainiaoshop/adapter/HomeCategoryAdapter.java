package com.example.cainiaoshop.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cainiaoshop.R;
import com.example.cainiaoshop.bean.Campaign;
import com.example.cainiaoshop.bean.HomeCampaign;
import com.example.cainiaoshop.bean.HomeCategory;
import com.squareup.picasso.Picasso;

//import com.squareup.picasso.Picasso;
import java.util.List;

/**
 * Created by YNUpanpan on 20/12/15.
 */

public class HomeCategoryAdapter extends RecyclerView.Adapter<HomeCategoryAdapter.ViewHolder> {


    private  static int VIEW_TYPE_L=0;
    private  static int VIEW_TYPE_R=1;

    private LayoutInflater mInflater;

    private List<HomeCampaign> mDatas;
    private Context mContext;

    private  OnCampaignClickListener mListener;
    public HomeCategoryAdapter(List<HomeCampaign> datas,Context context){

        mDatas=datas;
        this.mContext=context;
    }

    public void setOnCampaignClickListener(OnCampaignClickListener listener){

        this.mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        mInflater = LayoutInflater.from(parent.getContext());
        if(viewType == VIEW_TYPE_R){
            return  new ViewHolder(mInflater.inflate(R.layout.template_home_cardview2,parent,false));
        }

        return  new ViewHolder(mInflater.inflate(R.layout.template_home_cardview,parent,false));
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        HomeCampaign homeCampaign = mDatas.get(position);
//        holder.textTitle.setText(homeCampaign.getName());
//        holder.imageViewBig.setImageResource(homeCampaign.getImgBig());
//        holder.imageViewSmallTop.setImageResource(homeCampaign.getImgSmallTop());
//        holder.imageViewSmallBottom.setImageResource(homeCampaign.getImgSmallBottom());

        holder.textTitle.setText(homeCampaign.getTitle());

        Picasso.with(mContext).load(homeCampaign.getCpOne().getImgUrl()).into(holder.imageViewBig);
        Picasso.with(mContext).load(homeCampaign.getCpTwo().getImgUrl()).into(holder.imageViewSmallTop);
        Picasso.with(mContext).load(homeCampaign.getCpThree().getImgUrl()).into(holder.imageViewSmallBottom);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public int getItemViewType(int position) {

        if(position % 2==0){
            return  VIEW_TYPE_R;
        }
        else return VIEW_TYPE_L;
    }
     class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {

        TextView textTitle;
        ImageView imageViewBig;
        ImageView imageViewSmallTop;
        ImageView imageViewSmallBottom;

        public ViewHolder(View itemView) {
            super(itemView);


            textTitle = (TextView) itemView.findViewById(R.id.text_title);
            imageViewBig = (ImageView) itemView.findViewById(R.id.imgview_big);
            imageViewSmallTop = (ImageView) itemView.findViewById(R.id.imgview_small_top);
            imageViewSmallBottom = (ImageView) itemView.findViewById(R.id.imgview_small_bottom);

            imageViewBig.setOnClickListener(this);
            imageViewSmallTop.setOnClickListener(this);
            imageViewSmallBottom.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {


            if(mListener !=null){

                anim(v);

            }


        }

         private  void anim(final View v){

             ObjectAnimator animator =  ObjectAnimator.ofFloat(v, "rotationX", 0.0F, 360.0F)
                     .setDuration(200);
             animator.addListener(new AnimatorListenerAdapter() {
                 @Override
                 public void onAnimationEnd(Animator animation) {

                     HomeCampaign campaign = mDatas.get(getLayoutPosition());

                     switch (v.getId()){

                         case  R.id.imgview_big:
                             mListener.onClick(v, campaign.getCpOne());
                             break;

                         case  R.id.imgview_small_top:
                             mListener.onClick(v, campaign.getCpTwo());
                             break;

                         case R.id.imgview_small_bottom:
                             mListener.onClick(v,campaign.getCpThree());
                             break;

                     }

                 }
             });
             animator.start();
         }
     }

    public  interface OnCampaignClickListener{

        void onClick(View view, Campaign campaign);
    }
}
