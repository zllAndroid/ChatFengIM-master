package com.mding.chatfeng.login;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.IComponentCallback;
import com.mding.chatfeng.base_common.AppConfig;
import com.mding.chatfeng.base_common.bean.login.SmsLoginBean;
import com.mding.chatfeng.base_common.components.base.BaseActivity;
import com.mding.chatfeng.base_common.request.body.getSmsCodeBody;
import com.mding.chatfeng.base_common.request.body.smsLoginBody;
import com.mding.chatfeng.base_common.request.create;
import com.mding.chatfeng.base_common.utils.about_main_utils.IntentUtils;
import com.mding.chatfeng.base_common.utils.aboutsystem.AppManager;
import com.mding.chatfeng.base_common.utils.aboututils.MyLog;
import com.mding.chatfeng.base_common.utils.aboututils.NoDoubleClickUtils;
import com.mding.chatfeng.base_common.utils.aboututils.StrUtils;
import com.mding.chatfeng.base_common.utils.aboututils.ToastUtil;
import com.mding.chatfeng.login.utils.StringCode;

/**
 * 项目：ChatFengIM-master
 * 文件描述：验证码登录界面
 * 作者：ljj
 * 创建时间：2019/6/12
 * 修改人：
 * 更改时间：
 * 修改备注：
 */
public class SMSLoginActivity extends BaseActivity {

    TextView topTvTitle;
    TextView smsLoginTvGetCode;
    EditText smsLoginEdPhone;
    EditText smsLoginEdCode;
    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_smslogin);
