package com.sehrishsheikh.virtualcook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.sehrishsheikh.virtualcook.Available_Food.AvailableFood;
import com.sehrishsheikh.virtualcook.Login_As_Guest.Home.HomeClass;
import com.sehrishsheikh.virtualcook.Login_As_Guest.RecipeBox.BoxClass;
import com.sehrishsheikh.virtualcook.Login_As_Guest.Settings.SettingClass;
import com.sehrishsheikh.virtualcook.Login_As_Guest.ShoppingList.ListClass;
import com.sehrishsheikh.virtualcook.Login_As_Guest.Spinner.SpinnerClass;

public class DrawerLayoutActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    //declaration
    Toolbar toolbar;
    FrameLayout frameLayout;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //for hide top display
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //ending


        ActionBar actionBar = getActionBar();
        getSupportActionBar().hide();




        super.onCreate(savedInstanceState);
        //zgetSupportActionBar().hide();
        setContentView(R.layout.activity_drawer_layout);

        //intialization
        toolbar        = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        drawerLayout   =  findViewById(R.id.drawerlayout);
        frameLayout    =  findViewById(R.id.layout);
        navigationView =  findViewById(R.id.navgationView);


        //header layout
        View headerView = navigationView.getHeaderView(0);

        ImageView img_header = headerView.findViewById(R.id.header_img);
        TextView textView_header = headerView.findViewById(R.id.txt);

        //Intent intent=getIntent();
        // String email=intent.getStringExtra("email");
        //String name=intent.getStringExtra("name");
        //textView_header.setText(email);

        //set the name,email and the logo on the header part

        img_header.setImageResource(R.drawable.ic_account_circle_black_24dp);
        textView_header.setText("Sign in");


        //toogle button
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //navigation view
        navigationView.setNavigationItemSelectedListener(this);
        if (savedInstanceState == null)
            navigationView.getMenu().getItem(0).setChecked(true);
        changeFragment(new HomeClass() , "allrecipes");

    }
    public  void changeFragment(Fragment fragment , String title)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.layout , fragment)
                .commit();


        toolbar.setTitle(title);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
    {
        int id = menuItem.getItemId();
        switch (id)
        {
            case R.id.item1_home:
                changeFragment(new HomeClass() , "allrecipes");
                break;


            case R.id.itme2_dinnerspinner_fragment:
                changeFragment(new SpinnerClass() , "Spinner");
                break;


            case R.id.item3_recipebox:
                changeFragment(new BoxClass() , "   Inbox");
                break;


            case R.id.item4_shopinglist:
                changeFragment(new ListClass(), "Shoping List");
                break;

            case R.id.item5_settings:
                changeFragment(new SettingClass() , "Settings");
                break;

            case R.id.item6_avaliable_dishes:
                changeFragment(new AvailableFood(), "Available Food");
                break;





        }
        navigationView.setCheckedItem(id);
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;

    }

    @Override
    public void onBackPressed()
    {
        /*


        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {

            drawerLayout.closeDrawer(GravityCompat.START);

        } else {
            super.onBackPressed();
        }

   */

        new AlertDialog.Builder(DrawerLayoutActivity.this)
                .setTitle("exit")
                .setMessage("Do you want to exit?")
                .setIcon(R.mipmap.ic_launcher)
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNeutralButton("setting", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
                    }
                }).show();
        // super.onBackPressed();
    }
}

