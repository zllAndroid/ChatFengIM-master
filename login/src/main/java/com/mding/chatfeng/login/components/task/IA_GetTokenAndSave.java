package com.mding.chatfeng.login.components.task;


import android.util.Log;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;

/**
 * 根据登入请求信息获取Token
 * 用在登入界面
 */
public class IA_GetTokenAndSave implements IActionProcessor{

    @Override
    public String getActionName() {
        return "IA_GetTokenAndSave";
    }

    @Override
    public boolean onActionCall(CC cc) {
        //由登入页面请求到此组件
        //此处获得登入页的账号密码，并请求网络获得返回数据并解析
        //解析成功后存储token并调用home模块，然后回调通知登入页


        Log.d("xx","IA_Login:IA_GetTokenAndSave");
        CC.sendCCResult(cc.getCallId(), CCResult.success());
        return false;
    }
}
