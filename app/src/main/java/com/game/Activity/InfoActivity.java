package com.game.Activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.game.R;

/**
 * Created by longlong on 2015/4/27.
 */
public class InfoActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        initToolbar();
    }

    //初始化toolbar
    private void initToolbar() {
        //绑定toolbar
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        //找到toolbar标签
        TextView mToolBarTextView = (TextView) findViewById(R.id.text_view_toolbar_title);
        //将toolbar放到页面顶部
        setSupportActionBar(mToolbar);
        //设置左上角的图标可以点击
        getSupportActionBar().setHomeButtonEnabled(true);
        //左上角返回图标
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //使自定义的普通View能在title栏显示
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolBarTextView.setText("关于我的");
        mToolbar.setNavigationIcon(R.drawable.btn_back);
        //给返回的按钮设置点击监听
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //此函数系统原有  使用结束当前页面
                onBackPressed();
            }
        });
    }
}