//
//    }
    String phone;
    String code;
    @Override
    protected int getLayoutView() {
        return R.layout.activity_smslogin;
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();
        topTvTitle = findViewById(R.id.include_top_tv_title);
        smsLoginTvGetCode = findViewById(R.id.sms_login_tv_get_code);
        smsLoginEdPhone = findViewById(R.id.sms_login_ed_phone);
        smsLoginEdCode = findViewById(R.id.sms_login_ed_code);

        topTvTitle.setText(getResources().getString(R.string.login_sms_login_title));

        addOnClickListeners(R.id.sms_login_btn_login
                , R.id.sms_login_tv_pwd_login
                , R.id.sms_login_tv_get_code
                , R.id.sms_login_tv_new_resgister
                , R.id.include_top_lin_newback
        );

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        int i = v.getId();
        if (i == R.id.sms_login_btn_login) {
            if (NoDoubleClickUtils.isDoubleClick()) {
                // 短信验证码登录接口
                clickLogin();
            }

        } else if (i == R.id.sms_login_tv_pwd_login) {
            if (NoDoubleClickUtils.isDoubleClick()) {
                IntentUtils.JumpFinishTo(SMSLoginActivity.this, LoginActivity.class);
            }

        } else if (i == R.id.sms_login_tv_get_code) {
            if (NoDoubleClickUtils.isDoubleClick()) {
                // 请求获取验证码接口  type 1  登录
                clickGetCode();
            }

        } else if (i == R.id.sms_login_tv_new_resgister) {
            if (NoDoubleClickUtils.isDoubleClick()) {
                IntentUtils.JumpFinishTo(SMSLoginActivity.this, RegisterActivity.class);
            }

        } else if (i == R.id.include_top_lin_newback) {
            if (NoDoubleClickUtils.isDoubleClick()) {
                AppManager.getAppManager().finishActivity(RegisterActivity.class);
            }

        }
//            case R.id.sms_login_btn_login:
//                if (NoDoubleClickUtils.isDoubleClick()) {
//                    // 短信验证码登录接口
//                    clickLogin();
//                }
//                break;
//
//            case R.id.sms_login_tv_pwd_login:
//                if (NoDoubleClickUtils.isDoubleClick()) {
//                    IntentUtils.JumpFinishTo(SMSLoginActivity.this, LoginActivity.class);
//                }
//                break;
//
//            case R.id.sms_login_tv_get_code:
//                if (NoDoubleClickUtils.isDoubleClick()) {
//                    // 请求获取验证码接口  type 1  登录
//                    clickGetCode();
//                }
//                break;
//
//            case R.id.sms_login_tv_new_resgister:
//                if (NoDoubleClickUtils.isDoubleClick()) {
//                    IntentUtils.JumpFinishTo(SMSLoginActivity.this, RegisterActivity.class);
//                }
//                break;
//
//            case R.id.include_top_lin_newback:
//                if (NoDoubleClickUtils.isDoubleClick()) {
//                    AppManager.getAppManager().finishActivity(RegisterActivity.class);
//                }
//                break;
//
//        }
    }

    private void clickGetCode() {
        phone = smsLoginEdPhone.getText().toString().trim();
        if (StrUtils.isEmpty(phone)) {
            ToastUtil.show(getResources().getString(R.string.phone_is_null));
            return;
        }
        boolean correctPhone = StrUtils.isCorrectPhone(phone);
        MyLog.i("registerActivity", "---------------correctPhone：" + correctPhone);

        if (!StrUtils.isCorrectPhone(phone)) {
            ToastUtil.show(getResources().getString(R.string.phone_is_error));
            MyLog.i("registerActivity", "---------------" + getResources().getString(R.string.phone_is_error));
            return;
        }
        // 请求获取验证码接口  type 1  登录
        AppConfig.logs("请求获取验证码接口---调用=====：" + create.getComsApi().doCommonStr(create.login().controllersName, create.login().getSmsCode, new getSmsCodeBody(phone, StringCode.GET_SMS_CODE_LOGIN)));
        create.getComsApi().doCommon(create.login().controllersName, create.login().getSmsCode, new getSmsCodeBody(phone, StringCode.GET_SMS_CODE_LOGIN)).build()
                .callAsync(new IComponentCallback() {
                    @Override
                    public void onResult(CC loginCC, CCResult result) {
//                        AppConfig.logs("调用成败：" + result.isSuccess() + "  ---解析数据：" + mLoginInBean.getMsg());
                        AppConfig.logs("请求获取验证码接口---调用成败：" + result.isSuccess());
                        if (result.isSuccess()) {
                            timer.start();
                            smsLoginEdCode.setFocusable(true);
                            smsLoginEdCode.setFocusableInTouchMode(true);
//                            smsLoginEdCode.requestFocus();
                            MyLog.i("registerActivity", "---------------获取验证码成功");
                        }
                    }
                });
    }

    private CountDownTimer timer =new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long l) {
            try {
                smsLoginTvGetCode.setText((l / 1000) + "s");
                smsLoginTvGetCode.setClickable(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onFinish() {
            try {
                smsLoginTvGetCode.setEnabled(true);
                smsLoginTvGetCode.setClickable(true);
                smsLoginTvGetCode.setText(getResources().getString(R.string.login_get_code));
//                regTvSendCode.setText("获取验证码");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private void clickLogin() {
        timer.cancel();
        phone = smsLoginEdPhone.getText().toString().trim();
        code = smsLoginEdCode.getText().toString().trim();
        ToastUtil.isDebugShow("click");
        MyLog.i("LoginActivity", "---------------phone：" + phone + "------code：" + code);
        if (StrUtils.isEmpty(phone)) {
            ToastUtil.show(getResources().getString(R.string.phone_is_null));
            return;
        }
        if (!StrUtils.isCorrectPhone(phone)) {
            ToastUtil.show(getResources().getString(R.string.phone_is_error));
            return;
        }
        if (StrUtils.isEmpty(code)) {
            ToastUtil.show(getResources().getString(R.string.pwd_is_null));
            return;
        }
        MyLog.i("LoginActivity", "---------------phone：" + phone + "---------code：" + code);

        //TODO   请求验证码登录接口
//        create.getComsApi().doCommon(create.login().controllersName, create.login().loginIn, new LoginInBody("18150960006", "e5e94876560ab8220f43cbd52854e80a")).build()
        create.getComsApi().doCommon(create.login().controllersName, create.login().smsLogin, new smsLoginBody(phone, code)).build()
                .callAsync(new IComponentCallback() {
                    @Override
                    public void onResult(CC loginCC, CCResult result) {
                        //                        AppConfig.logs("调用成败：" + result.isSuccess() + "  ---解析数据：" + mLoginInBean.getMsg());
                        AppConfig.logs("请求验证码登录接口---调用成败：" + result.isSuccess());
                        if (result.isSuccess()) {
//                            SmsLoginBean mSmsLoginInBean = new GsonParamConverter().json2Object(result.getDataItem(loginCC.getActionName()).toString(), SmsLoginBean.class);
//                            // 处理数据
//                            onReceiveSuccess(mSmsLoginInBean);
                            // 跳转到Home组件
                            jump2Home();
                        }
                    }
                });
    }

    private void onReceiveSuccess(SmsLoginBean mSmsLoginInBean) {
        if (mSmsLoginInBean.getCode() == 9999)
            ToastUtil.show(mSmsLoginInBean.getMsg());
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

}
