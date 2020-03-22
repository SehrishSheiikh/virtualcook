package com.sehrishsheikh.massenger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sehrishsheikh.massenger.Massenger_request.Friend_Rq_Class;
import com.sehrishsheikh.massenger.People.People_Class;
import com.sehrishsheikh.massenger.Setting.Setting_Class;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener
{
    //Toolbar toolbar;
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // toolbar = v.findViewById(R.id.toolbar);
        //((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        bottomNavigationView =  findViewById(R.id.navigation);
        // toolbar.setTitle("Messenger");


        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        if (savedInstanceState == null)
            changeFragment(new Friend_Rq_Class(),"Messages");

    }



    public void changeFragment(Fragment fragment, String title) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentManager fm = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.layout, fragment)
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        switch (id) {
            case R.id.navigation_msg:
                changeFragment(new Friend_Rq_Class(), "Messages");
                break;
            case R.id.navigation_ppl:
                changeFragment(new People_Class(), "People");
                break;
            case R.id.navigation_settng:
                changeFragment(new Setting_Class(), "Setting");
                break;

        }
        bottomNavigationView.setOnNavigationItemSelectedListener(null);
        bottomNavigationView.setSelectedItemId(id);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        return false;


    }
}