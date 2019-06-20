package com.mding.chatfeng.base_common.components.base;

import android.app.Activity;
import android.os.SystemClock;
import android.widget.TextView;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.IComponentCallback;
import com.billy.cc.core.component.IDynamicComponent;
import com.billy.cc.core.component.IMainThread;
import com.mding.chatfeng.base_common.AppConfig;
import com.mding.chatfeng.base_common.bean.login.User;
import com.mding.chatfeng.base_common.components.common.IC_Common;
import com.mding.chatfeng.base_common.components.common.VaddDyCompent;

import static com.mding.chatfeng.base_common.components.base.DyCompentRegester.LoginUserObserverComponent.OBSERVER_ACTION_NAME;

public abstract class DyCompentRegester extends  Activity{
    CC componentBCC;
    protected LoginUserObserverComponent loginUserObserverComponent;
    protected abstract void addSucess();
    protected abstract void listenData();

    public DyCompentRegester() {
        addDynamicComponent();
    }
    protected void removeDynamicComponent() {
        if (loginUserObserverComponent != null) {
            //从CC框架中注销此动态组件
            CC.unregisterComponent(loginUserObserverComponent);
            //从ComponentB的登录状态监听列表中移除此动态组件：此后，登录状态改变将不再尝试通知此动态组件
            CC.obtainBuilder("ComponentB")
                    .setActionName("removeLoginObserver")
                    .addParam("componentName", loginUserObserverComponent.getName())
                    .build()
                    .callAsync();
            loginUserObserverComponent = null;
       /*     loginUserButton.setText(R.string.observe_login_user);
            loginUserTextView.setText("");*/
        }
    }

    protected void addDynamicComponent() {
        if (loginUserObserverComponent == null) {
            //创建动态组件对象
            loginUserObserverComponent = new LoginUserObserverComponent();
            //向CC注册此动态组件
            // 登录状态改变后，UserStateManager.onUserLoginStateUpdated()方法中会通过CC调用通知此组件当前的登录状态
            CC.registerComponent(loginUserObserverComponent);
            //通过CC调用ComponentB，将此动态组件注册为用户登录状态的监听器
            CC.obtainBuilder(IC_Common.class.getSimpleName())
                    .setActionName(VaddDyCompent.class.getSimpleName())
                    .addParam("dyComponentName", loginUserObserverComponent.getName())
                    .addParam("dyActionName", OBSERVER_ACTION_NAME)
                    .build()
                    .callAsync(new IComponentCallback() {
                        @Override
                        public void onResult(CC cc, CCResult result) {
                            AppConfig.logs(result.isSuccess()+"");
                        }
                    });


//            loginUserButton.setText("注销用户动态组件");
        }
    }




    protected  User user;

    /**
     * 监听用户登录状态的动态组件
     */
    class LoginUserObserverComponent implements IDynamicComponent, IMainThread {

        String observerComponentName;
        static final String OBSERVER_ACTION_NAME = "loginUserState";

        LoginUserObserverComponent() {
            //指定此动态组件的ComponentName为一个唯一值，不会因为activity有多个对象而出现重复
            this.observerComponentName = getClass().getSimpleName() + SystemClock.uptimeMillis();
        }

        @Override
        public String getName() {
            return observerComponentName;
        }

        @Override
        public boolean onCall(CC cc) {
            String actionName = cc.getActionName();
            if (OBSERVER_ACTION_NAME.equals(actionName)) {
                //在进入此处时，当前线程一定为主线程（是在shouldActionRunOnMainThread方法中指定的）
                return onLoginUserChanged(cc);
            }
            CC.sendCCResult(cc.getCallId(), CCResult.errorUnsupportedActionName());
            return false;
        }

        private boolean onLoginUserChanged(CC cc) {
             user = cc.getParamItem("user");
//            if (loginUserTextView != null) {
            listenData();
//                AppConfig.logs(user == null ? "null" : user.getUserName());

//                loginUserTextView.setText(getString(R.string.show_login_user, user == null ? "null" : user.getUserName()));
//            }
            CC.sendCCResult(cc.getCallId(), CCResult.success());
            return false;
        }

        @Override
        public Boolean shouldActionRunOnMainThread(String actionName, CC cc) {
            if (OBSERVER_ACTION_NAME.equals(actionName)) {
                //指定observerActionName被调用时，onCall方法在主线程运行
                return true;
            }
            return null;
        }
    }


    @Override
    protected void onDestroy() {
        if (componentBCC != null) {
            componentBCC.cancel();
        }
        removeDynamicComponent();
        super.onDestroy();
    }


}
