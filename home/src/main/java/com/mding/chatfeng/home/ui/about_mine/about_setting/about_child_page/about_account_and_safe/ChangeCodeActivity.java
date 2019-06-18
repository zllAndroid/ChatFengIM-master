package com.mding.chatfeng.home.ui.about_mine.about_setting.about_child_page.about_account_and_safe;

import android.graphics.Paint;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mding.chatfeng.base_common.components.base.BaseActivity;
import com.mding.chatfeng.base_common.utils.about_dialog.DialogUtils;
import com.mding.chatfeng.base_common.utils.about_main_utils.HelpUtils;
import com.mding.chatfeng.base_common.utils.about_main_utils.IntentUtils;
import com.mding.chatfeng.base_common.utils.aboututils.NoDoubleClickUtils;
import com.mding.chatfeng.base_common.utils.aboututils.StrUtils;
import com.mding.chatfeng.base_common.utils.aboututils.ToastUtil;
import com.mding.chatfeng.home.R;

public class ChangeCodeActivity extends BaseActivity {
    ImageView includeTopIvBack;
    TextView includeTopTvTitle;
    TextView mTvPwd;
    TextView changecodeTvAccount;
    EditText changecodeEdCode;
    EditText changecodeEdNewPsw;
    TextView mTvCode;
    LinearLayout mLin;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_change_code;
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();
        initViewUI();

        includeTopTvTitle.setText(getResources().getString(R.string.account_security_change_pwd));
//        includeTopTvTitle.setText("修改密码");
        mLin.setBackgroundColor(getResources().getColor(R.color.app_theme));
//        给密码修改按钮添加下划线
        mTvPwd.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        // TODO  从缓存中获取手机
//        String mPhone = (String)SPUtils.get(this, AppAllKey.SP_LOGIN_ACCOUNT, SplitWeb.getSplitWeb().MOBILE);
//        if (!StrUtils.isEmpty(mPhone))
//            changecodeTvCode.setText(mPhone);
    }

    private void initViewUI() {
        includeTopIvBack = getView(R.id.include_top_iv_back);
        includeTopTvTitle = getView(R.id.include_top_tv_title);
        mTvPwd = getView(R.id.changecode_tv_psw);
        changecodeTvAccount = getView(R.id.changecode_tv_account);
        changecodeEdCode = getView(R.id.changecode_ed_code);
        changecodeEdNewPsw = getView(R.id.changecode_ed_new_psw);
        mTvCode = getView(R.id.changecode_tv_code);
        mLin = getView(R.id.include_top_lin_background);

        addOnClickListeners(R.id.changecode_tv_code, R.id.changecode_tv_psw, R.id.changecode_btn_sure);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int i = view.getId();//            获取验证码按钮
        if (i == R.id.changecode_tv_code) {
            if (NoDoubleClickUtils.isDoubleClick())
                initSendSms();

            //跳转密码修改界面
        } else if (i == R.id.changecode_tv_psw) {
            if (NoDoubleClickUtils.isDoubleClick()) {
                IntentUtils.JumpFinishTo(ChangeCodeActivity.this, ChangePwdActivity.class);
//                    overridePendingTransition(0,0);
            }

//                确认提交按钮
        } else if (i == R.id.changecode_btn_sure) {
            if (NoDoubleClickUtils.isDoubleClick())
                initChange();

        }
    }

