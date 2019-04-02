package com.example.redittclient;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    //fragment list for keeping track of the fragment and title_heading.

    private final List<Fragment>  fragmentList =new ArrayList<>();
    private final List<String> fragementStringList = new ArrayList<>();

    public void addFragment(Fragment fragment,String name  )
    {
        fragmentList.add(fragment);
        fragementStringList.add(name);
    }

    public SectionsPagerAdapter(FragmentManager fm) { super(fm);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        return fragementStringList.get(position);
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }


}
