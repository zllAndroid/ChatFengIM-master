package com.mding.chatfeng.base_interceptor;
import android.annotation.SuppressLint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.Chain;
import com.billy.cc.core.component.IGlobalCCInterceptor;
/**
 * 通知栏
 * 请求加密
 */
public class Base  implements IGlobalCCInterceptor {
    private static final String TAG = "LogInterceptor";

    @Override
    public int priority() {
        return 1;
    }


    @SuppressLint("NewApi")
    @Override
    public CCResult intercept(Chain chain) {

/*        Log.i(TAG, "============log before:" + chain.getCC());


        String w = "dzzzzz";
        chain.getCC().getParams().replace("wdh",w);
        chain.getCC().cancel();*/

        CCResult result = chain.proceed();
/*        Log.i(TAG, "============log after:" + result);
       result.setSuccess(false);*/
        return result;
    }
}
