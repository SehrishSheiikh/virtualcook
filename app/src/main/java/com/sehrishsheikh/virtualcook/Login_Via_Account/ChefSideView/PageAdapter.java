package com.sehrishsheikh.virtualcook.Login_Via_accountt;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.sehrishsheikh.virtualcook.Login_As_Guest.Home.HomeClass;
import com.sehrishsheikh.virtualcook.Login_Via_accountt.FirstFragment.ViewImageGallery;


import com.sehrishsheikh.virtualcook.Login_Via_accountt.W_All_Recipe_fvrt_share_Download.Fragment.FavouritesFragmen;
import com.sehrishsheikh.virtualcook.Login_Via_accountt.W_All_Recipe_fvrt_share_Download.Fragment.HomeFragment;
import com.sehrishsheikh.virtualcook.Login_Via_accountt.W_All_Recipe_fvrt_share_Download.Fragment.SettingsFragment;

public class PageAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    PageAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new ViewImageGallery();
            case 2:
                 return new HomeClass();
            case 3:
                return new FavouritesFragmen();
            case 4:
                return new SettingsFragment();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}