package com.mding.chatfeng.base_common.components.common;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.mding.chatfeng.base_common.AppConfig;
import com.mding.chatfeng.base_common.bean.BaseBean;
import com.mding.chatfeng.base_common.net.NetManager;
import com.mding.chatfeng.base_common.request.Api;
import com.mding.chatfeng.base_common.request.create;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * 此处增对轻量级的请求，必须保证所有调用该请求的对象是有序的，不能存在同时调用，如需同时调用请走同级目录的独立业务
 */
public class Vcommon implements IA_Common {


    @Override
    public String getActionName() {
        return getClass().getSimpleName();
    }

    @Override
    public boolean onActionCall(final CC cc) {
        new NetManager(cc) {
            @Override
            protected void successData(String result) {

            }

            @Override
            protected void failData(BaseBean mBaseBean) {
                AppConfig.logs("提示："+mBaseBean.getMsg());
            }

            @Override
            protected void replaceApi() {
                //此处作为公共控制器和方法变量，不可删除或注释
                String[] keys=cc.getParamItem(cc.getComponentName()+cc.getActionName()).toString().split("@");
                ctn=keys[0];
                mtn=keys[1];
            }
        };

        return true;
    }
}