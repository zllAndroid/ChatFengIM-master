package com.rance.chatui.about_chat.chat_group;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mding.chatfeng.base_common.AppConfig;
import com.mding.chatfeng.base_common.components.base.BaseActivity;
import com.mding.chatfeng.base_common.components.base.BaseApplication;
import com.mding.chatfeng.base_common.components.model.DataAddQunDetails;
import com.mding.chatfeng.base_common.components.model.DataSetGroupHeadResult;
import com.mding.chatfeng.base_common.components.model.GroupHeadImgInfo;
import com.mding.chatfeng.base_common.utils.GsonParamConverter;
import com.mding.chatfeng.base_common.utils.HeadFileUtils;
import com.mding.chatfeng.base_common.utils.about_dialog.DialogUtils;
import com.mding.chatfeng.base_common.utils.about_img_clip.ClipImgActivity;
import com.mding.chatfeng.base_common.utils.about_main_utils.HelpUtils;
import com.mding.chatfeng.base_common.utils.about_main_utils.ImageUtils;
import com.mding.chatfeng.base_common.utils.about_main_utils.IntentUtils;
import com.mding.chatfeng.base_common.utils.about_popwindow.PhotoPopWindow;
import com.mding.chatfeng.base_common.utils.aboutsystem.AppManager;
import com.mding.chatfeng.base_common.utils.aboututils.ACache;
import com.mding.chatfeng.base_common.utils.aboututils.MyLog;
import com.mding.chatfeng.base_common.utils.aboututils.NoDoubleClickUtils;
import com.mding.chatfeng.base_common.utils.aboututils.StrUtils;
import com.mding.chatfeng.base_common.utils.aboututils.ToastUtil;
import com.mding.chatfeng.chat.R;
import com.rance.chatui.about_chat.chat_group.adapter.GroupMemberQunzhuAdapter;
import com.rance.chatui.about_chat.chat_group.sub_group.AddGroupWayActivity;
import com.rance.chatui.about_chat.chat_group.sub_group.EditGroupCardActivity;
import com.rance.chatui.about_chat.chat_group.sub_group.GroupChatSetActivity;
import com.rance.chatui.about_chat.chat_group.sub_group.GroupNoticeActivity;
import com.rance.chatui.about_chat.chat_group.sub_group.GrouperEscActivity;
import com.rance.chatui.about_chat.chat_group.sub_group.InvitationGroupChatActivity;
import com.rance.chatui.about_chat.chat_group.sub_group.about_intent_data.IntentDataInvitation;
import com.suke.widget.SwitchButton;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * 项目：DoubleQ
 * 文件描述：群聊资料
 * 作者：zll
 * 修改者：ljj
 */
