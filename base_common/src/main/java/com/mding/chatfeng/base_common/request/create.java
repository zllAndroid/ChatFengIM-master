package com.mding.chatfeng.base_common.request;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.CCUtil;
import com.billy.cc.core.component.IComponentCallback;
import com.mding.chatfeng.base_common.AppConfig;
import com.mding.chatfeng.base_common.bean.login.GetClusterIp;
import com.mding.chatfeng.base_common.components.ComsApi;
import com.mding.chatfeng.base_common.request.requsetkeys.ContactBean;
import com.mding.chatfeng.base_common.request.requsetkeys.KeysRoot;
import com.mding.chatfeng.base_common.request.requsetkeys.LoginBean;
import com.mding.chatfeng.base_common.request.requsetkeys.MyAMQPTaskBean;
import com.mding.chatfeng.base_common.request.requsetkeys.PersonCenterBean;
import com.mding.chatfeng.base_common.utils.GsonParamConverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * 请求调用入口
 * wdh
 */
public class create {
    public static KeysRoot mKeys=null;
    public static ComsApi mComsApi;


    //字典接口    http://www.xm6leefun.cn:5052/V3_0_0/Login/routeListTo
    public static void init(Context mContext) {

        StringBuilder newstringBuilder = new StringBuilder();
        InputStream inputStream = null;
        try {
            inputStream = mContext.getResources().getAssets().open("keys.json");
            InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(isr);
            String jsonLine;
            while ((jsonLine = reader.readLine()) != null) {
                newstringBuilder.append(jsonLine);
            }
            reader.close();
            isr.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            AppConfig.logs("配置初始化失败，json本地文件解析读取异常");
        }
        String result=newstringBuilder .toString();
        mKeys=  new GsonParamConverter().json2Object(result,KeysRoot.class);

    }


    public static ComsApi getComsApi(){
        if (mComsApi==null)
            mComsApi=new ComsApi();
        return mComsApi;
    }



    public static PersonCenterBean person(){
        return mKeys.record.PersonCenter;
    }
    public static LoginBean login(){
        return mKeys.record.Login;
    }
    public static ContactBean contact(){
        return mKeys.record.Contact;
    }
    public static MyAMQPTaskBean amqp(){
        return mKeys.record.MyAMQPTask;
    }

}
