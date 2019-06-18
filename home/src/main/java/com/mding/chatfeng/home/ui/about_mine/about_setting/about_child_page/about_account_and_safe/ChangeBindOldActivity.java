package com.mding.chatfeng.home.ui.about_mine.about_setting.about_child_page.about_account_and_safe;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mding.chatfeng.base_common.components.base.BaseActivity;
import com.mding.chatfeng.base_common.utils.about_main_utils.HelpUtils;
import com.mding.chatfeng.base_common.utils.about_main_utils.IntentUtils;
import com.mding.chatfeng.base_common.utils.aboututils.NoDoubleClickUtils;
import com.mding.chatfeng.base_common.utils.aboututils.StrUtils;
import com.mding.chatfeng.base_common.utils.aboututils.ToastUtil;
import com.mding.chatfeng.home.R;

/**
 * 更换绑定  原号码页面
 */
public class ChangeBindOldActivity extends BaseActivity {

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
        return R.layout.activity_change_bind_old;
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();
        initViewUI();
        includeTopTvTitle.setText(getResources().getString(R.string.change_phone_title));
//        includeTopTvTitle.setText("更换绑定");
        mLin.setBackgroundColor(getResources().getColor(R.color.app_theme));
    }

    private void initViewUI() {
        includeTopIvBack = getView(R.id.include_top_iv_back);
        includeTopTvTitle = getView(R.id.include_top_tv_title);
        mTvCode = getView(R.id.changebind_tv_old_getcode);
        changebindEdPhone = getView(R.id.changebind_ed_old_phone);
        changebindEdCode = getView(R.id.changebind_ed_old_code);
        mLin = getView(R.id.include_top_lin_background);

        addOnClickListeners(R.id.changebind_tv_old_getcode, R.id.changebind_btn_old_sure);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int i = view.getId();//            获取验证码按钮
        if (i == R.id.changebind_tv_old_getcode) {
            if (NoDoubleClickUtils.isDoubleClick())
                initSendSms();

//                确认提交按钮
        } else if (i == R.id.changebind_btn_old_sure) {
            if (NoDoubleClickUtils.isDoubleClick())
                initChange();

        }

    }

//    @OnClick({R.id.changebind_tv_old_getcode, R.id.changebind_btn_old_sure})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
////            获取验证码按钮
//            case R.id.changebind_tv_old_getcode:
//                if (NoDoubleClickUtils.isDoubleClick())
//                    initSendSms();
//                break;
////                确认提交按钮
//            case R.id.changebind_btn_old_sure:
//                if (NoDoubleClickUtils.isDoubleClick())
//                    initChange();
//                break;
//        }
//    }

    private void initChange() {
        final String phone = changebindEdPhone.getText().toString().trim();
        String code = changebindEdCode.getText().toString().trim();
        if (StrUtils.isEmpty(phone)) {
            ToastUtil.show(getResources().getString(R.string.phone_is_null));
//            ToastUtil.show("手机号不能为空");
            return;
        }
        if (StrUtils.isEmpty(code)) {
            ToastUtil.show(getResources().getString(R.string.code_is_null));
//            ToastUtil.show("验证码不得为空");
//            return;
        }
//        NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
//        netWorkUtlis.setOnNetWork(SplitWeb.getSplitWeb().replaceMobileOld(phone, code), new NetWorkUtlis.OnNetWork() {
//            @Override
//            public void onNetSuccess(String msg) {
//                IntentUtils.JumpFinishTo(ChangeBindOldActivity.this,ChangeBindNewActivity.class);
//            }
//        }, new NetWorkUtlis.OnNetWorkError() {
//            @Override
//            public void onNetError(String msg) {
//                DialogUtils.showDialogOne("您输入的验证码有误！", new DialogUtils.OnClickSureListener() {
//                    @Override
//                    public void onClickSure() {
//
//                    }
//                });
//                timer.start();
//            }
//        });
        // TODO 更换绑定  旧手机号码接口
//        sendWeb(SplitWeb.getSplitWeb().replaceMobileOld(phone,code));
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
//        netWorkUtlis.setOnNetWork(SplitWeb.getSplitWeb().smsCode(phone, "4"), new NetWorkUtlis.OnNetWork() {
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
        if (s.equals("replaceMobileOld"))
        {
            timer.cancel();
            IntentUtils.JumpFinishTo(ChangeBindOldActivity.this,ChangeBindNewActivity.class);

        }
    }
}
