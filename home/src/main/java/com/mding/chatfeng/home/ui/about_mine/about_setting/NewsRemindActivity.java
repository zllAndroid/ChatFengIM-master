package com.mding.chatfeng.home.ui.about_mine.about_setting;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mding.chatfeng.base_common.components.base.BaseActivity;
import com.mding.chatfeng.base_common.utils.about_main_utils.HelpUtils;
import com.mding.chatfeng.base_common.utils.aboutsystem.AppManager;
import com.mding.chatfeng.base_common.utils.aboututils.NoDoubleClickUtils;
import com.mding.chatfeng.base_common.utils.aboututils.ToastUtil;
import com.mding.chatfeng.home.R;
import com.suke.widget.SwitchButton;

public class NewsRemindActivity extends BaseActivity {
//    @BindView(R.id.include_top_tv_title)
    TextView includeTopTvTitle;
//    @BindView(R.id.include_top_lin_back)
    LinearLayout includeTopLin;
//    @BindView(R.id.inclu_tv_right)
    TextView includeTopTvRight;

//    @BindView(R.id.news_switch_jieshou_news)
    SwitchButton newsSwitchJieshouNews;
//    @BindView(R.id.news_switch_jieshou_yuyin)
    SwitchButton newsSwitchJieshouYuyin;
//    @BindView(R.id.news_switch_jieshou_video)
    SwitchButton newsSwitchJieshouVideo;


//    @BindView(R.id.news_switch_xianshi)
//    SwitchButton newsSwitchXianshi;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_set_news_remind;
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();
        initViewUI();

        includeTopTvTitle.setText(getResources().getString(R.string.msg_notification_title));
//        includeTopTvTitle.setText("消息提醒");
        includeTopLin.setBackgroundColor(getResources().getColor(R.color.app_theme));
        includeTopTvRight.setVisibility(View.VISIBLE);
        includeTopTvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NoDoubleClickUtils.isDoubleClick()) {
                    // TODO  接口
//                    sendWeb(SplitWeb.getSplitWeb().permissionSetTwo("2", isMsg, isVoice, isVideo));
                }
            }
        });
        switchListen();
        // TODO  接口
//        sendWeb( SplitWeb.getSplitWeb().getPermissStatu("2"));
    }

    private void initViewUI() {
        includeTopTvTitle = getView(R.id.include_top_tv_title);
        includeTopLin = getView(R.id.include_top_lin_back);
        includeTopTvRight = getView(R.id.inclu_tv_right);
        newsSwitchJieshouNews = getView(R.id.news_switch_jieshou_news);
        newsSwitchJieshouYuyin = getView(R.id.news_switch_jieshou_yuyin);
        newsSwitchJieshouVideo = getView(R.id.news_switch_jieshou_video);

    }

    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String method = HelpUtils.backMethod(responseText);
        switch (method)
        {
            case "getPermissStatu":
//                DataSetAbout dataSetAbout = JSON.parseObject(responseText, DataSetAbout.class);
//                DataSetAbout.RecordBean record = dataSetAbout.getRecord();
//                if (record!=null)
//                {
//                    String is_msg_remind = record.getIsMsgRemind();
//                    String is_voice_remind = record.getIsVoiceRemind();
//                    String is_video_remind = record.getIsVideoRemind();
//                    newsSwitchJieshouNews.setChecked(is_msg_remind.equals("1"));
//                    newsSwitchJieshouYuyin.setChecked(is_voice_remind.equals("1"));
//                    newsSwitchJieshouVideo.setChecked(is_video_remind.equals("1"));
//                }
                break;
            case "permissionSet":
//                DialogUtils.showDialogOne("消息提醒设置成功", new DialogUtils.OnClickSureListener() {
//                    @Override
//                    public void onClickSure() {
//                        AppManager.getAppManager().finishActivity();
//                    }
//                });
                ToastUtil.show(getResources().getString(R.string.msg_notification_save_succeed));
//                ToastUtil.show("消息提醒设置成功！");
                AppManager.getAppManager().finishActivity();
                break;
        }
    }
    String isMsg = "1";
    String isVoice = "1";
    String isVideo = "1";
    private void switchListen() {
        newsSwitchJieshouNews.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if(isChecked){
                    isMsg = "1";
//                    ToastUtil.show("接收新消息通知打开");
                }else{
                    isMsg = "0";
//                    ToastUtil.show("接收新消息通知关闭");
                }
            }
        });
        newsSwitchJieshouYuyin.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if(isChecked){
                    isVoice = "1";
//                    ToastUtil.show("语音通知打开");
                }else{
                    isVoice = "0";
//                    ToastUtil.show("语音通知关闭");
                }
            }
        });
        newsSwitchJieshouVideo.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if(isChecked){
                    isVideo = "1";
//                    ToastUtil.show("视频通知打开");
                }else{
                    isVideo = "0";
//                    ToastUtil.show("视频通知关闭");
                }
            }
        });
//        newsSwitchXianshi.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
//                if(isChecked){
//                    ToastUtil.show("推送显示内容打开");
//                }else{
//                    ToastUtil.show("推送显示内容关闭");
//                }
//            }
//        });
    }


}
