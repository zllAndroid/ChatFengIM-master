package com.mding.chatfeng.login;

import android.os.CountDownTimer;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.IComponentCallback;
import com.mding.chatfeng.base_common.AppConfig;
import com.mding.chatfeng.base_common.bean.person.UpPassWordBean;
import com.mding.chatfeng.base_common.components.base.BaseActivity;
import com.mding.chatfeng.base_common.request.body.getSmsCodeBody;
import com.mding.chatfeng.base_common.request.create;
import com.mding.chatfeng.base_common.utils.MD5Util;
import com.mding.chatfeng.base_common.utils.about_main_utils.IntentUtils;
import com.mding.chatfeng.base_common.utils.aboututils.MyLog;
import com.mding.chatfeng.base_common.utils.aboututils.NoDoubleClickUtils;
import com.mding.chatfeng.base_common.utils.aboututils.StrUtils;
import com.mding.chatfeng.base_common.utils.aboututils.ToastUtil;
import com.mding.chatfeng.login.utils.StringCode;

/**
 * 项目：ChatFengIM-master
 * 文件描述：重设密码界面
 * 作者：ljj
 * 创建时间：2019/6/12
 * 修改人：
 * 更改时间：
 * 修改备注：
 */
public class ResetPwdActivity extends BaseActivity {

    TextView topTvTitle;
    TextView resetPwdTvGetCode;
    EditText resetPwdEdPhone;
    EditText resetPwdEdCode;
    EditText resetPwdEdNewPwd;
    ImageView resetPwdIvEye;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_reset_pwd);
