package com.rance.chatui.about_chat.chat_group.sub_group;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.mding.chatfeng.base_common.components.base.BaseActivity;
import com.mding.chatfeng.base_common.components.model.DataTransferGroupMember;
import com.mding.chatfeng.base_common.utils.GsonParamConverter;
import com.mding.chatfeng.base_common.utils.about_custom.LetterBar;
import com.mding.chatfeng.base_common.utils.about_dialog.DialogUtils;
import com.mding.chatfeng.base_common.utils.about_main_utils.HelpUtils;
import com.mding.chatfeng.base_common.utils.aboutsystem.AppManager;
import com.mding.chatfeng.chat.R;
import com.rance.chatui.about_chat.chat_group.adapter.TransferGroupMemberAdapter;

import java.util.ArrayList;
import java.util.List;


//位置：转让群聊
public class ZhuanRangGroupActivity extends BaseActivity {
    TextView includeTopTvTital;
    LinearLayout includeTopLin;
    TextView mTvAbc;

    ExpandableListView mExpanList;
    LetterBar mLetterBar;

    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_zhuan_rang_group;
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();
        initUI();
        includeTopTvTital.setText("群聊成员");
        includeTopLin.setBackgroundColor(getResources().getColor(R.color.app_theme));
        initGroup();
        initIntent();
    }

    private void initUI() {

        includeTopTvTital=   getView(R.id.include_top_tv_title);
        includeTopLin=  getView(R.id.include_top_lin_background);
        mTvAbc=  getView(R.id.tv_abc);
        mExpanList= getView(R.id.group_team_expan);
        mLetterBar= getView(R.id.group_team_letter);


    }

    public  static  String GROUP_ID="groupId";
    public  static  String GROUP_NAME="groupName";
    String groupId;
    String groupName;
    private void initIntent() {
        Intent intent = getIntent();
        if (intent!=null) {
            String groupId = intent.getStringExtra(GROUP_ID);
            this.groupId = groupId;
            groupName = intent.getStringExtra(GROUP_NAME);
            if (groupId != null){
//        TODO        sendWeb(SplitWeb.getSplitWeb().getTransterGroupMemberInfo(groupId));
            }
        }
    }

    private void initGroup() {
        initABC2();
    }
    private ArrayList<DataTransferGroupMember.RecordBean.MemberListBean> allCusList = new ArrayList<>();

    List<String> ABCList = new ArrayList<>();

    public void initABC2() {
        ABCList.clear();
//        ABCList.add("");
//        ABCList.add("⇧");
        for (char i = 'A'; i <= 'Z'; i++) {
            ABCList.add(i + "");
        }
        runnable = new Runnable() {
            public void run() {
                mTvAbc.setVisibility(View.INVISIBLE);
            }
        };
        mLetterBar.setonTouchLetterListener(new LetterBar.onTouchLetterListener() {
            @Override
            public void onTouchDown(String letter) {
                mTvAbc.removeCallbacks(runnable);
                mTvAbc.setVisibility(View.VISIBLE);
                mTvAbc.setText(letter);
                if (letter.equals("⇧"))
                {
                    mExpanList.setSelection(0);
                    return;
                }
                for (int i = 0; i < allCusList.size(); i++) {
//                for (int i = 1; i < allCusList.size(); i++) {
                    //获取所有城市的首字母
                    String firstLetter = getFirstABC
                            (allCusList.get(i).getGroupName());
//                        String firstLetter = getFirstABC
//                                (allCusList.get(i).getGroupName());
                    if (firstLetter.equals("~"))
                    {
                        firstLetter="#";
                    }
                    if (letter.equals(firstLetter)) {
                        mExpanList.setSelectedGroup(i);
                        break;
                    }
                }
            }

            @Override
            public void onTouchUp() {
                mTvAbc.postDelayed(runnable, 1000);
            }
        });
    }

    public String getFirstABC(String pinyin) {
        String upperCase = pinyin.substring(0, 1).toUpperCase();
        return upperCase;
    }

    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String method = HelpUtils.backMethod(responseText);
        switch (method) {
            case "getTransterGroupMemberInfo":
                DataTransferGroupMember dataTransferGroupMember =   new GsonParamConverter().json2Object(responseText, DataTransferGroupMember.class);
                DataTransferGroupMember.RecordBean record = dataTransferGroupMember.getRecord();
                List<DataTransferGroupMember.RecordBean.MemberListBean> memberList = record.getMemberList();
                allCusList.clear();
                allCusList.addAll(memberList);
                initTransferGroupMemberAdapter();
                break;
            case "transferGroupOf":
                DialogUtils.showDialogOne("转让成功！", new DialogUtils.OnClickSureListener() {
                    @Override
                    public void onClickSure() {
                        AppManager.getAppManager().finishActivity();
                    }
                });
                break;
        }
    }

    private void initTransferGroupMemberAdapter() {
        TransferGroupMemberAdapter transferGroupMemberAdapter = new TransferGroupMemberAdapter(ZhuanRangGroupActivity.this, allCusList);
        mExpanList.setAdapter(transferGroupMemberAdapter);
        transferGroupMemberAdapter.notifyDataSetChanged();
        for (int i=0; i<allCusList.size(); i++)
        {
            mExpanList.expandGroup(i);
        }
        mExpanList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                //设置点击不关闭（不收回）
                v.setClickable(true);
                return true;
            }
        });

        mExpanList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long id) {
                final String transferUserId = allCusList.get(groupPosition).getGroupList().get(childPosition).getMemberId();
                final String transferUserName = allCusList.get(groupPosition).getGroupList().get(childPosition).getNickName();
                DialogUtils.showDialog("是否确认将该群转让给"+transferUserName+"？", new DialogUtils.OnClickSureListener() {
//                DialogUtils.showDialog("是否要将"+ groupName + "转让给"+transferUserName+"？", new DialogUtils.OnClickSureListener() {
                    @Override
                    public void onClickSure() {
                        if (groupId != null && transferUserId != null)
                        {
//           TODO                 sendWeb(SplitWeb.getSplitWeb().transferGroupOf(groupId,transferUserId));
                        }

                    }
                });
                return true;
            }
        });
    }
    public int positions;

}
