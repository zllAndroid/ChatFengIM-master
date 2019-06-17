package com.mding.chatfeng.base_common.components.person;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.mding.chatfeng.base_common.components.contact.IA_Contact;
import com.mding.chatfeng.base_common.request.create;


public class VupNickName implements IA_Person {


    @Override
    public String getActionName() {
        return create.person().upNickName.methodName;
    }

    @Override
    public boolean onActionCall(CC cc) {
        CC.sendCCResult(cc.getCallId(), CCResult.success());

        return false;
    }







}