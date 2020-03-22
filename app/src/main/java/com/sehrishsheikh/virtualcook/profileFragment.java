package com.example.hp.chattapp.Fragments;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import de.hdodenhof.circleimageview.CircleImageView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hp.chattapp.Model.User;
import com.example.hp.chattapp.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

import static android.app.Activity.RESULT_OK;


public class profileFragment extends Fragment {


    CircleImageView image_profile;
    TextView username;

    DatabaseReference reference;
    FirebaseUser fuser;

    StorageReference storageReference;
    private static final int IMAGE_REQUEST=1;
    private Uri imageuri;
    private StorageTask uploadtask;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_profile,container,false);
        image_profile=view.findViewById(R.id.profile_image);
        username=view.findViewById(R.id.usernamep);


        storageReference=FirebaseStorage.getInstance().getReference("uploads");
        fuser=FirebaseAuth.getInstance().getCurrentUser();

        reference=FirebaseDatabase.getInstance().getReference("user").child(fuser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
            User user=dataSnapshot.getValue(User.class);
            try {
                username.setText(user.getUsername());
            }catch (NullPointerException ignored)
            {}
            try {
                if (user.getImageUrl().equals("default"))
                {
                    image_profile.setImageResource(R.mipmap.ic_launcher);
                }
                else
                {
                    Glide.with(getContext()).load(user.getImageUrl()).into(image_profile);
                }

            }catch (NullPointerException ignored)
            {}

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        image_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openimage();
            }
        });



        return view;
    }
    private void openimage()
    {
        Intent intent=new Intent();
        intent.setType("image/+");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMAGE_REQUEST);
    }
    private String getFileExtention(Uri uri)
    {
        ContentResolver contentResolver=getContext().getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));

    }
    private void uploadImage()
    {
        final ProgressDialog pd=new ProgressDialog(getContext());
        pd.setMessage("uploading");
        pd.show();

        if (imageuri!=null)
        {
            final StorageReference fileReference=storageReference.child(System.currentTimeMillis()
                    +"."+getFileExtention(imageuri));
            uploadtask=fileReference.putFile(imageuri);
            uploadtask.continueWithTask(new Continuation<UploadTask.TaskSnapshot,Task<Uri>>() {
                @Override
                public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful())
                    {
                        throw task.getException();
                    }


                    return fileReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(Task<Uri> task) {
               if (task.isSuccessful())
               {
                   Uri downloadUri=task.getResult();
                   String mUri=downloadUri.toString();

                   reference=FirebaseDatabase.getInstance().getReference("users").child(fuser.getUid());
                   HashMap<String,Object> map=new HashMap<>();
                   map.put("imageUrl",mUri);
                   reference.updateChildren(map);
                   pd.dismiss();
               }
               else {
                   Toast.makeText(getContext(),"Failed",Toast.LENGTH_SHORT).show();
                   pd.dismiss();
               }

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(Exception e) {
                    Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    pd.dismiss();


                }
            });



        }
        else {
            Toast.makeText(getContext(),"No iImage Selected",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==IMAGE_REQUEST && resultCode==RESULT_OK && data!=null&&data.getData()!=null )
        {
            imageuri=data.getData();

            if (uploadtask!=null&&uploadtask.isInProgress())
            {
                Toast.makeText(getContext(),"Upload is in Progress",Toast.LENGTH_SHORT).show();
            }
            else {
                uploadImage();
            }
        }


    }
}
