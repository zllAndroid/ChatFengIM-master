package com.mding.chatfeng.home.fragment;

import android.view.View;

import com.mding.chatfeng.base_common.utils.about_custom.about_linearlayout.CusLinearLayout;
import com.mding.chatfeng.base_common.utils.about_dialog.DialogUtils;
import com.mding.chatfeng.home.BaseFragment;
import com.mding.chatfeng.home.R;


/**
 * 项目：DoubleQ
 * 文件描述：主界面FindFragment之发现页面
 * 作者：zll
 */
public class FindFragment extends BaseFragment {
  CusLinearLayout discoverLinFriendcircle;
  CusLinearLayout discoverLinMusicList;
    CusLinearLayout discoverLinAnimalFuhua;
   CusLinearLayout discoverLinAi;
   CusLinearLayout discoverLinAnimalShop;
   CusLinearLayout discoverLinAnimalLife;
   CusLinearLayout discoverLinYuyanjia;
   CusLinearLayout discoverLinGaoBaiQiang;
   CusLinearLayout discoverLinZhiAddFriend;
   CusLinearLayout discoverLinAppStore;
   CusLinearLayout discoverLinMusic;
   CusLinearLayout discoverLinShiYong;
   CusLinearLayout discoverLinFangWei;
   CusLinearLayout discoverLinGuangGao;

    public FindFragment() {
    }
//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(true);
//        if (this.getView() != null)
//            this.getView().setVisibility(isVisibleToUser ? View.VISIBLE : View.GONE);
//    }
//@Override
//protected FragmentTopBarLayout setFragmentTopBarLayout() {
//    return  getView(R.id.fg_top_bar);
//}
    @Override
    protected int setFragmentLayout() {
        return R.layout.fragment_discovery;
    }

    @Override
    protected void searchClickEvent() {
        // TODO  search
//        JumpTo(LoadLinkManActivity.class);
    }

    @Override
    protected void initBaseUI(View view) {
        super.initBaseUI(view);
        initViewUI();
        initUI(view);
    }

    private void initViewUI() {
        discoverLinFriendcircle= getView(R.id.discover_lin_friendcircle);
        discoverLinMusicList= getView(R.id.discover_lin_music_list);
        discoverLinAnimalFuhua= getView(R.id.discover_lin_animal_fuhua);
        discoverLinAi= getView(R.id.discover_lin_ai);
        discoverLinAnimalShop= getView(R.id.discover_lin_animal_shop);
        discoverLinAnimalLife= getView(R.id.discover_lin_animal_life);
        discoverLinYuyanjia= getView(R.id.discover_lin_yuyanjia);
        discoverLinGaoBaiQiang= getView(R.id.discover_lin_gaoBaiQiang);
        discoverLinZhiAddFriend= getView(R.id.discover_lin_zhi_addFriend);
        discoverLinAppStore= getView(R.id.discover_lin_app_store);
        discoverLinMusic= getView(R.id.discover_lin_music);
        discoverLinShiYong= getView(R.id.discover_lin_shiYong);
        discoverLinFangWei= getView(R.id.discover_lin_fangWei);
        discoverLinGuangGao= getView(R.id.discover_lin_guangGao);

        addOnClickListeners(R.id.discover_lin_friendcircle, R.id.discover_lin_animal_fuhua, R.id.discover_lin_ai,
                R.id.discover_lin_animal_shop, R.id.discover_lin_animal_life, R.id.discover_lin_yuyanjia,
                R.id.discover_lin_gaoBaiQiang, R.id.discover_lin_zhi_addFriend, R.id.discover_lin_app_store,
                R.id.discover_lin_music, R.id.discover_lin_shiYong, R.id.discover_lin_fangWei, R.id.discover_lin_guangGao);
        /*
        getView(R.id.discover_lin_friendcircle).setOnClickListener(this);
        getView(R.id.discover_lin_animal_fuhua).setOnClickListener(this);
        getView(R.id.discover_lin_ai).setOnClickListener(this);
        getView(R.id.discover_lin_animal_shop).setOnClickListener(this);
        getView(R.id.discover_lin_animal_life).setOnClickListener(this);
        getView(R.id.discover_lin_yuyanjia).setOnClickListener(this);
        getView(R.id.discover_lin_gaoBaiQiang).setOnClickListener(this);
        getView(R.id.discover_lin_zhi_addFriend).setOnClickListener(this);
        getView(R.id.discover_lin_app_store).setOnClickListener(this);
        getView(R.id.discover_lin_music).setOnClickListener(this);
        getView(R.id.discover_lin_shiYong).setOnClickListener(this);
        getView(R.id.discover_lin_fangWei).setOnClickListener(this);
        getView(R.id.discover_lin_guangGao).setOnClickListener(this);
         */
    }

