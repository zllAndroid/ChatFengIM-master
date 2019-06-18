package com.mding.chatfeng.home.ui.about_mine.about_setting;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mding.chatfeng.base_common.components.base.BaseActivity;
import com.mding.chatfeng.base_common.utils.about_main_utils.IntentUtils;
import com.mding.chatfeng.home.R;
import com.mding.chatfeng.home.ui.about_mine.about_setting.about_child_page.DongTuPlaySetActivity;

public class DiscoverSetActivity extends BaseActivity {
//    @BindView(R.id.include_top_tv_title)
    TextView includeTopTvTitle;
//    @BindView(R.id.inclu_tv_right)
    TextView incluTvRight;
//    @BindView(R.id.include_top_iv_back)
    ImageView includeTopIvBack;
//    @BindView(R.id.include_top_lin_background)
    LinearLayout includeTopLinBackground;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_discover_set);
//    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_discover_set;
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();
        initViewUI();

        incluTvRight.setVisibility(View.VISIBLE);
        includeTopTvTitle.setText("朋友圈设置");
        includeTopIvBack.setVisibility(View.VISIBLE);
        includeTopLinBackground.setBackgroundColor(getResources().getColor(R.color.app_theme));
    }

    private void initViewUI() {
        includeTopTvTitle = getView(R.id.include_top_tv_title);
        incluTvRight = getView(R.id.inclu_tv_right);
        includeTopIvBack = getView(R.id.include_top_iv_back);
        includeTopLinBackground = getView(R.id.include_top_lin_background);

        addOnClickListeners(R.id.discover_lin_gifPlay, R.id.discover_lin_hideMine, R.id.discover_lin_hideHis);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int i = view.getId();
        if (i == R.id.discover_lin_gifPlay) {
            IntentUtils.JumpTo(DongTuPlaySetActivity.class);

        } else if (i == R.id.discover_lin_hideMine) {

        } else if (i == R.id.discover_lin_hideHis) {

        }

    }

    @Override
    protected boolean isTopBack() {
        return true;
    }

    @Override
    protected boolean isGones() {
        return true;
    }

//    @OnClick({R.id.discover_lin_gifPlay, R.id.discover_lin_hideMine, R.id.discover_lin_hideHis})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.discover_lin_gifPlay:
//                IntentUtils.JumpTo(DongTuPlaySetActivity.class);
//                break;
//            case R.id.discover_lin_hideMine:
//                break;
//            case R.id.discover_lin_hideHis:
//                break;
//        }
//    }
}
