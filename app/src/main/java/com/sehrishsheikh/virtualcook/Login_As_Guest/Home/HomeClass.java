package com.sehrishsheikh.virtualcook.Login_As_Guest.Home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.sehrishsheikh.virtualcook.R;

import java.io.ByteArrayOutputStream;

public class HomeClass extends Fragment {
    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;

    RecyclerView myRecyclerView;
    FirebaseDatabase myfirebaseDatabase;
    DatabaseReference myRef;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home, container, false);


        //set toolbartitle color
        //Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        //toolbar.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.DeepSkyBlue));
        // toolbar.setTitleTextColor(ContextCompat.getColor(getActivity(), R.color.gradStart));


        //recyclerview
        myRecyclerView = v.findViewById(R.id.recyclerView_RVID);
        //finding listview

        myRecyclerView.setHasFixedSize(true);

        //set layout as linear layout
        myRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));


        //send query to firebasedatabase
        myfirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = myfirebaseDatabase.getReference("Recipe");    //Data is the folder in the firebase database


        // return inflater.inflate(R.layout.fragment_main, container, false);
        return v;

    }

    //search data code start
    //search data
    private void firebaseSearch(String searchText)
    {
        //convert string enter in search into lower case
        String query = searchText.toLowerCase();

        //Query firebaseSearchQuery = myRef.orderByChild("search").startAt(query).endAt(query + "\uf0ff");

          Query firebaseSearchQuery = myRef.child("Recipe").orderByChild("search").startAt(query).endAt(query + "\uf0ff");

        FirebaseRecyclerAdapter<Model, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Model, ViewHolder>(
                        Model.class,
                        R.layout.home,
                        ViewHolder.class,
                        firebaseSearchQuery
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Model model, int i) {

                        viewHolder.setDetails(getActivity().getApplicationContext(), model.getTitle(), model.getchef(), model.getImage());


                    }
                };


        //set adapter to reyclerview
        myRecyclerView.setAdapter(firebaseRecyclerAdapter);

    }
    //search data code ends


    //load data in recycler view
    public void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Model, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Model, ViewHolder>(
                        Model.class,
                        R.layout.home_item,
                        ViewHolder.class,
                        myRef
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Model model, int i) {
                        //set details is a method that has been defined in view holder class
                        viewHolder.setDetails(getActivity(), model.getTitle(), model.getchef(), model.getImage());

                    }


                    //item detail code start
                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                        ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);

                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                                //VIEWS
                                TextView myTitleTV = view.findViewById(R.id.rTitleTv_TVID);
                                TextView myDescTV = view.findViewById(R.id.rDescription_TVID);
                                ImageView myImageViewIV = view.findViewById(R.id.rImageView_IVID);

                                //get data from views
                                String myTitle = myTitleTV.getText().toString();
                                String myDesc = myDescTV.getText().toString();
                                Drawable myDrawable = myImageViewIV.getDrawable();
                                Bitmap myBitmap = ((BitmapDrawable) myDrawable).getBitmap();

                                //pass this data to new activity
                                Intent intent = new Intent(view.getContext(), HomeItemDetail.class);
                                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                myBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                                byte[] bytes = stream.toByteArray();
                                intent.putExtra("image", bytes);   //put bitmap images as array of bytes
                                intent.putExtra("title", myTitle);  //put title
                                intent.putExtra("chef", myDesc); //put description
                                startActivity(intent); //start the activity


                            }

                            @Override
                            public void onItemLongClick(View view, int position) {
                                //TODO do your own implementation on Long Item Click


                            }
                        });

                        return viewHolder;
                    }


                    //item detail code end
                };


        //set adapter to reyclerview
        myRecyclerView.setAdapter(firebaseRecyclerAdapter);

    }


/*
    //a method for search

    public boolean onCreateOptionsMenu(Menu menu)
    {
        //inflate the menu ;this  adds items to the action bar if it present
        getActivity().getMenuInflater().inflate(R.menu.menu , menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                firebaseSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                firebaseSearch(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

*/


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    // public boolean onCreateOptionsMenu(Menu menu)
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        // getMenuInflater().inflate(R.menu.menu , menu)
        getActivity().getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem item = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                firebaseSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                firebaseSearch(newText);
                return false;
            }
        });

        // return super.onCreateOptionsMenu(menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        //handle other action bar item click here
        if (id == R.id.action_setting) {
            //TODO
            return true;
        }
        return super.onOptionsItemSelected(item);


    }


    //a method for search




}


