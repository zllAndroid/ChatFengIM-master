package com.mding.chatfeng.home.ui.about_mine.about_setting;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mding.chatfeng.base_common.components.base.BaseActivity;
import com.mding.chatfeng.base_common.utils.about_cus_dialog.DialogExitUtils;
import com.mding.chatfeng.base_common.utils.about_custom.about_linearlayout.CusLinearLayout;
import com.mding.chatfeng.base_common.utils.about_dialog.DialogUtils;
import com.mding.chatfeng.base_common.utils.about_main_utils.IntentUtils;
import com.mding.chatfeng.base_common.utils.aboutsystem.DataCleanManager;
import com.mding.chatfeng.base_common.utils.aboututils.NoDoubleClickUtils;
import com.mding.chatfeng.base_common.utils.aboututils.ToastUtil;
import com.mding.chatfeng.home.R;


public class MineSetActivity extends BaseActivity {

    TextView includeTopTvTitle;
    LinearLayout includeTopLin;
    CusLinearLayout setCusLinShare;
    CusLinearLayout setLinCount;
    CusLinearLayout setLinYinsi;
    CusLinearLayout setLinMessage;
    CusLinearLayout setLinPingbi;
    CusLinearLayout setLinDiscover;
    CusLinearLayout setLinClearCache;
    CusLinearLayout setLinVersion;
    CusLinearLayout setLinAboutMe;

    @Override
    public int getLayoutView() {
        return R.layout.activity_mine_set;
    }

    String userPhone;
    String totalCacheSize;

    @Override
    protected void initBaseView() {
        super.initBaseView();
        initViewUI();
        includeTopTvTitle.setText(getResources().getString(R.string.setting_title));
//        includeTopTvTitle.setText("设置");
        includeTopLin.setBackgroundColor(getResources().getColor(R.color.app_theme));
        Intent intent = getIntent();
        if (intent != null) {
            userPhone = intent.getStringExtra("phone");
        }
        initLinearLaout();
    }

    private void initViewUI() {
        includeTopTvTitle = getView(R.id.include_top_tv_title);
        includeTopLin = getView(R.id.include_top_lin_back);
        setCusLinShare = getView(R.id.set_cus_lin_share);
        setLinCount = getView(R.id.set_lin_count);
        setLinYinsi = getView(R.id.set_lin_yinsi);
        setLinMessage = getView(R.id.set_lin_message);
        setLinPingbi = getView(R.id.set_lin_pingbi);
        setLinDiscover = getView(R.id.set_lin_discover);
        setLinClearCache = getView(R.id.set_lin_clear_cache);
        setLinVersion = getView(R.id.set_lin_version);
        setLinAboutMe = getView(R.id.set_lin_about_me);

        addOnClickListeners(R.id.set_lin_pingbi, R.id.set_cus_lin_share, R.id.set_lin_count, R.id.set_lin_yinsi, R.id.set_lin_message, R.id.set_lin_discover,
            R.id.set_lin_clear_cache, R.id.set_lin_version, R.id.set_lin_about_me, R.id.set_btn_esc);
    }

    private void initLinearLaout() {
//        名片分享与设置
        initShare();

//        帐号与安全
        initSafeCount();
//        隐私设置
        initYinSi();
//        消息提醒
        initMsgNotify();
//        屏蔽设置
        initPingBi();
//        朋友圈设置
        initDiscover();

//        清理缓存
        initCache();
//        检查更新
        initUpdateCheck();
//        关于我们
        initAboutUs();
    }

    //        名片分享与设置
    private void initShare() {
        setCusLinShare.setViewLineVisible(false);
        setCusLinShare.setImgLogo(getResources().getDrawable(R.drawable.mine_share));
        setCusLinShare.setTvTitle(getResources().getString(R.string.setting_card_share));
//        setCusLinShare.setTvTitle("名片分享设置");
    }

    //        帐号与安全
    private void initSafeCount() {
        setLinCount.setLinGreyBacVisible(true);
        setLinCount.setViewLineVisible(false);
        setLinCount.setImgLogo(getResources().getDrawable(R.drawable.set_count));
        setLinCount.setTvTitle(getResources().getString(R.string.setting_account_security));
//        setLinCount.setTvTitle("帐号与安全");
    }

    //        隐私设置
    private void initYinSi() {
        setLinYinsi.setImgLogo(getResources().getDrawable(R.drawable.set_yinsi));
        setLinYinsi.setTvTitle(getResources().getString(R.string.setting_privacy));
//        setLinYinsi.setTvTitle("隐私设置");
    }

