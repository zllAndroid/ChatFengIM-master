package com.mding.chatfeng.base_common.components.base;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.PowerManager;

import com.billy.cc.core.component.CC;
import com.mding.chatfeng.base_common.components.base.about_swipback.BGASwipeBackHelper;
import com.mding.chatfeng.base_common.utils.aboututils.ACache;

public class BaseApplication extends Application {
    //cpu常开，息屏保活
    private PowerManager pm;
    private PowerManager.WakeLock wakeLock;

    @SuppressLint("InvalidWakeLockTag")
    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this;
        initCC(false);
//        QMUISwipeBackActivityManager.init(this);
        /**
         * 必须在 Application 的 onCreate 方法中执行 BGASwipeBackHelper.init 来初始化滑动返回
         * 第一个参数：应用程序上下文
         * 第二个参数：如果发现滑动返回后立即触摸界面时应用崩溃，请把该界面里比较特殊的 View 的 class 添加到该集合中，目前在库中已经添加了 WebView 和 SurfaceView
         */
        BGASwipeBackHelper.init(this, null);
        pm=(PowerManager)getSystemService(Context.POWER_SERVICE);
        wakeLock=pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"CPUKeepRunning");
        wakeLock.acquire();
    }
    public static ACache aCache;
    public static Context mContext;
    public static ACache getaCache(){
        if (aCache==null)
        {
            aCache =  ACache.get(getAppContext());
        }
        return aCache;
    }
    public static Context getAppContext() {
        return mContext;
    }
    private void initCC(boolean state){
        CC.enableDebug(state);
        CC.enableVerboseLog(true);
        CC.enableRemoteCC(false);
    }
}