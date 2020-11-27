package com.example.cainiaoshop.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.TintTypedArray;
import androidx.appcompat.widget.Toolbar;

import com.example.cainiaoshop.R;


/**
 * Created by YNUpanpan on 20/11/25.
 */
public class CnToolbar extends Toolbar {
    
    private LayoutInflater mInflater;
    TextView mTextTitle;
    EditText mSearchView;
    ImageButton mRightImageButton;
    View mView;
    public CnToolbar(@NonNull Context context) {
        this(context,null);
    }

    public CnToolbar(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    @SuppressLint("RestrictedApi")
    public CnToolbar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        
        initView();
        setContentInsetsRelative(10,10);

        if(attrs != null){

            @SuppressLint
                    ("RestrictedApi") final TintTypedArray a = TintTypedArray.obtainStyledAttributes(getContext(),attrs,
                    R.styleable.CnToolbar,defStyleAttr,0);

            @SuppressLint
                    ("RestrictedApi") final Drawable rightIcon = a.getDrawable(R.styleable.CnToolbar_rightButtonIcon);
            if (rightIcon!= null) {
                //setNavigationIcon(navIcon);
                setRightButtonIcon(rightIcon);
            }

            @SuppressLint("RestrictedApi")
            boolean isShowSearchView = a.getBoolean(R.styleable.CnToolbar_isShowSearchView,false);

            if(isShowSearchView){

                showSearchView();
                hideTitleView();

            }


            a.recycle();


        }

    }

    private void initView() {

        if(mView==null){


        mInflater=LayoutInflater.from(getContext());
        mView=mInflater.inflate(R.layout.toolbar,null);

        mTextTitle = (TextView) mView.findViewById(R.id.toolbar_title);
        mSearchView = (EditText) mView. findViewById(R. id. toolbar_searchview);
        mRightImageButton = (ImageButton) mView.findViewById(R. id. toolbar_rightButton) ;
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup. LayoutParams .WRAP_CONTENT, Gravity.CENTER_HORIZONTAL);

        addView(mView,lp);

        }
    }

    public void  setRightButtonIcon(Drawable icon){

        if(mRightImageButton !=null){

            mRightImageButton.setImageDrawable(icon);
            mRightImageButton.setVisibility(VISIBLE);
        }

    }

    public  void setRightButtonOnClickListener(OnClickListener li){

        mRightImageButton.setOnClickListener(li);
    }


    @Override
    public void setTitle(int resId) {
       setTitle(getContext().getText(resId));
    }

    @Override
    public void setTitle(CharSequence title) {

        initView();

        if(mTextTitle!=null)
        mTextTitle.setText(title);
        showTitleView();

    }

    public  void showSearchView(){

        if(mSearchView !=null)
            mSearchView.setVisibility(VISIBLE);

    }


    public void hideSearchView(){
        if(mSearchView !=null)
            mSearchView.setVisibility(GONE);
    }

    public void showTitleView(){
        if(mTextTitle !=null)
            mTextTitle.setVisibility(VISIBLE);
    }


    public void hideTitleView() {
        if (mTextTitle != null)
            mTextTitle.setVisibility(GONE);

    }
}
