package com.mding.chatfeng.login;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.IComponentCallback;
import com.mding.chatfeng.base_common.AppConfig;
import com.mding.chatfeng.base_common.bean.login.LoginInBean;
import com.mding.chatfeng.base_common.components.base.BaseActivity;
import com.mding.chatfeng.base_common.request.body.LoginInBody;
import com.mding.chatfeng.base_common.request.create;
import com.mding.chatfeng.base_common.utils.GsonParamConverter;
import com.mding.chatfeng.base_common.utils.MD5Util;
import com.mding.chatfeng.base_common.utils.about_main_utils.IntentUtils;
import com.mding.chatfeng.base_common.utils.aboututils.MyLog;
import com.mding.chatfeng.base_common.utils.aboututils.NoDoubleClickUtils;
import com.mding.chatfeng.base_common.utils.aboututils.StrUtils;
import com.mding.chatfeng.base_common.utils.aboututils.ToastUtil;
import com.mding.chatfeng.login.utils.StringCode;

/**
 * 项目：ChatFengIM-master
 * 文件描述：Login组件 主界面（密码登录界面）
 * 作者：ljj
 * 创建时间：2019/6/12
 * 修改人：
 * 更改时间：
 * 修改备注：
 */
public class LoginActivity extends BaseActivity {

    TextView topTvTitle;
    EditText loginEdPhone;
    EditText loginEdPwd;
    LinearLayout topLinBack;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_reset_pwd);
