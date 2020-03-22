package com.example.hp.chattapp.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hp.chattapp.Adapter.UserAdapter;
import com.example.hp.chattapp.Adapter.messageAdapter;
import com.example.hp.chattapp.Model.User;
import com.example.hp.chattapp.Model.chat;
import com.example.hp.chattapp.Model.chatList;
import com.example.hp.chattapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class chatsFragment extends Fragment {


    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private List<User> mUsers;

    FirebaseUser firebaseUser;
    DatabaseReference reference;

    private List<chatList> userlist;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_chats,container,false);

        recyclerView=view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();


        userlist=new ArrayList<>();

        reference=FirebaseDatabase.getInstance().getReference("Chatlist").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
            userlist.clear();
            for (DataSnapshot snapshot:dataSnapshot.getChildren())
            {
               // Toast.makeText(getContext(),"gello",Toast.LENGTH_SHORT).show();
                chatList chatList=snapshot.getValue(chatList.class);
                userlist.add(chatList);
            }
            chatList();
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        return view;
    }
        private void chatList()
        {
            mUsers=new ArrayList<>();
            reference=FirebaseDatabase.getInstance().getReference("Users");
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
               mUsers.clear();
               for (DataSnapshot snapshot:dataSnapshot.getChildren())
               {
                   User user=snapshot.getValue(User.class);
                   for (chatList chatList:userlist)
                   {
                       if (user.getId().equals(chatList.getId()))
                       {
                           mUsers.add(user);
                       }
                   }
               }
               userAdapter=new UserAdapter(getContext(),mUsers,true);
               recyclerView.setAdapter(userAdapter);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });



        }

}
