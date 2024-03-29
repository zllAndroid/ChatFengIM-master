package com.rance.chatui.about_chat.chat_group;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.mding.chatfeng.base_common.AppConfig;
import com.mding.chatfeng.base_common.components.base.BaseActivity;
import com.mding.chatfeng.base_common.components.base.BaseApplication;
import com.mding.chatfeng.base_common.components.base.Methon;
import com.mding.chatfeng.base_common.components.model.CusChatPop;
import com.mding.chatfeng.base_common.components.model.DataChatGroupPop;
import com.mding.chatfeng.base_common.components.model.DataGroupChatResult;
import com.mding.chatfeng.base_common.components.model.DataGroupChatSend;
import com.mding.chatfeng.base_common.components.model.DataJieShou;
import com.mding.chatfeng.base_common.components.model.DataQueryRepetition;
import com.mding.chatfeng.base_common.utils.GsonParamConverter;
import com.mding.chatfeng.base_common.utils.about_immersive.StateBarUtils;
import com.mding.chatfeng.base_common.utils.about_main_utils.HelpUtils;
import com.mding.chatfeng.base_common.utils.about_main_utils.IntentUtils;
import com.mding.chatfeng.base_common.utils.about_main_utils.MathUtils;
import com.mding.chatfeng.base_common.utils.about_main_utils.SoftKeyboardUtils;
import com.mding.chatfeng.base_common.utils.about_main_utils.SysRunUtils;
import com.mding.chatfeng.base_common.utils.about_main_utils.TimeUtil;
import com.mding.chatfeng.base_common.utils.about_main_utils.windowStatusBar;
import com.mding.chatfeng.base_common.utils.aboutsystem.WindowBugDeal;
import com.mding.chatfeng.base_common.utils.aboututils.MyLog;
import com.mding.chatfeng.base_common.utils.aboututils.SPUtils;
import com.mding.chatfeng.base_common.utils.aboututils.StrUtils;
import com.mding.chatfeng.base_common.utils.aboututils.ToastUtil;
import com.mding.chatfeng.chat.R;
import com.rance.chatui.about_chat.ChatNewsWindow;
import com.rance.chatui.about_chat.EmotionInputDetector;
import com.rance.chatui.about_chat.GlobalOnItemClickManagerUtils;
import com.rance.chatui.about_chat.cus_data_group.CusGroupChatData;
import com.rance.chatui.enity.MessageInfo;
import com.rance.chatui.ui.ChatEmotionFragment;
import com.rance.chatui.ui.ChatFunctionFragment;
import com.rance.chatui.ui.CommonFragmentPagerAdapter;
import com.rance.chatui.util.Constants;
import com.rance.chatui.util.MediaManager;
import com.rance.chatui.widget.NoScrollViewPager;
import com.rance.chatui.widget.PopupList;
import com.rance.chatui.widget.StateButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


/**
 * 项目：DoubleQ
 * 文件描述：群聊界面
 * 作者：zll
 * 修改者：ljj
 */
public class ChatGroupActivity extends BaseActivity {

    EasyRecyclerView chatList;
    ImageView emotionVoice;
    ImageView mIvBack;
    EditText editText;
    TextView voiceText;
    ImageView emotionButton;
    ImageView emotionAdd;
    StateButton emotionSend;
    NoScrollViewPager viewpager;
    RelativeLayout emotionLayout;
    TextView mChatTvShow;
    LinearLayout mLinTop;
    LinearLayout mInputLinMain;
    LinearLayout mInputLin;
    LinearLayout mLinChatMain;

    ImageView includeTopIvMore;
    TextView incluTvRight;
    TextView includeTopTvTitle;
    LinearLayout chatLinMainWhole;
    ImageView includeTopIvDrop;
    LinearLayout includeTopLinTitle;
    TextView includeTopTvPeopleNum;
    ImageView mIvScroll;

    private EmotionInputDetector mDetector;
    private ArrayList<Fragment> fragments;
    private ChatEmotionFragment chatEmotionFragment;
    private ChatFunctionFragment chatFunctionFragment;
    private CommonFragmentPagerAdapter adapter;

