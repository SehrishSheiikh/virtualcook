package com.sehrishsheikh.virtualcook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.sehrishsheikh.virtualcook.Chef.ChefActivtiy;

public class LoginActivity extends AppCompatActivity
{
    EditText mEditTextemail , mEditTextpassword;
    TextView mTextView_create_btn , mTextview_guest;
    Button mBtn_login;
    ProgressBar mProgressBar;
    FirebaseAuth firebaseAuth;         //class that provide firebase



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //for hide top display
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //ending

        super.onCreate(savedInstanceState);
        // getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getActionBar();
        getSupportActionBar().hide();


        mEditTextemail = findViewById(R.id.et_login_email);
        mEditTextpassword = findViewById(R.id.et_login_password);
        mProgressBar = findViewById(R.id.progress_bar);
        mBtn_login = findViewById(R.id.login_button);
        mTextView_create_btn = findViewById(R.id.signup_tv);
        mTextview_guest = findViewById(R.id.tv_guest);


        firebaseAuth = FirebaseAuth.getInstance();           //we are getting the curent instance from the database from the firebase so we can perform various function on db


        //validate the user that first login the app
        mBtn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get value that would be enter in edittexts
                String email = mEditTextemail.getText().toString().trim();
                String password = mEditTextpassword.getText().toString().trim();

                //now validate the value that woulb be enter in ets
                if (TextUtils.isEmpty(email)) {
                    mEditTextemail.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    mEditTextpassword.setError("Password is required");
                    return;
                }
                //gonna check the length of the password
                if (password.length() < 8) {
                    mEditTextpassword.setError("must be greater than 8 character ");
                }

                //for progressbar
                mProgressBar.setVisibility(View.VISIBLE);  //use visibility method to see the visibilty of progress bar


                //now attenticate the user which is very different in login activity
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //check whether the login is suceful or not
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Login succesfully ", Toast.LENGTH_SHORT).show();
                            //send user to the mainactivity
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));    //will move user to home activity
                        } else {
                            Toast.makeText(LoginActivity.this, "Some error occur  " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                            //for progressive bar continues removing process
                            mProgressBar.setVisibility(View.GONE);
                            



                        }

                    }
                });

            }
        });

        //set the login textview work
        mTextView_create_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegistrationActivity.class));
            }
        });

        //when user comes as a guest
        mTextview_guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DrawerLayoutActivity.class));
            }
        });



    }
}
