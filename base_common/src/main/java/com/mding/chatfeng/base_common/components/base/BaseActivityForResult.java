package com.mding.chatfeng.base_common.components.base;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.mding.chatfeng.base_common.R;
import com.mding.chatfeng.base_common.utils.about_dialog.DialogUtils;
import com.mding.chatfeng.base_common.utils.about_immersive.StateBarUtils;
import com.mding.chatfeng.base_common.utils.about_main_utils.windowStatusBar;
import com.mding.chatfeng.base_common.utils.aboutsystem.AppManager;


public class BaseActivityForResult extends AppCompatActivity  {
    String simpleName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * 切换为非全屏
         */
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        if (isChenjinshi())
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_FULLSCREEN);

//        getWindow().setBackgroundDrawableResource(android.R.color.transparent);// 将 Activity 的背景色取消


        AppManager.getAppManager().addActivity(this);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        simpleName = getClass().getSimpleName();
//        ScreenUtils.setWindowStatusBarColor(AppManager.getAppManager().currentActivity(),R.color.red);
        if (isChenjinshi())
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

//            windowStatusBar.setStatusColor(this, getResources().getColor(R.color.app_theme), 50);
                getWindow().setNavigationBarColor(Color.WHITE);
            }
        initBeforeContentView();
        setContentView(getLayoutView());
        initBaseView();
    }

    protected void initBeforeContentView() {
        if (isChenjinshi()) {
            if (isGonesStatus()) {
                //全屏不显示状态栏导航栏
                StateBarUtils.setFullscreen(this, false, false);
            } else {
                //全屏显示状态栏隐藏导航栏
                StateBarUtils.setFullscreen(this, true, false);
//            设置状态栏的颜色
                windowStatusBar.setStatusColor(this, getResources().getColor(R.color.app_theme), 0);
//            StateBarUtils.setFullscreen(this,true,true);
            }
        }
    }

    protected void initBaseView() {
    }
    protected int getLayoutView() {
        return 0;
    }

    protected boolean isGones() {
        return true;
    }

    //    是否隐藏状态栏，默认不隐藏
    protected boolean isGonesStatus() {
        return false;
    }
    //    是否沉浸式状态栏  默认是
    protected boolean isChenjinshi() {
        return true;
    }

    //    是否聊天页面，默认不是
    protected boolean isChat() {
        return false;
    }

    //    是否登录页面 默认不是
    protected boolean isLogin() {
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        关闭eventbus
//        EventBus.getDefault().unregister(this);
//        关闭弹窗
        DialogUtils.isShow();
    }
    @Override
    protected void onStart() {
        super.onStart();
    }

    public boolean isSupportSwipeBack() {
        return true;
    }
    protected boolean isTopBack() {
        return true;
    }
    //接收到消息，传递给子类
    public void receiveResultMsg(String responseText) {


    }

}