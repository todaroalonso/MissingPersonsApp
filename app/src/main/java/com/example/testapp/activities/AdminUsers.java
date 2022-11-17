package com.example.testapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp.R;
import com.example.testapp.adapters.TextAdapter;
import com.example.testapp.models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class AdminUsers extends AppCompatActivity implements TextAdapter.OnItemClickListener  {
    private RecyclerView mRecyclerView;
    private TextAdapter mAdapter;
    private FirebaseStorage mStorage;
    private ProgressBar mProgressCircle;
    private ValueEventListener mDBListener;
    private DatabaseReference mDatabaseRef;
    private List<User> mUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mProgressCircle = findViewById(R.id.progress_circle);

        mStorage=FirebaseStorage.getInstance();
        mUsers = new ArrayList<>();
        mAdapter=new TextAdapter(AdminUsers.this,mUsers);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(AdminUsers.this);



        mDatabaseRef = FirebaseDatabase.getInstance().getReference("users");

        mDBListener= mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mUsers.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    User user = postSnapshot.getValue(User.class);
                    //saves the unique key on the upload item
                    user.setKey(postSnapshot.getKey());
                    mUsers.add(user);
                }
//updates recycler view after it is filled with items
                mAdapter.notifyDataSetChanged();

                mProgressCircle.setVisibility(View.INVISIBLE);




            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(AdminUsers.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(this, "Normal click at position: " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onWhatEverClick(int position) {
        Toast.makeText(this, "Whatever click at position: " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteClick(int position) {

    }

    /*
        @Override
        public void onDeleteClick(int position) {
           User selectedItem = mUsers.get(position);
            final String selectedKey = selectedItem.getKey();

            //StorageReference imageRef = mStorage.getReferenceFromUrl(selectedItem.getImageUrl());
            //imageRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    mDatabaseRef.child(selectedKey).removeValue();
                    Toast.makeText(ImagesActivity.this, "Item deleted", Toast.LENGTH_SHORT).show();
                }
            });
        }*/
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDatabaseRef.removeEventListener(mDBListener);
    }


}
