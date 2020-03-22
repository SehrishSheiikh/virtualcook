package com.example.hp.chattapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class startActivity extends AppCompatActivity {

    Button login,registers;
    FirebaseUser firebaseUser;

    @Override
    protected void onStart() {
        super.onStart();
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser!=null)
        {
            Intent intent=new Intent(startActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();

        if (firebaseUser!=null)
        {
            Intent intent=new Intent(startActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }

        login=(Button)findViewById(R.id.btnlogin);
        registers=(Button)findViewById(R.id.btnregister);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(startActivity.this,loginActivity.class));
            }
        });
        registers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(startActivity.this,RegisterActivity.class));
            }
        });



    }
}
