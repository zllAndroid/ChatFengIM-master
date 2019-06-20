package com.rance.chatui.about_chat;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhouwei.library.CustomPopWindow;
import com.mding.chatfeng.base_common.components.base.BaseActivity;
import com.mding.chatfeng.base_common.components.model.DataChatFriendInfo;
import com.mding.chatfeng.base_common.cus_model.PersonData;
import com.mding.chatfeng.base_common.utils.GsonParamConverter;
import com.mding.chatfeng.base_common.utils.about_dialog.DialogUtils;
import com.mding.chatfeng.base_common.utils.about_main_utils.HelpUtils;
import com.mding.chatfeng.base_common.utils.about_main_utils.ImageUtils;
import com.mding.chatfeng.base_common.utils.about_main_utils.IntentUtils;
import com.mding.chatfeng.base_common.utils.aboutsystem.AppManager;
import com.mding.chatfeng.base_common.utils.aboututils.NoDoubleClickUtils;
import com.mding.chatfeng.base_common.utils.aboututils.StrUtils;
import com.mding.chatfeng.base_common.utils.aboututils.ToastUtil;
import com.mding.chatfeng.chat.R;
import com.suke.widget.SwitchButton;



public class ChatSetActivity extends BaseActivity {

    ImageView includeTopIvBack;
    TextView includeTopTopTvTitle;
    ImageView includeTopIvMore;
    TextView incluTvRight;
    TextView mTvName;
    TextView fdTvContant;
    TextView fdTvGesign;
    ImageView mIvHead;
    ImageView fdIvQrcode;
    LinearLayout includeTopLinBackground;

    SwitchButton chatsetSwiZhidingChat;

    SwitchButton chatsetMsgMiandarao;

    LinearLayout mLinTop;
    LinearLayout mLinNoCall;

    @Override
    protected int getLayoutView() {
        return R.layout.activity_chat_set;
    }

    String FriendId;
    View mView;

   /* RealmHomeHelper realmHelper;
    RealmChatHelper realmChatHelper;
    */
   CustomPopWindow popWindow;

    String type = "1";
    TextView mTvShield;

    @Override
    protected void initBaseView() {
        super.initBaseView();
//
        initUI();
        includeTopIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppManager.getAppManager().finishActivity(ChatSetActivity.this);
            }
        });
        includeTopTopTvTitle.setText("聊天设置");
        incluTvRight.setVisibility(View.GONE);
        includeTopIvMore.setVisibility(View.VISIBLE);
        includeTopLinBackground.setBackgroundColor(getResources().getColor(R.color.app_theme));

        Intent intent = getIntent();
        FriendId = intent.getStringExtra("FriendId");


//   TODO 请求     sendWeb(SplitWeb.getSplitWeb().getFriendInfo(FriendId));
        initSwiButton();
//   TODO
     /*   realmHelper = new RealmHomeHelper(this);
        realmChatHelper = new RealmChatHelper(this);*/
        mView = LayoutInflater.from(this).inflate(R.layout.pop_good_friend, null);
        mTvShield = mView.findViewById(R.id.pop_tv_pingbi);


        mView.findViewById(R.id.pop_tv_del).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ToastUtil.show("点击了删除");
             DialogUtils.showDialog("是否确定删除该好友？", new DialogUtils.OnClickSureListener() {
                    @Override
                    public void onClickSure() {
//         TODO               sendWebHaveDialog(SplitWeb.getSplitWeb().deleteFriend(FriendId), "正在删除...", "删除成功");
                    }
                });
                if (popWindow != null)
                    popWindow.dissmiss();
            }
        });
        mView.findViewById(R.id.pop_tv_dongtai).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popWindow != null)
                    popWindow.dissmiss();
               if (NoDoubleClickUtils.isDoubleClick())
               {
//      TODO             IntentUtils.JumpTo(DongTaiSetActivity.class);
               }

            }
        });

    }

    private void initUI() {

         includeTopIvBack=getView(R.id.include_top_iv_back);
         includeTopTopTvTitle=getView(R.id.include_top_tv_title);
         includeTopIvMore=getView(R.id.include_top_iv_more);
         incluTvRight=getView(R.id.inclu_tv_right);
         mTvName=getView(R.id.fd_tv_name);
         fdTvContant=getView(R.id.fd_tv_contant);
         fdTvGesign=getView(R.id.fd_tv_gesign);
         mIvHead=getView(R.id.fd_iv_head);
         fdIvQrcode=getView(R.id.fd_iv_qrcode);
         includeTopLinBackground=getView(R.id.include_top_lin_background);

         chatsetSwiZhidingChat=getView(R.id.chatset_swi_zhiding_chat);

         chatsetMsgMiandarao=getView(R.id.chatset_msg_miandarao);
         mLinTop=getView(R.id.chatset_lin_zhiding_chat);
         mLinNoCall=getView(R.id.chatset_lin_msg_miandarao);

         addOnClickListeners(R.id.include_top_iv_more, R.id.fd_lin_name, R.id.chatset_lin_chat_history, R.id.chatset_lin_del_chat_history);
    }

    private void initSwiButton() {
        chatsetSwiZhidingChat.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
//                boolean checked = chatsetSwiZhidingChat.isChecked();
//                if (dataRecord!=null)
                String type = isChecked ? "2" : "1";
//    TODO            send(SplitWeb.getSplitWeb().topFriend(FriendId, type));
            }
        });
        chatsetMsgMiandarao.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
