package com.mding.chatfeng.base_common.components.common;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;

import com.mding.chatfeng.base_common.AppConfig;
import com.mding.chatfeng.base_common.components.base.UserStateManager;


public class VaddDyCompent implements IA_Common {
    @Override
    public String getActionName() {
        return getClass().getSimpleName();
    }

    @Override
    public boolean onActionCall(CC cc) {
        String dynamicComponentName = cc.getParamItem("dyComponentName");
        String dynamicActionName = cc.getParamItem("dyActionName");
        AppConfig.logs(dynamicComponentName+"+++++++++++"+dynamicActionName);
        boolean success = UserStateManager.addObserver(dynamicComponentName, dynamicActionName);
        //告诉注册入口，组件是否成功添加到字典中
        CCResult result = success ? CCResult.success() : CCResult.error("");
        CC.sendCCResult(cc.getCallId(), result);
        //同步调用
        return false;
    }
}