//    @OnClick({R.id.changecode_tv_code, R.id.changecode_tv_psw, R.id.changecode_btn_sure})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
////            获取验证码按钮
//            case R.id.changecode_tv_code:
//                if (NoDoubleClickUtils.isDoubleClick())
//                    initSendSms();
//                break;
//            //跳转密码修改界面
//            case R.id.changecode_tv_psw:
//                if (NoDoubleClickUtils.isDoubleClick())
//                {
//                    IntentUtils.JumpFinishTo(ChangeCodeActivity.this,ChangePwdActivity.class);
////                    overridePendingTransition(0,0);
//                }
//                break;
////                确认提交按钮
//            case R.id.changecode_btn_sure:
//                if (NoDoubleClickUtils.isDoubleClick())
//                    initChange();
//                break;
//        }
//    }

        String phone = null;
    private void initChange() {
//        final String phone = changecodeTvCode.getText().toString().trim();
        phone = changecodeTvAccount.getText().toString().trim();
        String code = changecodeEdCode.getText().toString().trim();
        String psw = changecodeEdNewPsw.getText().toString().trim();
        if (StrUtils.isEmpty(phone)) {
            ToastUtil.show(getResources().getString(R.string.phone_is_null));
//            ToastUtil.show("手机号不能为空");
//            Tip.getDialog(ResgisterActivity.this,getResources().getString(R.string.phone_is_error));
            return;
        }
        if (StrUtils.isEmpty(code)) {
            ToastUtil.show(getResources().getString(R.string.code_is_null));
//            ToastUtil.show("验证码不得为空");
            return;
        }
        if (StrUtils.isEmpty(psw)) {
            ToastUtil.show(getResources().getString(R.string.pwd_is_null));
//            ToastUtil.show("密码不得为空");
            return;
        }

        boolean b = StrUtils.validatePassword(psw);
        Log.e("validatePassword","我是否满足="+b+"");
        if (!b) {
//            ToastUtil.show("满足");
            DialogUtils.showDialog("密码至少要包括:\n字母、数字、标点符号\n的其中两项,长度为6-20位");
            return;
        }

//        if (phone.equals(psw)){
//            DialogUtils.showDialog("密码不能与会员名相同");
//            return;
//        }

        // TODO  短信验证码修改登录密码接口
//        sendWeb(SplitWeb.getSplitWeb().upPassWordSms(phone,code,psw));


//        NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
//        netWorkUtlis.setOnNetWork(AppAllKey.LodingFlower, SplitWeb.getSplitWeb().smsCode(phone,psw,code), new NetWorkUtlis.OnNetWork() {
//            @Override
//            public void onNetSuccess(String result) {
//                DialogUtils.showDialogOne("注册成功", new DialogUtils.OnClickSureListener() {
//                    @Override
//                    public void onClickSure() {
//                        AppManager.getAppManager().finishActivity();
//                        overridePendingTransition(0,0);
//                    }
//                });
////                Tip.getDialog(RegisterActivity.this,"注册成功",true);
//            }
//        });
    }

    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String s = HelpUtils.backMethod(responseText);
        if (s.equals("upPassWordSms"))
        {
            DialogUtils.showDialogOne(getResources().getString(R.string.change_pwd_use_code_change_success), new DialogUtils.OnClickSureListener() {
//            DialogUtils.showDialogOne("修改密码成功", new DialogUtils.OnClickSureListener() {
                @Override
                public void onClickSure() {
                    // TODO  修改密码成功  跳转至登录页
//                    timer.cancel();
//                    SplitWeb.getSplitWeb().USER_ID="";
//                    IntentUtils.JumpToHaveOne(LoginActivity.class,"phone",phone);
//                    AppManager.getAppManager().finishAllActivity();
//                    overridePendingTransition(0,0);
//
////                    Intent intent_recharge = new Intent(ChangeCodeActivity.this, LoginActivity.class);
////                    startActivity(intent_recharge);
////                    overridePendingTransition(0,0);
//
//                    ACache.get(ChangeCodeActivity.this).clear();
//                    SPUtils.clear(ChangeCodeActivity .this);
////                    AppManager.getAppManager().finishActivity();
////
                }
            });
        }

    }

    private void initSendSms() {
        String phone = changecodeTvAccount.getText().toString().trim();
        if (StrUtils.isEmpty(phone)) {
            ToastUtil.show(getResources().getString(R.string.phone_is_null));
//            Tip.getDialog(this,"手机号不能为空");
            return;
        }
        if (StrUtils.isCorrectPhone(phone)) {
            ToastUtil.show(getResources().getString(R.string.phone_is_error));
//            Tip.getDialog(this,"手机号输入有误");
            return;
        }
//        sendWeb(SplitWeb.getSplitWeb().upPassWordSms(phone));

        // TODO  新短信验证码接口  类型 3为修改登录密码  并开启计时器
//        NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
//        netWorkUtlis.setOnNetWork(SplitWeb.getSplitWeb().smsCode(phone, "3"), new NetWorkUtlis.OnNetWork() {
//            @Override
//            public void onNetSuccess(String msg) {
//                timer.start();
//            }
//        });
    }
    private CountDownTimer timer =new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long l) {
            mTvCode.setText((l / 1000) + "s");
            mTvCode.setClickable(false);
//            regTvSendCode.setBackgroundResource(R.drawable.btn_stroke_nor_b5);
        }
        @Override
        public void onFinish() {
            mTvCode.setEnabled(true);
            mTvCode.setClickable(true);
            mTvCode.setText(getResources().getString(R.string.change_pwd_use_code_get_code));
//            mTvCode.setText("获取验证码");
//            regTvSendCode.setBackgroundResource(R.drawable.btn_stroke_sel);
        }
    };
}
