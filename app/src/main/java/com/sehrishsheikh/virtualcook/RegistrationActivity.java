package com.sehrishsheikh.virtualcook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity
{
    EditText _et_name , _et_email , _et_phone , _et_address , _et_password ,  _et_confirm_password ;
    TextView _tv_login_btn;
    Button _btn_sigup;
    ProgressBar mProgressBar;
    FirebaseAuth firebaseAuth;         //class that provide firebase

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //for hide top display
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //ending

        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_registration);


        _et_name = findViewById(R.id.et_reg_name);
        _et_email = findViewById(R.id.et_reg_email);
        _et_phone = findViewById(R.id.et_reg_phone);
        _et_address = findViewById(R.id.et_reg_address);
        _et_password = findViewById(R.id.et_reg_password);
        _et_confirm_password = findViewById(R.id.et_reg_confirm_password);
        _tv_login_btn = findViewById(R.id.tv_login);
        _btn_sigup      = findViewById(R.id.reg_submit_button);
        mProgressBar = findViewById(R.id.progress_bar);

        //for node cretion
        databaseReference = FirebaseDatabase.getInstance().getReference("User");


        //we are getting the curent instance from the database from the firebase so we can perform various function on db
        firebaseAuth = FirebaseAuth.getInstance();



        //process to check whether the user is already login or not and already have an accoutn before,so it moves to mainactivity
        //so need to display login and signup who is already login
        if (firebaseAuth.getCurrentUser() != null)
        {
            //send user to the mainactivity who already login
            startActivity(new Intent(getApplicationContext() , HomeActivity.class));    //will move user to home activity
            finish();
        }


        //actual code that register the user in firebase

        _btn_sigup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                //get value that would be enter in edittexts
                final String name = _et_name.getText().toString().trim();
                final String email = _et_email.getText().toString().trim();
                final String contact = _et_phone.getText().toString().trim();
                final String address = _et_address.getText().toString().trim();
                String pass = _et_password.getText().toString().trim();
                String con_pass = _et_confirm_password.getText().toString().trim();

                //now validate the value that woulb be enter in ets
                if(TextUtils.isEmpty(name))
                {
                    _et_name.setError("Name is required");
                    return;
                }
                if(TextUtils.isEmpty(email))
                {
                    _et_email.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(contact))
                {
                    _et_phone.setError("Contact is required");
                    return;
                }

                if(TextUtils.isEmpty(address))
                {
                    _et_address.setError("Address is required");
                    return;
                }
                if (TextUtils.isEmpty(pass))
                {
                    _et_password.setError("Pasword is required");
                    return;
                }
                if (TextUtils.isEmpty(con_pass))
                {
                    _et_confirm_password.setError("Confirm Pasword is required");
                    return;
                }
                //gonna check the length of the password
                if (pass.length() < 8)
                {
                    _et_password.setError("Password must be > = 8 character ");
                }

                //for progressbar
                mProgressBar.setVisibility(View.VISIBLE);  //use visibility method to see the visibilty of progress bar


                //real time dATABASE coding start

                //
                //register the user in firebase
                firebaseAuth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>()
                        {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful())
                                {
                                    registrationdatabasehelpermodel information = new registrationdatabasehelpermodel(
                                            name,
                                            email,
                                            contact,
                                            address
                                    );

                                    FirebaseDatabase.getInstance().getReference("User")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(RegistrationActivity.this , "Registration Successful " , Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                        }
                                    });

                                }
                                else
                                {
                                    //Toast.makeText(SignUp_Form.this , "Failed " , Toast.LENGTH_SHORT).show();
                                    Toast.makeText(RegistrationActivity.this , "Registration Failed" +task.getException().getMessage()  , Toast.LENGTH_SHORT).show();

                                    //for progressive bar continues removing process
                                    mProgressBar.setVisibility(View.GONE);
                                }


                            }
                        });

                //real time database coding end


            }
        });



    }
}
