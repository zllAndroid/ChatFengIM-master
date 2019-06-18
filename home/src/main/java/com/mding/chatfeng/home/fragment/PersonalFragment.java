package com.mding.chatfeng.home.fragment;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.mding.chatfeng.base_common.AppConfig;
import com.mding.chatfeng.base_common.components.model.DataMyZiliao;
import com.mding.chatfeng.base_common.components.model.HeadImgInfo;
import com.mding.chatfeng.base_common.components.model.PersonInfo;
import com.mding.chatfeng.base_common.utils.GsonParamConverter;
import com.mding.chatfeng.base_common.utils.about_custom.about_linearlayout.CusLinearLayout;
import com.mding.chatfeng.base_common.utils.about_custom.about_top_bar.FragmentTopBarLayout;
import com.mding.chatfeng.base_common.utils.about_dialog.DialogUtils;
import com.mding.chatfeng.base_common.utils.about_key.AppAllKey;
import com.mding.chatfeng.base_common.utils.about_main_utils.HelpUtils;
import com.mding.chatfeng.base_common.utils.about_main_utils.ImageUtils;
import com.mding.chatfeng.base_common.utils.about_main_utils.IntentUtils;
import com.mding.chatfeng.base_common.utils.about_main_utils.MyJsonUtils;
import com.mding.chatfeng.base_common.utils.aboututils.ACache;
import com.mding.chatfeng.base_common.utils.aboututils.StrUtils;
import com.mding.chatfeng.home.BaseFragment;
import com.mding.chatfeng.home.R;
import com.mding.chatfeng.home.ui.about_contacts.about_search.SearchActivity;
import com.mding.chatfeng.home.ui.about_mine.ChangeInfoActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * 项目：DoubleQ
 * 文件描述：主界面FindFragment之个人中心页面
 * 作者：zll
 * 修改者：ljj
 */
public class PersonalFragment extends BaseFragment {

    TextView includeFragTvTitle;
    TextView mineTvName;
    TextView mineTvSign;
    ImageView mineIvPerson;
    ImageView mineIvAdd;
  CusLinearLayout mineLinShare;
  CusLinearLayout mineLinSet;
  CusLinearLayout mineLinOrangePocket;

    public PersonalFragment() {
    }

    ACache aCache;

    @Override
    protected int setFragmentLayout() {
        return R.layout.fragment_personal;
    }
    @Override
    protected FragmentTopBarLayout setFragmentTopBarLayout() {
        return  getView(R.id.fg_top_bar);
    }
    boolean isFirst = true;

    @Override
    protected void initBaseUI(View view) {
        super.initBaseUI(view);
        FragmentTopBarLayout mTopBar = getView(R.id.fg_top_bar);
//        mTopBar.setBackgroundColor(getResources().getColor(R.color.app_theme));
        mTopBar.setTopLinBackground(getResources().getColor(R.color.app_theme));
    }

    @Override
    public void onStart() {
        super.onStart();
        initFindUI();
        if (aCache == null)
            aCache = ACache.get(getActivity());
        if (isFirst)
            initUI();
        isFirst = false;
        // 名字加粗
        mineTvName.getPaint().setFakeBoldText(true);
        initLinearLayout();
    }

    private void initLinearLayout() {
        // 分享
        initShare();
        // 设置
        initSet();
        // 橙子口袋
        initOrangePocket();
    }

    private void initShare() {
        mineLinShare.setViewLineVisible(false);
        mineLinShare.setImgLogo(getResources().getDrawable(R.drawable.mine_share));
        mineLinShare.setTvTitle(getResources().getString(R.string.personal_share));
//        mineLinShare.setTvTitle("分享");
    }
    private void initSet() {
        mineLinSet.setImgLogo(getResources().getDrawable(R.drawable.mine_set));
        mineLinSet.setTvTitle(getResources().getString(R.string.personal_setting));
//        mineLinSet.setTvTitle("设置");
    }

