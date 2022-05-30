package com.example.androidcourseexm3.utils;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * @作者: yzq
 * @创建日期: 2019/8/2 12:21
 * @文件作用:
 */
public class StatusBarUtil {
    /**
     * 通过设置全屏，设置状态栏透明
     * @param activity
     */
    public static void fullScreen(Activity activity) {
        //4.4以上才能设置状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //5.0开始需要把颜色设置透明，否则导航栏会呈现系统默认的浅灰色
                Window window = activity.getWindow();
                View decorView = window.getDecorView();
                //多个flag结合使用，具体含义见浏览器或实验报告
                int option = View.SYSTEM_UI_FLAG_LAYOUT_STABLE  // 避免在显隐状态栏导航栏的时候发生布局的变化
                        | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN  // 隐藏状态栏
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION  // 隐藏导航栏
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;  // 当状态栏隐藏的时候，手动调出状态栏导航栏，显示一会儿随后就会隐藏掉。设置该属性后不会清除flag，该属性是比较常用的一种。但是离开页面肯定是会导致flag被清除掉的
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                decorView.setSystemUiVisibility(option);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
                Log.d("EXM_StatusBarUtils", "fullScreen");
                //底部导航栏颜色也可以正常设置
                //window.setNavigationBarColor(Color.TRANSPARENT);  // 没效果
            } else {
                Window window = activity.getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
                int flagTranslucentNavigation = WindowManager.LayoutParams
                        .FLAG_TRANSLUCENT_NAVIGATION;
                attributes.flags |= flagTranslucentStatus;
                //底部导航栏
//                attributes.flags |= flagTranslucentNavigation;
                window.setAttributes(attributes);
            }
        }
    }

    /**
     * 添加状态栏占位视图
     *
     * @param activity
     */
    public static  void addStatusViewWithColor(Activity activity, int colorId) {
        //4.4以上才能设置状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //设置 paddingTop 状态栏的高度
            ViewGroup rootView = (ViewGroup) activity.getWindow().getDecorView()
                    .findViewById(android.R.id.content);
            rootView.setPadding(0, getStatusBarHeightPx(activity), 0, 0);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //5.0以上直接设置状态栏颜色
                activity.getWindow().setStatusBarColor(activity.getResources().getColor(colorId));

            } else {
                //增加占位状态栏
                ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
                decorView.clearFocus();
                View statusBarView = new View(activity);
                ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams
                        .MATCH_PARENT,
                        getStatusBarHeightPx(activity));
                statusBarView.setBackgroundColor(colorId);
                decorView.addView(statusBarView, lp);
            }
        }
    }
    public static int getStatusBarHeightPx(Activity activity) {
        int height = 0;
        int resourceId = activity.getApplicationContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            height = activity.getApplicationContext().getResources().getDimensionPixelSize(resourceId);
        }
        return height;
    }

}