    private ChatGroupAdapter chatAdapter;
//    private LinearLayoutManager layoutManager;

    //录音相关
    int animationRes = 0;
    int res = 0;
    AnimationDrawable animationDrawable = null;
    private ImageView animView;
    //    外来消息弹窗显示时间
    int showTime = 2000;


//    RealmGroupChatHelper realmGroupChatHelper;
//    RealmHomeHelper realmHomeHelper;

    HideControl hideControl;

    public static String groupId;

    private List<MessageInfo> messageInfos;

//    ChatPopWindow chatGroupPopWindow;

    DataChatGroupPop dataChatGroupPop;
    CusChatPop cusChatPop;
    String cardName;
    String isChecked = "3";

    @Override
    protected boolean isChenjinshi() {
        return false;
    }
    @Override
    protected void initBeforeContentView() {
        super.initBeforeContentView();
        WindowBugDeal.SetTop(this);
        windowStatusBar.setStatusColor(this, getResources().getColor(R.color.app_theme), 0);
//        StateBarUtils.setFullscreen(this, false, false);

//        设置状态栏字体颜色为浅色（白色）
        StateBarUtils.setAndroidNativeLightStatusBar(this,false);
        if (Build.VERSION.SDK_INT >= 21)
            getWindow().setNavigationBarColor(Color.WHITE);
    }
//    private RealmGroupChatHelper getRealmGroupChatHelper()
//    {
//        if (realmGroupChatHelper == null)
//            realmGroupChatHelper = new RealmGroupChatHelper(this);
//        return realmGroupChatHelper;
//    }
//    private  RealmHomeHelper   getRealmHomeHelper()
//    {
//        if (realmHomeHelper == null)
//            realmHomeHelper = new RealmHomeHelper(this);
//        return realmHomeHelper;
//    }
    @Override
    protected void initBaseView() {
        super.initBaseView();
        initUI();
        setAboutBar();
//        TODO
      /*  SplitWeb.getSplitWeb().IS_CHAT_GROUP = "2";*/
        if (hideControl == null)
            hideControl = new HideControl();
        mChatTvShow.setBackgroundResource(R.color.chattrans);
//        realmLink = new RealmLinkManHelper(this);
        // 初始化接收广播
        initReceiver();
        Intent intent = getIntent();
        if (intent != null) {
            groupId = intent.getStringExtra(AppConfig.KEY_GROUP_Id);
//            TODO
           /* String  nickName=  new RealmGroupHelper(this).queryLinkFriendReturnName(groupId);//获取群聊群名
            if (!StrUtils.isEmpty(nickName))
            {
                includeTopTvTitle.setText(nickName);
            }*/
            initWidget();
//        初始化数据库的聊天记录
            initRealm();

            listenEnter();
        }
        incluTvRight.setVisibility(View.GONE);
        includeTopIvMore.setVisibility(View.VISIBLE);
        includeTopIvMore.setImageResource(R.drawable.group_chat_head_right);
        MyLog.e("ChatActivity","--------------------标题---------Group---------------->>"+includeTopTvTitle.getText().toString());
        /*sendWeb(SplitWeb.getSplitWeb().groupSendInterface(groupId));*/
        includeTopTvTitle.setMovementMethod(new ScrollingMovementMethod());
    }

    private void initUI() {
        chatList=getView(R.id.chat_list);
        emotionVoice=getView(R.id.emotion_voice);
        mIvBack=getView(R.id.include_top_iv_back);
        editText=getView(R.id.edit_text);
        voiceText=getView(R.id.voice_text);
        emotionButton=getView(R.id.emotion_button);
        emotionAdd=getView(R.id.emotion_add);
        emotionSend= getView(R.id.emotion_send);
        viewpager=getView(R.id.viewpager);
        emotionLayout=getView(R.id.emotion_layout);
        mChatTvShow=getView(R.id.chat_tv_show);
        mLinTop=getView(R.id.include_top_lin_back);
        mInputLinMain=getView(R.id.input_lin_main);
        mInputLin=getView(R.id.input_lin_in);
        mLinChatMain=getView(R.id.chat_lin_main);

        includeTopIvMore=getView(R.id.include_top_iv_more);
        incluTvRight=getView(R.id.inclu_tv_right);
        includeTopTvTitle=getView(R.id.include_top_tv_title);
        chatLinMainWhole=getView(R.id.chat_lin_main_whole);
        includeTopIvDrop=getView(R.id.include_top_iv_drop);
        includeTopLinTitle=getView(R.id.include_top_lin_title);
        includeTopTvPeopleNum=getView(R.id.include_top_tv_people_num);

        mIvScroll= getView(R.id.chat_scroll_bot);
        addOnClickListeners(R.id.include_top_iv_more, R.id.include_top_lin_title,R.id.chat_scroll_bot);
    }


