package com.example.androidcourseexm3;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.example.androidcourseexm3.utils.StatusBarUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.qweather.sdk.view.HeConfig;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;
    private SharedPreferences mSPrf;

    /*Handler mHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            mProgressDialog.dismiss();

        }
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);  // 透明状态栏
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);  // 透明导航栏
        setContentView(R.layout.activity_main);


        //getWindow().setStatusBarColor(Color.TRANSPARENT);  // 将状态栏设为透明，无论放在Activity或是Fragment都无效，下同
        //getWindow().setNavigationBarColor(Color.TRANSPARENT);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        mSPrf = getSharedPreferences("data", MODE_PRIVATE);
        if(!mSPrf.getBoolean("QWeather_init", false)){
            /*初始化QWeather，根据API描述只需初始化一次，因此使用SP保存*/
            HeConfig.init("HE2205301102411998", "171134f3f1d54c65a886af730e0386fe");
            HeConfig.switchToDevService();  // 切换至开发版服务
            SharedPreferences.Editor mSPEditor = getSharedPreferences("data", MODE_PRIVATE).edit();
            mSPEditor.putBoolean("QWeather_init", true);
            mSPEditor.apply();
        }



    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            StatusBarUtil.fullScreen(this);  // 每次获取到焦点就执行一次隐藏状态栏操作
        }
    }

    /*private void test_loading(){
        mProgressDialog = ProgressDialog.show(this, null, "正在加载...");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                }catch (Exception e){}
                mHandler.sendEmptyMessage(0x110);
            }
        }).start();
    }*/
}