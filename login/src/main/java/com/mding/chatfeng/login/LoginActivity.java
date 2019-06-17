package com.mding.chatfeng.login;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mding.chatfeng.base_common.AppConfig;
import com.mding.chatfeng.base_common.bean.login.User;
import com.mding.chatfeng.base_common.components.base.DyCompentRegester;
import com.mding.chatfeng.base_common.components.base.UserStateManager;



public class LoginActivity extends DyCompentRegester {
//    @BindView(R.id.login_user_state_observer)
//     TextView loginUserButton;
//    @BindView(R.id.textView)
//     TextView loginUserTextView;
    //    private LoginUserObserverComponent loginUserObserverComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        loginUserButton = (TextView) findViewById(R.id.login_user_state_observer);
//        loginUserButton.setOnClickListener(this);
//        loginUserTextView = (TextView) findViewById(R.id.textView);
//        loginUserTextView.setOnClickListener(this);
//        loginUserButton.setText("注销用户动态组件");
    }


//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.login_user_state_observer:
//                if (loginUserObserverComponent == null) {
//                    addDynamicComponent();
//                    loginUserButton.setText("注销用户动态组件");
//                } else {
//                    removeDynamicComponent();
//                    loginUserButton.setText(R.string.observe_login_user);
//                    loginUserTextView.setText("");
//                }
//                break;
//            case R.id.textView:
//                UserStateManager.setLoginUser(new User(1, "wdh"));
////              UserStateManager.setLoginUser(new UserBean("123123","124123"));
//                break;
//        }
//
//
//    }

    /**
     * 添加组件到字典监听列表成功
     */
    @Override
    protected void addSucess() {

    }

    /**
     * 远程数据返回时候，调用  UserStateManager.setLoginUser(new User(1, "wdh"));此方法 触发所有监听组件
     */
    @Override
    protected void listenData() {

//        if (loginUserTextView != null) {
//
//            AppConfig.logs(user == null ? "null" : user.getUserName());
//
//            loginUserTextView.setText(getString(R.string.show_login_user, user == null ? "null" : user.getUserName()));
//        }
    }

//    @OnClick({R.id.login_user_state_observer, R.id.textView})
//    public void onViewClick(View view) {
//        switch (view.getId()) {
//            case R.id.login_user_state_observer:
//                Toast.makeText(this,"4646",Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.textView:
//                break;
//        }
//    }
}