public class GroupChatDetailsActivity extends BaseActivity
//        implements ChangeInfoWindow.OnAddContantClickListener
{
    LinearLayout includeTopLinBackground;
    TextView includeTopTvTital;
    TextView incluTvRight;
    ImageView includeTopIvMore;
    ImageView groupDataIvHead;
    TextView groupDataTvChatnum;
    RecyclerView groupDataRecyc;
    TextView groupDataTvGonggao;
    LinearLayout groupDetailsLinHaveSet;
    LinearLayout mLinMain;
    TextView groupDataTvMineName;
    LinearLayout groupDataLinMyGroupCard;
    LinearLayout groupDetailsLinGroupNotice;
    TextView groupDetailsTvGroupId;
    ImageView groupDetailsIvEdit;
    LinearLayout groupDetailsLinName;
    TextView groupDetailsTvName;

    SwitchButton chatsetSwiDarao;
    SwitchButton chatsetSwiQunZhu;

    TextView groupDataTvGroupingName;
//    private RealmGroupChatHeaderHelper realmGroupChatHeaderHelper;
//    RealmMsgInfoTotalHelper realmMsgInfoTotalHelper;
//    DataSearch dataSearch = null;
    static String groupId;
    static String groupType;
    static String groupName;
    static String groupChatName;
    //请求相机
    private int CAMERA_RESULT_GROUP = 200;
    //请求相册
    private static final int REQUEST_PICK = 201;
    //请求截图
    private static final int REQUEST_CROP_PHOTO = 202;
    //请求访问外部存储
    private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 203;
    //请求写入外部存储
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 204;
    boolean isNeedChangeHeadImg = true;
    ACache mACache;
    String searchDetailInfo;

    @Override
    protected void initBaseView() {
        super.initBaseView();
        initUIView();
        includeTopTvTital.setText("群聊资料");
        incluTvRight.setVisibility(View.GONE);
        includeTopIvMore.setVisibility(View.VISIBLE);
        includeTopLinBackground.setBackgroundColor(getResources().getColor(R.color.app_theme));

        groupDataRecyc.setHasFixedSize(true);
        groupDataRecyc.setNestedScrollingEnabled(false);
        groupDataRecyc.setLayoutManager(new GridLayoutManager(this, 5));

      /*  realmHelper = new RealmHomeHelper(this);
        realmGroupChatHeaderHelper = new RealmGroupChatHeaderHelper(this);
        realmMsgInfoTotalHelper = new RealmMsgInfoTotalHelper(this);*/


        Intent intent = getIntent();
        if (intent != null) {
            groupId = intent.getStringExtra(AppConfig.GROUP_ID);
            groupType = intent.getStringExtra(AppConfig.IS_CHATGROUP_TYPE);
            MyLog.e("myGroupId","-----------------------details-------------------------"+groupId+"groupType=="+groupType);
            if (!StrUtils.isEmpty(groupId)) {
//                TODO
              /*  searchDetailInfo = BaseApplication.getaCache().getAsString(groupId + SplitWeb.getSplitWeb().USER_ID);
              if (StrUtils.isEmpty(searchDetailInfo))  {
                    sendWeb(SplitWeb.getSplitWeb().searchDetailInfo(groupId));
                }
                else{
                    dealSearchDetailInfo(searchDetailInfo);
                    sendWeb(SplitWeb.getSplitWeb().searchDetailInfo(groupId, verificationMD5));
                }*/
            }
        }
        initRightPop();
        initSwiButton();
    }

    private void initUIView() {

         includeTopLinBackground=
        getView(R.id.include_top_lin_background);
         includeTopTvTital=
        getView(R.id.include_top_tv_title);
         incluTvRight=
        getView(R.id.inclu_tv_right);
         includeTopIvMore=
        getView(R.id.include_top_iv_more);
         groupDataIvHead=
        getView(R.id.group_data_iv_head);
         groupDataTvChatnum=
        getView(R.id.group_data_tv_chatnum);
         groupDataRecyc=
        getView(R.id.group_data_recyc);
         groupDataTvGonggao=
        getView(R.id.group_data_tv_gonggao);
         groupDetailsLinHaveSet=
        getView(R.id.group_details_lin_have_set);
         mLinMain=
        getView(R.id.group_details_lin_main);
         groupDataTvMineName=
        getView(R.id.group_data_tv_mine_name);
         groupDataLinMyGroupCard=
        getView(R.id.group_data_lin_myGroupCard);
         groupDetailsLinGroupNotice=
        getView(R.id.group_details_lin_group_notice);
         groupDetailsTvGroupId=
        getView(R.id.group_details_tv_group_id);
         groupDetailsIvEdit=
        getView(R.id.group_details_iv_edit);
         groupDetailsLinName=
        getView(R.id.group_details_lin_name);
         groupDetailsTvName=
        getView(R.id.group_details_tv_name);

         chatsetSwiDarao=
        getView(R.id.group_chat_data_swibtn_nodarao);
         chatsetSwiQunZhu=
        getView(R.id.group_chat_data_swibtn_qunzhu);

         groupDataTvGroupingName=
        getView(R.id.group_data_tv_grouping_name);

         addOnClickListeners(R.id.group_data_lin_intogrouplist, R.id.include_top_iv_more, R.id.group_data_lin_myGroupCard,
                 R.id.group_details_lin_group_notice, R.id.group_details_lin_set, R.id.group_details_lin_add_type, R.id.group_details_lin_group_notice, R.id.group_details_lin_chat_old, R.id.group_details_lin_del_chat,
                 R.id.include_top_iv_zhuanfa, R.id.group_details_iv_qrcode, R.id.group_details_lin_name, R.id.group_data_iv_head,
                 R.id.group_chat_data_swibtn_nodarao, R.id.group_chat_data_lin_nodarao,R.id.group_data_lin_grouping, R.id.group_details_tv_to_chat);

//         addOnClickListeners(R.id.group_data_lin_intogrouplist, R.id.include_top_iv_more, R.id.group_data_lin_myGroupCard,
//                 R.id.group_details_lin_group_notice);

    }

    private void initSwiButton() {
        chatsetSwiDarao.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
               /* String daRao = isChecked ? "2" : "1";
                send(SplitWeb.getSplitWeb().setUserGroupDisturb(groupId, daRao));*/
            }
        });
        chatsetSwiQunZhu.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
               /* String ass = isChecked ? "2" : "1";
                send(SplitWeb.getSplitWeb().setUserGroupAssistant(groupId, ass));*/
            }
        });
    }

    public static boolean isFirst = false;
    @Override
    protected void onResume() {
        super.onResume();
        if (isFirst) {
            if (!StrUtils.isEmpty(groupId)) {
              /*  searchDetailInfo = BaseApplication.getaCache().getAsString(groupId + SplitWeb.getSplitWeb().USER_ID);
                if (StrUtils.isEmpty(searchDetailInfo)) {
                    sendWeb(SplitWeb.getSplitWeb().searchDetailInfo(groupId));
                }
                else{
//                    dealSearchDetailInfo(searchDetailInfo);
                    sendWeb(SplitWeb.getSplitWeb().searchDetailInfo(groupId,verificationMD5));
                }*/
            }
        }
        isFirst = true;
    }

    View mView;
    private void initRightPop() {
        mView = LayoutInflater.from(this).inflate(R.layout.pop_group_details_esc, null);
        mView.findViewById(R.id.pop_btn_esc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ToastUtil.show("点击了屏蔽");
//                if (popWindow != null)
//                    popWindow.dissmiss();
                if (isGrouper) {
                    IntentUtils.JumpToHaveTwo(GrouperEscActivity.class, AppConfig.GROUPER_ESC, groupId, AppConfig.GROUPER_NAME, groupName);
                    AppManager.getAppManager().finishActivity(GroupChatDetailsActivity.this);
                } else {
                  /*  DialogUtils.showDialog("是否确认退出群聊", new DialogUtils.OnClickSureListener() {
                        @Override
                        public void onClickSure() {
                            sendWebHaveDialog(SplitWeb.getSplitWeb().outGroupChat(groupId), "正在退出...", "退出成功");
                        }
                    });*/
                }
            }
        });
      /*  mView.findViewById(R.id.pop_btn_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popWindow != null)
                    popWindow.dissmiss();
            }
        });
        mView.findViewById(R.id.pop_v_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popWindow != null)
                    popWindow.dissmiss();
            }
        });*/
    }

    public static final String ACTION_UP_GROUP_NAME = "action.upGroupName";

    boolean isGrouper = false;//    是否群主
    DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupInfoBean dataRecord;
    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String s = HelpUtils.backMethod(responseText);
        switch (s) {
            case "searchDetailInfo":
              /*  BaseApplication.getaCache().put(groupId + SplitWeb.getSplitWeb().USER_ID, responseText);

                dealSearchDetailInfo(responseText);*/
                break;
            case "outGroupChat":
               /* DialogUtils.showDialogOne("退出群聊成功", new DialogUtils.OnClickSureListener() {
                    @Override
                    public void onClickSure() {
                        RealmHomeHelper realmHomeHelper = new RealmHomeHelper(GroupChatDetailsActivity.this);
                        realmHomeHelper.deleteRealmMsg(groupId);
//                        Intent intent2 = new Intent();
//                        intent2.putExtra("id", groupId);
//                        intent2.setAction("del.refreshMsgFragment");
//                        sendBroadcast(intent2);
//                        退出群聊删除首页聊天内容
                        EventBus.getDefault().post(new MsgHomeEvent("",groupId,AppConfig.MSG_DEL_REFRESH));
                        AppManager.getAppManager().finishActivity(GroupChatDetailsActivity.this);
                        AppManager.getAppManager().finishActivity(ChatGroupActivity.class);
                        overridePendingTransition(0, 0);
                    }
                });*/
                break;

            case "upGroupName":
               /* realmHelper.updateGroupName(groupId, contant);//更新群名
                //        发送广播更新
                EventBus.getDefault().post(new MsgHomeEvent(contant,groupId,AppConfig.MSG_ACTION_REFRESH));
                groupDetailsTvName.setText(contant);*/
                break;

            case "upGroupHeadImg":
                isNeedChangeHeadImg = false;
                DataSetGroupHeadResult dataSetGroupHeadResult = new GsonParamConverter().json2Object(responseText, DataSetGroupHeadResult.class);
                final DataSetGroupHeadResult.RecordBean recordImg = dataSetGroupHeadResult.getRecord();
                if (recordImg != null) {
                    String headImg = recordImg.getHeadImg();
                    if (!StrUtils.isEmpty(headImg)) {
                        String contains = headImg.contains("_")?"yes" : "no";
                        MyLog.i("imageBase64","------------groupChatDetailsActivity--------------"+contains);
//                        ImageUtils.useBase64(GroupChatDetailsActivity.this, groupDataIvHead,  headImg.substring(0, headImg.indexOf("_")));
                        ImageUtils.useBase64(GroupChatDetailsActivity.this, groupDataIvHead, headImg);
                        GroupHeadImgInfo groupHeadImgInfo = new GroupHeadImgInfo();
                        groupHeadImgInfo.setGroupHeadImgBase64(headImg);
                        EventBus.getDefault().postSticky(groupHeadImgInfo);
                    }


                   /* BaseApplication.getaCache().put(groupId + SplitWeb.getSplitWeb().USER_ID, responseText);*/
                }
                break;
            case "setUserGroupAssistant":
                boolean checked = chatsetSwiQunZhu.isChecked();
                String text = checked ? "加入群助手" : "取消加入群助手";
                ToastUtil.show(text);
                break;
            case "setUserGroupDisturb":
                boolean checked2 = chatsetSwiDarao.isChecked();
                String text1 = checked2 ? "设置免打扰成功" : "取消免打扰";
                ToastUtil.show(text1);
                break;

        }
    }
    String verificationMD5;
    private void dealSearchDetailInfo(String responseText) {
        DataAddQunDetails dataAddQunDetails = new GsonParamConverter().json2Object(responseText, DataAddQunDetails.class);
        DataAddQunDetails.RecordBean record = dataAddQunDetails.getRecord();
        if (record != null) {
            DataAddQunDetails.RecordBean.GroupDetailInfoBean group_detail_info = record.getGroupDetailInfo();
            verificationMD5 = record.getVerificationMD5();
            if (group_detail_info != null) {
                DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupInfoBean groupInfo = group_detail_info.getGroupInfo();
                DataAddQunDetails.RecordBean.GroupDetailInfoBean.UserInfoBean userInfo = group_detail_info.getUserInfo();
                if (userInfo != null) {
                    initUserInfo(userInfo);
                    String identityType = userInfo.getIdentityType();
                    isGrouper = identityType.equals("3") ? false : true;
                    if (isGrouper) {
                        groupDetailsIvEdit.setVisibility(View.VISIBLE);
                        groupDetailsLinName.setClickable(true);
                    } else {
                        groupDetailsIvEdit.setVisibility(View.GONE);
                        groupDetailsLinName.setClickable(false);
                    }
                    List<DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean> group_user_info = group_detail_info.getGroupUserInfo();
                    if (group_user_info.size() > 0) {
//                                initListHead(group_user_info);
                        initAdapter(group_user_info, isGrouper);
                    }
                }
                if (groupInfo != null) {
                    dataRecord = groupInfo;
                    initUI(groupInfo);
                }
                DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupNoticeBean group_notice = group_detail_info.getGroupNotice();
                if (group_notice != null) {
                    initNotice(group_notice);
                }
            }
//            BaseApplication.getaCache().put(groupId + SplitWeb.getSplitWeb().USER_ID, responseText);
        }
    }