    private void initOrangePocket() {
        mineLinOrangePocket.setLinGreyBacVisible(true);
        mineLinOrangePocket.setViewLineVisible(false);
        mineLinOrangePocket.setImgLogo(getResources().getDrawable(R.drawable.mine_orange_pocket));
        mineLinOrangePocket.setTvTitle("橙子口袋");
    }

    //    修改头像
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDataSynEvent(final HeadImgInfo headImgInfo) {
        totalBase64Event = headImgInfo.getHeadImgBase64();
        ImageUtils.useBase64(getActivity(), mineIvPerson, totalBase64Event);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDataSign(final PersonInfo personInfo) {
        String name = personInfo.getNickName();
        String sign = personInfo.getSign();
        if (!StrUtils.isEmpty(name))
            mineTvName.setText(name);
        if (!StrUtils.isEmpty(sign)) {
            String signature = StrUtils.isEmpty(sign) ? getResources().getString(R.string.personal_set_signature) : sign;
            mineTvSign.setText(signature);
            if (signature.equals(getResources().getString(R.string.personal_set_signature))) {
                mineTvSign.setTextColor(getResources().getColor(R.color.greye5));
            } else {
                mineTvSign.setTextColor(getResources().getColor(R.color.grey72));
            }
        }
    }
    @Override
    protected String setFragmentTital() {
        return getResources().getString(R.string.personal_fragment);
//        return "个人中心";
    }

    @Override
    protected int setTopBarBackground() {
        return getContext().getResources().getColor(R.color.app_theme);
    }

    private void initUI() {
        initName();
    }

    private void initName() {

        String json = aCache.getAsString(AppAllKey.PPERSON_iNFO);
        Log.e("DataMyZiliao", "---个人中心DataMyZiliao----");
        if (!StrUtils.isEmpty(json)) {
            DataMyZiliao.RecordBean recordBean = new GsonParamConverter().json2Object(json, DataMyZiliao.RecordBean.class);
            if (recordBean != null) {
                String headImg = recordBean.getHeadImg();
                ImageUtils.useBase64(getActivity(), mineIvPerson, headImg);
                aCache.put(AppConfig.IMAGE_BASE64, headImg);
                mineTvName.setText(recordBean.getNickName());
                userPhone = recordBean.getMobile();
                String signature = StrUtils.isEmpty(recordBean.getPersonaSignature()) ? getResources().getString(R.string.personal_set_signature) : recordBean.getPersonaSignature();
                mineTvSign.setText(signature);
                if (signature.equals(getResources().getString(R.string.personal_set_signature))) {
                    mineTvSign.setTextColor(getResources().getColor(R.color.greye5));
                } else {
                    mineTvSign.setTextColor(getResources().getColor(R.color.grey72));
                }
            } else {
//                TODO 请求个人中心接口
//                sendWeb(SplitWeb.getSplitWeb().personalCenter());
            }
        } else {

            //                TODO 请求个人中心接口
//            sendWeb(SplitWeb.getSplitWeb().personalCenter());
        }
    }

    public static boolean isChange = false;
    public static boolean isChangeHead = false;
    String userId;
    String userPhone;

    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String method = HelpUtils.backMethod(responseText);
        switch (method) {
            case "personalCenter":
                DataMyZiliao dataMyZiliao =  new GsonParamConverter().json2Object(responseText, DataMyZiliao.class);
                DataMyZiliao.RecordBean record = dataMyZiliao.getRecord();
                if (record != null) {
                    String jsonString = MyJsonUtils.toChangeJson(record);//将java对象转换为json对象
                    aCache.put(AppAllKey.PPERSON_iNFO, jsonString);
                    String headImg = record.getHeadImg();
                    if (!StrUtils.isEmpty(headImg)) {
                        totalBase64 = headImg;
                        ImageUtils.useBase64(getActivity(), mineIvPerson, headImg);
                        aCache.put(AppConfig.IMAGE_BASE64, headImg);
                    }
                    mineTvName.setText(record.getNickName());
                    userPhone = record.getMobile();
                    String signature = StrUtils.isEmpty(record.getPersonaSignature()) ? getResources().getString(R.string.personal_set_signature) : record.getPersonaSignature();
//                    String signature = StrUtils.isEmpty(record.getPersonaSignature()) ? "你还没有设置签名哦！" : record.getPersonaSignature();
                    mineTvSign.setText(signature);
                    if (signature.equals(getResources().getString(R.string.personal_set_signature))) {
//                    if (signature.equals("你还没有设置签名哦！")) {
                        mineTvSign.setTextColor(getResources().getColor(R.color.greye5));
                    } else {
                        mineTvSign.setTextColor(getResources().getColor(R.color.grey72));
                    }
                }
                break;
        }
    }

