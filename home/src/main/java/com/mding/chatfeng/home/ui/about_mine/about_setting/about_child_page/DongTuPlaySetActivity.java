package com.mding.chatfeng.home.ui.about_mine.about_setting.about_child_page;

import android.widget.TextView;

import com.mding.chatfeng.base_common.components.base.BaseActivity;
import com.mding.chatfeng.home.R;

public class DongTuPlaySetActivity extends BaseActivity {
//    @BindView(R.id.include_top_tv_title)
    TextView includeTopTvTitle;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_dong_tu_play_set;
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();
        initViewUI();

        includeTopTvTitle.setText(getResources().getString(R.string.dong_tu_play_title));
//        includeTopTvTitle.setText("动图播放设置");
    }

    private void initViewUI() {
        includeTopTvTitle = getView(R.id.include_top_tv_title);

    }

}
