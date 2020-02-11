package com.sehrishsheikh.virtualcook;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class Pager_Adapter extends PagerAdapter
{
    Context context; //use to add contect wherever context is required
    LayoutInflater layoutInflater;

    public Pager_Adapter(Context context) {
        this.context = context;

    }

    //this array will store the value for our silder
    public  int[] slide_images = {
            R.drawable.imag2,
            R.drawable.img1,
            R.drawable.pg1
    };

    //this array is for our headings
    public String[] slide_headings_1 =
            {
                    "What is Your ",
                    "What is Your ",
                    "Enjoy "

            };
    //this array is for our headings
    public String[] slide_headings_2 =
            {
                    "diet program ?",
                    "favorite food",
                    "the food"

            };



    //this array is for our discriptions
    public  String[] slide_description_1 =
            {
                    "Choose diet program between loss fat and muscles",
                    "Finish your order ,wait for a minute for your order " ,
                    "There is lot of food category that you can "
            };

    public String[] slide_description_2 =
            {
                    "gain program to tell us what meal do you need",
                    "deliver by our counter",
                    "choose your favorite"


            };
    //ana array will be made for the background if u want

    @Override
    public int getCount() {
        return slide_headings_1.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view ==  o;
    }

    //method used for those sider effects , inflate these things into adapter
    @SuppressLint("ResourceAsColor")
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        //calling view that inflate that view into our slide layout
        View view = layoutInflater.inflate(R.layout.pager_layout , container , false);

        //intialize imageviews and textview in our slider
        ImageView slider_imageview = view.findViewById(R.id.slide_img);
        TextView slider_textview1 = view.findViewById(R.id.pg_heading_1);
        TextView slider_textview4 = view.findViewById(R.id.pg_heading_2);
        TextView slider_textview2 = view.findViewById(R.id.pg_description_1);
        TextView slider_textview3 = view.findViewById(R.id.pg_description_2);

        //set some values
        slider_imageview.setImageResource(slide_images[position]);   ///pass array
        slider_textview1.setText(slide_headings_1[position]);          //pass array
        slider_textview4.setText(slide_headings_2[position]);          //pass array
        slider_textview2.setText(slide_description_1[position]);       //pass array
        slider_textview3.setText(slide_description_2[position]);       //pass array
        //slider_textview1.setTextColor(R.color.pink);

        container.addView(view);

        return view;
    }

    //this method is fot stop after the last page //also prevent from errors
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object)
    {
        container.removeView((View) object);
    }
}
