package com.sehrishsheikh.virtualcook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity {
    TextView tv1,tv2;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //for hide top display
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //ending

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getActionBar();
        getSupportActionBar().hide();
        //OR getSupportActionBar();
//        actionBar.hide();

        getSupportActionBar().hide();

        //getActionBar().hide();
        //intialization
        tv1 = findViewById(R.id.tv_splash1);
        tv2 = findViewById(R.id.tv_splash2);
        img = findViewById(R.id.img_splash);


        //animation code
        Animation myanim = AnimationUtils.loadAnimation(this , R.anim.mytransition);
        tv1.startAnimation(myanim);
        tv2.startAnimation(myanim);
        img.startAnimation(myanim);
        //end


        final Intent intent = new Intent(this ,Pager.class);

        Thread timer = new Thread(){
            public  void run(){
                try
                {
                    sleep(4000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    startActivity(intent);
                    finish();

                }
            }
        };
        timer.start();
    }

}
