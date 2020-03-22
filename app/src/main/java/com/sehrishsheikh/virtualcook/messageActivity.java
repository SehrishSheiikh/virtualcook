package com.example.hp.chattapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.hp.chattapp.Adapter.messageAdapter;
import com.example.hp.chattapp.Model.User;
import com.example.hp.chattapp.Model.chat;
import com.example.hp.chattapp.Model.chatList;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class messageActivity extends AppCompatActivity {

    CircleImageView profile_image;
    TextView username;

    FirebaseUser fuser;
    DatabaseReference reference;

    ImageButton btn_send;
    EditText send_text;

    Intent intent;



    ValueEventListener seenListner;

    messageAdapter messageAdapter;
    List<chat> mchat;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        androidx.appcompat.widget.Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(messageActivity.this,MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });
        recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);



        profile_image=findViewById(R.id.profile_image);
        username=findViewById(R.id.username);
        btn_send=findViewById(R.id.btn_send);
        send_text=findViewById(R.id.text_send);



        intent=getIntent();
        //userid here...
        final String userid=intent.getStringExtra("userid");
      //  User user=new User();
       // final String userid=user.getId();
        fuser=FirebaseAuth.getInstance().getCurrentUser();
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg=send_text.getText().toString();
                if (!msg.equals(""))
                {
                    sendMessage(fuser.getUid(),userid,msg);

                }
                else
                {
                    Toast.makeText(messageActivity.this,"You can't send Empty Message",Toast.LENGTH_LONG).show();
                }
                send_text.setText("");
            }
        });




        reference=FirebaseDatabase.getInstance().getReference("Users").child(userid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                User user=dataSnapshot.getValue(User.class);
            username.setText(user.getUsername());
            if (user.getImageUrl().equals("default"))
            {
                profile_image.setImageResource(R.mipmap.ic_launcher);
            }
            else
                {
                    Glide.with(getApplicationContext()).load(user.getImageUrl()).into(profile_image);
                }
                readMessage(fuser.getUid(),userid,user.getImageUrl());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        seenMessage(userid);


    }


    private void seenMessage(final String userid)
    {
        reference=FirebaseDatabase.getInstance().getReference("chats");
        seenListner=reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    chat chat=snapshot.getValue(chat.class);
                    try {
                        if (chat.getReciever().equals(fuser.getUid())&&chat.getSender().equals(userid))
                        {
                            HashMap<String,Object> hashMap=new HashMap<>();
                            hashMap.put("isseen",true);
                            snapshot.getRef().updateChildren(hashMap);
                        }
                    }catch (NullPointerException ignored)
                    {}

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void sendMessage(String sender, final String reciever, String message)
    {
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference("chats");
        final String userid=intent.getStringExtra("userid");

        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("sender",sender);
        hashMap.put("reciever",reciever);
        hashMap.put("message",message);
        hashMap.put("isseen",false);
        reference.push().setValue(hashMap);

        //Add user to chat fragment

       // User user=new User();
        //final String userid=user.getId();

        final DatabaseReference chatRef=FirebaseDatabase.getInstance().getReference("Chatlist");
        chatRef.child(fuser.getUid()).child(userid).child("id").setValue(userid);
        chatRef.child(userid).child(fuser.getUid()).child("id").setValue(fuser.getUid());
//                .child(fuser.getUid())
//                .child(userid);
       // chatRef.child("id").setValue(userid);
//        final DatabaseReference _chatRef=FirebaseDatabase.getInstance().getReference("Chatlist")
//                .child(userid)
//                .child(fuser.getUid());
//        chatRef.child("id").setValue(fuser.getUid());

//        chatRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//
//                if (!dataSnapshot.exists())
//                {
//                    chatRef.child("id").setValue(userid);
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });






    }
    private void readMessage(final String myid, final String userid, final String imageurl)
    {
        mchat=new ArrayList<>();
        reference=FirebaseDatabase.getInstance().getReference("chats");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mchat.clear();
                for (DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    chat chat=snapshot.getValue(chat.class);
                    try {
                        if (chat.getReciever().equals(myid)&&chat.getSender().equals(userid)||chat.getReciever().equals(userid)&&chat.getSender().equals(myid))
                        {
                            mchat.add(chat);
                        }
                    }catch (NullPointerException ignored)
                    {}

                    messageAdapter=new messageAdapter(messageActivity.this,mchat,imageurl);
                    recyclerView.setAdapter(messageAdapter);
                }



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void status(String status)
    {
        reference=FirebaseDatabase.getInstance().getReference("Users").child(fuser.getUid());

        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("status",status);

        reference.updateChildren(hashMap);
    }

    @Override
    protected void onResume() {
        super.onResume();
        status("online");
    }

    @Override
    protected void onPause() {
        super.onPause();
        reference.removeEventListener(seenListner);
        status("offline");
    }

}