//                boolean check2 = chatsetMsgMiandarao.isChecked();
                String type2 = isChecked ? "2" : "1";
//      TODO          send(SplitWeb.getSplitWeb().disturbFriend(FriendId, type2));
            }
        });
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int i = view.getId();
        if (i == R.id.include_top_iv_more) {
            if (popWindow == null) {
                popWindow = new CustomPopWindow.PopupWindowBuilder(ChatSetActivity.this)
                        .setView(mView)
                        .setFocusable(true)
                        .setOutsideTouchable(true)
                        .size(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                        .setAnimationStyle(R.style.AnimDown) // 添加自定义显示和消失动画
                        .create()
                        .showAsDropDown(includeTopIvMore, 0, 0);
            } else
                popWindow.showAsDropDown(includeTopIvMore, 0, 0);


        } else if (i == R.id.fd_lin_name) {
            if (dataRecord != null) {
                PersonData personData = new PersonData();
                personData.setHeadImg(dataRecord.getHeadImg());
                personData.setName(dataRecord.getNickName());
                personData.setScanTital("扫一扫,添加" + dataRecord.getNickName() + "为好友");
                personData.setTital(getResources().getString(R.string.qrcode_title_friend));

                if (FriendId != null) {
                    String string = type + "_xm6leefun_" + FriendId;
                    Log.e("qrcode", "----------chatSetActivity--------------" + string);
//                        Bitmap bitmap = ZXingUtils.createQRImage(string,300,300);
//                        Drawable drawable = new BitmapDrawable(bitmap);
//                        Log.e("qrcode","-------record.getQrcode()---------"+drawable);
//                        qrcodeIvQrcode.setBackground(drawable);
                    personData.setQrCode(string);
                }
//      TODO          IntentUtils.JumpToHaveObj(MyAccountActivity.class, MyAccountActivity.TITAL_NAME, personData);
            }

        } else if (i == R.id.chatset_lin_chat_history) {
            DialogUtils.showDialog(getResources().getString(R.string.stay_tuned));

        } else if (i == R.id.chatset_lin_del_chat_history) {
            DialogUtils.showDialog("确定删除与该好友的聊天记录？", new DialogUtils.OnClickSureListener() {
                @Override
                public void onClickSure() {
//                    TODO
                 /*   realmChatHelper.delChatMsgAll(FriendId);
                    realmHelper.deleteRealmMsg(FriendId);//更新首页聊天界面数据（消息和时间）*/
                    Intent intent = new Intent();
                    intent.putExtra("id", FriendId);
                    intent.setAction("del.refreshMsgFragment");
                    sendBroadcast(intent);
                }
            });


        }
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
            case "deleteFriend":
                DialogUtils.showDialogOne("删除好友成功", new DialogUtils.OnClickSureListener() {
                    @Override
                    public void onClickSure() {
//                        TODO
                     /*   realmHelper.deleteRealmMsg(FriendId);
                        realmChatHelper.deleteRealmMsg(FriendId);*/
                        Intent intent2 = new Intent();
                        intent2.putExtra("id", FriendId);
                        intent2.setAction("del.refreshMsgFragment");
                        sendBroadcast(intent2);
                        AppManager.getAppManager().finishActivity(ChatSetActivity.this);
                        AppManager.getAppManager().finishActivity(ChatActivity.class);
                    }
                });
                break;
            case "shieldFriend":
                if (shieldType != null) {
                    String shile = shieldType.equals("2") ? "取消屏蔽好友成功" : "屏蔽好友成功";
                    DialogUtils.showDialogOne(shile, new DialogUtils.OnClickSureListener() {
                        @Override
                        public void onClickSure() {
                            AppManager.getAppManager().finishActivity(ChatSetActivity.this);
                        }
                    });
                }

                break;
            case "topFriend":
                boolean checked = chatsetSwiZhidingChat.isChecked();
                String text = checked ? "置顶成功" : "取消置顶";
                ToastUtil.show(text);
                break;
            case "disturbFriend":
                boolean check = chatsetMsgMiandarao.isChecked();
                String text1 = check ? "设置免打扰成功" : "取消免打扰";
                ToastUtil.show(text1);
                break;
        }
    }

    DataChatFriendInfo.RecordBean dataRecord;
    String shieldType;

    private void initDataFriend(String responseText) {
        DataChatFriendInfo dataChatFriendInfo =    new GsonParamConverter().json2Object(responseText, DataChatFriendInfo.class);
        DataChatFriendInfo.RecordBean record = dataChatFriendInfo.getRecord();
        if (record != null) {
            dataRecord = record;
            String headImg = record.getHeadImg();
//            ImageUtils.useBase64WithError(ChatSetActivity.this, mIvHead,  headImg.substring(0, headImg.indexOf("_")), R.drawable.first_head_nor);
            ImageUtils.useBase64WithError(ChatSetActivity.this, mIvHead, record.getHeadImg(), R.drawable.first_head_nor);
//            Glide.with(this).load(record.getHeadImg())
//                    .bitmapTransform(new CropCircleTransformation(ChatSetActivity.this))
//                    .into(mIvHead);
            String signText = StrUtils.isEmpty(record.getPersonaSignature()) ? "暂未设置签名" : record.getPersonaSignature();
            fdTvGesign.setText(signText);
            if (record.getIsSnoShow().equals("0")) {// 0为不显示
                fdTvContant.setVisibility(View.GONE);
//                fdTvContant.setText("不显示帐号");
            } else
                fdTvContant.setText("(" + record.getWxSno() + ")");
//            若有设置备注，仅显示备注；若无备注则显示昵称
            String nameText = StrUtils.isEmpty(record.getRemarkName()) ? record.getNickName() : record.getRemarkName();
            mTvName.setText(nameText);
//            mTvName.setText(record.getNickName() + "(" + record.getRemarkName() + ")");
            chatsetMsgMiandarao.setChecked(record.getDisturbType().equals("2"));
            chatsetSwiZhidingChat.setChecked(record.getTopType().equals("2"));
            shieldType = record.getShieldType();
            String shile = shieldType.equals("2") ? "取消屏蔽" : "屏蔽好友";

            mTvShield.setText(shile);
            mTvShield.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                ToastUtil.show("点击了屏蔽");
                    if (shieldType.equals("2")) {
                        DialogUtils.showDialog("是否取消屏蔽此好友？", new DialogUtils.OnClickSureListener() {
                            @Override
                          public void onClickSure() {
//               TODO                   sendWebHaveDialog(SplitWeb.getSplitWeb().shieldFriend(FriendId, "1"), "正在取消屏蔽...", "取消屏蔽成功");
                            }
                        });
                    } else {
                        DialogUtils.showDialog("是否屏蔽此好友？", new DialogUtils.OnClickSureListener() {
                            @Override
                            public void onClickSure() {
//                         TODO         sendWebHaveDialog(SplitWeb.getSplitWeb().shieldFriend(FriendId, "2"), "正在屏蔽...", "屏蔽成功");
                            }
                        });
                    }

                    if (popWindow != null)
                        popWindow.dissmiss();
                }
            });
            if (dataRecord.getIsQrcodeShow().equals("0")) {  // 0为不显示
                fdIvQrcode.setVisibility(View.GONE);
            } else
                fdIvQrcode.setVisibility(View.VISIBLE);
//            手机号码是否显示
//            if (dataRecord.getIsMobileShow().equals("0")) {  // 0为不显示
//                fdIvQrcode.setVisibility(View.GONE);
//            } else
//                fdIvQrcode.setVisibility(View.VISIBLE);
        }
    }

}
