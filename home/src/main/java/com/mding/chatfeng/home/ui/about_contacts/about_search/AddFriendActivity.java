package com.mding.chatfeng.home.ui.about_contacts.about_search;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mding.chatfeng.base_common.components.base.BaseActivity;
import com.mding.chatfeng.base_common.utils.about_custom.CustomViewPager;
import com.mding.chatfeng.home.R;
import com.mding.chatfeng.home.ui.about_contacts.add_friend.AddFriendsFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;


public class AddFriendActivity extends BaseActivity {

    TextView includeTopTvTitle;
    LinearLayout mLinBack;
    SmartTabLayout viewPagerTab;
    CustomViewPager mViewpager;
    private void initViewUI() {
        includeTopTvTitle=getView(R.id.include_top_tv_title);
        mLinBack=getView(R.id.include_top_lin_back);
        viewPagerTab=getView(R.id.add_friend_viewpagertab);
        mViewpager=getView(R.id.add_friend_viewpager);
    }
    boolean ismPager = true;
    private MyPagerAdapter myPagerAdapter;


    @Override
    protected void initBaseView() {
        super.initBaseView();
        initViewUI();
        includeTopTvTitle.setText("添加");
        mLinBack.setBackgroundColor(getResources().getColor(R.color.app_theme));
        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        if (ismPager)
        {
            ismPager = !ismPager;
            mViewpager.setAdapter(myPagerAdapter);
            viewPagerTab.setViewPager(mViewpager);
        }
    }



    private String[] contacts_tou = new String[]{"找人","找群"};
    @Override
    protected int getLayoutView() {
        return R.layout.activity_add_friend;
    }
    private final class MyPagerAdapter extends FragmentPagerAdapter
    {
        public CharSequence getPageTitle(int position)
        {
            return contacts_tou[position];
        }

        private MyPagerAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public int getCount()
        {
            return contacts_tou.length;
        }

        @Override
        public Fragment getItem(int position)
        {
            AddFriendsFragment fragment = new AddFriendsFragment();
            Bundle args = new Bundle();
            args.putInt("position", position);
            args.putString("text", contacts_tou[position]);
            fragment.setArguments(args);
            return fragment;
        }
    }
}
