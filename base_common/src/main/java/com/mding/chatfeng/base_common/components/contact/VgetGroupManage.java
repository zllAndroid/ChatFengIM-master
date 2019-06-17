package com.mding.chatfeng.base_common.components.contact;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.mding.chatfeng.base_common.AppConfig;
import com.mding.chatfeng.base_common.request.create;

/**
 * 独立业务
 */
public class VgetGroupManage implements IA_Contact {


    @Override
    public String getActionName() {
        return create.contact().getGroupManage.methodName;
    }

    @Override
    public boolean onActionCall(CC cc) {
        AppConfig.logs("-------------------------------------busyhaibusyhai-----------------------------------------------------------------------------------");
        CC.sendCCResult(cc.getCallId(), CCResult.success());

        return false;
    }
}