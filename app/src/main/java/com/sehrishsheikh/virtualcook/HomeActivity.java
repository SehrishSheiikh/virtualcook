package com.sehrishsheikh.virtualcook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.sehrishsheikh.virtualcook.Login_Via_Account.ChefSideView.BNV;

public class HomeActivity extends AppCompatActivity
{
    Button btn_insert;
    Button btn_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //for hide top display
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //ending

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);


        ActionBar actionBar = getActionBar();
        getSupportActionBar().hide();


        btn_insert = findViewById(R.id.btn_insert);
        btn_menu = findViewById(R.id.btn_menu);

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this , BNV.class);
                startActivity(intent);
                finish();
            }
        });

        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this , DrawerLayoutActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }



    public  void logout(View view)
    {
        FirebaseAuth.getInstance().signOut();               //logout of the user and once the user is logout he will move to login activity
        startActivity(new Intent(getApplicationContext() , LoginActivity.class));
        finish();
    }


}
