package com.mding.chatfeng.base_common.utils.about_main_utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.text.TextUtils;

import java.util.List;


public class SysRunUtils {
    //    方法一
    public static boolean isRunningForeground ( Context context)
    {
        ActivityManager am = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
        String currentPackageName = cn.getPackageName();
        if(!TextUtils.isEmpty(currentPackageName) && currentPackageName.equals(context.getPackageName()))
        {
            return true ;
        }

        return false ;
    }
    //    方法二
    public static boolean isAppOnForeground(  Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasksInfo = manager.getRunningTasks(1);
        if (tasksInfo.size() > 0) {
            // 应用程序位于堆栈的顶层
            String src = context.getPackageName();
            String des = tasksInfo.get(0).topActivity.getPackageName();
            if (src.equalsIgnoreCase(des)) {
                return true;
            }
        }
        return false;
    }

}
