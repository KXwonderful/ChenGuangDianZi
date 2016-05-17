package com.wonderful.chenguangdianzi.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by KXwon on 2016/3/18.
 */
public class MyFragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;
    public MyFragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        // TODO Auto-generated constructor stub

        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int arg0) {
        // TODO Auto-generated method stub
        return fragmentList.get(arg0);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return fragmentList.size();
    }
}
