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
 * 此处为获取集群IP的独立业务，如需将IP进行本地化，可在此模块编码，此次是由  create.class 调用
 */
public class VgetClusterIp  implements IA_Login {

    @Override
    public String getActionName() {
        return create.login().getClusterIp.methodName;
    }

    @Override
    public boolean onActionCall(final CC cc) throws UnsupportedEncodingException {

        //单独设置获取集群的域名(临时改变域名),集群地址比较特殊，可能是一个独立的
        cc.getParams().put("url", AppConfig.clusterIp);
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
                //替换新的API接口请求
                /*return api.getClusterIp(AppConfig.pathVersion,cc.getComponentName(),cc.getActionName(), cc.getParamItem(cc.getActionName()));*/
                //如果没有大于两层路由，则返回null即可

            }
        };

        //true ：异步调用该组件
        return true;
    }
    }
