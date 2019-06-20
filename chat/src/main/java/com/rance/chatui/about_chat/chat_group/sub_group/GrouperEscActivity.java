package com.rance.chatui.about_chat.chat_group.sub_group;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.mding.chatfeng.base_common.AppConfig;
import com.mding.chatfeng.base_common.components.base.BaseActivity;
import com.mding.chatfeng.base_common.utils.about_dialog.DialogUtils;
import com.mding.chatfeng.base_common.utils.about_main_utils.HelpUtils;
import com.mding.chatfeng.base_common.utils.about_main_utils.IntentUtils;
import com.mding.chatfeng.base_common.utils.aboutsystem.AppManager;
import com.mding.chatfeng.chat.R;
import com.rance.chatui.about_chat.chat_group.ChatGroupActivity;


/**
 * 群设置-编辑群聊资料
 */
public class GrouperEscActivity extends BaseActivity {

    TextView includeTopTvTital;
    TextView incluTvRight;


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    String  groupId = null;
    String  groupName = null;

    @Override
    protected void initBaseView() {
        super.initBaseView();
        initUI();
        includeTopTvTital.setText("退出该群");
        Intent intent = getIntent();
        if (intent != null) {
            groupId=intent.getStringExtra(AppConfig.GROUPER_ESC);
            groupName=intent.getStringExtra(AppConfig.GROUPER_NAME);
        }
    }

    private void initUI() {
        includeTopTvTital=getView(R.id.include_top_tv_title);
        incluTvRight=getView(R.id.inclu_tv_right);
        addOnClickListeners(R.id.group_esc_tv_zhuanrang, R.id.group_esc_tv_jiesan);
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_grouper_esc;
    }


    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String method = HelpUtils.backMethod(responseText);
        switch (method) {
            case "addGroupOf":
                DialogUtils.showDialog("申请入群成功");
                break;
            case "outGroupChat":
                DialogUtils.showDialogOne("已解散该群", new DialogUtils.OnClickSureListener() {
                    @Override
                    public void onClickSure() {
//                        TODO
                      /*  RealmHomeHelper realmHomeHelper = new RealmHomeHelper(GrouperEscActivity.this);
                        realmHomeHelper.deleteRealmMsg(groupId);
                        Intent intent2 = new Intent();
                        intent2.putExtra("id",groupId);
                        intent2.setAction("del.refreshMsgFragment");
                        sendBroadcast(intent2);
                        AppManager.getAppManager().finishActivity(GrouperEscActivity.this);
                        AppManager.getAppManager().finishActivity(ChatGroupActivity.class);*/
                    }
                });
                break;
            default:
                break;
        }
    }


    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.group_esc_tv_zhuanrang:
                IntentUtils.JumpToHaveTwo(ZhuanRangGroupActivity.class, ZhuanRangGroupActivity.GROUP_ID, groupId,ZhuanRangGroupActivity.GROUP_NAME,groupName);
                AppManager.getAppManager().finishActivity();

                break;
            case R.id.group_esc_tv_jiesan:
                DialogUtils.showDialog("是否确认解散该群", new DialogUtils.OnClickSureListener() {
                    @Override
                    public void onClickSure() {
//                        TODO
//                        sendWebHaveDialog(SplitWeb.getSplitWeb().outGroupChat(groupId), "正在解散...", "解散成功");
                    }
                });
                break;
        }
    }
}
