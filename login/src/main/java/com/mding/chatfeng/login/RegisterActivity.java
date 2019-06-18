package com.mding.chatfeng.login;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.IComponentCallback;
import com.mding.chatfeng.base_common.AppConfig;
import com.mding.chatfeng.base_common.components.base.BaseActivity;
import com.mding.chatfeng.base_common.request.body.getSmsCodeBody;
import com.mding.chatfeng.base_common.request.body.registerBody;
import com.mding.chatfeng.base_common.request.create;
import com.mding.chatfeng.base_common.utils.MD5Util;
import com.mding.chatfeng.base_common.utils.about_main_utils.IntentUtils;
import com.mding.chatfeng.base_common.utils.aboutsystem.AppManager;
import com.mding.chatfeng.base_common.utils.aboututils.MyLog;
import com.mding.chatfeng.base_common.utils.aboututils.NoDoubleClickUtils;
import com.mding.chatfeng.base_common.utils.aboututils.StrUtils;
import com.mding.chatfeng.base_common.utils.aboututils.ToastUtil;
import com.mding.chatfeng.login.utils.StringCode;

/**
 * 项目：ChatFengIM-master
 * 文件描述：注册界面
 * 作者：ljj
 * 创建时间：2019/6/12
 * 修改人：
 * 更改时间：
 * 修改备注：
 */
public class RegisterActivity extends BaseActivity {

    TextView topTvTitle;
    TextView registerTvGetCode;
    EditText registerEdPhone;
    EditText registerEdCode;
    EditText registerEdPwd;
    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);
//
//    }
    String phone;
    String pwd;
    String code;
    @Override
    protected int getLayoutView() {
        return R.layout.activity_register;
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();
        topTvTitle = findViewById(R.id.include_top_tv_title);
        registerTvGetCode = findViewById(R.id.reg_tv_get_code);
        registerEdPhone = findViewById(R.id.reg_ed_phone);
        registerEdCode = findViewById(R.id.reg_ed_code);
        registerEdPwd = findViewById(R.id.reg_ed_pwd);

        topTvTitle.setText(getResources().getString(R.string.register_title));

        addOnClickListeners(R.id.reg_btn_register
                , R.id.include_top_lin_newback
                , R.id.reg_tv_get_code
        );
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        int i = v.getId();
        if (i == R.id.reg_btn_register) {
            if (NoDoubleClickUtils.isDoubleClick()) {
                clickRegister();
            }

        } else if (i == R.id.reg_tv_get_code) {
            if (NoDoubleClickUtils.isDoubleClick()) {
                clickGetCode();
            }

        } else if (i == R.id.include_top_lin_newback) {
            if (NoDoubleClickUtils.isDoubleClick()) {
                AppManager.getAppManager().finishActivity(RegisterActivity.class);
            }

        }
//        switch(v.getId()){
//            case R.id.reg_btn_register:
//                if (NoDoubleClickUtils.isDoubleClick()) {
//                    clickRegister();
//                }
//                break;
//
//            case R.id.reg_tv_get_code:
//                if (NoDoubleClickUtils.isDoubleClick()) {
//                    clickGetCode();
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
        phone = registerEdPhone.getText().toString().trim();
        if (StrUtils.isEmpty(phone)) {
            ToastUtil.show(getResources().getString(R.string.phone_is_null));
            return;
        }
        if (!StrUtils.isCorrectPhone(phone)) {
            ToastUtil.show(getResources().getString(R.string.phone_is_error));
            return;
        }
        // TODO 请求获取验证码接口  type 2  注册
        AppConfig.logs("请求获取验证码接口---调用=====：" + create.getComsApi().doCommonStr(create.login().controllersName, create.login().getSmsCode, new getSmsCodeBody(phone, StringCode.GET_SMS_CODE_REGISTER)));
        create.getComsApi().doCommon(create.login().controllersName, create.login().getSmsCode, new getSmsCodeBody(phone, StringCode.GET_SMS_CODE_REGISTER)).build()
                .callAsync(new IComponentCallback() {
                    @Override
                    public void onResult(CC loginCC, CCResult result) {
//                        AppConfig.logs("调用成败：" + result.isSuccess() + "  ---解析数据：" + mLoginInBean.getMsg());
                        AppConfig.logs("请求获取验证码接口---调用成败：" + result.isSuccess());
                        if (result.isSuccess()) {
                            timer.start();
                            registerEdCode.setFocusable(true);
                            registerEdCode.setFocusableInTouchMode(true);
//                            registerEdCode.requestFocus();
                            MyLog.i("registerActivity", "---------------获取验证码成功");
                        }
                    }
                });
    }

    private CountDownTimer timer =new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long l) {
            try {
                registerTvGetCode.setText((l / 1000) + "s");
                registerTvGetCode.setClickable(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onFinish() {
            try {
                registerTvGetCode.setEnabled(true);
                registerTvGetCode.setClickable(true);
                registerTvGetCode.setText(getResources().getString(R.string.login_get_code));
//                regTvSendCode.setText("获取验证码");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private void clickRegister() {
        timer.cancel();
        phone = registerEdPhone.getText().toString().trim();
        pwd = registerEdPwd.getText().toString().trim();
        code = registerEdCode.getText().toString().trim();
        if (StrUtils.isEmpty(phone)){
            ToastUtil.show(getResources().getString(R.string.phone_is_null));
            return;
        }
        if (!StrUtils.isCorrectPhone(phone)){
            ToastUtil.show(getResources().getString(R.string.phone_is_error));
            return;
        }
        if (StrUtils.isEmpty(pwd)){
            ToastUtil.show(getResources().getString(R.string.pwd_is_null));
            return;
        }
        if (StrUtils.isEmpty(code)){
            ToastUtil.show(getResources().getString(R.string.code_is_null));
            return;
        }
        // TODO 请求用户注册接口
        create.getComsApi().doCommon(create.login().controllersName, create.login().register, new registerBody(phone, MD5Util.string2MD5(MD5Util.string2MD5(pwd)+"mding"), code)).build()
                .callAsync(new IComponentCallback() {
                    @Override
                    public void onResult(CC registerCC, CCResult result) {
//                        AppConfig.logs("调用成败：" + result.isSuccess() + "  ---解析数据：" + mLoginInBean.getMsg());
                        AppConfig.logs("请求用户注册接口---调用成败：" + result.isSuccess());
                        if (result.isSuccess()) {
                            IntentUtils.JumpToHaveOne(LoginActivity.class, StringCode.PHONE_NUMBER, phone);
                        }
                    }
                });
    }

}
