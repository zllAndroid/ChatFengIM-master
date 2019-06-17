//package com.mding.chatfeng.home;
//
//import android.annotation.SuppressLint;
//import android.app.Application;
//import android.content.Context;
//import android.content.res.Resources;
//
//public class BaseApplication  extends Application {
//
//    @SuppressLint("StaticFieldLeak") private static Context context;
//
//    public static Context getContext() {
//        return context;
//    }
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        context = getApplicationContext();
///*        if (LeakCanary.isInAnalyzerProcess(this)) {
//            return;
//        }
//        LeakCanary.install(this);
//
//        QDUpgradeManager.getInstance(this).check();
//        QMUISwipeBackActivityManager.init(this);*/
//    }
//
//    @Override
//    public Resources.Theme getTheme() {
//        return super.getTheme();
//    }
//}