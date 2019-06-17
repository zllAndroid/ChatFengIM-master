package com.mding.chatfeng.home.component;

import android.util.Log;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.CCUtil;
import com.billy.cc.core.component.IComponent;
import com.billy.cc.core.component.annotation.SubProcess;
import com.mding.chatfeng.home.MainActivity;

@SubProcess(":home")
public class IC_CheckLogin implements IComponent {
    @Override
    public String getName() {

        return "home.IC_CheckLogin";
    }

    @Override
    public boolean onCall(CC cc) {
        String actionName = cc.getActionName();
        switch (actionName) {
            case "start2Home":
                //欢迎页直接登入主界面，如果进不去则通知欢迎页跳转到登入页
                openHome(cc);
                break;

            default:
                //这个逻辑分支上没有调用CC.sendCCResult(...),是一种错误的示例
                //并且方法的返回值为false，代表不会异步调用CC.sendCCResult(...)
                //在LocalCCInterceptor中将会返回错误码为-10的CCResult
                break;
        }
        return false;
    }



    private void onLoading(CC cc) {
        //此处写异步加载数据函数
        Log.d("xx","此处写异步加载数据函数");

        while (true){
            try {
                Thread.sleep(1000);
                CC.obtainBuilder("mainActivityUserObserver_")
                        .setActionName("loginUserState")
                        .build().callAsync();
                Log.d("xx","onLoading~~~");
            }catch (Exception e){
                e.printStackTrace();
            }
        }


    }



    private void openHome(CC cc) {
        //是否需要自动登入,true表示为token未失效
        //需要调用网络请求得到此状态
        boolean isNeedAutoLogin=true;
        if(isNeedAutoLogin){
            //先预加载数据,让显示速度更快
            loadData(cc);
            //让欢迎页等待5秒,再显示主界面
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            CCUtil.navigateTo(cc, MainActivity.class);
            CC.sendCCResult(cc.getCallId(), CCResult.success());
        }else{
            //通知欢迎页,无法用Token直接登入,请用登入页的账号密码登入
            CC.sendCCResult(cc.getCallId(), CCResult.error("toLoginAct"));
        }
    }

    private void loadData(CC cc) {
        //加载数据，本地或首次登陆数据
    }
}
