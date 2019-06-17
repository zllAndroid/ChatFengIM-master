package com.mding.chatfeng.base_common.utils;

import com.billy.cc.core.component.CC;


public abstract class CompentHelper {


    public static  CC.Builder create(Class IC, Class IA) {
        return  CC.obtainBuilder(getICName(IC)).setActionName(getIAName(IA)).withoutGlobalInterceptor();
    }



    public static String getICName(Class clazz){
        return clazz.getPackage().getName()+"."+clazz.getSimpleName();
    }


    public static String getIAName(Class clazz){
        return clazz.getSimpleName();
    }

}
