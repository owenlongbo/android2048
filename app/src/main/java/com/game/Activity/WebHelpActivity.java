package com.game.Activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.game.R;

public class WebHelpActivity extends AppCompatActivity {

    private WebView wv;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        init();
        loadurl(wv, "http://jingyan.baidu.com/article/e4511cf309d9842b845eafc7.html");
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void init() {
        initToolbar();

        //加载中对话框 设置
        pd = new ProgressDialog(this);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setMessage("数据载入中请稍后...");
        wv = findViewById(R.id.wv);

        //设置js脚本可以运行
        wv.getSettings().setJavaScriptEnabled(true);
        //设置滚动条样式
        wv.setScrollBarStyle(View.SCROLLBARS_INSIDE_INSET);
        // 设置webviewclient（web登录器）
        wv.setWebViewClient(new WebViewClient() {

            // 在点击请求的是链接是才会调用，重写此方法返回true表明点击网页里面的链接还是在当前的webview里跳转
            // 重写点击动作,用webview载入
            public boolean shouldOverrideUrlLoading(final WebView view, final String url) {
                loadurl(view, url);// 载入网页
                return true;
            }

        });

        // 载入进度改变而触发
        wv.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if (progress == 100) {
                    new Handler().post(() -> pd.show());
                }
                super.onProgressChanged(view, progress);
            }
        });
    }

    private void initToolbar() {
        Toolbar mToolbar = findViewById(R.id.toolbar);
        TextView mToolBarTextView = findViewById(R.id.text_view_toolbar_title);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        mToolBarTextView.setText("教程攻略");
        mToolbar.setNavigationIcon(R.drawable.btn_back);
        mToolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && wv.canGoBack()) {
            //返回上一层
            wv.goBack();
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_BACK) {
            ConfirmExit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //弹出 退出教程  对话框
    public void ConfirmExit() {
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle("退出");
        ad.setMessage("是否退出教程？");
        // �˳���ť
        ad.setPositiveButton("是", (dialog, i) -> finish());
        ad.setNegativeButton("否", (dialog, i) -> {
        });
        ad.show();
    }

    public void loadurl(final WebView view, final String url) {
        view.loadUrl(url);
    }

}
