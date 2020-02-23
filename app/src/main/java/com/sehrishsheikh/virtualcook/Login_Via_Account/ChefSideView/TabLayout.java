package com.sehrishsheikh.virtualcook.Login_Via_accountt;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.os.Build;
import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
import com.sehrishsheikh.virtualcook.R;

public class TabLayout extends AppCompatActivity
{

    //Toolbar toolbar;
    com.google.android.material.tabs.TabLayout tabLayout;
    ViewPager viewPager;
    PageAdapter pageAdapter;
    TabItem tabChats;
    TabItem tabStatus;
    TabItem tabCalls;
    TabItem tabCallss;
    TabItem tabCallsss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);



        //toolbar =  findViewById(R.id.toolbar);


        // toolbar.setTitle(getResources().getString(R.string.app_name));
        //  setSupportActionBar(toolbar);

        tabLayout = findViewById(R.id.tablayout);
        tabChats = findViewById(R.id.tabChats);
        tabStatus = findViewById(R.id.tabStatus);
        tabCalls = findViewById(R.id.tabCalls);
        tabCallss = findViewById(R.id.tabCallss);
        tabCallsss = findViewById(R.id.tabCallsss);
        viewPager = findViewById(R.id.viewPager);

        pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);
        viewPager.addOnPageChangeListener(new com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new com.google.android.material.tabs.TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(com.google.android.material.tabs.TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 1) {
                    // toolbar.setBackgroundColor(ContextCompat.getColor(MainActivity.this,
                    // R.color.colorAccent));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(TabLayout.this,
                            R.color.lilwhite));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(TabLayout.this,
                                R.color.lilwhite));
                    }
                } else if (tab.getPosition() == 2) {
                    // toolbar.setBackgroundColor(ContextCompat.getColor(MainActivity.this,
                    // android.R.color.darker_gray));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(TabLayout.this,
                            R.color.lilwhite));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(TabLayout.this,
                                R.color.lilwhite));
                    }

                }
                else if (tab.getPosition() == 3) {
                    // toolbar.setBackgroundColor(ContextCompat.getColor(MainActivity.this,
                    // android.R.color.darker_gray));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(TabLayout.this,
                            R.color.lilwhite));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(TabLayout.this,
                                R.color.lilwhite));
                    }

                }

                else if (tab.getPosition() == 4) {
                    // toolbar.setBackgroundColor(ContextCompat.getColor(MainActivity.this,
                    // android.R.color.darker_gray));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(TabLayout.this,
                            R.color.lilwhite));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(TabLayout.this,
                                R.color.lilwhite));
                    }

                }



                else {
                    //toolbar.setBackgroundColor(ContextCompat.getColor(MainActivity.this,
                    //   R.color.colorPrimary));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(TabLayout.this,
                            R.color.lilwhite));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(TabLayout.this,
                                R.color.lilwhite));
                    }
                }
            }

            @Override
            public void onTabUnselected(com.google.android.material.tabs.TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(com.google.android.material.tabs.TabLayout.Tab tab) {

            }
        });


    }
}
