package com.example.cainiaoshop.activity;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cainiaoshop.CniaoApplication;
import com.example.cainiaoshop.bean.User;



/**
 * Created by YNUpanpan on 20/12/15.
 */
public class BaseActivity extends AppCompatActivity {


    protected static final String TAG = BaseActivity.class.getSimpleName();

    public void startActivity(Intent intent,boolean isNeedLogin){


        if(isNeedLogin){

            User user = CniaoApplication.getInstance().getUser();
            if(user !=null){
                super.startActivity(intent);
            }
            else{

                CniaoApplication.getInstance().putIntent(intent);
                Intent loginIntent = new Intent(this
                        , LoginActivity.class);
                super.startActivity(intent);

            }

        }
        else{
            super.startActivity(intent);
        }

    }
}
