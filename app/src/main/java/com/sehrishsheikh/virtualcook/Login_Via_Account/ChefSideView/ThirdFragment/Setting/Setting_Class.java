package com.sehrishsheikh.massenger.Setting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sehrishsheikh.massenger.R;

import java.util.ArrayList;

public class Setting_Class extends Fragment
{
    //(1)=>declaration
    ListView lstview;
    Integer[] icon = {R.drawable.ic_notifications_black_24dp, R.drawable.ic_people_black_24dp,R.drawable.ic_sms_black_24dp,
            R.drawable.ic_compare_arrows_black_24dp,R.drawable.ic_settings_black_24dp,R.drawable.ic_report_problem_black_24dp,
            R.drawable.ic_help_black_24dp,R.drawable.ic_priority_high_black_24dp};

    String[]  text = {"Notification & Sound", "People","Messsage Requests","Switch Account","Report a Problem","Help","Privacy & Terms"};

    ArrayList<Setting_Model> arrayList ;

    //(2)=>call OnCreateView function (ctrl+o)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        //(3)=>convert layout into a view
        View v = inflater.inflate(R.layout.massenger_setting_listview ,container, false);

        //(4)=>find view by id
        lstview = v.findViewById(R.id.setting_lstview_id);
        arrayList = new ArrayList<>();

        for(int i = 0 ; i < text.length ; i++)
        {
            Setting_Model model = new Setting_Model(text[i] , icon[i]);
            arrayList.add(model);


        }
        Setting_Adapter adapter = new Setting_Adapter(getActivity(),arrayList);
        lstview.setAdapter(adapter);

        return v;

    }


}