    IntentFilter intentFilter;

    //广播接收消息推送
    private void initReceiver() {
        if (intentFilter == null) {
            intentFilter = new IntentFilter();
            intentFilter.addAction(GroupChatDetailsActivity.ACTION_UP_GROUP_NAME);
            registerReceiver(mRefreshBroadcastReceiver, intentFilter);
        }
    }

    //    static RealmHomeHelper realmHelper;
    public BroadcastReceiver mRefreshBroadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
//            if (realmHelper == null) {
//                realmHelper = new RealmHomeHelper(ChatGroupActivity.this);
//            }
            if (action.equals(GroupChatDetailsActivity.ACTION_UP_GROUP_NAME)) {
                initGroupName(intent);
            }
//            sendBroadcast();
        }
    };

    private void initGroupName(Intent intent) {
//        String groupId = intent.getStringExtra("id");
        String groupName = intent.getStringExtra("groupName");
        includeTopTvTitle.setText(groupName);
    }

    //设置状态栏的高度为负状态栏高度，因为xml 设置了 android:fitsSystemWindows="true",会占用一个状态栏的高度；
    private void setAboutBar() {
//        获取状态栏的高度
        int statusBarHeight = WindowBugDeal.getStatusBarHeight(this);
        //这里我用RelativeLayout布局为列，其他布局设置方法一样，只需改变布局名就行
        LinearLayout.LayoutParams layout = (LinearLayout.LayoutParams) mLinChatMain.getLayoutParams();
//获得button控件的位置属性，需要注意的是，可以将button换成想变化位置的其它控件
        layout.setMargins(0, -statusBarHeight, 0, 0);
//设置button的新位置属性,left，top，right，bottom
        mLinChatMain.setLayoutParams(layout);
    }

    private void listenEnter() {
        editText.setSingleLine();
        editText.setImeOptions(EditorInfo.IME_ACTION_SEND);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    //处理事件
                    String ed = editText.getText().toString().trim();
                    if (!StrUtils.isEmpty(ed)) {

//                        TODO
                       /* if (groupId != null)
                            send(SplitWeb.getSplitWeb().groupSend(groupId, ed, AppConfig.SEND_MESSAGE_TYPE_TEXT, TimeUtil.getTime()));*/
                    } else {
                        editText.setText("");
                        ToastUtil.show("发送的内容不能为空");
                    }
                }
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        includeTopIvDrop.setClickable(false);// 屏蔽主动获得点击
        includeTopIvDrop.setPressed(false);
        includeTopIvDrop.setEnabled(false);
       /* if (chatGroupPopWindow != null) {
            chatGroupPopWindow.dismiss();
        }
        sendWeb(SplitWeb.getSplitWeb().groupSendInterface(groupId));*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
     /*   SplitWeb.getSplitWeb().IS_CHAT_GROUP = "00";
        getRealmHomeHelper().updateNumZero(groupId);
        EventBus.getDefault().post(new MsgHomeEvent("",groupId,AppConfig.MSG_ZERO_REFRESH));*/
