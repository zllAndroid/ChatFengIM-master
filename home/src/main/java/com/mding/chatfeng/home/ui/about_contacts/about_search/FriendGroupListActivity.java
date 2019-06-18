package com.mding.chatfeng.home.ui.about_contacts.about_search;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mding.chatfeng.base_common.AppConfig;
import com.mding.chatfeng.base_common.components.base.BaseActivity;
import com.mding.chatfeng.base_common.components.model.DataFriendGroup;
import com.mding.chatfeng.base_common.utils.GsonParamConverter;
import com.mding.chatfeng.base_common.utils.about_main_utils.HelpUtils;
import com.mding.chatfeng.base_common.utils.aboutsystem.AppManager;
import com.mding.chatfeng.base_common.utils.aboututils.StrUtils;
import com.mding.chatfeng.home.R;
import com.mding.chatfeng.home.ui.about_contacts.ChangeInfoWindow;
import com.mding.chatfeng.home.ui.about_contacts.about_contacts_adapter.AddFriendFenzuAdapter;

import java.util.List;

//位置：
public class FriendGroupListActivity extends BaseActivity {
    TextView includeTopTvTitle;
    TextView includeTopTvRight;
    LinearLayout includeTopLin;
    LinearLayout mLinMain;

    RecyclerView mRecyclerView;
    TextView chooseGroupTvNothing;
    TextView chooseGroupTvAdd;
    LinearLayout chooseGroupLinFenzu;


    String mShare = "1";
    public static String CHOOSE_GROUP_KEY = "choosegroup";
    public static String CHOOSE_NAME = "groupname";
    public static String CHOOSE_ID = "groupid";
    String groupType = "";
    String addType = "1";

    @Override
    protected void initBaseView() {
        super.initBaseView();
initviewUI();
        includeTopTvTitle.setText("选择分组");
        includeTopLin.setBackgroundColor(getResources().getColor(R.color.app_theme));

//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(FriendGroupListActivity.this));
        Intent intent = getIntent();
        if (intent != null) {
            groupType = intent.getStringExtra(AppConfig.KEY_FRIEND_GROUP);
            if (!StrUtils.isEmpty(groupType))
            {
//TODO 获取数据
//                sendWeb(SplitWeb.getSplitWeb().groupManageInfo(groupType));
            }
        }
    }

    private void initviewUI() {
        includeTopTvTitle=  getView(R.id.include_top_tv_title);
        includeTopTvRight=  getView(R.id.inclu_tv_right);
        includeTopLin=  getView(R.id.include_top_lin_back);
        mLinMain=  getView(R.id.choose_group_lin_main);
        mRecyclerView=  getView(R.id.choose_group_recyc_fenzu);
        chooseGroupTvNothing=  getView(R.id.choose_group_tv_nothing);
        chooseGroupTvAdd=  getView(R.id.choose_group_tv_add);
        chooseGroupLinFenzu=  getView(R.id.choose_group_lin_fenzu);
        addOnClickListeners(R.id.choose_group_tv_add);
    }

    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String method = HelpUtils.backMethod(responseText);
        switch (method) {
            case "groupManageInfo":
                DataFriendGroup dataGroupManage = new GsonParamConverter().json2Object(responseText, DataFriendGroup.class);
                DataFriendGroup.RecordBean record = dataGroupManage.getRecord();
                if (record == null) {
                    return;
                }
                List<DataFriendGroup.RecordBean.GroupInfoBean> groupInfo = record.getGroupInfo();
                if (groupInfo.size() > 0) {
                    if (StrUtils.isEmpty(groupInfo.get(0).getId())) {
                        groupInfo.remove(0);
                    }
                    if (groupInfo.size() > 0) {
                        chooseGroupLinFenzu.setVisibility(View.VISIBLE);
                        chooseGroupTvNothing.setVisibility(View.GONE);
                        chooseGroupTvAdd.setVisibility(View.GONE);
                        initAdapter(groupInfo);
                    } else {
                        chooseGroupLinFenzu.setVisibility(View.GONE);
                        chooseGroupTvNothing.setVisibility(View.VISIBLE);
                        chooseGroupTvAdd.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case "addFriendGroup":
//
//                TODO   sendWeb(SplitWeb.getSplitWeb().groupManageInfo(groupType));
                break;
        }
    }

    AddFriendFenzuAdapter blackAdapter = null;
    DataFriendGroup.RecordBean.GroupInfoBean item;
    public int positions;

    private void initAdapter(List<DataFriendGroup.RecordBean.GroupInfoBean> groupInfo) {
        blackAdapter = new AddFriendFenzuAdapter(this, groupInfo);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(blackAdapter);
        blackAdapter.notifyDataSetChanged();
        blackAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                ToastUtil.show("点击同意");
                item = (DataFriendGroup.RecordBean.GroupInfoBean) adapter.getItem(position);
                dealItemClick(item);
            }
        });
    }

    private void dealItemClick(DataFriendGroup.RecordBean.GroupInfoBean item) {
        Intent intent = new Intent();
        // 获取用户计算后的结果
        intent.putExtra(CHOOSE_NAME, item.getGroupName());
        intent.putExtra(CHOOSE_ID, item.getId());
        setResult(AppConfig.FRIEND_ADD_GROUP_RESULT, intent);
        AppManager.getAppManager().finishActivity(FriendGroupListActivity.this);
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_choose_group;
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        if (view.getId()==R.id.choose_group_tv_add)
        {
            ChangeInfoWindow changeInfoWindowsign = new ChangeInfoWindow(FriendGroupListActivity.this, "增加分组", "");
            changeInfoWindowsign.showAtLocation(mLinMain, Gravity.CENTER, 0, 0);
            changeInfoWindowsign.setOnAddpopClickListener(new ChangeInfoWindow.OnAddContantClickListener() {
                @Override
                public void onSure(String contant) {
//                    TODO 增加分组
//                    sendWeb(SplitWeb.getSplitWeb().addFriendGroup(groupType, addType, contant, ""));//增加分组  type = 1
                    if (blackAdapter != null)
                        blackAdapter.notifyDataSetChanged();
                }
                @Override
                public void onCancle() {

                }
            });
        }
    }
}
