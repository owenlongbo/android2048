package com.game.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;

import com.game.R;


public class SplashActivity extends FragmentActivity {
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //线程开启器
        mHandler = new Handler();
        //延时两秒 开启下面的gotoLogin线程
        mHandler.postDelayed(gotoLoginAct, 2000);
    }

    Runnable gotoLoginAct = new Runnable() {

        @Override
        public void run() {
            Intent i = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        }
    };
}