//        try {
//            realmGroupChatHelper.close();
//            realmGroupChatHelper.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        try {
            if (mRefreshBroadcastReceiver != null)
                unregisterReceiver(mRefreshBroadcastReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
       /* if (chatGroupPopWindow != null){
            chatGroupPopWindow.dismiss();
            chatGroupPopWindow=null;
        }*/
    }

    private void initRealm() {
//        List<CusGroupChatData> cusRealmChatMsgs = getRealmGroupChatHelper().queryAllGroupChat(groupId);
//        if (cusRealmChatMsgs != null && cusRealmChatMsgs.size() != 0) {
//            chatAdapter.addAll(cusRealmChatMsgs);
//            chatAdapter.notifyDataSetChanged();
//            //    滑动到底部
//            layoutManager.scrollToPositionWithOffset(chatAdapter.getCount() - 1, 0);
//        }
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.chat_scroll_bot:
                if (mIvScroll.getVisibility()==View.VISIBLE)
                    layoutManager.scrollToPositionWithOffset(chatAdapter.getCount() - 1, 0);
                break;
            case R.id.include_top_iv_more:
//                IntentUtils.JumpToHaveOne(GroupChatDetailsActivity.class, AppConfig.GROUP_ID, groupId);
//                AppManager.getAppManager().isNowActivityFinish(GroupChatDetailsActivity.class);

//                IntentUtils.JumpToHaveTwo(GroupDetailActivity.class, AppConfig.GROUP_ID,groupId,AppConfig.IS_CHATGROUP_TYPE,AppConfig.CHATGROUP);
                IntentUtils.JumpToHaveTwo(GroupChatDetailsActivity.class, AppConfig.GROUP_ID,groupId,AppConfig.IS_CHATGROUP_TYPE,AppConfig.CHATGROUP);
                break;
            case R.id.include_top_lin_title:
               /* includeTopIvDrop.setActivated(true);
                cusChatPop = new CusChatPop();
                cusChatPop.setType("2");
                cusChatPop.setContext(this);
                cusChatPop.setChatLinMainWhole(chatLinMainWhole);
                cusChatPop.setGroupId(groupId);
                cusChatPop.setIsChecked(isChecked);
//                String cardNames = cardName.equals("暂无") ? "" : cardName;
                cusChatPop.setCardName(cardName);
                if (chatGroupPopWindow == null)
                    chatGroupPopWindow = new ChatPopWindow(cusChatPop);
                chatGroupPopWindow.showAtBottom(includeTopLinTitle);
                chatGroupPopWindow.setOnClickOutSideListener(new ChatPopWindow.OnClickOutSideListener() {
                    @Override
                    public void Clicked(String type) {
                        includeTopIvDrop.setActivated(false);
                    }
                });
                chatGroupPopWindow.setOnReCardNameListener(new ChatPopWindow.OnReCardNameListener() {
                    @Override
                    public void reCardName(String cardName) {
                        if (cardName != null)
                            sendWeb(SplitWeb.getSplitWeb().setGroupCarteModify(groupId, cardName));
                    }
                });*/
                break;
        }
    }
    private StaggeredGridLayoutManager layoutManager;
    @Override
    protected int getLayoutView() {
        return R.layout.activity_chat;
    }

    //屏幕高度
    private int screenHeight = 0;
    //软件盘弹起后所占高度阀值
    private int keyHeight = 0;

    @SuppressLint("ClickableViewAccessibility")
    private void initWidget() {
        fragments = new ArrayList<>();
        chatEmotionFragment = new ChatEmotionFragment();
        fragments.add(chatEmotionFragment);
        chatFunctionFragment = new ChatFunctionFragment();
        fragments.add(chatFunctionFragment);
        adapter = new CommonFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        viewpager.setAdapter(adapter);
        viewpager.setCurrentItem(0);

        mDetector = EmotionInputDetector.with(this)
                .setEmotionView(emotionLayout)
                .setViewPager(viewpager)
                .bindToContent(chatList)
                .bindToEditText(editText)
                .bindToEmotionButton(emotionButton)
                .bindToAddButton(emotionAdd)
                .bindToSendButton(emotionSend)
                .bindToVoiceButton(emotionVoice)
                .bindToVoiceText(voiceText)
                .bindToInputLayout(mInputLinMain)
                .build();

        //获取屏幕高度
        screenHeight = this.getWindowManager().getDefaultDisplay().getHeight();
        //阀值设置为屏幕高度的1/3
        keyHeight = screenHeight / 3;
        mInputLinMain.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, final int left, final int top, final int right, final int bottom, final int oldLeft, final int oldTop, final int oldRight, final int oldBottom) {
                if (emotionLayout.isShown() || isSoftShowing()) {
                    if (layoutManager != null && chatAdapter != null)
                        layoutManager.scrollToPositionWithOffset(chatAdapter.getCount() - 1, 0);
                }
            }
        });
        GlobalOnItemClickManagerUtils globalOnItemClickListener = GlobalOnItemClickManagerUtils.getInstance(this);
        globalOnItemClickListener.attachToEditText(editText);
        if (chatAdapter==null)
            chatAdapter = new ChatGroupAdapter(this);
