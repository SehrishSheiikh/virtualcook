package com.sehrishsheikh.virtualcook.Login_Via_Account.ChefSideView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sehrishsheikh.virtualcook.Login_Via_Account.ChefSideView.FirstFragment.ChefPostActivity;
import com.sehrishsheikh.virtualcook.Login_Via_Account.ChefSideView.SecondFragment.ViewImageGallery;
import com.sehrishsheikh.virtualcook.Login_Via_Account.ChefSideView.ThirdFragment.SettingClass;
import com.sehrishsheikh.virtualcook.R;

public class BNV extends AppCompatActivity
{


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chef_side_bnv);


        ActionBar actionBar = getActionBar();
        getSupportActionBar().hide();
        //OR getSupportActionBar();
//        actionBar.hide();

        getSupportActionBar().hide();

        //getActionBar().hide();

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new ViewImageGallery()).commit();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId())
                    {
                        case R.id.nav_favorites:
                            selectedFragment = new ViewImageGallery();
                            break;

                        case R.id.nav_home:
                            selectedFragment = new ChefPostActivity();
                            break;

                        case R.id.nav_search:
                            selectedFragment = new SettingClass();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };
}