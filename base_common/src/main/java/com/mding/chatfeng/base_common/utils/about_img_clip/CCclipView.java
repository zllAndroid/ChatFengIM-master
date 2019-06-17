package com.mding.chatfeng.base_common.utils.about_img_clip;

import android.graphics.Bitmap;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.CCUtil;
import com.billy.cc.core.component.IComponent;
import com.mding.chatfeng.base_common.AppConfig;
import com.mding.chatfeng.base_common.utils.HeadFileUtils;

/**
 * 项目：ChatFengIM-master
 * 文件描述：
 * 作者：zll
 * 创建时间：2019/6/14 0014
 * 修改人：
 * 更改时间：
 * 修改备注：
 */
public class CCclipView implements IComponent {
    @Override
    public String getName() {
        //指定组件的名称
        return "CCclipView";
    }

    @Override
    public boolean onCall(CC cc) {
        //在此处将组件内部的服务暴露给外部调用
        //组件内部的逻辑与外部完全解耦
        String actionName = cc.getActionName();
        switch (actionName) {
            case "cclip": //响应actionName为"showActivity"的组件调用
                String path= cc.getParamItem("path");
                Bitmap bitmap =ClipViewLayout.decodeSampledBitmap(path, 720, 1280);
                //跳转到页面：ActivityA
//                CCUtil.navigateTo(cc, MainActivity.class);
                //返回处理结果给调用方
                CC.sendCCResult(cc.getCallId(), CCResult.success("bitmap",bitmap));
                break;
            case "ccrotation": //响应actionName为"showActivity"的组件调用
                String path1= cc.getParamItem("path");
                int rotation = ClipViewLayout.getExifOrientation(path1); //查询旋转角度
                //返回处理结果给调用方
                CC.sendCCResult(cc.getCallId(), CCResult.success("rotation",rotation));
                break;
            default:
                //其它actionName当前组件暂时不能响应，可以通过如下方式返回状态码为-12的CCResult给调用方
                CC.sendCCResult(cc.getCallId(), CCResult.errorUnsupportedActionName());
                break;
        }
        return false;
    }
}