    @Override
    protected String setFragmentTital() {
        return getResources().getString(R.string.discover_title);
//        return "朋友圈";
    }

    private void initUI(final View view) {
        initLinearLaout();
//        view.findViewById(R.id.include_frag_img_add).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (confirmPopWindow==null)
//                    confirmPopWindow = new ConfirmPopWindow(getActivity());
//                confirmPopWindow.showAtBottom(view.findViewById(R.id.include_frag_img_add));
//            }
//        });
//        view.findViewById(R.id.include_frag_img_search).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                IntentUtils.JumpTo(SearchActivity.class);
//            }
//        });
//        //        朋友圈
//        view.findViewById(R.id.discover_lin_friendcircle).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), FriendCircleActivity.class));
//            }
//        });
//        view.findViewById(R.id.find_fuhua).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), ZllActivity.class));
//            }
//        });
    }

    private void initLinearLaout() {
        // 朋友圈
        initDiscover();
        // 我的歌单
        initMusicList();

//        // 宠物孵化基地
//        initAnimalFuHua();
//        // AI宠物学舌
//        initAI();
//        // 宠物商城
//        initAnimalShop();
//        // 宠物生涯
//        initAnimalLife();
//
//        // 我是预言家
//        initYuYanJia();
//        // 世界告白墙
//        initGaoBaiWall();
//        // 吱一吱，加好友
//        initZhiAddFriend();
//        // 应用商城
//        initAppStore();
//
//        // 中国好音乐
//        initMusic();
//        // 周周试用免单
//        initShiYong();
//        // 防伪溯源鉴定
//        initFangWei();
//        // 广告墙
//        initGuangGaoWall();

    }

    private void initDiscover() {
        discoverLinFriendcircle.setImgLogo(getResources().getDrawable(R.drawable.discover_friendcircle));
        discoverLinFriendcircle.setTvTitle(getResources().getString(R.string.discover_discover));
//        discoverLinFriendcircle.setTvTitle("朋友圈");
    }

    private void initMusicList() {
        discoverLinMusicList.setImgLogo(getResources().getDrawable(R.drawable.discover_music_list));
        discoverLinMusicList.setTvTitle(getResources().getString(R.string.discover_music_list));
//        discoverLinAi.setTvTitle("AI宠物学舌");
    }

    private void initAnimalFuHua() {
        discoverLinAnimalFuhua.setLinGreyBacVisible(true);
        discoverLinAnimalFuhua.setViewLineVisible(false);
        discoverLinAnimalFuhua.setImgLogo(getResources().getDrawable(R.drawable.discover_animal_fuhua));
        discoverLinAnimalFuhua.setTvTitle(getResources().getString(R.string.discover_discover));
    }
    private void initAI() {
        discoverLinAi.setImgLogo(getResources().getDrawable(R.drawable.discover_ai));
        discoverLinAi.setTvTitle(getResources().getString(R.string.discover_pet_ai));
//        discoverLinAi.setTvTitle("AI宠物学舌");
    }
    private void initAnimalShop() {
        discoverLinAnimalShop.setImgLogo(getResources().getDrawable(R.drawable.discover_animal_shop));
        discoverLinAnimalShop.setTvTitle(getResources().getString(R.string.discover_pet_shop));
//        discoverLinAnimalShop.setTvTitle("宠物商城");
    }
    private void initAnimalLife() {
        discoverLinAnimalLife.setImgLogo(getResources().getDrawable(R.drawable.discover_animal_life));
        discoverLinAnimalLife.setTvTitle(getResources().getString(R.string.discover_pet_life));
//        discoverLinAnimalLife.setTvTitle("宠物生涯");
    }

