
package com.game.Activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.game.R;
import com.game.Service.BackGroundMusicService;
import com.game.Utils.Titanic;
import com.game.Utils.TitanicTextView;

import java.util.Timer;
import java.util.TimerTask;


public class LoginActivity extends Activity implements View.OnClickListener {

    private long firsttime; // 监听两次返回
    private Intent i;  //绑定监听service
    private BackGroundMusicService musicService;

    //监听当前页面是否在运行
    private ServiceConnection conn = new ServiceConnection() {
        @Override

        //当service连接时
        public void onServiceConnected(ComponentName name, IBinder service) {
            musicService = ((BackGroundMusicService.AudioBinder) service).getService();
        }

        //当serivce没有连接时
        @Override
        public void onServiceDisconnected(ComponentName name) {
            musicService = null;
        }
    };


    //以下为加载进度条

    private int counter;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化一个intent（传递 谁  启动  谁）
        i = new Intent(this, BackGroundMusicService.class);
        setContentView(R.layout.activity_login);
        //打开服务
        startService(i);
        //绑定服务
        bindService(i, conn, Context.BIND_AUTO_CREATE);

        //两个动画按钮
        final TitanicTextView mTvStartGame = (TitanicTextView) findViewById(R.id.startGame);
        final TitanicTextView mTvStartCharts = (TitanicTextView) findViewById(R.id.startCharts);

        //设置点击监听
        mTvStartCharts.setOnClickListener(this);
        mTvStartGame.setOnClickListener(this);

        //先隐藏了
        mTvStartGame.setVisibility(View.INVISIBLE);
        mTvStartCharts.setVisibility(View.INVISIBLE);

        //开启动画

        //首先创建一个显示器
        Titanic startGameAnim = new Titanic();
        //然后传进一个  按钮对象  给显示器  开启动画
        startGameAnim.start(mTvStartGame);
        Titanic startChartsAnim = new Titanic();
        startChartsAnim.start(mTvStartCharts);

        //进度条
        final NumberProgressBar bnp = (NumberProgressBar) findViewById(R.id.number_progress_bar);

        //开始加载
        counter = 0;
        timer = new Timer();

        //进度条线程
        timer.schedule(new TimerTask() {
            //重写线程的run方法
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        //一次加载几个百分比
                        bnp.incrementProgressBy(1);
                        counter++;
                        //当进度条到达100时候进度条结束加载
                        if (counter == 100) {
                            //设置progress样式
                            bnp.setProgress(0);
                            counter = 0;
                            //隐藏进度条   显示两个按钮
                            bnp.setVisibility(View.INVISIBLE);
                            mTvStartCharts.setVisibility(View.VISIBLE);
                            mTvStartGame.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }
        }, 500, 50);
    }

    @Override
    protected void onPause() {
        super.onPause();

        //解绑服务
        unbindService(conn);

        //停止服务
        stopService(i);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //绑定service
        bindService(i, conn, Context.BIND_AUTO_CREATE);

        //开始service
        startService(i);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //停掉 服务
        stopService(i);
    }

    //点击监听
    @Override
    public void onClick(View v) {
        Intent next;
        switch (v.getId()) {
            case R.id.startGame:
//                Toast.makeText(this, "开始游戏", Toast.LENGTH_SHORT).show();
                next = new Intent(this, MainActivity.class);
                startActivity(next);
                finish();
                break;
            case R.id.startCharts:
//                Toast.makeText(this, "排行榜", Toast.LENGTH_SHORT).show();
                next = new Intent(this, ChartsActivity.class);
                startActivity(next);
                break;
            default:
                break;
        }
    }

    //点击两次退出
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - firsttime < 3000) {
                finish();
                return true;
            } else {
                firsttime = System.currentTimeMillis();
                Toast.makeText(this, "再点一次退出", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return false;
    }
}
