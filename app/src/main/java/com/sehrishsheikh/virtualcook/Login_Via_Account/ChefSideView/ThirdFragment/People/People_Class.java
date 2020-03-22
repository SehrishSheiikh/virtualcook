package com.sehrishsheikh.massenger.People;

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

public class People_Class extends Fragment
{

    ListView lstview;

    Integer[] image = {R.drawable.ic_people_black_24dp, R.drawable.ic_people_black_24dp,R.drawable.ic_people_black_24dp  ,
            R.drawable.ic_people_black_24dp, R.drawable.ic_people_black_24dp,R.drawable.ic_people_black_24dp  ,
            R.drawable.ic_people_black_24dp, R.drawable.ic_people_black_24dp,R.drawable.ic_people_black_24dp  };
    String[] title  = {"Sehrish Sheikh", "Arham Yaya","Maida Tarrar", "Gohar Ali Awan" , "Miss world " , "Annie Sheikh" ,
            "Arham Yaya","Maida Tarrar", "Gohar Ali Awan" };
    String[] day    = {"Active 4d ago","Active 2h ago","Active 2h ago" , "Active 1d ago","Active 45min ago","Active 9sec ago" ,
            "Active 45min ago","Active 9sec ago","Active 9sec ago"};

    ArrayList<People_Model> arrayList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v=inflater.inflate(R.layout.massenger_ppl_listview,container,false);
        lstview = v.findViewById(R.id.mxngr_ppl_lstview_id);

        arrayList = new ArrayList<>();
        for (int i = 0; i < title.length; i++)
        {
            People_Model model = new People_Model(title[i],day[i],image[i]);
            arrayList.add(model);

        }

        People_Adapter adapter=new People_Adapter(getActivity(),arrayList);
        lstview.setAdapter(adapter);

        return v;

    }


}
