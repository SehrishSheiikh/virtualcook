package com.sehrishsheikh.virtualcook.Login_Via_Account.ChefSideView.SecondFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sehrishsheikh.virtualcook.Login_Via_Account.ChefSideView.FirstFragment.Chef_Dishes.ImageAdapter;
import com.sehrishsheikh.virtualcook.Login_Via_Account.ChefSideView.FirstFragment.Chef_Dishes.Upload;
import com.sehrishsheikh.virtualcook.R;

import java.util.ArrayList;
import java.util.List;

public class ViewImageGallery extends Fragment

{
    private RecyclerView mRecyclerView;
    private ImageAdapter mAdapter;

    private ProgressBar progressBar;

    private DatabaseReference mDatabaseRef;
    private List<Upload> mUploads;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_view_image, container, false);

        mRecyclerView = v.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        progressBar = v.findViewById(R.id.progress_circular);

        mUploads = new ArrayList<>();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Upload upload = postSnapshot.getValue(Upload.class);
                    mUploads.add(upload);
                }
                mAdapter = new ImageAdapter(getActivity(), mUploads);
                mRecyclerView.setAdapter(mAdapter);
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
        return v;
    }
}