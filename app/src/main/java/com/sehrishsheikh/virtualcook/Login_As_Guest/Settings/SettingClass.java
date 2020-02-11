package com.sehrishsheikh.virtualcook.Login_As_Guest.Settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sehrishsheikh.virtualcook.HomeActivity;
import com.sehrishsheikh.virtualcook.R;
import com.sehrishsheikh.virtualcook.RegistrationActivity;

public class SettingClass extends Fragment
{
    Button btn_logout_back;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_setting_class, container, false);

        btn_logout_back = v.findViewById(R.id.logout_button_back);


        btn_logout_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }

}
