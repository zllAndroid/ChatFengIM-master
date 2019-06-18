package com.mding.chatfeng.home.ui.about_mine.about_setting;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mding.chatfeng.base_common.components.base.BaseActivity;
import com.mding.chatfeng.base_common.utils.about_main_utils.IntentUtils;
import com.mding.chatfeng.base_common.utils.aboututils.NoDoubleClickUtils;
import com.mding.chatfeng.home.R;
import com.mding.chatfeng.home.ui.about_mine.about_setting.about_child_page.about_account_and_safe.ChangeBindOldActivity;
import com.mding.chatfeng.home.ui.about_mine.about_setting.about_child_page.about_account_and_safe.ChangePwdActivity;

public class AccountAndSafeActivity extends BaseActivity {

    ImageView includeTopIvBack;
    TextView includeTopTvTitle;
    LinearLayout countLinChangePWD;
    LinearLayout countLinChangeBind;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    @Override
    protected void initBaseView() {
        super.initBaseView();
        initViewUI();
        includeTopTvTitle.setText(getResources().getString(R.string.account_security_title));
//        includeTopTvTitle.setText("帐号与安全");
        includeTopIvBack.setVisibility(View.VISIBLE);
    }

    private void initViewUI() {
        includeTopIvBack = getView(R.id.include_top_iv_back);
        includeTopTvTitle = getView(R.id.include_top_tv_title);
        countLinChangePWD = getView(R.id.count_lin_changePWD);
        countLinChangeBind = getView(R.id.count_lin_changeBind);

        addOnClickListeners(R.id.count_lin_changePWD,R.id.count_lin_changeBind);
    }

    @Override
    protected boolean isTopBack() {
        return true;
    }

    @Override
    protected boolean isGones() {
        return true;
    }
    @Override
    public int getLayoutView() {
        return R.layout.activity_count_and_safe;
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int i = view.getId();
//            打开  更换密码  界面
        if (i == R.id.count_lin_changePWD) {
            if (NoDoubleClickUtils.isDoubleClick())
                IntentUtils.JumpTo(ChangePwdActivity.class);

        } else if (i == R.id.count_lin_changeBind) {
            if (NoDoubleClickUtils.isDoubleClick())
                IntentUtils.JumpTo(ChangeBindOldActivity.class);

        }
    }

    //    @OnClick({R.id.count_lin_changePWD,R.id.count_lin_changeBind})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//
////            打开  更换密码  界面
//            case R.id.count_lin_changePWD:
//                if (NoDoubleClickUtils.isDoubleClick())
//                    IntentUtils.JumpTo(ChangePwdActivity.class);
//                break;
//            case R.id.count_lin_changeBind:
//                if (NoDoubleClickUtils.isDoubleClick())
//                    IntentUtils.JumpTo(ChangeBindOldActivity.class);
//                break;
//        }
//    }
}
