package com.sehrishsheikh.virtualcook.Login_Via_accountt.W_All_Recipe_fvrt_share_Download.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sehrishsheikh.virtualcook.Login_Via_accountt.W_All_Recipe_fvrt_share_Download.Models.Category;
import com.sehrishsheikh.virtualcook.Login_Via_accountt.W_All_Recipe_fvrt_share_Download.WallpapersActivity;
import com.sehrishsheikh.virtualcook.R;

import java.util.List;

public class CategoriesAdapter  extends RecyclerView.Adapter< com.sehrishsheikh.virtualcook.Login_Via_accountt.W_All_Recipe_fvrt_share_Download.adapter.CategoriesAdapter.CategoryViewHolder> {

    private Context mCtx;
    private List<Category> categoryList;

//private InterstitialAd mInterstitialAd;

    public CategoriesAdapter(Context mCtx, List<Category> categoryList) {
        this.mCtx = mCtx;
        this.categoryList = categoryList;

        // mInterstitialAd = new InterstitialAd(mCtx);
        //mInterstitialAd.setAdUnitId("ca-app-pub-6677140660293215/9762844227");
        //mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }


    @Override
    public  com.sehrishsheikh.virtualcook.Login_Via_accountt.W_All_Recipe_fvrt_share_Download.adapter.CategoriesAdapter.CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_categories, parent, false);
        return new  com.sehrishsheikh.virtualcook.Login_Via_accountt.W_All_Recipe_fvrt_share_Download.adapter.CategoriesAdapter.CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder( com.sehrishsheikh.virtualcook.Login_Via_accountt.W_All_Recipe_fvrt_share_Download.adapter.CategoriesAdapter.CategoryViewHolder holder, int position) {
        Category c = categoryList.get(position);
        holder.textView.setText(c.name);
        Glide.with(mCtx)
                .load(c.thumb)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textView;
        ImageView imageView;

        public CategoryViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.text_view_cat_name);
            imageView = itemView.findViewById(R.id.image_view);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {


        /*
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            mInterstitialAd.loadAd(new AdRequest.Builder().build());
        }
        */

            int p = getAdapterPosition();
            Category c = categoryList.get(p);

            Intent intent = new Intent(mCtx, WallpapersActivity.class);
            intent.putExtra("category", c.name);

            mCtx.startActivity(intent);
        }
    }
}