//        layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
//        layoutManager.setRecycleChildrenOnDetach(true);//复用RecycledViewPool
        chatList.setLayoutManager(layoutManager);


        chatList.setAdapter(chatAdapter);
        initPopMenu();
        chatList.getRecyclerView().setItemViewCacheSize(20);
        chatAdapter.addItemClickListener(itemClickListener);

        chatList.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN) {
//                    case MotionEvent.ACTION_DOWN: //手指按下
//                        点击列表，软键盘或者表情列表存在，则关闭他们；
                    Log.e("chatList",isSoftShowing()+"---"+emotionLayout.isShown());
                    if (isSoftShowing() || emotionLayout.isShown()) {
                        emotionLayout.setVisibility(View.GONE);
                        chatAdapter.handler.removeCallbacksAndMessages(null);
                        mDetector.hideEmotionLayout(false);
                        mDetector.hideSoftInput();
                    }
                } //end switch
                return false;
            }
        });
        chatList.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (chatList != null) {
                    boolean visBottom = isVisBottom();
                    if (visBottom) {
                        mIvScroll.setVisibility(View.GONE);
                    } else {
                        mIvScroll.setVisibility(View.VISIBLE);
                    }
                }
            }
        });


    }
    public  boolean isVisBottom(){
        StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) chatList.getRecyclerView().getLayoutManager();

        //屏幕中最后一个可见子项的position
        int[] lastVisibleItemPosition = layoutManager.findLastVisibleItemPositions(null);
        //当前屏幕所看到的子项个数
        int visibleItemCount = layoutManager.getChildCount();
        //当前RecyclerView的所有子项个数
        int totalItemCount = layoutManager.getItemCount();
        //RecyclerView的滑动状态