    private void initYuYanJia() {
        discoverLinYuyanjia.setLinGreyBacVisible(true);
        discoverLinYuyanjia.setViewLineVisible(false);
        discoverLinYuyanjia.setImgLogo(getResources().getDrawable(R.drawable.discover_yuyanjia));
        discoverLinYuyanjia.setTvTitle(getResources().getString(R.string.discover_predictor));
//        discoverLinYuyanjia.setTvTitle("我是预言家");
    }
    private void initGaoBaiWall() {
        discoverLinGaoBaiQiang.setImgLogo(getResources().getDrawable(R.drawable.discover_gaobai));
        discoverLinGaoBaiQiang.setTvTitle(getResources().getString(R.string.discover_gaobai_wall));
//        discoverLinGaoBaiQiang.setTvTitle("世界告白墙");
    }
    private void initZhiAddFriend() {
        discoverLinZhiAddFriend.setImgLogo(getResources().getDrawable(R.drawable.discover_zhi_addfriend));
        discoverLinZhiAddFriend.setTvTitle(getResources().getString(R.string.discover_zhi_add_friend));
//        discoverLinZhiAddFriend.setTvTitle("吱一吱，加好友");
    }
    private void initAppStore() {
        discoverLinAppStore.setImgLogo(getResources().getDrawable(R.drawable.discover_shop));
        discoverLinAppStore.setTvTitle(getResources().getString(R.string.discover_app_store));
//        discoverLinAppStore.setTvTitle("应用商城");
    }

