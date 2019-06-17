package com.mding.chatfeng.home.ui.about_contacts.about_search;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mding.chatfeng.base_common.AppConfig;
import com.mding.chatfeng.base_common.components.base.BaseActivity;
import com.mding.chatfeng.base_common.components.model.DataSearchResult;
import com.mding.chatfeng.base_common.utils.GsonParamConverter;
import com.mding.chatfeng.base_common.utils.about_dialog.DialogUtils;
import com.mding.chatfeng.base_common.utils.about_main_utils.HelpUtils;
import com.mding.chatfeng.base_common.utils.about_main_utils.IntentUtils;
import com.mding.chatfeng.base_common.utils.aboututils.NoDoubleClickUtils;
import com.mding.chatfeng.base_common.utils.aboututils.StrUtils;
import com.mding.chatfeng.home.R;

import java.util.ArrayList;
import java.util.List;


//搜索好友界面
public class SearchActivity extends BaseActivity {

    TextView includeTopTvTital;
    ImageView includeTopIvBack;
    TextView incluTvRight;
    LinearLayout includeTopLinBack;
    EditText seachEdInput;
    ImageView seachIvClose;
    ImageView seachIvFind;
    RecyclerView seachRecyc;
    LinearLayout seachLinList;
    TextView seachTvNoSearch;
    RelativeLayout seachLinNoSearch;
    RecyclerView mRecyclerView;



    private void initViewUI() {
        mRecyclerView = getView(R.id.search_recyc);
        seachLinNoSearch = getView(R.id.seach_lin_noSearch);
        seachTvNoSearch = getView(R.id.seach_tv_noSearch);
        seachLinList = getView(R.id.seach_lin_list);
        seachRecyc = getView(R.id.seach_recyc);
        seachIvFind = getView(R.id.seach_iv_find);
        seachIvClose = getView(R.id.seach_iv_close);
        seachEdInput = getView(R.id.seach_ed_input);
        includeTopLinBack = getView(R.id.include_top_lin_back);
        incluTvRight = getView(R.id.inclu_tv_right);
        includeTopIvBack = getView(R.id.include_top_iv_back);
        includeTopTvTital = getView(R.id.include_top_tv_tital);
        addOnClickListeners(R.id.seach_iv_close, R.id.seach_iv_find);

    }
    //    @BindView(R.id.search_tv_empty)
//    TextView searchTvEmpty;
    public  static  String SeacchKey="search_type";
    public  static  String SeacchFriend="1";
    public  static  String SeacchGroup="2";
    /**
     * 搜索关键字全部匹配的适配器
     */
    private SearchAdapter alterSearchAdapter = null;
    String type;
    @Override
    protected void initBaseView() {
        super.initBaseView();
        initViewUI();

        Intent intent = getIntent();
        if (intent!=null)
            type = intent.getStringExtra(SeacchKey);

        includeTopTvTital.setText(getResources().getString(R.string.search_title));


        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(new GridLayoutManager(SearchActivity.this, 1));
        LinearLayout footer=  (LinearLayout)LayoutInflater.from(this).inflate(R.layout.item_search_bot, null);
//        监听软键盘的回车键
        listenEnter();

//        mRecyclerView.addHeaderView(footer);
    }



    //    监听软键盘的回车键
    private void listenEnter() {
        seachEdInput.setImeOptions(EditorInfo.IME_ACTION_SEND);
        seachEdInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    //处理事件
                    clickSearch();
                }
                return false;
            }
        });
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        seachEdInput.setFocusable(true);
        seachEdInput.setFocusableInTouchMode(true);
        seachEdInput.requestFocus();
        seachEdInput.findFocus();

