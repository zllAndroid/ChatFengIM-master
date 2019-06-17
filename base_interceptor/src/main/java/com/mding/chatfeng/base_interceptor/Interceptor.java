package com.mding.chatfeng.base_interceptor;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.Chain;
import com.billy.cc.core.component.ICCInterceptor;
import com.mding.chatfeng.base_common.AppConfig;

public class Interceptor implements ICCInterceptor {


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public CCResult intercept(Chain chain) {

        AppConfig.logs(chain.getCC().toString());

        CCResult result = chain.proceed();

        AppConfig.logs(result.toString());

        return result;
    }
}