//
////        EditText editText = findViewById(R.id.login_ed_pwd);
////        // 密码可见
////        editText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
////        // 密码不可见
////        editText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD|InputType.TYPE_CLASS_TEXT);
//
//    }

    String phone;
    String code;
    String newPwd;
    @Override
    protected int getLayoutView() {
        return R.layout.activity_reset_pwd;
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();
        topTvTitle = findViewById(R.id.include_top_tv_title);
        resetPwdTvGetCode = findViewById(R.id.reset_pwd_tv_get_code);
        resetPwdEdPhone = findViewById(R.id.reset_pwd_ed_phone);
        resetPwdEdCode = findViewById(R.id.reset_pwd_ed_code);
        resetPwdEdNewPwd = findViewById(R.id.reset_pwd_ed_new_pwd);
        resetPwdIvEye = findViewById(R.id.reset_pwd_iv_eye);

        topTvTitle.setText(getResources().getString(R.string.reset_pwd_title));

        addOnClickListeners(
                R.id.reset_pwd_tv_get_code
                , R.id.reset_pwd_btn_sure
                , R.id.reset_pwd_iv_eye
                , R.id.include_top_lin_newback
        );
    }

    boolean isClicked = true;
    @Override
    public void onClick(View v) {
        super.onClick(v);
        int i = v.getId();
        if (i == R.id.reset_pwd_tv_get_code) {
            if (NoDoubleClickUtils.isDoubleClick()) {
                // TODO 请求获取短信接口  type 3  修改登录密码
                clickGetCode();
            }

        } else if (i == R.id.reset_pwd_btn_sure) {
            if (NoDoubleClickUtils.isDoubleClick()) {
                // TODO 请求用户注册接口
                clickResetPwd();
            }

        } else if (i == R.id.reset_pwd_iv_eye) {
            if (NoDoubleClickUtils.isDoubleClick()) {
                setPwdVisible();
            }

        } else if (i == R.id.include_top_lin_newback) {
            if (NoDoubleClickUtils.isDoubleClick()) {
                IntentUtils.JumpFinishTo(ResetPwdActivity.this, LoginActivity.class);
            }

        }
//        switch(v.getId()){
//            case R.id.reset_pwd_tv_get_code:
//                if (NoDoubleClickUtils.isDoubleClick()) {
//                    // TODO 请求获取短信接口  type 3  修改登录密码
//                    clickGetCode();
//                }
//            break;
//
//            case R.id.reset_pwd_btn_sure:
//                if (NoDoubleClickUtils.isDoubleClick()) {
//                    // TODO 请求用户注册接口
//                    clickResetPwd();
//                }
//            break;
//
//            case R.id.reset_pwd_iv_eye:
//                if (NoDoubleClickUtils.isDoubleClick()) {
//                    setPwdVisible();
//                }
//            break;
//
//            case R.id.include_top_lin_newback:
//                if (NoDoubleClickUtils.isDoubleClick()) {
//                    IntentUtils.JumpFinishTo(ResetPwdActivity.this, LoginActivity.class);
//                }
//            break;
//
//        }
    }

    private void setPwdVisible() {
        // 密码默认不可见  点击可见
        if (isClicked) {
            resetPwdEdNewPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            resetPwdIvEye.setImageDrawable(getResources().getDrawable(R.drawable.reset_pwd_eye_close));
            isClicked = !isClicked;
        } else {
            resetPwdEdNewPwd.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
            resetPwdIvEye.setImageDrawable(getResources().getDrawable(R.drawable.reset_pwd_eye));
            isClicked = !isClicked;
        }
    }

    private void clickGetCode() {
        phone = resetPwdEdPhone.getText().toString().trim();
        if (StrUtils.isEmpty(phone)) {
            ToastUtil.show(getResources().getString(R.string.phone_is_null));
            return;
        }
        if (!StrUtils.isCorrectPhone(phone)) {
            ToastUtil.show(getResources().getString(R.string.phone_is_error));
            MyLog.i("registerActivity", "---------------" + getResources().getString(R.string.phone_is_error));
            return;
        }
        AppConfig.logs("请求获取验证码接口---调用=====：" + create.getComsApi().doCommonStr(create.login().controllersName, create.login().getSmsCode, new getSmsCodeBody(phone, StringCode.GET_SMS_CODE_MODIFY_PWD)));
        // TODO 请求获取验证码接口  type 3  修改登录密码
        create.getComsApi().doCommon(create.login().controllersName, create.login().getSmsCode, new getSmsCodeBody(phone, StringCode.GET_SMS_CODE_MODIFY_PWD)).build()
                .callAsync(new IComponentCallback() {
                    @Override
                    public void onResult(CC loginCC, CCResult result) {
//                        AppConfig.logs("调用成败：" + result.isSuccess() + "  ---解析数据：" + mLoginInBean.getMsg());
                        AppConfig.logs("请求获取验证码接口---z`q调用成败：" + result.isSuccess());
                        if (result.isSuccess()) {
                            timer.start();
                            resetPwdEdCode.setFocusable(true);
                            resetPwdEdCode.setFocusableInTouchMode(true);
//                            resetPwdEdCode.requestFocus();
                            MyLog.i("registerActivity", "---------------获取验证码成功");
                        }
                    }
                });
    }
    private CountDownTimer timer =new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long l) {
            try {
                resetPwdTvGetCode.setText((l / 1000) + "s");
                resetPwdTvGetCode.setClickable(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onFinish() {
            try {
                resetPwdTvGetCode.setEnabled(true);
                resetPwdTvGetCode.setClickable(true);
                resetPwdTvGetCode.setText(getResources().getString(R.string.login_get_code));
//                regTvSendCode.setText("获取验证码");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private void clickResetPwd() {
        timer.cancel();
        phone = resetPwdEdPhone.getText().toString().trim();
        code = resetPwdEdCode.getText().toString().trim();
        newPwd = resetPwdEdNewPwd.getText().toString().trim();
        ToastUtil.isDebugShow("click");
        MyLog.i("LoginActivity", "---------------phone：" + phone + "------pwd(MD5)：" + MD5Util.string2MD5(newPwd));
        if (StrUtils.isEmpty(phone)) {
            ToastUtil.show(getResources().getString(R.string.phone_is_null));
            return;
        }
        if (!StrUtils.isCorrectPhone(phone)) {
            ToastUtil.show(getResources().getString(R.string.phone_is_error));
            return;
        }
        if (StrUtils.isEmpty(code)) {
            ToastUtil.show(getResources().getString(R.string.code_is_null));
            return;
        }
        if (StrUtils.isEmpty(newPwd)) {
            ToastUtil.show(getResources().getString(R.string.pwd_is_null));
            return;
        }
        MyLog.i("LoginActivity", "---------------phone：" + phone + "---------pwd(MD5)：" + MD5Util.string2MD5(newPwd));

        //TODO   请求重设密码接口
////        create.getComsApi().doCommon(create.login().controllersName, create.login().loginIn, new LoginInBody("18150960006", "e5e94876560ab8220f43cbd52854e80a")).build()
//        create.getComsApi().doCommon(create.login().controllersName, create.login().loginIn, new LoginInBody(phone,  MD5Util.string2MD5(MD5Util.string2MD5(newPwd)+"mding"))).build()
//                .callAsync(new IComponentCallback() {
//                    @Override
//                    public void onResult(CC loginCC, CCResult result) {
////                        AppConfig.logs("调用成败：" + result.isSuccess() + "  ---解析数据：" + mLoginInBean.getMsg());
//                        AppConfig.logs("调用成败：" + result.isSuccess());
//                        if (result.isSuccess()) {
//                            UpPassWordBean mUpPasswordBean = new GsonParamConverter().json2Object(result.getDataItem(loginCC.getActionName()).toString(), UpPassWordBean.class);
//                            // 处理数据
//                            onReceiveSuccess(mUpPasswordBean);
//                            // 跳转到登录界面
//                            IntentUtils.JumpToHaveOne(LoginActivity.class, StringCode.PHONE_NUMBER, phone);
//                        }
//                    }
//                });
    }

    private void onReceiveSuccess(UpPassWordBean mUpPasswordBean) {

    }


}
