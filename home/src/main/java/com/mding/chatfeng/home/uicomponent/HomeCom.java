package com.mding.chatfeng.home.uicomponent;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.CCUtil;
import com.billy.cc.core.component.IComponent;
import com.mding.chatfeng.home.MainActivity;

/**
 * 项目：ChatFengIM-master
 * 文件描述：Home组件（跨模块跳转的组件调用响应）
 * 作者：ljj
 * 创建时间：2019/6/17
 * 修改人：
 * 更改时间：
 * 修改备注：
 */
public class HomeCom implements IComponent {
    @Override
    public String getName() {
        //指定组件的名称
        return "HomeCom";
    }
    @Override
    public boolean onCall(CC cc) {
        String actionName = cc.getActionName();
        switch (actionName) {
            case "MainActivity": //响应actionName为"showActivity"的组件调用
                //跳转到页面：ActivityA
                CCUtil.navigateTo(cc, MainActivity.class);
                //返回处理结果给调用方
                CC.sendCCResult(cc.getCallId(), CCResult.success());
                //同步方式实现（在return之前听过CC.sendCCResult()返回组件调用结果），return false
                return false;

            default:
                //其它actionName当前组件暂时不能响应，可以通过如下方式返回状态码为-12的CCResult给调用方
                CC.sendCCResult(cc.getCallId(), CCResult.errorUnsupportedActionName());
                return false;
        }
    }
}
