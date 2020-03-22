package com.sehrishsheikh.massenger.Setting;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sehrishsheikh.massenger.R;

import java.util.ArrayList;

public class Setting_Adapter extends BaseAdapter
{
    //(1)=>declaration
    Activity activity;
    ArrayList<Setting_Model> arrayList;
    LayoutInflater inflater ;

    //(2)=>construction
    public Setting_Adapter(Activity activity , ArrayList<Setting_Model> arrayList)
    {
        this.activity = activity;
        this.arrayList = arrayList;
        this.inflater = activity.getLayoutInflater();
    }


    //(3)=>methods of base adapter
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Setting_Model getItem(int position)
    {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        //(4)=>convert layout into a view
        View v = convertView;
        if( v == null)
        {
            v= inflater.inflate(R.layout.massenger_setting_pg  ,null);
        }

        //(5)=>intialization

        ImageView setting_icon = v.findViewById(R.id.setting_pg_icon_img1);
        TextView  setting_text = v.findViewById(R.id.setting_pg_txt_tv1);

        //(6)=>call model for geting data
        final Setting_Model model = getItem(position);

        //(7)=>Setting data
        setting_icon.setImageResource(model.getImg());
        setting_text.setText(model.getText());


        return v;
    }
}
