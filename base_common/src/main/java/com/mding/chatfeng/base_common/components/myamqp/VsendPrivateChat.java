package com.mding.chatfeng.base_common.components.myamqp;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.mding.chatfeng.base_common.AppConfig;
import com.mding.chatfeng.base_common.bean.BaseBean;
import com.mding.chatfeng.base_common.net.NetManager;
import com.mding.chatfeng.base_common.request.Api;
import com.mding.chatfeng.base_common.request.create;

import okhttp3.ResponseBody;
import retrofit2.Call;


public class VsendPrivateChat implements IA_Amqp {


    @Override
    public String getActionName() {
        return create.amqp().sendPrivateChat.methodName;
    }

    @Override
    public boolean onActionCall(CC cc) {

//        AppConfig.logs("VsendPrivateChat----:"+cc.getParamItem(cc.getActionName()));

        new NetManager(cc) {
            @Override
            protected void successData(String result) {
                AppConfig.logs("-----------");
            }

            @Override
            protected void failData(BaseBean mBaseBean) {

            }

            @Override
            protected void replaceApi() {

            }
        };


        AppConfig.logs("---11111111111--------");
        return true;
    }







}