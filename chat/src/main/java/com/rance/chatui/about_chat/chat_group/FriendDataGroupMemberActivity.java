package com.rance.chatui.about_chat.chat_group;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mding.chatfeng.base_common.components.base.BaseActivity;
import com.mding.chatfeng.base_common.components.model.DataGroupMemberInfo;
import com.mding.chatfeng.base_common.components.model.DataMyFriend;
import com.mding.chatfeng.base_common.components.model.DataScanFirendRequest;
import com.mding.chatfeng.base_common.utils.GsonParamConverter;
import com.mding.chatfeng.base_common.utils.about_main_utils.HelpUtils;
import com.mding.chatfeng.base_common.utils.about_main_utils.IntentUtils;
import com.mding.chatfeng.base_common.utils.aboututils.NoDoubleClickUtils;
import com.mding.chatfeng.base_common.utils.aboututils.StrUtils;
import com.mding.chatfeng.chat.R;

import jp.wasabeef.glide.transformations.CropCircleTransformation;


/**
 * 位置：群成员详情
 * zll
 */
public class FriendDataGroupMemberActivity extends BaseActivity {
    TextView includeTopTvTital;
    ImageView includeTopIvMore;
    ImageView mIvHead;
    TextView incluTvRight;
    TextView mTvName;
    TextView fdaTvNum;
    TextView fdaTvSign;
    TextView fdTvAddFriend;

    public static final String FRIENG_ID_KEY = "friendId";
    public static final String GROUP_ID_KEY = "groupId";
    public static final String IS_FRIEND = "2";
    public static final String NOT_FRIEND = "1";

//    DataSearch dataSearch = null;

    @Override
    protected void initBaseView() {
        super.initBaseView();

        initUI();

        includeTopTvTital.setText("好友资料");
        incluTvRight.setVisibility(View.GONE);
        includeTopIvMore.setVisibility(View.GONE);
        Intent intent = getIntent();
        if (intent != null) {
            String friendId = intent.getStringExtra(FRIENG_ID_KEY);
            String groupId = intent.getStringExtra(GROUP_ID_KEY);
//TODO 请求
         /*   sendWeb(SplitWeb.getSplitWeb().getGroupMemberInfo(friendId, groupId));
            sendWeb(SplitWeb.getSplitWeb().addFriendQrCode(friendId));*/
//            }
        }
    }

