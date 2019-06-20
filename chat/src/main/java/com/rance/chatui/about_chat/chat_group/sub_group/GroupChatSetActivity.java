package com.rance.chatui.about_chat.chat_group.sub_group;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mding.chatfeng.base_common.components.base.BaseActivity;
import com.mding.chatfeng.base_common.cus_model.DataSearch;
import com.mding.chatfeng.base_common.utils.about_dialog.DialogUtils;
import com.mding.chatfeng.base_common.utils.about_main_utils.HelpUtils;
import com.mding.chatfeng.base_common.utils.aboututils.ToastUtil;
import com.mding.chatfeng.chat.R;


/**
 * 群设置-编辑群聊资料
 */
public class GroupChatSetActivity extends BaseActivity {

    TextView includeTopTvTital;
    TextView incluTvRight;

    public static String DataKey = "addgroup";
    ImageView groupSetIvHead;
    TextView groupSetTvName;
    LinearLayout groupSetLinChangeHead;
    EditText groupSetEdGroupname;
    LinearLayout groupSetLinMain;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    DataSearch dataSearch = null;

    @Override
    protected void initBaseView() {
        super.initBaseView();
        initUI();
        includeTopTvTital.setText("验证信息");
        incluTvRight.setVisibility(View.VISIBLE);
        incluTvRight.setText("提交");
//        Intent intent = getIntent();
//        if (intent != null) {
//             dataSearch = (DataSearch) intent.getSerializableExtra(AppConfig.GROUP_ADDKEY);
////            dataSearch.getHead_img()
//            if (dataSearch==null)
//                return;
//            Glide.with(this).load(dataSearch.getHeadImg()).error(R.drawable.first_head_nor).into(fdaIvHead);
//        groupSetEdGroupname.setText(dataSearch.getName());
//        }
    }

    private void initUI() {
        TextView includeTopTvTital=
        getView(R.id.include_top_tv_title);
        TextView incluTvRight=
        getView(R.id.inclu_tv_right);

        ImageView groupSetIvHead=
        getView(R.id.group_set_iv_head);
        TextView groupSetTvName=
        getView(R.id.group_set_tv_name);
        LinearLayout groupSetLinChangeHead=
        getView(R.id.group_set_lin_change_head);
        EditText groupSetEdGroupname=
        getView(R.id.group_set_ed_groupname);
        LinearLayout groupSetLinMain=
        getView(R.id.group_set_lin_main);
        addOnClickListeners(R.id.inclu_tv_right,R.id.group_set_lin_change_head, R.id.group_set_ed_groupname);
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_group_chat_set;
    }


    //点击发送
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.group_set_lin_change_head:

                break;
            case R.id.group_set_ed_groupname:

                break;
            case R.id.inclu_tv_right:
                ToastUtil.show("我点击了发送");
                break;
        }
    }

    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String method = HelpUtils.backMethod(responseText);
        switch (method) {
            case "addGroupOf":
                DialogUtils.showDialog("申请入群成功");
                break;
            default:
                break;
        }
    }

}