    String totalBase64;
    String totalBase64Event;
    private void initFindUI() {
        includeFragTvTitle=getView(R.id.include_frag_tv_title);
        mineTvName=getView(R.id.mine_tv_name);
        mineTvSign=getView(R.id.mine_tv_sign);
        mineIvPerson=getView(R.id.mine_iv_person);
        mineIvAdd=getView(R.id.include_frag_img_add);
        mineLinShare=getView(R.id.mine_lin_share);
        mineLinSet=getView(R.id.mine_lin_set);
        mineLinOrangePocket=getView(R.id.mine_lin_orange_pocket);

        addOnClickListeners(R.id.mine_iv_qrcode, R.id.mine_iv_person,R.id.mine_lin_person_info,
                R.id.mine_lin_share, R.id.mine_lin_set, R.id.mine_lin_discover, R.id.mine_lin_orange_pocket);

//        getView(R.id.mine_lin_discover).setOnClickListener(this);
//        getView(R.id.mine_lin_orange_pocket).setOnClickListener(this);
//        getView(R.id.mine_iv_qrcode).setOnClickListener(this);
//        getView(R.id.mine_iv_person).setOnClickListener(this);
////        getView(R.id.include_frag_img_search).setOnClickListener(this);
//        getView(R.id.mine_lin_person_info).setOnClickListener(this);
//        getView(R.id.mine_lin_share).setOnClickListener(this);
//        getView(R.id.mine_lin_set).setOnClickListener(this);
    }

//    @OnClick({R.id.mine_iv_qrcode, R.id.mine_iv_person, R.id.include_frag_img_search, R.id.mine_lin_person_info,
//            R.id.mine_lin_share, R.id.mine_lin_set, R.id.mine_lin_discover, R.id.mine_lin_orange_pocket})

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.mine_iv_person) {
            jumpBigImg();
        } else if (i == R.id.include_frag_img_search) {
            IntentUtils.JumpTo(SearchActivity.class);

        } else if (i == R.id.mine_lin_person_info) {
            IntentUtils.JumpTo(ChangeInfoActivity.class);

        } else if (i == R.id.mine_lin_share) {
//            IntentUtils.JumpTo(MyAccountActivity.class);

        } else if (i == R.id.mine_iv_qrcode) {
//            IntentUtils.JumpTo(MyAccountActivity.class);

        } else if (i == R.id.mine_lin_set) {
//            IntentUtils.JumpToHaveOne(MineSetActivity.class, "phone", userPhone);

        } else if (i == R.id.mine_lin_discover) {
            DialogUtils.showDialog(getResources().getString(R.string.stay_tuned));
            //                DialogUtils.showDialog(getResources().getString(R.string.stay_tuned));
        } else if (i == R.id.mine_lin_orange_pocket) {
//            IntentUtils.JumpToHaveOne(OrangePocketActivity.class, "userId", userId);

        }
    }

    private void jumpBigImg() {
        String json = aCache.getAsString(AppAllKey.PPERSON_iNFO);
        if (!StrUtils.isEmpty(json)) {
//            new GsonParamConverter().json2Object()
            DataMyZiliao.RecordBean recordBean = new GsonParamConverter().json2Object(json, DataMyZiliao.RecordBean.class);
            if (recordBean != null) {

//                IntentUtils.JumpToHaveOne(ShowFullImgActivity.class, AppConfig.IMAGE_BASE64, recordBean.getHeadImg());
            }
        }
    }
}