//
//    }

    String phone;
    String intenPhoneNumber;
    String pwd;
    @Override
    protected int getLayoutView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();
        topTvTitle = findViewById(R.id.include_top_tv_title);
        topLinBack = findViewById(R.id.include_top_lin_newback);
        loginEdPhone = findViewById(R.id.login_ed_phone);
        loginEdPwd = findViewById(R.id.login_ed_pwd);

        topTvTitle.setText(getResources().getString(R.string.login_pwd_login_title));
        topLinBack.setVisibility(View.INVISIBLE);

        addOnClickListeners(R.id.login_btn_login
                , R.id.login_tv_sms_login
                , R.id.login_tv_forget_pwd
                , R.id.login_tv_register
        );

        Intent intent = getIntent();
        if (intent != null){
            intenPhoneNumber = intent.getStringExtra(StringCode.PHONE_NUMBER);
            loginEdPhone.setText(intenPhoneNumber);
        }
    }

    @Override
    public void onClick(View v) {
//        super.onClick(v);
        int i = v.getId();
        if (i == R.id.login_btn_login) {
            if (NoDoubleClickUtils.isDoubleClick()) {
                clickLogin();
            }

        } else if (i == R.id.login_tv_sms_login) {
            if (NoDoubleClickUtils.isDoubleClick()) {
                IntentUtils.JumpTo(SMSLoginActivity.class);
            }

        } else if (i == R.id.login_tv_forget_pwd) {
            if (NoDoubleClickUtils.isDoubleClick()) {
                IntentUtils.JumpTo(ResetPwdActivity.class);
            }

        } else if (i == R.id.login_tv_register) {
            if (NoDoubleClickUtils.isDoubleClick()) {
                IntentUtils.JumpTo(RegisterActivity.class);
            }

        }
//        switch(v.getId()){
//            case R.id.login_btn_login:
//                if (NoDoubleClickUtils.isDoubleClick()) {
//                    clickLogin();
//                }
//                break;
//
//            case R.id.login_tv_sms_login:
//                if (NoDoubleClickUtils.isDoubleClick()) {
//                    IntentUtils.JumpTo(SMSLoginActivity.class);
//                }
//                break;
//
//            case R.id.login_tv_forget_pwd:
//                if (NoDoubleClickUtils.isDoubleClick()) {
//                    IntentUtils.JumpTo(ResetPwdActivity.class);
//                }
//                break;
//
//            case R.id.login_tv_register:
//                if (NoDoubleClickUtils.isDoubleClick()) {
//                    IntentUtils.JumpTo(RegisterActivity.class);
//                }
//                break;
//
//        }
    }

    private void clickLogin() {
        phone = loginEdPhone.getText().toString().trim();
        pwd = loginEdPwd.getText().toString().trim();
        ToastUtil.isDebugShow("click");
        MyLog.i("LoginActivity", "---------------phone：" + phone + "------pwd(MD5)：" + MD5Util.string2MD5(pwd));
        if (StrUtils.isEmpty(phone)) {
            ToastUtil.show(getResources().getString(R.string.phone_is_null));
            return;
        }
        if (!StrUtils.isCorrectPhone(phone)) {
            ToastUtil.show(getResources().getString(R.string.phone_is_error));
            MyLog.i("loginActivity", "---------------------" + getResources().getString(R.string.phone_is_error));
            return;
        }
        if (StrUtils.isEmpty(pwd)) {
            ToastUtil.show(getResources().getString(R.string.pwd_is_null));
            return;
        }

        //TODO   请求登录接口
        AppConfig.logs("请求登录接口---调用=====：" + create.getComsApi().doCommonStr(create.login().controllersName, create.login().loginIn, new LoginInBody(phone, MD5Util.string2MD5(MD5Util.string2MD5(pwd)+"mding"))));
//        create.getComsApi().doCommon(create.login().controllersName, create.login().loginIn, new LoginInBody("18150960006", "e5e94876560ab8220f43cbd52854e80a")).build()
        create.getComsApi().doCommon(create.login().controllersName, create.login().loginIn, new LoginInBody(phone, MD5Util.string2MD5(MD5Util.string2MD5(pwd)+"mding"))).build()
                .callAsync(new IComponentCallback() {
                    @Override
                    public void onResult(CC loginCC, CCResult result) {
//                        AppConfig.logs("调用成败：" + result.isSuccess() + "  ---解析数据：" + mLoginInBean.getMsg());
                        AppConfig.logs("请求登录接口---调用成败：" + result.isSuccess());
                        if (result.isSuccess()) {
                            // 处理数据
                            LoginInBean mLoginInBean = new GsonParamConverter().json2Object(result.getDataItem(loginCC.getActionName()).toString(), LoginInBean.class);
                            onReceiveSuccess(mLoginInBean);
                        }
                    }
                });
    }

    public void onReceiveSuccess(LoginInBean loginInBean){
        LoginInBean.RecordBean recordBean = loginInBean.getRecord();
        if (recordBean != null){
            String nickName = recordBean.getNickName();
            String userId = recordBean.getUserId();
            int isFirstLogin = recordBean.getIsFirstLogin();
            // 是否第一次登录 1是 2否
            if (isFirstLogin == 1){
                IntentUtils.JumpToHaveOne(SetHeadImgActivity.class, StringCode.USER_ID, userId);
            }
            else if (isFirstLogin == 2){
                // 跳转到Home组件
                jump2Home();
            }

            MyLog.i("loginActivity", "---------------nickName：" + nickName + "-------------userId：" + userId);
        }

    }
    public void jump2Home() {
        CC.obtainBuilder("HomeCom")
                .setActionName("MainActivity")
                .build()
                .callAsyncCallbackOnMainThread(new IComponentCallback() {
                    @Override
                    public void onResult(CC cc, CCResult result) {
                        //此onResult在主线程中运行

                    }
                });
    }

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            Intent home = new Intent(Intent.ACTION_MAIN);
            home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            home.addCategory(Intent.CATEGORY_HOME);
            startActivity(home);
        }
        return true;
    }

    /**
     * 添加组件到字典监听列表成功
     */
   /*
    @Override
    protected void addSucess() {

    }

    */
    /**
     * 远程数据返回时候，调用  UserStateManager.setLoginUser(new User(1, "wdh"));此方法 触发所有监听组件
     */
   /*
    @Override
    protected void listenData() {

        if (loginUserTextView != null) {

            AppConfig.logs(user == null ? "null" : user.getUserName());

           loginUserTextView.setText(getString(R.string.show_login_user, user == null ? "null" : user.getUserName()));
    }
    }
    */
}