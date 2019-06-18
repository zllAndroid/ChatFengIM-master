package com.mding.chatfeng.home.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.mding.chatfeng.base_common.utils.about_custom.CustomViewPager;
import com.mding.chatfeng.home.BaseFragment;
import com.mding.chatfeng.home.R;
import com.mding.chatfeng.home.ui.about_contacts.ContactChildFragment;
import com.mding.chatfeng.home.ui.about_contacts.cus_model.ModelbyMissABC;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;


/**
 * 项目：DoubleQ
 * 文件描述：主界面FindFragment之联系人页面
 * 作者：zll
 * 修改者：ljj
 */
public class LinkManFragment extends BaseFragment {

    public LinkManFragment() {}
    //    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(true);
//        if (this.getView() != null)
//            this.getView().setVisibility(isVisibleToUser ? View.VISIBLE : View.GONE);
//    }
    CustomViewPager mViewpager;
    private SmartTabLayout mSmartTab;

    @Override
    protected int setFragmentLayout() {
        return R.layout.fragment_contacts;
    }
    @Override
    protected void initBaseUI(View view) {
        super.initBaseUI(view);
        initUI(view);
    }
    boolean ismPager = true;
    private MyPagerAdapter myPagerAdapter;

    @Override
    protected String setFragmentTital() {
        return getResources().getString(R.string.contacts_fragment);
//        return "联系人";
    }
    public static int isChangePage = 0;
    private void initUI(View mView) {
//        getData()
        mSmartTab = mView.findViewById(R.id.contacts_viewpagertab);
        mViewpager = mView.findViewById(R.id.contacts_viewpager);
        mSmartTab.setViewPager(mViewpager);
//        initData();
        initViewPagerAdapter();
    }
    List<String> mStr;
        private void getData() {
        mStr = new ArrayList<>();
        mStr.clear();
        mStr.add(getResources().getString(R.string.contacts_friend));
        mStr.add(getResources().getString(R.string.contacts_group));
    }
    private void initViewPagerAdapter() {
//        myPagerAdapter = new MyPagerAdapter(getChildFragmentManager());
        String[] title = getResources().getStringArray(R.array.linkman_top_name);
        if (myPagerAdapter==null) {
                myPagerAdapter = new MyPagerAdapter(getActivity().getSupportFragmentManager(), title);
        }
        mViewpager.setAdapter(myPagerAdapter);
        mSmartTab.setViewPager(mViewpager);
        if (ismPager) {
            ismPager = !ismPager;
            mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int i, float v, int i1) {
                }
                @Override
                public void onPageSelected(int i) {
                    isChangePage = i;
                    ModelbyMissABC modelbyMissABC = new ModelbyMissABC();
                    modelbyMissABC.setMissWho(i);
                    EventBus.getDefault().post(modelbyMissABC);
                }
                @Override
                public void onPageScrollStateChanged(int i) {
                }
            });
        }
    }
    private final class MyPagerAdapter extends FragmentPagerAdapter
    {
        //FragmentStatePagerAdapter
        // FragmentPagerAdapter
        //两者的区别在于一个状态FragmentStatePagerAdapter表示只创建一个PageView
        // FragmentPagerAdapter一次性全部创建
        String[] mTitle;
        public MyPagerAdapter(FragmentManager fm,   String[] title ) {
//        public MyPagerAdapter(FragmentManager fm, ArrayList<SmartTabInfo> fragments) {
            super(fm);
            this.mTitle=title;
        }
        @Override
        public Fragment getItem(int position) {
            ContactChildFragment fragment = new ContactChildFragment();
            Bundle args = new Bundle();
            args.putInt("position", position);
            args.putString("text", mTitle[position]);
//            args.putString("text", mFragments.get(position).getTitle());
            fragment.setArguments(args);
            return fragment;
//            return mFragments.get(position).getClz();
        }
        @Override
        public int getCount() {
            return mTitle.length;
//            return mFragments.size();
        }
        //返回页面标题
        @Override
        public CharSequence getPageTitle(int position) {
            return mTitle[position];
//            return mFragments.get(position).getTitle();
        }
    }
}
