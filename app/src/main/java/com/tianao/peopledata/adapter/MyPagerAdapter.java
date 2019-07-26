package com.tianao.peopledata.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class MyPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mfragmentList;
    public MyPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.mfragmentList = fragmentList;
    }

    @Override
    public int getCount() {
        return mfragmentList.size();
    }

    @Override
    public Fragment getItem(int i) {
        return mfragmentList.get(i);
    }

}