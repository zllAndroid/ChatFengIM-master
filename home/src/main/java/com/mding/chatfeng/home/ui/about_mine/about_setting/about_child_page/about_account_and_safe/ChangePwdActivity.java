package com.mding.chatfeng.home.ui.about_mine.about_setting.about_child_page.about_account_and_safe;

import android.graphics.Paint;
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
import com.mding.chatfeng.home.R;

public class ChangePwdActivity extends BaseActivity {
    ImageView includeTopIvBack;
    LinearLayout mLin;
    TextView includeTopTvTitle;
    TextView mTvYan;
    EditText changepwdEdOldPsw;
    EditText changepwdEdPsw;
    EditText changepwdEdSurePsw;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_change_pwd;
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();
        initViewUI();

        includeTopTvTitle.setText(getResources().getString(R.string.account_security_change_pwd));
//        includeTopTvTitle.setText("修改密码");
//        mLin.setBackgroundColor(0);
        mLin.setBackgroundColor(getResources().getColor(R.color.app_theme));
        mTvYan.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
    }

    private void initViewUI() {
        includeTopIvBack = getView(R.id.include_top_iv_back);
        includeTopTvTitle = getView(R.id.include_top_tv_title);
        mTvYan = getView(R.id.changepwd_tv_yanzhengma);
        changepwdEdOldPsw = getView(R.id.changepwd_ed_old_psw);
        changepwdEdPsw = getView(R.id.changepwd_ed_psw);
        changepwdEdSurePsw = getView(R.id.changepwd_ed_sure_psw);
        mLin = getView(R.id.include_top_lin_background);

        addOnClickListeners(R.id.changepwd_tv_yanzhengma, R.id.changepwd_btn_sure);
    }

    private void clickSure() {
        final   String oldPwd = changepwdEdOldPsw.getText().toString().trim();
        String newPwd = changepwdEdPsw.getText().toString().trim();
        String surePwd = changepwdEdSurePsw.getText().toString().trim();
        if (StrUtils.isEmpty(oldPwd)) {
            DialogUtils.showDialog("旧密码不得为空");
            return;
        }
        if (StrUtils.isEmpty(newPwd)) {
            DialogUtils.showDialog("新密码不得为空");
            return;
        }
        if (StrUtils.isEmpty(surePwd)) {
            DialogUtils.showDialog("确认新密码不得为空");
            return;
        }

        if (!surePwd.equals(newPwd)) {
            DialogUtils.showDialog("两次密码输入不一致");
            return;
        }

        boolean b = StrUtils.validatePassword(newPwd);
        Log.e("validatePassword","我是否满足="+b+"");
        if (!b) {
//            ToastUtil.show("满足");
            DialogUtils.showDialog("密码至少要包括:\n字母、数字、标点符号\n的其中两项,长度为6-20位");
            return;
        }

//        DataMyZiliao.RecordBean recordBean = new DataMyZiliao.RecordBean();
//        String phone = recordBean.getMobile();
//        if (phone.equals(newPwd)){
//            DialogUtils.showDialog("密码不能与会员名相同");
//            return;
//        }

        // TODO 修改密码接口
//        sendWeb(SplitWeb.getSplitWeb().upPassWord(oldPwd,newPwd,surePwd));

    }

    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String method = HelpUtils.backMethod(responseText);
        switch (method){
            case "upPassWord":
                DialogUtils.showDialogOne(getResources().getString(R.string.change_pwd_change_success), new DialogUtils.OnClickSureListener() {
//                DialogUtils.showDialogOne("修改密码成功", new DialogUtils.OnClickSureListener() {
                    @Override
                    public void onClickSure() {
                        // TODO  修改密码成功
//                        WsChannelService.isBind = true;
//                        BaseApplication.isHomeMsgFragment = true;
////                                    ChatService
////                                    unbindService(ChatService.this);
////                                    stopService(intent);
//                        Intent intent2 = new Intent(ChangePwdActivity.this, ChatService.class);
//                        stopService(intent2);// 关闭服务
////                                    unbindService(intent2);
////                                    ToastUtil.show("1");
//                        SplitWeb.getSplitWeb().IS_SET_PERSON_HEAD = true;
//                        sendWeb(SplitWeb.getSplitWeb().kickUid());
//                        SplitWeb.getSplitWeb().USER_ID = "";
//                        SplitWeb.getSplitWeb().USER_TOKEN = "";
//                        ACache.get(ChangePwdActivity.this).clear();
//                        SPUtils.put(ChangePwdActivity.this, AppAllKey.USER_ID_KEY, "");
//                        SPUtils.put(ChangePwdActivity.this, AppAllKey.USER_Token, "");
////                                    SPUtils.clear(MineSetActivity.this);
//                        AppManager.getAppManager().onAppExit(ChangePwdActivity.this);
////                                    AppManager.getAppManager().finishAllActivity();
////                                    Intent intent_recharge = new Intent(MineSetActivity.this, LoginActivity.class);
////                                    startActivity(intent_recharge);
////                                    Log.e("userPhone","-------------mineSet-----------------"+userPhone);
//                        String mPhone = (String)SPUtils.get(ChangePwdActivity.this, AppAllKey.SP_LOGIN_ACCOUNT, SplitWeb.getSplitWeb().MOBILE);
//                        IntentUtils.JumpToHaveOne(LoginActivity.class, "phone", mPhone);
//                        overridePendingTransition(0, 0);
//
//
////                        SplitWeb.getSplitWeb().USER_ID="";
////                        AppManager.getAppManager().finishAllActivity();
////                        String mPhone = (String)SPUtils.get(ChangePwdActivity.this, AppAllKey.SP_LOGIN_ACCOUNT, SplitWeb.getSplitWeb().MOBILE);
////                        if (!StrUtils.isEmpty(mPhone))
////                            IntentUtils.JumpToHaveOne(LoginActivity.class,"phone",mPhone);
////
//////                Intent intent_recharge = new Intent(ChangePwdActivity.this, LoginActivity.class);
//////                startActivity(intent_recharge);
//////                overridePendingTransition(0,0);
////                        ACache.get(ChangePwdActivity.this).clear();
////                        SPUtils.clear(ChangePwdActivity.this);
//////                AppManager.getAppManager().finishActivity();
                    }
                });
                break;
        }

    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int i = view.getId();
        if (i == R.id.changepwd_tv_yanzhengma) {
            if (NoDoubleClickUtils.isDoubleClick()) {
                IntentUtils.JumpFinishTo(ChangePwdActivity.this, ChangeCodeActivity.class);
            }

        } else if (i == R.id.changepwd_btn_sure) {
            if (NoDoubleClickUtils.isDoubleClick())
                clickSure();

        }
    }

    //    @OnClick({R.id.changepwd_tv_yanzhengma, R.id.changepwd_btn_sure})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.changepwd_tv_yanzhengma:
//                if (NoDoubleClickUtils.isDoubleClick()) {
//                    IntentUtils.JumpFinishTo(ChangePwdActivity.this,ChangeCodeActivity.class);
//                }
//                break;
//            case R.id.changepwd_btn_sure:
//                if (NoDoubleClickUtils.isDoubleClick())
//                    clickSure();
//                break;
//        }
//    }
}