//    CustomPopWindow popWindow;
    private PhotoPopWindow photoPopWindow = null;
    //为弹出窗口实现监听类
    public View.OnClickListener MyClick = new View.OnClickListener() {
        public void onClick(View v) {
            photoPopWindow.dismiss();
            int i = v.getId();
            if (i == R.id.btn_open_cramre) {
                destoryImage();
                getPicturesFile();

            } else if (i == R.id.btn_open_xaingce) {//	相册
//                    Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                    startActivityForResult(i, RESULT_LOAD_IMAGE_GROUP);

                //权限判断
                if (ContextCompat.checkSelfPermission(GroupChatDetailsActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请READ_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(GroupChatDetailsActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            READ_EXTERNAL_STORAGE_REQUEST_CODE);
                } else {
                    //跳转到相册
                    goToAlbum();
                }
                photoPopWindow.dismiss();

            } else {
            }
        }
    };

    /**
     * 跳转到相册
     */
    private void goToAlbum() {
        //跳转到调用系统图库
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "请选择图片"), REQUEST_PICK);
    }
    /**
     * 打开截图界面
     */
    public void goToClipActivity(Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(this, ClipImgActivity.class);
        intent.setData(uri);
        startActivityForResult(intent, REQUEST_CROP_PHOTO);
    }
    /**
     * 外部存储权限申请返回
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
//                goToCamera();
                destoryImage();
                getPicturesFile();
            }
        } else if (requestCode == READ_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                goToAlbum();
            }
        }
    }


    //设置普通群成员与群主之间的界面差异
    private void initUserInfo(DataAddQunDetails.RecordBean.GroupDetailInfoBean.UserInfoBean userInfo) {
        groupDataTvMineName.setText(userInfo.getCarteName());
//        群员身份   1：群主  2：管理员    3：普通群员
        switch (userInfo.getIdentityType()) {
            case "1":
                groupDetailsLinHaveSet.setVisibility(View.VISIBLE);
                break;
            case "2":
                groupDetailsLinHaveSet.setVisibility(View.VISIBLE);
                break;
            case "3":
                groupDetailsLinHaveSet.setVisibility(View.GONE);
                break;
        }

//        设置群各种状态
        String assistantType = userInfo.getAssistantType();
        String disturbType = userInfo.getDisturbType();
        if (assistantType != null) {
            chatsetSwiQunZhu.setChecked(assistantType.equals("2"));
        }
        if (disturbType != null) {
            chatsetSwiDarao.setChecked(disturbType.equals("2"));
        }

//        设置所在群分组
        groupDataTvGroupingName.setText(userInfo.getGroupManageName());
    }

    private void initNotice(DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupNoticeBean group_notice) {
        if (!StrUtils.isEmpty(group_notice.getNoticeContent()))
            groupDataTvGonggao.setText(group_notice.getNoticeContent());
    }

    List<DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean> mList = new ArrayList<>();
    List<DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean> mList2 = new ArrayList<>();
    private void initAdapter(List<DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean> group_user_info, final boolean isGrouper) {
//添加群员信息到好友表中
//        initUserRealm(group_user_info);
        mList2.clear();
        if (group_user_info.size() > 9) {
            for (int i = 0; i <= 9; i++) {
                DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean groupUserInfoBean = group_user_info.get(i);
                mList2.add(i, group_user_info.get(i));
                //添加群员信息到好友表中
                addRealm(groupUserInfoBean);
            }
        } else {
            mList2.addAll(group_user_info);
            mList2.add(group_user_info.get(0));
            if (isGrouper)
                mList2.add(group_user_info.get(0));
        }
//        groupDataTvChatnum.setText("群成员(" +  + ")");
        GroupMemberQunzhuAdapter groupMemberAdapter = new GroupMemberQunzhuAdapter(this, mList2, true, isGrouper);
        groupDataRecyc.setAdapter(groupMemberAdapter);
        groupMemberAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean item = (DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean) adapter.getItem(position);
                IntentDataInvitation intentDataInvitation = new IntentDataInvitation();
                intentDataInvitation.setGroupId(groupId);
                if (position == (mList2.size() - 1)) {
                    // 点击跳转群成员列表
                    if (groupId != null) {
//如果是群主，则最后一个是删除群成员，如果是群成员，最后一个是邀请
                        if (!isGrouper) {
                            intentDataInvitation.setGroupTital("邀请新成员");
                            intentDataInvitation.setGroupType(AppConfig.GROUP_QUZHU);
//                            邀请新成员
                            IntentUtils.JumpToHaveObj(InvitationGroupChatActivity.class, AppConfig.GROUP_ID, intentDataInvitation);
                        } else {
                            intentDataInvitation.setGroupTital("删除成员");
                            intentDataInvitation.setGroupType(AppConfig.GROUP_MEMBER);
//                            删除群成员
                            IntentUtils.JumpToHaveObj(InvitationGroupChatActivity.class, AppConfig.GROUP_ID, intentDataInvitation);
                        }
                    }
//                    如果是群主，倒数第二个是添加
                } else if ((position == (mList2.size() - 2)) && (isGrouper)) {
                    intentDataInvitation.setGroupTital("邀请新成员");
                    intentDataInvitation.setGroupType(AppConfig.GROUP_QUZHU);
//                            邀请新成员
                    IntentUtils.JumpToHaveObj(InvitationGroupChatActivity.class, AppConfig.GROUP_ID, intentDataInvitation);

                } else {
                    //好友关系 1是未添加 2是已添加
                    //群成员关系  1是未添加 2是已添加 3是自己
                   /* if (item != null) {
                        if (item.getIsRelation().equals("3")) {
                            // 自己，跳转个人资料界面
                            IntentUtils.JumpTo(ChangeInfoActivity.class);
                        } else {
                            // 自己，跳转个人资料界面
                            IntentUtils.JumpToHaveOne(FriendDataMixActivity.class, "id", item.getUserId());
                        }
                    }*/
                }
            }
        });
     /*   groupMemberAdapter.notifyDataSetChanged();*/
    }

    private void addRealm(DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean record) {

       /* try {
            if (!StrUtils.isEmpty(record.getUserId())) {
                CusDataFriendUser cusDataFriendUser = new CusDataFriendUser();
                cusDataFriendUser.setFriendId(record.getUserId());
                cusDataFriendUser.setHeadImgBase64(record.getHeadImg());
                cusDataFriendUser.setName(record.getNickName());
                cusDataFriendUser.setRemarkName(record.getNickName());
                //TODO 用户信息存库（用户表）
                getFriendUserHelper().updateAllOrAdd(record.getUserId(), cusDataFriendUser);//添加或者更新（存在则更新，不存在则增加）
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
   /* RealmFriendUserHelper friendUserHelper;
    private  RealmFriendUserHelper  getFriendUserHelper(){
        if (friendUserHelper==null)
            friendUserHelper = new RealmFriendUserHelper(this);
        return  friendUserHelper;
    }*/
   /* private void initUserRealm(List<DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean> group_user_info) {
        if (friendUserHelper==null)
            friendUserHelper = new RealmFriendUserHelper(this);
        if (group_user_info.size()>0)
        {
            for (DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean groupUser : group_user_info)
            {
//                String headImg = groupUser.getHeadImg();
                String friendId = groupUser.getUserId();
                CusDataFriendUser cusDataFriendUser = new CusDataFriendUser();
                cusDataFriendUser.setFriendId(groupUser.getUserId());
                cusDataFriendUser.setHeadImgBase64(groupUser.getHeadImg());
                cusDataFriendUser.setName(groupUser.getNickName());
                cusDataFriendUser.setRemarkName(groupUser.getNickName());
                cusDataFriendUser.setTime(groupUser.getModified());
                //TODO 用户信息存库（用户表）
                friendUserHelper.updateAllOrAdd(friendId,cusDataFriendUser);//添加或者更新（存在则更新，不存在则增加）
            }
        }

    }*/

    String groupHeadImg;
    private void initUI(DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupInfoBean groupInfoBean) {
        groupHeadImg = groupInfoBean.getGroupHeadImg();
        groupChatName = groupInfoBean.getGroupName();
        if (isNeedChangeHeadImg){
            ImageUtils.useBase64(GroupChatDetailsActivity.this, groupDataIvHead, groupInfoBean.getGroupHeadImg());
        }else {
            isNeedChangeHeadImg = true;
        }
        groupDetailsTvGroupId.setText("(" + groupInfoBean.getGroupSno() + ")");
        groupDetailsTvName.setText(groupInfoBean.getGroupName());
        groupDataTvChatnum.setText("群成员(" + groupInfoBean.getNowNum() + ")");
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_group_details;
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int i = view.getId();
        if (i == R.id.group_data_lin_intogrouplist) {
            if (groupId != null) {
//                    TODO
//                    IntentUtils.JumpToHaveOne(GroupTeamActivity.class, GroupTeamActivity.GROUP_ID, groupId);
            }

        } else if (i == R.id.include_top_iv_more) {
          /*  if (popWindow == null)
                popWindow = new CustomPopWindow.PopupWindowBuilder(GroupChatDetailsActivity.this)
                        .setView(mView)
                        .setFocusable(true)
                        .setOutsideTouchable(true)
                        .size(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                        .setAnimationStyle(0) // 添加自定义显示和消失动画
                        .create()
                        .showAsDropDown(mLinMain, 0, 0);
            else
                popWindow.showAsDropDown(mLinMain, 0, 0);*/

        } else if (i == R.id.group_data_lin_myGroupCard) {
            if (NoDoubleClickUtils.isDoubleClick()) {
                Intent intent = new Intent(GroupChatDetailsActivity.this, EditGroupCardActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("groupofId", groupId);
                if (groupDataTvMineName.getText() != null)
                    bundle.putString("cardName", groupDataTvMineName.getText().toString());
                intent.putExtras(bundle);
                startActivityForResult(intent, AppConfig.EDIT_GROUP_CARD_REQUEST);
            }

        } else if (i == R.id.group_details_lin_group_notice) {
            if (NoDoubleClickUtils.isDoubleClick()) {
                Intent intent_notice = new Intent(GroupChatDetailsActivity.this, GroupNoticeActivity.class);
                Bundle bundle_notice = new Bundle();
                bundle_notice.putString("groupId", groupId);
                bundle_notice.putBoolean("isGrouper", isGrouper);
                if (groupDataTvGonggao != null)
                    bundle_notice.putString("content", groupDataTvGonggao.getText().toString());
                intent_notice.putExtras(bundle_notice);
                startActivityForResult(intent_notice, AppConfig.EDIT_GROUP_NOTICE_REQUEST);
            }

        }else  if (i == R.id.group_details_lin_set) {
            IntentUtils.JumpToHaveOne(GroupChatSetActivity.class, "groupId", groupId);

        } else if (i == R.id.group_details_lin_add_type) {
            IntentUtils.JumpTo(AddGroupWayActivity.class);

        } else if (i == R.id.group_details_lin_group_notice) {
            // 聊天记录
        } else if (i == R.id.group_details_lin_chat_old) {
            DialogUtils.showDialog(getResources().getString(R.string.stay_tuned));

            // 删除聊天记录
        } else if (i == R.id.group_details_lin_del_chat) {
            DialogUtils.showDialog(getResources().getString(R.string.stay_tuned));

        } else if (i == R.id.include_top_iv_zhuanfa) {
        } else if (i == R.id.group_details_iv_qrcode) {/*   if (dataRecord != null) {
                    PersonData personData = new PersonData();
                    personData.setHeadImg(dataRecord.getGroupHeadImg());
                    personData.setName(dataRecord.getGroupName());
                    personData.setScanTital(getResources().getString(R.string.qrcode_add_group));
                    personData.setTital(getResources().getString(R.string.qrcode_title_group));
                    personData.setQrCode(dataRecord.getGroupQrcode());
                    IntentUtils.JumpToHaveObj(QunCodeActivity.class, AppConfig.GROUP_ADDKEY, personData);
                }*/

        } else if (i == R.id.group_details_lin_name) {
//   TODO         doChangeName();

        } else if (i == R.id.group_data_iv_head) {
            if (isGrouper) {
                if (photoPopWindow == null)
                    photoPopWindow = new PhotoPopWindow(GroupChatDetailsActivity.this, MyClick);
                photoPopWindow.showAtLocation(mLinMain, Gravity.NO_GRAVITY, 0, 0);
            }

        } else if (i == R.id.group_chat_data_swibtn_nodarao) {//                type = chatsetSwiDarao.isChecked() ? "1" : "2";
//                Log.e("disturbGroup", "--------------------------------------type = " + type + "+++++++++++++++++++++++i = " + (i++));
//                sendWeb(SplitWeb.getSplitWeb().setUserGroupDisturb(groupId, type));

        } else if (i == R.id.group_chat_data_lin_nodarao) {//                type = chatsetSwiDarao.isChecked() ? "1" : "2";
//                Log.e("disturbGroup", "--------------------------------------type = " + type + "+++++++++++++++++++++++j = " + (j++));
//                sendWeb(SplitWeb.getSplitWeb().setUserGroupDisturb(groupId, type));

        } else if (i == R.id.group_data_lin_grouping) {/* if (NoDoubleClickUtils.isDoubleClick()) {
                    Intent intent = new Intent(this, ChooseGroupActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("groupId", groupId);
                    Log.e("group_id","-------------------------------------------"+groupId);
                    bundle.putString("type", "2");
                    intent.putExtras(bundle);
                    startActivityForResult(intent, AppConfig.GROUP_DATA_GROUPING_REQUEST);
                }*/

        } else if (i == R.id.group_details_tv_to_chat) {/*if (AppConfig.CHATGROUP.equals(groupType))
                {
                    AppManager.getAppManager().finishActivity(GroupChatDetailsActivity.this);
                }else{
                    final CusHomeRealmData cusHomeRealmData = new CusHomeRealmData();
                    cusHomeRealmData.setHeadImg(groupHeadImg);
                    cusHomeRealmData.setFriendId(groupId);
                    cusHomeRealmData.setNickName(groupChatName);
                    cusHomeRealmData.setNum(0);
                    CusHomeRealmData cusHomeRealmData1 = realmHelper.queryAllRealmChat(groupId);
                    if (cusHomeRealmData1!=null)
                    {
                        realmHelper.updateGroup(groupId,cusHomeRealmData);
                    }else
                    {
                        realmHelper.addRealmMsgQun(cusHomeRealmData);
                    }
//                    // 群聊
                    IntentUtils.JumpToHaveOne(ChatGroupActivity.class,AppConfig.KEY_GROUP_Id,groupId);
                }*/

        }


    }


    private Bitmap photo;
    private void destoryImage() {
        if (photo != null) {
            photo.recycle();
            photo = null;
        }
    }

    String result;
    private File mPhotoFile;
    private int RESULT_LOAD_IMAGE_GROUP = 301;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        修改群名片
        if (resultCode == AppConfig.EDIT_GROUP_CARD_RESULT && null != data) {
            if (requestCode == AppConfig.EDIT_GROUP_CARD_REQUEST){
                result = data.getExtras().getString("myGroupCard");
                groupDataTvMineName.setText(result);
            }
        }
//        修改群公告
        else if (resultCode == AppConfig.EDIT_GROUP_NOTICE_RESULT && null != data) {
            if (requestCode == AppConfig.EDIT_GROUP_NOTICE_REQUEST) {
                String msg = data.getExtras().getString(GroupNoticeActivity.GROUP_NOTICES);
                groupDataTvGonggao.setText(msg);
            }
        }
//        群选择分组
        else if (resultCode == AppConfig.GROUP_DATA_GROUPING_RESULT && null != data) {
            if (requestCode == AppConfig.GROUP_DATA_GROUPING_REQUEST) {
             /*   String name = data.getStringExtra(ChooseGroupActivity.CHOOSE_NAME_GROUP);
                groupDataTvGroupingName.setText(name);*/
            }
        }
        //		相机
        else if (requestCode == CAMERA_RESULT_GROUP) {
//        else if (requestCode == CAMERA_RESULT_GROUP && resultCode == RESULT_OK) {
            if (mPhotoFile != null && mPhotoFile.exists()) {
                BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                bitmapOptions.inJustDecodeBounds = true;
                Bitmap bitmap = BitmapFactory.decodeFile(mPhotoFile.getPath(), bitmapOptions);
                bitmapOptions.inJustDecodeBounds = false;
                int be = (int) (bitmapOptions.outHeight / (float) 200);
                if (be <= 0)
                    be = 1;
                bitmapOptions.inSampleSize = be;
                bitmap = BitmapFactory.decodeFile(mPhotoFile.getPath(), bitmapOptions);
                if(resultCode == Activity.RESULT_CANCELED) {
                    return;
                }
                if (isBackKey){
                    isBackKey = false;
                    return;
                }
                if (bitmap==null)
                {
                    ToastUtil.show("不支持的图片，请重新选择");
                    return;
                }
                goToClipActivity(Uri.fromFile(mPhotoFile));
            }
        }
        //		相册
        else if (requestCode == REQUEST_PICK && null != data && null != data) {
//        else if (requestCode == RESULT_LOAD_IMAGE_GROUP && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            if (isBackKey){
                isBackKey = false;
                return;
            }
            if (bitmap==null)
            {
                ToastUtil.show("不支持的图片，请重新选择");
                return;
            }
            Drawable drawable = new BitmapDrawable(getResources(), bitmap);
            c.close();
            goToClipActivity(selectedImage);
//            sendWeb(SplitWeb.getSplitWeb().upGroupHeadImg(groupId, ImageUtils.GetStringByImageView(bm)));
        }
        else if (requestCode == REQUEST_CROP_PHOTO && null != data){
            final Uri uri = data.getData();
            if (uri == null) {
                return;
            }
            String cropImagePath = HeadFileUtils.getRealFilePathFromUri(getApplicationContext(), uri);
            // 高清头像
            Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);
            String originBase64 = ImageUtils.Bitmap2StrByBase64(bitMap);
            //TODO 压缩头像
            Bitmap bm = ImageUtils.imageZoom(bitMap);
            String compressBase64 = ImageUtils.Bitmap2StrByBase64(bm);
            String totalBase64 = originBase64 + "_" + compressBase64;

         /*   sendWeb(SplitWeb.getSplitWeb().upGroupHeadImg(groupId, totalBase64));*/
        }
    }
    boolean isBackKey = false;
    /**
     * 监听Back键按下事件,方法1:
     * 注意:
     * super.onBackPressed()会自动调用finish()方法,关闭
     * 当前Activity.
     * 若要屏蔽Back键盘,注释该行代码即可
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        isBackKey = true;
        System.out.println("按下了back键   onBackPressed()");
    }
    String mTmpPath;
    /**
     * 7.0 拍照权限
     */
    public void getPicturesFile() {
        mPhotoFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/chat_image/" + System.currentTimeMillis() + ".jpg");
        try {
            mPhotoFile.getParentFile().mkdirs();
            mPhotoFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mTmpPath = mPhotoFile.getAbsolutePath();
        final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
        //判断一下当前的系统版本，然后在分配权限
        if (Build.VERSION.SDK_INT >= 24) {
            //Android 7.0权限申请
            ContentValues contentValues = new ContentValues(1);
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(GroupChatDetailsActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, CAMERA_RESULT_GROUP);
            }
            contentValues.put(MediaStore.Images.Media.DATA, mTmpPath);
            Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(intent, CAMERA_RESULT_GROUP);
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(mTmpPath)));
            startActivityForResult(intent, CAMERA_RESULT_GROUP);
        }
    }

    //TODO 修改群名
 /*   private void doChangeName() {
        ChangeInfoWindow changeInfoWindow = new ChangeInfoWindow(GroupChatDetailsActivity.this, "修改群名", groupDetailsTvName.getText().toString().trim());
        changeInfoWindow.showAtLocation(mLinMain, Gravity.CENTER, 0, 0);
        changeInfoWindow.setOnAddpopClickListener(this);
    }*/

    String contant = null;
    //0 修改群名   1 修改群头像
    String isChangeName = "0";
//TODO
   /* @Override
    public void onSure(String contant) {
        this.contant = contant;
        switch (isChangeName) {
            case "0":  //群名
                sendWeb(SplitWeb.getSplitWeb().upGroupName(groupId, contant));
                break;
            case "1":  //群头像
//                sendWeb(SplitWeb.getSplitWeb().upGroupHeadImg(groupId,contant));
                break;

        }
    }*/

  /*  @Override
    public void onCancle() {
    }*/
    @Override
    protected void onDestroy() {
        super.onDestroy();
        isFirst = false;
    }
}
