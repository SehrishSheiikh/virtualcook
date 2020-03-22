package com.sehrishsheikh.massenger.People;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.sehrishsheikh.massenger.R;

import java.util.ArrayList;

public class People_Adapter extends BaseAdapter
{
    Activity activity;
    ArrayList<People_Model> arrayList;
    LayoutInflater inflater;

    public People_Adapter(Activity activity, ArrayList<People_Model> arrayList) {
        this.activity = activity;
        this.arrayList = arrayList;
        this.inflater=activity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public People_Model getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v=convertView;
        if (v==null)
            v=inflater.inflate(R.layout.massenger_people_pg , null);
        //initialization

        TextView title=v.findViewById(R.id.mxngr_ppl_name_tv1);
        TextView day=v.findViewById(R.id.mxngr_text_day_tv3);
        ImageView image=v.findViewById(R.id.mxngr_ppl_dp_img1);


        final People_Model model=getItem(position); //get data
        //set data

        image.setImageResource(model.getImage());
        title.setText(model.getTitle());
        day.setText(model.getDay());

        return v;
    }

}