    private void initMusic() {
        discoverLinMusic.setLinGreyBacVisible(true);
        discoverLinMusic.setViewLineVisible(false);
        discoverLinMusic.setImgLogo(getResources().getDrawable(R.drawable.discover_music));
        discoverLinMusic.setTvTitle(getResources().getString(R.string.discover_music));
//        discoverLinMusic.setTvTitle("中国好音乐");
    }
    private void initShiYong() {
        discoverLinShiYong.setImgLogo(getResources().getDrawable(R.drawable.discover_shiyong));
        discoverLinShiYong.setTvTitle(getResources().getString(R.string.discover_shiyong));
//        discoverLinShiYong.setTvTitle("周周试用免单");
    }
    private void initFangWei() {
        discoverLinFangWei.setImgLogo(getResources().getDrawable(R.drawable.discover_fangwei));
        discoverLinFangWei.setTvTitle(getResources().getString(R.string.discover_jianding));
//        discoverLinFangWei.setTvTitle("防伪溯源鉴定");
    }
    private void initGuangGaoWall() {
        discoverLinGuangGao.setImgLogo(getResources().getDrawable(R.drawable.discover_guanggao));
        discoverLinGuangGao.setTvTitle(getResources().getString(R.string.discover_ad_wall));
//        discoverLinGuangGao.setTvTitle("广告墙");
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int i = view.getId();
        if (i == R.id.discover_lin_friendcircle) {
            DialogUtils.showDialog(getResources().getString(R.string.stay_tuned));

        } else if (i == R.id.discover_lin_music_list) {
            DialogUtils.showDialog(getResources().getString(R.string.stay_tuned));

        } else if (i == R.id.discover_lin_animal_fuhua) {
            DialogUtils.showDialog(getResources().getString(R.string.stay_tuned));

        } else if (i == R.id.discover_lin_ai) {
            DialogUtils.showDialog(getResources().getString(R.string.stay_tuned));

        } else if (i == R.id.discover_lin_animal_shop) {
            DialogUtils.showDialog(getResources().getString(R.string.stay_tuned));

        } else if (i == R.id.discover_lin_animal_life) {
            DialogUtils.showDialog(getResources().getString(R.string.stay_tuned));

        } else if (i == R.id.discover_lin_yuyanjia) {
            DialogUtils.showDialog(getResources().getString(R.string.stay_tuned));

        } else if (i == R.id.discover_lin_gaoBaiQiang) {
            DialogUtils.showDialog(getResources().getString(R.string.stay_tuned));

        } else if (i == R.id.discover_lin_zhi_addFriend) {
            DialogUtils.showDialog(getResources().getString(R.string.stay_tuned));

        } else if (i == R.id.discover_lin_app_store) {
            DialogUtils.showDialog(getResources().getString(R.string.stay_tuned));

        } else if (i == R.id.discover_lin_music) {
            DialogUtils.showDialog(getResources().getString(R.string.stay_tuned));

        } else if (i == R.id.discover_lin_shiYong) {
            DialogUtils.showDialog(getResources().getString(R.string.stay_tuned));

        } else if (i == R.id.discover_lin_fangWei) {
            DialogUtils.showDialog(getResources().getString(R.string.stay_tuned));

        } else if (i == R.id.discover_lin_guangGao) {
            DialogUtils.showDialog(getResources().getString(R.string.stay_tuned));

        }
    }

//    @OnClick({R.id.discover_lin_friendcircle, R.id.discover_lin_animal_fuhua, R.id.discover_lin_ai, R.id.discover_lin_animal_shop, R.id.discover_lin_animal_life, R.id.discover_lin_yuyanjia, R.id.discover_lin_gaoBaiQiang, R.id.discover_lin_zhi_addFriend, R.id.discover_lin_app_store, R.id.discover_lin_music, R.id.discover_lin_shiYong, R.id.discover_lin_fangWei, R.id.discover_lin_guangGao})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.discover_lin_friendcircle:
//                DialogUtils.showDialog(getResources().getString(R.string.notice_waiting));
//                break;
//            case R.id.discover_lin_animal_fuhua:
//                DialogUtils.showDialog(getResources().getString(R.string.stay_tuned));
//                break;
//            case R.id.discover_lin_ai:
//                DialogUtils.showDialog(getResources().getString(R.string.stay_tuned));
//                break;
//            case R.id.discover_lin_animal_shop:
//                DialogUtils.showDialog(getResources().getString(R.string.stay_tuned));
//                break;
//            case R.id.discover_lin_animal_life:
//                DialogUtils.showDialog(getResources().getString(R.string.stay_tuned));
//                break;
//            case R.id.discover_lin_yuyanjia:
//                DialogUtils.showDialog(getResources().getString(R.string.stay_tuned));
//                break;
//            case R.id.discover_lin_gaoBaiQiang:
//                DialogUtils.showDialog(getResources().getString(R.string.stay_tuned));
//                break;
//            case R.id.discover_lin_zhi_addFriend:
//                DialogUtils.showDialog(getResources().getString(R.string.stay_tuned));
//                break;
//            case R.id.discover_lin_app_store:
//                DialogUtils.showDialog(getResources().getString(R.string.stay_tuned));
//                break;
//            case R.id.discover_lin_music:
//                DialogUtils.showDialog(getResources().getString(R.string.stay_tuned));
//                break;
//            case R.id.discover_lin_shiYong:
//                DialogUtils.showDialog(getResources().getString(R.string.stay_tuned));
//                break;
//            case R.id.discover_lin_fangWei:
//                DialogUtils.showDialog(getResources().getString(R.string.stay_tuned));
//                break;
//            case R.id.discover_lin_guangGao:
//                DialogUtils.showDialog(getResources().getString(R.string.stay_tuned));
//                break;
//        }
//    }
}
