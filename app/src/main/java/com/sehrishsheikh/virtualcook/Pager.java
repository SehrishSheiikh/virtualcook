package com.sehrishsheikh.virtualcook;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class Pager extends AppCompatActivity
{

    private ViewPager myViewPager;
    private LinearLayout myLinearLayout;
    private Pager_Adapter myslideradapter;
    private  TextView[]  mydots;                    //array will store all the dots

    //intial button
    private  TextView tv_skip;
    private Button btn_next;
    private  Button btn_prev;
    private TextView tv_finish;


    private int mcCurrentpg;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);

        ActionBar actionBar = getActionBar();
        getSupportActionBar().hide();


        myViewPager = findViewById(R.id.view_pager_id);
        myLinearLayout = findViewById(R.id.dots_layout);
        btn_next = findViewById(R.id.btn_forward);
        btn_prev = findViewById(R.id.btn_prev);
        tv_skip = findViewById(R.id.tv_pg_skip);
        tv_finish = findViewById(R.id.tv_finish);

        tv_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , LoginActivity.class);
            }
        });

        myslideradapter = new Pager_Adapter(this);
        //set slider_adapter on main activity
        myViewPager.setAdapter(myslideradapter);

        //call the addDotsIndicator
        addDotsIndicator(0);
        myViewPager.addOnPageChangeListener(viewListener);

        //ON CLICK LISTENER
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewPager.setCurrentItem(mcCurrentpg + 1);
            }
        });
        btn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewPager.setCurrentItem(mcCurrentpg - 1);
            }
        });

        tv_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewPager.setCurrentItem(mcCurrentpg + 1);
                startActivity(new Intent(getApplicationContext() , LoginActivity.class));
            }
        });
    }




    //DOTS CODE(Which count the no. of items)
    public void addDotsIndicator(int position)
    {
        //it will actually count the number of items which we manually assign it
        mydots = new TextView[3];           //three dots one for each page
        myLinearLayout.removeAllViews();
        for (int i = 0 ; i< mydots.length; i++)
        {
            mydots[i] = new TextView(this);
            mydots[i].setText(Html.fromHtml("&#8226;"));    //font of some size
            mydots[i].setTextSize(55);
            mydots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));

            //now add these dots in our dot layout
            myLinearLayout.addView(mydots[i]);  //->now call the method obove


        }
        if (mydots.length > 0)
        {
            mydots[position].setTextColor(getResources().getColor(R.color.graydark));
        }
    }

    //which page is currently on
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onPageSelected(int position) {

            addDotsIndicator(position);
            mcCurrentpg = position;
            if (position == 0) {
                btn_next.setEnabled(true);
                btn_prev.setEnabled(false);
                tv_skip.setEnabled(false);

                btn_next.setVisibility(View.VISIBLE);
                btn_prev.setVisibility(View.INVISIBLE);
                tv_skip.setVisibility(View.INVISIBLE);


                btn_next.setText("Next");
                btn_prev.setText("");
                tv_skip.setText("");
            }
            else if (position == mydots.length -1)             //-1 is for when page  starts with 0 counting
            {
                btn_next.setEnabled(true);
                btn_prev.setEnabled(true);
                tv_skip.setEnabled(true);

                btn_prev.setVisibility(View.INVISIBLE);
                btn_next.setVisibility(View.INVISIBLE);
                tv_skip.setVisibility(View.VISIBLE);

                btn_next.setText("Finish");
                btn_prev.setText("Back");


            }

            else {
                btn_next.setEnabled(true);
                btn_prev.setEnabled(true);
                tv_skip.setEnabled(true);

                btn_next.setVisibility(View.VISIBLE);
                btn_prev.setVisibility(View.INVISIBLE);
                tv_skip.setVisibility(View.INVISIBLE);

                btn_next.setText("Next");
                btn_prev.setText("back");

            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }

    };



}