    //        消息提醒
    private void initMsgNotify() {
        setLinMessage.setImgLogo(getResources().getDrawable(R.drawable.set_message));
        setLinMessage.setTvTitle(getResources().getString(R.string.setting_msg_notifications));
//        setLinMessage.setTvTitle("消息提醒");
    }

    //        屏蔽设置
    private void initPingBi() {
        setLinPingbi.setImgLogo(getResources().getDrawable(R.drawable.set_pingbi));
        setLinPingbi.setTvTitle(getResources().getString(R.string.setting_block_list));
//        setLinPingbi.setTvTitle("屏蔽设置");
    }

    //        朋友圈设置
    private void initDiscover() {
        setLinDiscover.setImgLogo(getResources().getDrawable(R.drawable.set_pingbi));
        setLinDiscover.setTvTitle(getResources().getString(R.string.setting_discover));
//        setLinDiscover.setTvTitle("朋友圈设置");
    }

    //        清理缓存
    private void initCache() {
        setLinClearCache.setLinGreyBacVisible(true);
        setLinClearCache.setViewLineVisible(false);
        setLinClearCache.setImgLogo(getResources().getDrawable(R.drawable.set_clean));
        setLinClearCache.setTvTitle(getResources().getString(R.string.setting_clear_cache));
//        setLinClearCache.setTvTitle("" + "清理缓存");
        try {
            totalCacheSize = DataCleanManager.getTotalCacheSize(MineSetActivity.this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setLinClearCache.setTvContent(totalCacheSize);
        setLinClearCache.setImgToRightVisible(false);
    }

    //        检查更新
    private void initUpdateCheck() {
        setLinVersion.setImgLogo(getResources().getDrawable(R.drawable.set_upda));
        setLinVersion.setTvTitle(getResources().getString(R.string.setting_check_version_update));
//        setLinVersion.setTvTitle("检查更新");
//        TODO 获取版本号
//        String versionName = HelpUtils.getLocalVersionName();
//        setLinVersion.setTvContent("v" + versionName);
        setLinClearCache.setImgToRightVisible(false);
    }
    //        关于我们
    private void initAboutUs() {
        setLinAboutMe.setImgLogo(getResources().getDrawable(R.drawable.set_aboutme));
        setLinAboutMe.setTvTitle(getResources().getString(R.string.setting_about_us));
//        setLinAboutMe.setTvTitle("关于我们");
    }

    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
//        if (SplitWeb.getSplitWeb().IS_SET_ACTIVITY.equals("1")) {
//            String method = HelpUtils.backMethod(responseText);
//            switch (method) {
//                case "kickUid":
//                    Log.e("kickUid", "----------------------kickUid-----------------");
//                    break;
//            }
//        }
    }

    //    清理缓存
    private void cleanCaChe() {
        try {
            // TODO 群聊删除聊天记录

            // TODO 私聊删除聊天记录

            ToastUtil.show(getResources().getString(R.string.setting_discover));
//            ToastUtil.show("清理缓存成功");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int i = view.getId();
//            打开  名片分享  界面
        if (i == R.id.set_cus_lin_share) {
            if (NoDoubleClickUtils.isDoubleClick())
                IntentUtils.JumpTo(ShareSetActivity.class);


//                打开  帐号与安全  界面
        } else if (i == R.id.set_lin_count) {
            if (NoDoubleClickUtils.isDoubleClick())
                IntentUtils.JumpTo(AccountAndSafeActivity.class);


//                打开 隐私设置 界面
        } else if (i == R.id.set_lin_yinsi) {
            if (NoDoubleClickUtils.isDoubleClick())
                IntentUtils.JumpTo(YinSiActivity.class);


//                打开消息提醒界面
        } else if (i == R.id.set_lin_message) {
            if (NoDoubleClickUtils.isDoubleClick())
                IntentUtils.JumpTo(NewsRemindActivity.class);


//                打开 屏蔽设置  界面
        } else if (i == R.id.set_lin_pingbi) {
            if (NoDoubleClickUtils.isDoubleClick())
                IntentUtils.JumpTo(LaBlackActivity.class);


//                打开 朋友圈设置 界面
        } else if (i == R.id.set_lin_discover) {
            if (NoDoubleClickUtils.isDoubleClick())
                IntentUtils.JumpTo(DiscoverSetActivity.class);


//                清理缓存
        } else if (i == R.id.set_lin_clear_cache) {
            if (NoDoubleClickUtils.isDoubleClick()) {
                String tvContent = setLinClearCache.getTvContent();
                if (!tvContent.equals("0KB")) {
                    DialogUtils.showDialog("确认清理" + tvContent + "缓存？", new DialogUtils.OnClickSureListener() {
                        @Override
                        public void onClickSure() {
//                                        cleanCaChe();
                            setLinClearCache.setTvContent("0KB");
                        }
                    });
                } else {
                    ToastUtil.show(getResources().getString(R.string.setting_no_cache));
//                        ToastUtil.show("暂无缓存");
                }
            }

//                检查更新
        } else if (i == R.id.set_lin_version) {
            if (NoDoubleClickUtils.isDoubleClick()) {
                //    TODO    版本更新
//                    try {
//                        int localVersion = HelpUtils.getLocalVersion(this);
////                    sendWeb(SplitWeb.getSplitWeb().appUpdate("" + localVersion));
//                        NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
//                        netWorkUtlis.setOnNetWork(AppConfig.NORMAL, SplitWeb.getSplitWeb().appUpdateHttp(localVersion + ""), new NetWorkUtlis.OnNetWork() {
//                            @Override
//                            public void onNetSuccess(String result) {
//                                String isSucess = HelpUtils.HttpIsSucess(result);
//                                if (isSucess.equals(AppConfig.CODE_OK))
//                                    VersionCheckUtils.initUpdata(result, false);
//                            }
//                        });
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
            }


//                关于我们界面
        } else if (i == R.id.set_lin_about_me) {
            ToastUtil.show(getResources().getString(R.string.setting_about_us_tip));
//                ToastUtil.show("这里是关于我们\n敬请期待！");


//                退出帐号    回到登录界面
//            TODO  设置退出帐号
        } else if (i == R.id.set_btn_esc) {
            if (NoDoubleClickUtils.isDoubleClick()) {
                DialogExitUtils.showDialog(getResources().getString(R.string.setting_btn_just_log_out), getResources().getString(R.string.setting_btn_log_out_and_delete), new DialogExitUtils.OnClickSureListener() {
                    //                    DialogExitUtils.showDialog("仅退出帐号", "退出并删除帐号信息", new DialogExitUtils.OnClickSureListener() {
                    @Override
                    public void onClickSure(String checkingId) {
                        switch (checkingId) {
                            case "1":
//                                    WsChannelService.isBind = true;
//                                    BaseApplication.isHomeMsgFragment = true;
//                                    Intent intent2 = new Intent(MineSetActivity.this, ChatService.class);
//                                    stopService(intent2);// 关闭服务
//                                    SplitWeb.getSplitWeb().IS_SET_PERSON_HEAD = true;
//                                    sendWeb(SplitWeb.getSplitWeb().kickUid());
//                                    SplitWeb.getSplitWeb().USER_ID = "";
//                                    SplitWeb.getSplitWeb().USER_TOKEN = "";
//                                    ACache.get(MineSetActivity.this).clear();
//                                    SPUtils.put(MineSetActivity.this, AppAllKey.USER_ID_KEY, "");
//                                    SPUtils.put(MineSetActivity.this, AppAllKey.USER_Token, "");
//                                    AppManager.getAppManager().onAppExit(MineSetActivity.this);
//                                    IntentUtils.JumpToHaveOne(LoginActivity.class, "phone", userPhone);
//                                    overridePendingTransition(0, 0);

                                break;
                            case "2":
//                                    WsChannelService.isBind = true;
//                                    BaseApplication.isHomeMsgFragment = true;
//                                    SplitWeb.getSplitWeb().IS_SET_PERSON_HEAD = true;
//                                    sendWeb(SplitWeb.getSplitWeb().kickUid());
//                                    SplitWeb.getSplitWeb().USER_ID = "";
//                                    SplitWeb.getSplitWeb().USER_TOKEN = "";
//                                    ACache.get(MineSetActivity.this).clear();
//                                    SPUtils.put(MineSetActivity.this, AppAllKey.USER_ID_KEY, "");
//                                    SPUtils.put(MineSetActivity.this, AppAllKey.USER_Token, "");
//                                    SPUtils.clear(MineSetActivity.this);
//                                    AppManager.getAppManager().finishAllActivity();
//                                    Intent intent = new Intent(MineSetActivity.this, LoginActivity.class);
//                                    startActivity(intent);
//                                    overridePendingTransition(0, 0);
//                                    realmHelper.deleteAll();
//                                    realmChatHelper.deleteAll();
                                break;
                        }

                    }
                });
            }

        }
//        switch (view.getId()) {
////            打开  名片分享  界面
//            case R.id.set_cus_lin_share:
//                if (NoDoubleClickUtils.isDoubleClick())
//                    IntentUtils.JumpTo(ShareSetActivity.class);
//                break;
//
////                打开  帐号与安全  界面
//            case R.id.set_lin_count:
//                if (NoDoubleClickUtils.isDoubleClick())
//                    IntentUtils.JumpTo(AccountAndSafeActivity.class);
//                break;
//
////                打开 隐私设置 界面
//            case R.id.set_lin_yinsi:
//                if (NoDoubleClickUtils.isDoubleClick())
//                    IntentUtils.JumpTo(YinSiActivity.class);
//                break;
//
////                打开消息提醒界面
//            case R.id.set_lin_message:
//                if (NoDoubleClickUtils.isDoubleClick())
//                    IntentUtils.JumpTo(NewsRemindActivity.class);
//                break;
//
////                打开 屏蔽设置  界面
//            case R.id.set_lin_pingbi:
//                if (NoDoubleClickUtils.isDoubleClick())
//                    IntentUtils.JumpTo(LaBlackActivity.class);
//                break;
//
////                打开 朋友圈设置 界面
//            case R.id.set_lin_discover:
//                if (NoDoubleClickUtils.isDoubleClick())
//                    IntentUtils.JumpTo(DiscoverSetActivity.class);
//                break;
//
////                清理缓存
//            case R.id.set_lin_clear_cache:
//                if (NoDoubleClickUtils.isDoubleClick()) {
//                    String tvContent = setLinClearCache.getTvContent();
//                    if (!tvContent.equals("0KB")) {
//                        DialogUtils.showDialog("确认清理" + tvContent + "缓存？", new DialogUtils.OnClickSureListener() {
//                            @Override
//                            public void onClickSure() {
////                                        cleanCaChe();
//                                setLinClearCache.setTvContent("0KB");
//                            }
//                        });
//                    } else {
//                        ToastUtil.show(getResources().getString(R.string.setting_no_cache));
////                        ToastUtil.show("暂无缓存");
//                    }
//                }
//                break;
////                检查更新
//            case R.id.set_lin_version:
//                if (NoDoubleClickUtils.isDoubleClick()) {
//                    //    TODO    版本更新
////                    try {
////                        int localVersion = HelpUtils.getLocalVersion(this);
//////                    sendWeb(SplitWeb.getSplitWeb().appUpdate("" + localVersion));
////                        NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
////                        netWorkUtlis.setOnNetWork(AppConfig.NORMAL, SplitWeb.getSplitWeb().appUpdateHttp(localVersion + ""), new NetWorkUtlis.OnNetWork() {
////                            @Override
////                            public void onNetSuccess(String result) {
////                                String isSucess = HelpUtils.HttpIsSucess(result);
////                                if (isSucess.equals(AppConfig.CODE_OK))
////                                    VersionCheckUtils.initUpdata(result, false);
////                            }
////                        });
////                    } catch (Exception e) {
////                        e.printStackTrace();
////                    }
//                }
//                break;
//
////                关于我们界面
//            case R.id.set_lin_about_me:
//                ToastUtil.show(getResources().getString(R.string.setting_about_us_tip));
////                ToastUtil.show("这里是关于我们\n敬请期待！");
//                break;
//
////                退出帐号    回到登录界面
////            TODO  设置退出帐号
//            case R.id.set_btn_esc:
//                if (NoDoubleClickUtils.isDoubleClick()) {
//                    DialogExitUtils.showDialog(getResources().getString(R.string.setting_btn_just_log_out), getResources().getString(R.string.setting_btn_log_out_and_delete), new DialogExitUtils.OnClickSureListener() {
//                        //                    DialogExitUtils.showDialog("仅退出帐号", "退出并删除帐号信息", new DialogExitUtils.OnClickSureListener() {
//                        @Override
//                        public void onClickSure(String checkingId) {
//                            switch (checkingId) {
//                                case "1":
////                                    WsChannelService.isBind = true;
////                                    BaseApplication.isHomeMsgFragment = true;
////                                    Intent intent2 = new Intent(MineSetActivity.this, ChatService.class);
////                                    stopService(intent2);// 关闭服务
////                                    SplitWeb.getSplitWeb().IS_SET_PERSON_HEAD = true;
////                                    sendWeb(SplitWeb.getSplitWeb().kickUid());
////                                    SplitWeb.getSplitWeb().USER_ID = "";
////                                    SplitWeb.getSplitWeb().USER_TOKEN = "";
////                                    ACache.get(MineSetActivity.this).clear();
////                                    SPUtils.put(MineSetActivity.this, AppAllKey.USER_ID_KEY, "");
////                                    SPUtils.put(MineSetActivity.this, AppAllKey.USER_Token, "");
////                                    AppManager.getAppManager().onAppExit(MineSetActivity.this);
////                                    IntentUtils.JumpToHaveOne(LoginActivity.class, "phone", userPhone);
////                                    overridePendingTransition(0, 0);
//
//                                    break;
//                                case "2":
////                                    WsChannelService.isBind = true;
////                                    BaseApplication.isHomeMsgFragment = true;
////                                    SplitWeb.getSplitWeb().IS_SET_PERSON_HEAD = true;
////                                    sendWeb(SplitWeb.getSplitWeb().kickUid());
////                                    SplitWeb.getSplitWeb().USER_ID = "";
////                                    SplitWeb.getSplitWeb().USER_TOKEN = "";
////                                    ACache.get(MineSetActivity.this).clear();
////                                    SPUtils.put(MineSetActivity.this, AppAllKey.USER_ID_KEY, "");
////                                    SPUtils.put(MineSetActivity.this, AppAllKey.USER_Token, "");
////                                    SPUtils.clear(MineSetActivity.this);
////                                    AppManager.getAppManager().finishAllActivity();
////                                    Intent intent = new Intent(MineSetActivity.this, LoginActivity.class);
////                                    startActivity(intent);
////                                    overridePendingTransition(0, 0);
////                                    realmHelper.deleteAll();
////                                    realmChatHelper.deleteAll();
//                                    break;
//                            }
//
//                        }
//                    });
//                }
//                break;
//        }
    }

    //    @OnClick({R.id.set_lin_pingbi, R.id.set_cus_lin_share, R.id.set_lin_count, R.id.set_lin_yinsi, R.id.set_lin_message, R.id.set_lin_discover,
//            R.id.set_lin_clear_cache, R.id.set_lin_version, R.id.set_lin_about_me, R.id.set_btn_esc})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
////            打开  名片分享  界面
//            case R.id.set_cus_lin_share:
//                if (NoDoubleClickUtils.isDoubleClick())
//                    IntentUtils.JumpTo(ShareSetActivity.class);
//                break;
//
////                打开  帐号与安全  界面
//            case R.id.set_lin_count:
//                if (NoDoubleClickUtils.isDoubleClick())
//                    IntentUtils.JumpTo(AccountAndSafeActivity.class);
//                break;
//
////                打开 隐私设置 界面
//            case R.id.set_lin_yinsi:
//                if (NoDoubleClickUtils.isDoubleClick())
//                    IntentUtils.JumpTo(YinSiActivity.class);
//                break;
//
////                打开消息提醒界面
//            case R.id.set_lin_message:
//                if (NoDoubleClickUtils.isDoubleClick())
//                    IntentUtils.JumpTo(NewsRemindActivity.class);
//                break;
//
////                打开 屏蔽设置  界面
//            case R.id.set_lin_pingbi:
//                if (NoDoubleClickUtils.isDoubleClick())
//                    IntentUtils.JumpTo(LaBlackActivity.class);
//                break;
//
////                打开 朋友圈设置 界面
//            case R.id.set_lin_discover:
//                if (NoDoubleClickUtils.isDoubleClick())
//                    IntentUtils.JumpTo(DiscoverSetActivity.class);
//                break;
//
////                清理缓存
//            case R.id.set_lin_clear_cache:
//                if (NoDoubleClickUtils.isDoubleClick()) {
//                    String tvContent = setLinClearCache.getTvContent();
//                    if (!tvContent.equals("0KB")) {
//                        DialogUtils.showDialog("确认清理" + tvContent + "缓存？", new DialogUtils.OnClickSureListener() {
//                            @Override
//                            public void onClickSure() {
////                                        cleanCaChe();
//                                setLinClearCache.setTvContent("0KB");
//                            }
//                        });
//                    } else {
//                        ToastUtil.show(getResources().getString(R.string.setting_no_cache));
////                        ToastUtil.show("暂无缓存");
//                    }
//                }
//                break;
////                检查更新
//            case R.id.set_lin_version:
//                if (NoDoubleClickUtils.isDoubleClick()) {
//                    //    TODO    版本更新
////                    try {
////                        int localVersion = HelpUtils.getLocalVersion(this);
//////                    sendWeb(SplitWeb.getSplitWeb().appUpdate("" + localVersion));
////                        NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
////                        netWorkUtlis.setOnNetWork(AppConfig.NORMAL, SplitWeb.getSplitWeb().appUpdateHttp(localVersion + ""), new NetWorkUtlis.OnNetWork() {
////                            @Override
////                            public void onNetSuccess(String result) {
////                                String isSucess = HelpUtils.HttpIsSucess(result);
////                                if (isSucess.equals(AppConfig.CODE_OK))
////                                    VersionCheckUtils.initUpdata(result, false);
////                            }
////                        });
////                    } catch (Exception e) {
////                        e.printStackTrace();
////                    }
//                }
//                break;
//
////                关于我们界面
//            case R.id.set_lin_about_me:
//                ToastUtil.show(getResources().getString(R.string.setting_about_us_tip));
////                ToastUtil.show("这里是关于我们\n敬请期待！");
//                break;
//
////                退出帐号    回到登录界面
////            TODO  设置退出帐号
//            case R.id.set_btn_esc:
//                if (NoDoubleClickUtils.isDoubleClick()) {
//                    DialogExitUtils.showDialog(getResources().getString(R.string.setting_btn_just_log_out), getResources().getString(R.string.setting_btn_log_out_and_delete), new DialogExitUtils.OnClickSureListener() {
//                        //                    DialogExitUtils.showDialog("仅退出帐号", "退出并删除帐号信息", new DialogExitUtils.OnClickSureListener() {
//                        @Override
//                        public void onClickSure(String checkingId) {
//                            switch (checkingId) {
//                                case "1":
////                                    WsChannelService.isBind = true;
////                                    BaseApplication.isHomeMsgFragment = true;
////                                    Intent intent2 = new Intent(MineSetActivity.this, ChatService.class);
////                                    stopService(intent2);// 关闭服务
////                                    SplitWeb.getSplitWeb().IS_SET_PERSON_HEAD = true;
////                                    sendWeb(SplitWeb.getSplitWeb().kickUid());
////                                    SplitWeb.getSplitWeb().USER_ID = "";
////                                    SplitWeb.getSplitWeb().USER_TOKEN = "";
////                                    ACache.get(MineSetActivity.this).clear();
////                                    SPUtils.put(MineSetActivity.this, AppAllKey.USER_ID_KEY, "");
////                                    SPUtils.put(MineSetActivity.this, AppAllKey.USER_Token, "");
////                                    AppManager.getAppManager().onAppExit(MineSetActivity.this);
////                                    IntentUtils.JumpToHaveOne(LoginActivity.class, "phone", userPhone);
////                                    overridePendingTransition(0, 0);
//
//                                    break;
//                                case "2":
////                                    WsChannelService.isBind = true;
////                                    BaseApplication.isHomeMsgFragment = true;
////                                    SplitWeb.getSplitWeb().IS_SET_PERSON_HEAD = true;
////                                    sendWeb(SplitWeb.getSplitWeb().kickUid());
////                                    SplitWeb.getSplitWeb().USER_ID = "";
////                                    SplitWeb.getSplitWeb().USER_TOKEN = "";
////                                    ACache.get(MineSetActivity.this).clear();
////                                    SPUtils.put(MineSetActivity.this, AppAllKey.USER_ID_KEY, "");
////                                    SPUtils.put(MineSetActivity.this, AppAllKey.USER_Token, "");
////                                    SPUtils.clear(MineSetActivity.this);
////                                    AppManager.getAppManager().finishAllActivity();
////                                    Intent intent = new Intent(MineSetActivity.this, LoginActivity.class);
////                                    startActivity(intent);
////                                    overridePendingTransition(0, 0);
////                                    realmHelper.deleteAll();
////                                    realmChatHelper.deleteAll();
//                                    break;
//                            }
//
//                        }
//                    });
//                }
//                break;
//        }
//    }

}