package com.mding.chatfeng.base_common.components.login;

import com.billy.cc.core.component.CC;
import com.mding.chatfeng.base_common.AppConfig;
import com.mding.chatfeng.base_common.bean.BaseBean;
import com.mding.chatfeng.base_common.net.NetManager;
import com.mding.chatfeng.base_common.request.Api;
import com.mding.chatfeng.base_common.request.create;

import java.io.UnsupportedEncodingException;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * 做具体的数据耗时处理，如调用数据库组件等，把最终UI需要的json数据或Bean实体返回
 */
public class Vregister  implements IA_Login {

    @Override
    public String getActionName() {
        return create.login().register.methodName;
    }

    @Override
    public boolean onActionCall(final CC cc) throws UnsupportedEncodingException {

        //单独设置获取集群的域名,此处如果不是特俗独立地址，可删除
//        cc.getParams().put("url",AppConfig.httpProtocolIp);

        new NetManager(cc) {
            @Override
            protected void successData(String result) {
//                AppConfig.logs(result);

             }

            @Override
            protected void failData(BaseBean mBaseBean) {
//              AppConfig.logs(  mBaseBean.getMsg());

            }

            @Override
            protected void replaceApi() {
                /*return api.getClusterIp(AppConfig.pathVersion,cc.getComponentName(),cc.getActionName(), cc.getParamItem(cc.getActionName()));*/
                //如果没有大于两层路由，则返回null即可

            }
        };

        //true ：异步调用该组件
        return true;
    }







}