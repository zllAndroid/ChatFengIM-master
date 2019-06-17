package com.mding.chatfeng.home.call_home;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.CCUtil;
import com.billy.cc.core.component.IComponent;
import com.mding.chatfeng.base_common.AppConfig;
import com.mding.chatfeng.home.MainActivity;

/**
 * 项目：ChatFengIM-master
 * 文件描述：
 * 作者：zll
 * 创建时间：2019/6/17 0017
 * 修改人：
 * 更改时间：
 * 修改备注：
 */
public class CommCallMain implements IComponent {
    @Override
    public String getName() {
        //指定组件的名称
        return "CommCallMain";
    }

    @Override
    public boolean onCall(CC cc) {
        //在此处将组件内部的服务暴露给外部调用
        //组件内部的逻辑与外部完全解耦
        String actionName = cc.getActionName();
        switch (actionName) {
            case "CommMain": //响应CommMain为"BaseActivity"的组件调用,隐藏或者显示沉浸式
                //跳转到页面：ActivityA
                AppConfig.logs(cc.getParamItem("zll").toString());
                CCUtil.navigateTo(cc, MainActivity.class);
                //返回处理结果给调用方
                CC.sendCCResult(cc.getCallId(), CCResult.success());
                break;
            default:
                //其它actionName当前组件暂时不能响应，可以通过如下方式返回状态码为-12的CCResult给调用方
                CC.sendCCResult(cc.getCallId(), CCResult.errorUnsupportedActionName());
                break;
        }
        return false;
    }
}
