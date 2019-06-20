package com.rance.chatui.about_chat.chat_group.sub_group;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mding.chatfeng.base_common.AppConfig;
import com.mding.chatfeng.base_common.components.base.BaseActivity;
import com.mding.chatfeng.base_common.components.model.DataNotice;
import com.mding.chatfeng.base_common.utils.GsonParamConverter;
import com.mding.chatfeng.base_common.utils.about_main_utils.HelpUtils;
import com.mding.chatfeng.base_common.utils.about_main_utils.ImageUtils;
import com.mding.chatfeng.base_common.utils.aboutsystem.AppManager;
import com.mding.chatfeng.base_common.utils.aboututils.ToastUtil;
import com.mding.chatfeng.chat.R;

/**
 * 群公告页面
 * */
public class GroupNoticeActivity extends BaseActivity {

    ImageView includeTopIvBack;
    LinearLayout includeTopLinNewback;
    TextView includeTopTvTital;
    TextView incluTvRight;
    LinearLayout includeTopLinBackground;
    ImageView acIvHead;
    TextView acTvName;
    LinearLayout acLinTime;
    TextView acTvTime;
    EditText acEtContent;
    LinearLayout acLinGrouperData;

    String content;


    @Override
    protected int getLayoutView() {
        return R.layout.activity_group_notice;
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();


        initUI();
        includeTopTvTital.setText("群公告");

        Intent intent = getIntent();
        if (intent != null) {
            groupofId = intent.getStringExtra("groupId");
            isGrouper = intent.getBooleanExtra("isGrouper", false);
            content = intent.getStringExtra("content");

//          todo  sendWeb(SplitWeb.getSplitWeb().groupNoticeInfo(groupofId));
        }
        initNotice();
        // 判断是否为群主（或管理员），是则可编辑群公告，不是则不可编辑群公告
        if (isGrouper) {
            acEtContent.setFocusable(true);
            acEtContent.setFocusableInTouchMode(true);
            acEtContent.requestFocus();
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
            acEtContent.addTextChangedListener(textWatcher);
            acEtContent.setSelection(acEtContent.getText().toString().length());
        }
        else {
            acEtContent.setFocusable(false);
            acEtContent.setFocusableInTouchMode(false);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        }
    }

    private void initUI() {
         includeTopIvBack=
        getView(R.id.include_top_iv_back);
         includeTopLinNewback=
        getView(R.id.include_top_lin_newback);
         includeTopTvTital=
        getView(R.id.include_top_tv_title);
         incluTvRight=
        getView(R.id.inclu_tv_right);
         includeTopLinBackground=
        getView(R.id.include_top_lin_background);
         acIvHead=
        getView(R.id.ac_iv_head);
         acTvName=
        getView(R.id.ac_tv_name);
         acLinTime=
        getView(R.id.ac_lin_time);
         acTvTime=
        getView(R.id.ac_tv_time);
         acEtContent=
        getView(R.id.ac_et_content);
         acLinGrouperData=
        getView(R.id.ac_lin_grouper_data);

         addOnClickListeners(R.id.inclu_tv_right);
    }

    // 设置从群聊资料界面传来的群公告
    private void initNotice() {
        if (isGrouper){
            if (content != null && !content.equals("暂无公告")) {
                acEtContent.setText(content);
            }
            else {
                acLinGrouperData.setVisibility(View.GONE);
            }
        }
        else {
            if (content != null && !content.equals("暂无公告")) {
                acEtContent.setText(content);
            }
            else {
                acLinGrouperData.setVisibility(View.GONE);
                acEtContent.setText("暂无公告");
            }
        }
    }

    // 群公告编辑监听，有改变则显示右上角“保存”按钮
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            // 判断当下输入的内容是否与之前的内容相同并且不为空，相同或者为空则不出现保存按钮，否则显示
            if (!content.equals(editable.toString()) && !editable.toString().equals(""))
                incluTvRight.setVisibility(View.VISIBLE);
            else
                incluTvRight.setVisibility(View.INVISIBLE);
        }
    };

    String groupofId;

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId())
        {
            case R.id.inclu_tv_right:
                if (groupofId!=null){
//                  todo  sendWeb(SplitWeb.getSplitWeb().editGroupNotice(groupofId, acEtContent.getText().toString()));
                }
                else
                    ToastUtil.show("系统故障");
                break;
        }
    }


    boolean isGrouper;
public  static final  String GROUP_NOTICES="group_notice";
    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String method = HelpUtils.backMethod(responseText);
        switch (method) {
            case "editGroupNotice":
                ToastUtil.show("保存成功");
                Intent intent = new Intent();
                intent.putExtra(GROUP_NOTICES,acEtContent.getText().toString());
                setResult(AppConfig.EDIT_GROUP_NOTICE_RESULT,intent);
                AppManager.getAppManager().finishActivity(GroupNoticeActivity.this);
                break;
            case "groupNoticeInfo":
                DataNotice dataNotice =  new GsonParamConverter().json2Object(responseText, DataNotice.class);
                DataNotice.RecordBean noticeRecord = dataNotice.getRecord();
                if (noticeRecord != null) {
                    DataNotice.RecordBean.GroupInfoBean groupInfoBean = noticeRecord.getGroupInfo();
                    if (groupInfoBean != null) {
                        DataNotice.RecordBean.MemberInfoBean memberInfoBean = noticeRecord.getMemberInfo();
                        if (memberInfoBean != null) {
                            acTvTime.setText(groupInfoBean.getCreated());
                            acTvName.setText(memberInfoBean.getNickName());
                            String headImg = memberInfoBean.getHeadImg();
//                            ImageUtils.useBase64WithError(GroupNoticeActivity.this, acIvHead,  headImg.substring(0, headImg.indexOf("_")), R.drawable.first_head_nor);
                            ImageUtils.useBase64WithError(GroupNoticeActivity.this, acIvHead, memberInfoBean.getHeadImg(), R.drawable.first_head_nor);
//                            Glide.with(this).load(memberInfoBean.getHeadImg())
//                                    .bitmapTransform(new CropCircleTransformation(GroupNoticeActivity.this))
//                                    .error(R.drawable.qun_head)
//                                    .into(acIvHead);
                            Log.e("acTvTime", "++++++++++++++++++++++++++++" + acTvTime.getText());
                            Log.e("acTvName", "++++++++++++++++++++++++++++" + acTvName.getText());
                        }
                    }
                }
                break;
        }
    }


}
