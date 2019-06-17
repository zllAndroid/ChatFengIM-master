package com.mding.chatfeng.login.components.task;

import android.util.Log;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.CCUtil;
import com.mding.chatfeng.login.LoginActivity;



/**
 * 使用Toekn作为登入依据
 * 用在欢迎页&登入页
 */
public class IA_FindTokenAndLogin implements IActionProcessor{
    long splashTimeSet=5000;//毫秒
     long splashTime=0;
    //找到Token
    Boolean isFind=false;
    //Token未失效
    Boolean isCan=true;
    @Override
    public String getActionName() {
        return "VupNickName";
    }

    @Override
    public boolean onActionCall(CC cc) {

  /*      boolean is2Act= (boolean) cc.getParams().get("toAct");
        if(is2Act)
        {
            CCUtil.navigateTo(cc, LoginActivity.class);
            return false;
        }
*/
        long startTime = System.currentTimeMillis();
        doFind(cc);
        long consumingTime = System.currentTimeMillis() - startTime;
        if(splashTimeSet>consumingTime)
            splashTime=splashTimeSet-consumingTime;



        if (isFind&&isCan){
            //如果找到token并token有效，则通知欢迎页执行通知Home页面


            //先通知欢迎页显示加载进度条
            CC.sendCCResult(cc.getCallId(), CCResult.error("loading"));
            //请求至Home模块，待Home模块数据加载完毕，回调此处执行通知欢迎页做finish
            CC.sendCCResult(cc.getCallId(), CCResult.success());

        }else {
            //如果找不到Token或token失效则等待splashTime秒跳转到登入页，并通知欢迎页做finish
            try {
                Thread.sleep(splashTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.i("xfff", "===lor:" +  CCResult.success()+"============="+cc.getParamItem("wdh"));
            CC.sendCCResult(cc.getCallId(), CCResult.success());
            CCUtil.navigateTo(cc, LoginActivity.class);
        }








        return false;
    }

    private void doFind(CC cc){
        //从缓存或数据库获取token，存在并未失效，回调给欢迎页
         isFind=false;
         isCan=true;
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }





}