    private void initUI() {
        includeTopTvTital=getView(R.id.include_top_tv_title);
         includeTopIvMore=getView(R.id.include_top_iv_more);
         mIvHead=getView(R.id.data_iv_head);
         incluTvRight=getView(R.id.inclu_tv_right);
         mTvName=getView(R.id.fda_tv_name);
         fdaTvNum= getView(R.id.fda_tv_num);
         fdaTvSign=getView(R.id.fda_tv_sign);
         fdTvAddFriend=getView(R.id.fd_tv_add_friend);
    }

    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String method = HelpUtils.backMethod(responseText);
        switch (method) {
            //            获取好友数据
            case "getFriendInfo":
                initDataFriend(responseText);
                break;
            case "getGroupMemberInfo":

                initDataMember(responseText);
                break;
            case "addFriendQrCode":
                DataScanFirendRequest dataScanFirendRequest =  new GsonParamConverter().json2Object(responseText, DataScanFirendRequest.class);
                DataScanFirendRequest.RecordBean record = dataScanFirendRequest.getRecord();
                if (record != null) {
                    mTvName.setText(record.getNickName());
                    fdaTvNum.setText(record.getWxSno());
                    String signText = StrUtils.isEmpty(record.getPersonaSignature()) ? "暂未签名" : record.getPersonaSignature();
                    fdaTvSign.setText(signText);
                    Glide.with(this).load(record.getHeadImg())
                            .bitmapTransform(new CropCircleTransformation(FriendDataGroupMemberActivity.this))
                            .into(mIvHead);

//                    dataSearch = new DataSearch();
//                    dataSearch.setSno(record.getWxSno());
//                    dataSearch.setName(record.getNickName());
//                    dataSearch.setId(record.getFriendId());
//                    dataSearch.setHeadImg(record.getHeadImg());
                }
                break;



        }

    }

    private void initDataMember(String responseText) {
//        DataGroupMemberInfo
        DataGroupMemberInfo dataGroupMemberInfo = new GsonParamConverter().json2Object(responseText, DataGroupMemberInfo.class);
        DataGroupMemberInfo.RecordBean record = dataGroupMemberInfo.getRecord();
        if (record != null) {
            mTvName.setText(record.getNickName());
            fdaTvNum.setText(record.getWxSno());
//            String signText= StrUtils.isEmpty(record.get())?"暂未签名":record.getPersonaSignature();
//            fdaTvSign.setText(signText);
            Glide.with(this).load(record.getHeadImg())
                    .bitmapTransform(new CropCircleTransformation(FriendDataGroupMemberActivity.this))
                    .into(mIvHead);
        }


    }

    DataMyFriend.RecordBean dataRecord;

    private void initDataFriend(String responseText) {

        DataMyFriend dataMyFriend = new GsonParamConverter().json2Object(responseText, DataMyFriend.class);

        DataMyFriend.RecordBean record = dataMyFriend.getRecord();
        if (record != null) {
            dataRecord = record;
            Glide.with(this).load(record.getHeadImg())
                    .bitmapTransform(new CropCircleTransformation(FriendDataGroupMemberActivity.this))
                    .into(mIvHead);
            String signText = StrUtils.isEmpty(record.getPersonaSignature()) ? "暂未设置签名" : record.getPersonaSignature();
            fdaTvSign.setText(signText);
//            fdaTvFenzu.setText(record.getGroupName() + "");
            fdaTvNum.setText(record.getWxSno());
            mTvName.setText(record.getNickName());
            String beizhuText = StrUtils.isEmpty(record.getRemarkName()) ? "暂未设置备注" : record.getRemarkName();
//            mTvName.setText(nameText);
//            fdTvBeizhu.setText(beizhuText);
        }
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_friend_data_group_member;
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
//            顶部点点点按钮
            case R.id.include_top_iv_more:
                break;
//                二维码按钮
            case R.id.data_iv_qrcode:
                /*if (NoDoubleClickUtils.isDoubleClick()) {
                    if (dataSearch != null) {
                        PersonData personData = new PersonData();
                        personData.setHeadImg(dataSearch.getHeadImg());
                        personData.setName(dataSearch.getName());
                        personData.setScanTital("扫一扫,添加" + dataSearch.getName() + "为好友");
                        personData.setTital(getResources().getString(R.string.qrcode_title_friend));
                        personData.setQrCode(dataSearch.getQrcode());
                        IntentUtils.JumpToHaveObj(MyAccountActivity.class, MyAccountActivity.TITAL_NAME, personData);
                    }
                }*/
                break;
//                头像按钮
            case R.id.data_iv_head:
                break;
//                添加好友
            case R.id.fd_tv_add_friend:
               /* if (NoDoubleClickUtils.isDoubleClick())
                    IntentUtils.JumpToHaveObj(AddGoodFriendActivity.class, AddGoodFriendActivity.DataKey, dataSearch);*/
                break;
////                发送消息
//            case R.id.fd_tv_send_msg:
//                if (NoDoubleClickUtils.isDoubleClick()) {
//                    if (dataRecord != null) {
//                        CusJumpChatData cusJumpChatData = new CusJumpChatData();
//                        cusJumpChatData.setFriendHeader(dataRecord.getHeadImg());
//                        cusJumpChatData.setFriendId(dataRecord.getFriendId());
//                        cusJumpChatData.setFriendName(dataRecord.getNickName());
//                        realmHelper.addRealmMsg(cusJumpChatData);
//                        IntentUtils.JumpToHaveObj(ChatActivity.class, Constants.KEY_FRIEND_HEADER, cusJumpChatData);
//                    }
//                }
//                break;
////                发送语音
//            case R.id.fd_tv_send_call:
//                if (NoDoubleClickUtils.isDoubleClick()) {
//
//                }
//                break;
        }
    }
}