//        int state = recyclerView.getScrollState();
        for (int position : lastVisibleItemPosition) {
            if (visibleItemCount > 0 && position == totalItemCount - 1) {
                return true;
            }
        }
        return false;
    }
    private List<String> popupMenuItemList = new ArrayList<>();
    private void initPopMenu() {

        popupMenuItemList.add(getString(R.string.copy));
        popupMenuItemList.add(getString(R.string.delete));
        popupMenuItemList.add(getString(R.string.share));
    }
    private boolean isSoftShowing() {
        //获取当前屏幕内容的高度
        int screenHeight = getWindow().getDecorView().getHeight();
        //获取View可见区域的bottom
        Rect rect = new Rect();
        //DecorView即为activity的顶级view
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        //考虑到虚拟导航栏的情况（虚拟导航栏情况下：screenHeight = rect.bottom + 虚拟导航栏高度）
        //选取screenHeight*2/3进行判断
        return screenHeight * 2 / 3 > rect.bottom;
    }

    //订阅方法，接收到服务器返回事件处理
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(DataJieShou.RecordBean messageInfo) {
        String ed = editText.getText().toString().trim();
        if (!StrUtils.isEmpty(ed)) {
            if (groupId != null) {
                /*send(SplitWeb.getSplitWeb().groupSend(groupId, ed, AppConfig.SEND_MESSAGE_TYPE_TEXT, TimeUtil.getTime()));
                MyLog.e("groupSend","groupSend="+SplitWeb.getSplitWeb().groupSend(groupId, ed, AppConfig.SEND_MESSAGE_TYPE_TEXT, TimeUtil.getTime()));
 */           }
            else {
                editText.setText("");
                ToastUtil.show("发送的内容不能为空");
            }
        }
    }

    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String method = HelpUtils.backMethod(responseText);
        switch (method) {
//            发送消息返回
            case Methon.GroupChatSend: //sendGroupChat  群聊新接口
                String ed = editText.getText().toString().trim();
                if (!StrUtils.isEmpty(ed)) {
                    editText.setText("");
                }
                dealSendResult(responseText);
                break;
//                接收消息返回
            case Methon.ReceiveGroupChat:
                dealReceiverResult(responseText);
                break;
            case "groupSendInterface":
//                Message message = new Message();
//                message.
//                mHandler.sendMessage(message);
//

                dataChatGroupPop =  new GsonParamConverter().json2Object(responseText, DataChatGroupPop.class);
                DataChatGroupPop.RecordBean recordBean = dataChatGroupPop.getRecord();
                if (recordBean != null) {
                    DataChatGroupPop.RecordBean.GroupDetailInfoBean groupDetailInfoBean = recordBean.getGroupDetailInfo();
                    DataChatGroupPop.RecordBean.GroupDetailInfoBean.UserInfoBean userInfoBean = groupDetailInfoBean.getUserInfo();
                    DataChatGroupPop.RecordBean.GroupDetailInfoBean.GroupInfoBean groupInfo = groupDetailInfoBean.getGroupInfo();
                    includeTopTvTitle.setText(groupInfo.getGroupName());
                    includeTopTvPeopleNum.setText("人数：" + groupInfo.getNowNum());
                    includeTopTvPeopleNum.setVisibility(View.VISIBLE);
                    cardName = StrUtils.isEmpty(userInfoBean.getCarteName()) ? "暂无" : userInfoBean.getCarteName();
                    isChecked = userInfoBean.getDisturbType();
                }
                break;
            // 请求MD5返回值判断
            case "getQueryRepetition":
                DataQueryRepetition dataQueryRepetition =  new GsonParamConverter().json2Object(responseText, DataQueryRepetition.class);
                if (dataQueryRepetition != null) {
                    DataQueryRepetition.RecordBean recordBeanQuery = dataQueryRepetition.getRecord();
                    if (recordBeanQuery != null) {
//                        TODO
                       /* if (recordBeanQuery.getFileType() == 1) {
                            // 是否发送 1需发送 2无需发送
                            send(SplitWeb.getSplitWeb().groupSend(ChatGroupActivity.groupId, imgTotalFunFrag, messageTypeImg, TimeUtil.getTime()));
                        } else if (recordBeanQuery.getFileType() == 2) {
                            send(SplitWeb.getSplitWeb().groupSend(ChatGroupActivity.groupId, imgMD5FunFrag, messageTypeImg, TimeUtil.getTime()));
                        }*/
                    }
                }
                break;
        }
    }

    ChatNewsWindow chatWindow = null;
    private void dealReceiverResult(String responseText) {

    }
