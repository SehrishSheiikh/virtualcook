package com.sehrishsheikh.virtualcook.Chef;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sehrishsheikh.virtualcook.R;
import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder
{
    View myView;

    public ViewHolder(@NonNull View itemView)
    {
        super(itemView);

        myView = itemView;

        //item Click
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myClickListener.onItemClick(view , getAdapterPosition());
            }
        });

        //item long click
        itemView.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View view) {
                myClickListener.onItemLongClick(view , getAdapterPosition());
                return true;
            }
        });



    }


    //set details to recycler view row
    public void setDetails(Context ctx , String title , String description , String image)
    {

        //views
        TextView myTitleTv = myView.findViewById(R.id.rTitleTv_TVID);
        TextView myDetailTv = myView.findViewById(R.id.rDescription_TVID);
        ImageView myImageIV = myView.findViewById(R.id.rImageView_IVID);

        //now set data to views
        myTitleTv.setText(title);
        myDetailTv.setText(description);
      // Picasso.get().load(image).into(myImageIV);
        Picasso.with(ctx).load(image).into(myImageIV);



    }

    private com.sehrishsheikh.virtualcook.Login_As_Guest.Home.ViewHolder.ClickListener myClickListener;

    //interface to send back call
    public  interface ClickListener
    {
        void onItemClick(View view , int position);
        void onItemLongClick(View view , int position);
    }

    public void setOnClickListener(com.sehrishsheikh.virtualcook.Login_As_Guest.Home.ViewHolder.ClickListener clickListener)
    {
        myClickListener = clickListener;
    }





}
