package com.mding.chatfeng.home.ui.about_mine.about_setting.about_child_page.about_account_and_safe;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mding.chatfeng.base_common.components.base.BaseActivity;
import com.mding.chatfeng.base_common.utils.about_dialog.DialogUtils;
import com.mding.chatfeng.base_common.utils.about_main_utils.HelpUtils;
import com.mding.chatfeng.base_common.utils.aboututils.NoDoubleClickUtils;
import com.mding.chatfeng.base_common.utils.aboututils.StrUtils;
import com.mding.chatfeng.base_common.utils.aboututils.ToastUtil;
import com.mding.chatfeng.home.R;

/**
 * 更换绑定  新号码页面
 */
public class ChangeBindNewActivity extends BaseActivity {

    ImageView includeTopIvBack;
    TextView includeTopTvTitle;
    TextView mTvCode;
    EditText changebindEdPhone;
    EditText changebindEdCode;
    LinearLayout mLin;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }


    @Override
    protected int getLayoutView() {
        return R.layout.activity_change_bind_new;
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();
        initViewUI();
        includeTopTvTitle.setText(getResources().getString(R.string.account_security_change_bind));
//        includeTopTvTitle.setText("更换绑定");
        mLin.setBackgroundColor(getResources().getColor(R.color.app_theme));
    }

    private void initViewUI() {
        includeTopIvBack = getView(R.id.include_top_iv_back);
        includeTopTvTitle = getView(R.id.include_top_tv_title);
        mTvCode = getView(R.id.changebind_tv_new_getcode);
        changebindEdPhone = getView(R.id.changebind_ed_new_phone);
        changebindEdCode = getView(R.id.changebind_ed_new_code);
        mLin = getView(R.id.include_top_lin_background);

        addOnClickListeners(R.id.changebind_tv_new_getcode, R.id.changebind_btn_new_sure);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int i = view.getId();
        // 获取验证码按钮
        if (i == R.id.changebind_tv_new_getcode) {
            if (NoDoubleClickUtils.isDoubleClick())
                initSendSms();

//                确认提交按钮
        } else if (i == R.id.changebind_btn_new_sure) {
            if (NoDoubleClickUtils.isDoubleClick())
                initChange();

        }
    }

//    @OnClick({R.id.changebind_tv_new_getcode, R.id.changebind_btn_new_sure})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
////            获取验证码按钮
//            case R.id.changebind_tv_new_getcode:
//                if (NoDoubleClickUtils.isDoubleClick())
//                    initSendSms();
//                break;
////                确认提交按钮
//            case R.id.changebind_btn_new_sure:
//                if (NoDoubleClickUtils.isDoubleClick())
//                    initChange();
//                break;
//        }
//    }

    private void initChange() {
//        timer.cancel();
        final String phone = changebindEdPhone.getText().toString().trim();
        String code = changebindEdCode.getText().toString().trim();
        if (StrUtils.isEmpty(phone)) {
//        if (!EditCheckUtils.isMobileNO(phone)) {
            ToastUtil.show(getResources().getString(R.string.phone_is_null));
//            Tip.getDialog(ResgisterActivity.this,getResources().getString(R.string.phone_is_error));
            return;
        }
        if (StrUtils.isEmpty(code)) {
            ToastUtil.show(getResources().getString(R.string.code_is_null));
//            return;
        }
        // TODO  更换绑定  新手机号码接口
//        sendWeb(SplitWeb.getSplitWeb().replaceMobileNew(phone,code));
    }

    private void initSendSms() {
        String phone = changebindEdPhone.getText().toString().trim();
        if (StrUtils.isEmpty(phone)) {
            ToastUtil.show(getResources().getString(R.string.phone_is_null));
            return;
        }
        if (StrUtils.isEmpty(phone)) {
            ToastUtil.show(getResources().getString(R.string.phone_is_error));
            return;
        }
        // TODO  新短信验证码接口  开启计时器  类型 4修改绑定手机号（旧） 5修改绑定手机号（新）
//        NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
//        netWorkUtlis.setOnNetWork(SplitWeb.getSplitWeb().smsCode(phone, "5"), new NetWorkUtlis.OnNetWork() {
//            @Override
//            public void onNetSuccess(String msg) {
//                timer.start();
//            }
//        });
    }

    private CountDownTimer timer =new CountDownTimer(60000, 1000) {
        @SuppressLint("SetTextI18n")
        @Override
        public void onTick(long l) {
            mTvCode.setText((l / 1000) + "s");
            mTvCode.setClickable(false);
        }
        @Override
        public void onFinish() {
            mTvCode.setEnabled(true);
            mTvCode.setClickable(true);
            mTvCode.setText(getResources().getString(R.string.change_phone_get_code));
//            mTvCode.setText("获取验证码");
        }
    };

    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String s = HelpUtils.backMethod(responseText);
        if (s.equals("replaceMobileNew"))
        {
            timer.cancel();
            DialogUtils.showDialogOne(getResources().getString(R.string.change_phone_success), new DialogUtils.OnClickSureListener() {
//            DialogUtils.showDialogOne("换绑号码成功", new DialogUtils.OnClickSureListener() {
                @Override
                public void onClickSure() {
                    // TODO  换绑号码成功  返回到登录界面
//                    SplitWeb.getSplitWeb().USER_ID="";
//                    AppManager.getAppManager().finishAllActivity();
//                    overridePendingTransition(0,0);
//                    Intent intent_recharge = new Intent(ChangeBindNewActivity.this, LoginActivity.class);
//                    startActivity(intent_recharge);
//                    overridePendingTransition(0,0);
//                    ACache.get(ChangeBindNewActivity.this).clear();
//                    SPUtils.clear(ChangeBindNewActivity .this);
                }
            });
        }
    }

}