//    private void dealReceiverResult(String responseText) {
//        DataGroupChatResult dataJieShou1 = JSON.parseObject(responseText, DataGroupChatResult.class);
//        final DataGroupChatResult.RecordBean record2 = dataJieShou1.getRecord();
//        if (record2 != null) {
////                    收到聊天页的此人的消息
//            if (record2.getGroupId().equals(groupId)) {
////                record2.setSendState(Constants.CHAT_ITEM_SEND_SUCCESS);
////                record2.setType(Constants.CHAT_ITEM_TYPE_LEFT);
////                CusChatData cusRealmChatMsg = new CusChatData();
////                    realmHelper.addRealmChat(FriendId,msg,messageType,Constants.CHAT_ITEM_TYPE_RIGHT, TimeUtil.sf.format(new Date()));
//                String myTime = record2.getRequestTime();
//                String time = (String) SPUtils.get(this, AppConfig.CHAT_RECEIVE_TIME, "");
//                SPUtils.put(this, AppConfig.CHAT_RECEIVE_TIME, (String) record2.getRequestTime());
//                if (!StrUtils.isEmpty(time)) {
//                    try {
//                        int i = TimeUtil.stringDaysBetween(myTime, time);
//                        if (MathUtils.abs(i) < 5) {
//                            myTime = "";
//                        }
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                CusGroupChatData groupChatData = new CusGroupChatData();
//                groupChatData.setNameFriend(record2.getMemberName());
//                groupChatData.setNameGroup(record2.getGroupName());
//                groupChatData.setMessage(record2.getMessage());
//                groupChatData.setImgGroup(record2.getGroupHeadImg());
//                groupChatData.setImgHead(record2.getMemberHeadImg());
//                groupChatData.setGroupId(record2.getGroupId());
//                groupChatData.setFriendId(record2.getMemberId());
//
//                groupChatData.setGroupUserId(record2.getGroupId() + SplitWeb.getSplitWeb().getUserId());
//                groupChatData.setSendState(Constants.CHAT_ITEM_SEND_SUCCESS);
//                groupChatData.setCreated(myTime);
//                groupChatData.setMessageType(record2.getMessageType());
//                groupChatData.setUserMessageType(Constants.CHAT_ITEM_TYPE_LEFT);
//                chatAdapter.add(groupChatData);
//                chatAdapter.notifyDataSetChanged();
////                    滑动到底部
//                layoutManager.scrollToPositionWithOffset(chatAdapter.getCount() - 1, 0);
//                EventBus.getDefault().post(new MsgHomeEvent(record2.getMessage(), record2.getGroupId(), AppConfig.MSG_ZERO_REFRESH));
//            } else {
//                ToastUtil.show("收到一条群聊消息");
//                EventBus.getDefault().post(new MsgHomeEvent(record2.getMessage(), record2.getGroupId(), AppConfig.MSG_ACTION_REFRESH));
//            }
//            if (!SysRunUtils.isAppOnForeground(BaseApplication.getAppContext())) {
//                EventBus.getDefault().post(new MsgHomeEvent(record2.getMessage(), record2.getGroupId(), AppConfig.MSG_ACTION_REFRESH));
//            }
//        }
//    }

    private void dealSendResult(String responseText) {
//        DataGroupChatSend dataJieShou = new GsonParamConverter().json2Object(responseText, DataGroupChatSend.class);
//        DataGroupChatSend.RecordBean record = dataJieShou.getRecord();
//        if (record != null) {
//            String time = (String) SPUtils.get(this, AppConfig.CHAT_SEND_TIME, "");
//            if (StrUtils.isEmpty(time)) {
//                SPUtils.put(this, AppConfig.CHAT_SEND_TIME, (String) record.getRequestTime());
//            } else {
//                try {
//                    int i = TimeUtil.stringDaysBetween(record.getRequestTime(), time);
//                    SPUtils.put(this, AppConfig.CHAT_SEND_TIME, (String) record.getRequestTime());
//                    if (MathUtils.abs(i) < 5) {
//                        record.setRequestTime("");
//                    } else {
//                        record.setRequestTime(record.getRequestTime());
//                    }
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//            }
//            CusGroupChatData groupChatData = new CusGroupChatData();
//            groupChatData.setMessage(record.getMessage());
//            groupChatData.setGroupId(record.getGroupId());
//            groupChatData.setFriendId(record.getUserId());
//            groupChatData.setGroupUserId(record.getGroupId() + SplitWeb.getSplitWeb().getUserId());
//            groupChatData.setSendState(Constants.CHAT_ITEM_SEND_SUCCESS);
//            groupChatData.setCreated(record.getRequestTime());
//            groupChatData.setMessageType(record.getMessageType());
////            groupChatData.setType(Constants.CHAT_ITEM_TYPE_RIGHT);
//            groupChatData.setUserMessageType(Constants.CHAT_ITEM_TYPE_RIGHT);
//            chatAdapter.add(groupChatData);
//            chatAdapter.notifyDataSetChanged();
////                    滑动到底部
//            layoutManager.scrollToPositionWithOffset(chatAdapter.getCount() - 1, 0);
//        }
    }
    View view = null;
    /**
     * item点击事件
     */
    private ChatGroupAdapter.onItemClickListener itemClickListener = new ChatGroupAdapter.onItemClickListener() {

        @Override
        public void onHeaderClick(int position, int type, String friendId) {
            switch (type) {
                case Constants.CHAT_ITEM_TYPE_LEFT:

                    //                    TODO 点击朋友头像，显示朋友的信息
//                    IntentUtils.JumpToHaveOne(FriendDataMixActivity.class, "id", friendId);
                    break;
                case Constants.CHAT_ITEM_TYPE_RIGHT:
//                    TODO 点击自己头像，显示自己的信息
//                    IntentUtils.JumpTo(ChangeInfoActivity.class);
                    break;
            }
        }

        @Override
        public void onConClick(View view, int position, String conText) {
            initPopChoose(view, position, conText);
        }
        private void initPopChoose(View view, int position, String conText) {
            int[] location = new int[2];
            view.getLocationOnScreen(location);
            PopupList popupList = new PopupList(view.getContext());
            popupList.showPopupListWindow(view, position, location[0] + view.getWidth() / 2,
                    location[1], popupMenuItemList, new PopupList.PopupListListener() {
                        @Override
                        public boolean showPopupList(View adapterView, View contextView, int contextPosition) {
                            return true;
                        }
                        @Override
                        public void onPopupListClick(View contextView, int contextPosition, int positions) {
//                            第二个参数是第几条数据，第三个参数本列表选择第几个
                            switch (positions)
                            {
                                case  0://复制
                                    ClipboardManager myClipboard;
                                    myClipboard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
                                    ClipData myClip;
                                    myClip = ClipData.newPlainText("text", conText);
                                    myClipboard.setPrimaryClip(myClip);
                                    ToastUtil.show("复制成功，可以去粘贴板黏贴哦");
                                    break;
                                case  1://删除
//                                    chatAdapter.getItem(contextPosition);
                                 /*   boolean b = getRealmGroupChatHelper().deletePosition(groupId, contextPosition);
//                                        boolean b = getRealmChatHelper().deletePosition(FriendId, item.getMessageStoId());
                                    if (b) {
//                                        chatAdapter.deleteWho(contextPosition);

                                        chatAdapter.remove(contextPosition);
                                        ToastUtil.show("删除成功");
                                        chatAdapter.notifyItemChanged(contextPosition);
//                                        chatAdapter.notifyAdapter(contextPosition);
//                                            chatAdapter.notifyDataSetChanged();
                                    } else {
                                        ToastUtil.show("删除失败，请重试");
                                    }*/
                                    break;
                                case  2://转发

                                    break;
                            }
                        }
                    });
        }
        @Override
        public void onImageClick( int position, String imgHttp) {
            IntentUtils.JumpToHaveOne(ShowChatImgActivity.class, ShowChatImgActivity.SHOW_CHAT_IMG_REGION, imgHttp);
        }
        @Override
        public void onVoiceClick(final ImageView imageView, final int position) {
            if (animView != null) {
                animView.setImageResource(res);
                animView = null;
            }
            switch (messageInfos.get(position).getType()) {
                case 1:
                    animationRes = R.drawable.voice_left;
                    res = R.mipmap.icon_voice_left3;
                    break;
                case 2:
                    animationRes = R.drawable.voice_right;
                    res = R.mipmap.icon_voice_right3;
                    break;
            }
            animView = imageView;
            animView.setImageResource(animationRes);
            animationDrawable = (AnimationDrawable) imageView.getDrawable();
            animationDrawable.start();
            MediaManager.playSound(messageInfos.get(position).getFilepath(), new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    animView.setImageResource(res);
                }
            });
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onBackPressed() {
        if (!mDetector.interceptBackPress()) {
            super.onBackPressed();
        }
    }

    public class HideControl {
        public final static int MSG_HIDE = 0x01;

        private HideHandler mHideHandler;

        public HideControl() {
            mHideHandler = new HideHandler();
        }

        public class HideHandler extends Handler {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case MSG_HIDE:
                        if (chatWindow != null) {
                            chatWindow.dismiss();
                            chatWindow = null;
                            if (SoftKeyboardUtils.isSoftShowing(ChatGroupActivity.this)) {
                                SoftKeyboardUtils.showSoftKeyboard(ChatGroupActivity.this);
                            }

                        }
                        break;
                }

            }
        }
    }

}
