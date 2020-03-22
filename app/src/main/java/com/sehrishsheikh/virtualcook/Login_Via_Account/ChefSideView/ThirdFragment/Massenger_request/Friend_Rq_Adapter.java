package com.sehrishsheikh.massenger.Massenger_request;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sehrishsheikh.massenger.R;

import java.util.ArrayList;

public class Friend_Rq_Adapter extends BaseAdapter
{
    Activity activity;
    ArrayList<Friend_Rq_Model> arrayList;
    LayoutInflater inflater;

    public Friend_Rq_Adapter(Activity activity, ArrayList<Friend_Rq_Model> arrayList)
    {
        this.activity = activity;
        this.arrayList = arrayList;
        this.inflater = activity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Friend_Rq_Model getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if (v == null)
            v = inflater.inflate(R.layout.massenger_frnd_rq_pg, null);
        //initialization

        TextView title = v.findViewById(R.id.frnd_rq_name_tv1);
        TextView description = v.findViewById(R.id.frnd_msg_tv2);
        TextView day = v.findViewById(R.id.frnd_text_day_tv3);
        ImageView dp = v.findViewById(R.id.frnd_rq_dp_img1);


        final Friend_Rq_Model model = getItem(position); //get data
        //set data

        dp.setImageResource(model.getDp());
        title.setText(model.getTitle());
        description.setText(model.getDescription());
        day.setText(model.getDay());

        return v;
    }

}

