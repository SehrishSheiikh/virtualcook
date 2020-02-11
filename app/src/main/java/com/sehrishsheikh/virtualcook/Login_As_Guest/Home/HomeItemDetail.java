package com.sehrishsheikh.virtualcook.Login_As_Guest.Home;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.sehrishsheikh.virtualcook.R;

public class HomeItemDetail extends AppCompatActivity {
    TextView myTitleTV , myDetailTV;
    ImageView myImageViewIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_item_detail);


        //action bar
        //ActionBar actionBar = getSupportActionBar();
        //set title to action bar
//        actionBar.setTitle("Post Details");

        //set back button in action bar
//        actionBar.setDisplayHomeAsUpEnabled(true);
  //      actionBar.setDisplayShowHomeEnabled(true);

        //intailize views
        myTitleTV = findViewById(R.id.rTitleTv_TVID);
        myDetailTV = findViewById(R.id.rDescription_TVID);
        myImageViewIV = findViewById(R.id.rImageView_IVID);

        //get data from intent
        byte[] bytes = getIntent().getByteArrayExtra("image");
        String title = getIntent().getStringExtra("title");
        String desc = getIntent().getStringExtra("chef");
        Bitmap bmp = BitmapFactory.decodeByteArray(bytes , 0 ,bytes.length);

        //set data to views
        myTitleTV.setText(title);
        myDetailTV.setText(desc);
        myImageViewIV.setImageBitmap(bmp);













    }

    //handle the back press(go to the previous activity)


    @Override
    public boolean onSupportNavigateUp()
    {
        onBackPressed();
        return true;
    }
}