//        seachEdInput.setFocusable(true);
//        seachEdInput.setFocusableInTouchMode(true);
//        seachEdInput.requestFocus();
//        InputMethodManager inputManager =
//                (InputMethodManager) seachEdInput.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//        inputManager.showSoftInput(seachEdInput, 0);
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_add_friend_search;
    }

    @Override
    protected boolean isTopBack() {
        return true;
    }

    @Override
    protected boolean isGones() {
        return true;
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int i = view.getId();
        if (i == R.id.seach_iv_close) {
            seachEdInput.setText("");

        } else if (i == R.id.seach_iv_find) {
            if (NoDoubleClickUtils.isDoubleClick()) {
                clickSearch();
            }
        }
    }

    List<DataSearch> mList =new ArrayList<>();
    //    List<DataSearch> keyWordList =new ArrayList<>();
    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String s = HelpUtils.backMethod(responseText);
        switch (s)
        {
            case "searchInfo":
//                try
//                {
                DataSearchResult dataSearchResult = new GsonParamConverter().json2Object(responseText, DataSearchResult.class);
                DataSearchResult.RecordBean record = dataSearchResult.getRecord();
                if (record!=null)
                {
                    mList.clear();
                    List<DataSearchResult.RecordBean.SeachGroupInfoBean> seachGroupInfo = record.getSeachGroupInfo();
                    List<DataSearchResult.RecordBean.SeachUserInfoBean> seachUserInfo = record.getSeachUserInfo();
                    if (seachGroupInfo.size()>0) {
                        String id = seachGroupInfo.get(0).getId();
                        if (!StrUtils.isEmpty(id)) {
                            for (int i = 0; i < seachGroupInfo.size(); i++) {
                                DataSearchResult.RecordBean.SeachGroupInfoBean seachGroupInfoBean = seachGroupInfo.get(i);
                                DataSearch dataSearch = new DataSearch();
                                dataSearch.setHeadImg(seachGroupInfoBean.getGroupHeadImg());
                                dataSearch.setId(seachGroupInfoBean.getId());
                                dataSearch.setName(seachGroupInfoBean.getGroupName());
                                dataSearch.setSno(seachGroupInfoBean.getGroupSno());
                                dataSearch.setQrcode(seachGroupInfoBean.getGroupQrcode());
                                dataSearch.setIsRelation(seachGroupInfoBean.getIsRelation());
//                                2代表群组
                                dataSearch.setType("2");
                                mList.add(dataSearch);
                            }
                        }
                    }
                    if (seachUserInfo.size()>0) {
                        String id = seachUserInfo.get(0).getUserId();
                        if (!StrUtils.isEmpty(id)) {
                            for (int j = 0; j < seachUserInfo.size(); j++) {
                                DataSearchResult.RecordBean.SeachUserInfoBean seachGroupInfoBean = seachUserInfo.get(j);
                                DataSearch dataSearch = new DataSearch();
                                dataSearch.setHeadImg(seachGroupInfoBean.getHeadImg());
                                dataSearch.setId(seachGroupInfoBean.getUserId());
                                dataSearch.setName(seachGroupInfoBean.getNickName());
                                dataSearch.setSno(seachGroupInfoBean.getWxSno());
                                dataSearch.setQrcode(seachGroupInfoBean.getQrcode());
                                dataSearch.setIsRelation(seachGroupInfoBean.getIsRelation());
                                dataSearch.setSign(seachGroupInfoBean.getPersonaSignature());
//                                1代表好友
                                dataSearch.setType("1");
                                mList.add(dataSearch);
                            }
                        }
                    }
                    if (mList.size()>0)
                    {
                        seachLinNoSearch.setVisibility(View.GONE);
                        seachLinList.setVisibility(View.VISIBLE);
                        initadapter(mList);
                    }else {
                        seachLinNoSearch.setVisibility(View.VISIBLE);
                        seachLinList.setVisibility(View.GONE);
                    }
                }
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                break;
        }
    }
    private void initadapter(List<DataSearch> seach_info) {
        SearchFriendAndGroupAdapter searchAdapter = new SearchFriendAndGroupAdapter(SearchActivity.this, seach_info);
        mRecyclerView.setAdapter(searchAdapter);
        searchAdapter.notifyDataSetChanged();
        searchAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DataSearch item =(DataSearch) adapter.getItem(position);
                if (item.getType().equals("1"))
                {
                    /*if (item.getId().equals(SplitWeb.getSplitWeb().getUserId()))
                    {
//                        TODO  跳转 ChangeInfoActivity
//                        IntentUtils.JumpTo(ChangeInfoActivity.class);
                        return;
                    }*/
//                    if (item.getIsRelation().equals("1"))
//                        IntentUtils.JumpToHaveObj(FriendDataAddActivity.class,"dataSearch",item);
//                    else
//                    {
//                        IntentUtils.JumpToHaveOne(FriendDataActivity.class,"id",item.getId());
//                    }
//                    TODO  跳转  FriendDataMixActivity
//                    IntentUtils.JumpToHaveOne(FriendDataMixActivity.class,"id",item.getId());
                }else
                {
//                    添加群
                    Log.e("qrCode","----------getIsRelation------------------"+item.getIsRelation());
                    if (item.getIsRelation().equals("1")){
//                    TODO 未添加过该群，
//                      跳转未添加的群详情，部分信息看不到
//                        IntentUtils.JumpToHaveObj(GroupDataAddActivity.class, AppConfig.GROUP_ADDKEY,item);

                    }
                    else
                    {
//                      TODO    添加过该群，跳转群详情
//                        JumpToHaveOne(GroupChatDetailsActivity.class, AppConfig.GROUP_ID,item.getId());

                    }
                }
//                ToastUtil.show("好友");
            }
        });
        LinearLayout footer=(LinearLayout)LayoutInflater.from(this).inflate(R.layout.item_search_bot, null);
        searchAdapter.addFooterView(footer);
        searchAdapter.notifyDataSetChanged();
    }
    private void clickSearch() {
        String edInput = seachEdInput.getText().toString().trim();
        if (StrUtils.isEmpty(edInput))
        {
            DialogUtils.showDialog("搜索内容不能为空");
            return;
        }
        if (type==null)
            type=SeacchFriend;
//        TODO 搜索
//        sendWebHaveDialog(SplitWeb.getSplitWeb().searchInfo(edInput,type),"搜索中...","搜索成功");
//        alterSearchAdapter.setText(edInput);
//        doChangeColor(edInput);
    }

//    private void doChangeColor(String text) {
//        //  clear是必须的，不然只要改变EditText的数据，list 就会一直添加数据进来
//        mList.clear();
//        //  不需要匹配，把所有数据都传进来，不需要变色
//        if (text.equals("")){
////            mList.addAll(mList);
//            //  防止匹配过文字之后点击删除按钮，字体仍然变色的问题
//            searchAdapter.setText(null);
////            refershUI();
//            initadapter(mList);
//            Log.e("search","---------mList_doChangeColor----0-----"+mList.size());
//        }else {
//            //  如果EditText里面有数据，则根据EditText里面的数据进行匹配，用contains判断是否包含该数据，包含的话则加入到list中
//            for (DataSearch i : mList){
//
//                if (i.getName().contains(text) || i.getId().contains(text)){
//                    mList.add(i);
//                    Log.e("search","---------mList_doChangeColor----1----"+mList.size());
////                    refershUI();
//                }
//            }
//            //  设置要变色的关键字
//            searchAdapter.setText(text);
//            initadapter(mList);
//        }
//    }

}
