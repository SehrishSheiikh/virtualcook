package com.sehrishsheikh.massenger.Massenger_request;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sehrishsheikh.massenger.R;

import java.util.ArrayList;

public class Friend_Rq_Class extends Fragment {
    ListView lstview;

    Integer[] dp = {R.drawable.dp1, R.drawable.dp2, R.drawable.dp3, R.drawable.dp4, R.drawable.dp5,
            R.drawable.dp1, R.drawable.dp2, R.drawable.dp3, R.drawable.dp4, R.drawable.dp5};

    String[] title = {"Haya Ali", "Mena Shah", "Haya Ali", "Mena Shah", "Haya Ali",
            "Mena Shah", "Haya Ali", "Mena Shah", "Haya Ali", "Mena Shah"};

    String[] description = {"hi", "hey,u there?", "hi", "hey,u there?", "hi", "hey,u there?",
            "hi", "hey,u there?", "hi", "hey,u there?"};

    String[] day = {"Mon", "15 Jul", "Mon", "15 Jul", "Mon", "15 Jul",
            "Mon", "15 Jul", "Mon", "15 Jul"};

    ArrayList<Friend_Rq_Model> arrayList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frndreq_lstview, container, false);

        lstview = v.findViewById(R.id.lstview_1);
        registerForContextMenu(lstview);
        arrayList = new ArrayList<>();
        for (int i = 0; i < title.length; i++) {
            Friend_Rq_Model friend_rq_model = new Friend_Rq_Model(title[i], description[i], day[i], dp[i]);
            arrayList.add(friend_rq_model);

        }
        Friend_Rq_Adapter adapter = new Friend_Rq_Adapter(getActivity(), arrayList);
        lstview.setAdapter(adapter);


        return v;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getActivity().getMenuInflater().inflate(R.menu.context_menu, menu);
        //getMenuInflater().inflate(R.menu.context_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int listPosition = info.position;
        Friend_Rq_Model model = arrayList.get(listPosition);//list item title

        switch (item.getItemId()) {
            case R.id.item_11:
                Toast.makeText(getActivity(), "READ MSG", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item_22:
                Toast.makeText(getActivity(), "MARK AS UNREAD", Toast.LENGTH_SHORT).show();
                return true;


            default:
                return false;
        }
    }
}