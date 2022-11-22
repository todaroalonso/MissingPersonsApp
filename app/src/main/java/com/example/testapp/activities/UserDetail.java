package com.example.testapp.activities;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testapp.R;
import com.example.testapp.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserDetail extends AppCompatActivity {


    private TextView textView;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private TextView textView6;
    private TextView textView7;
    DatabaseReference ref;
    String postKey = null;
    String DOB=null;
    private Context mContext;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_detail);

        User model=(User)getIntent().getSerializableExtra("model");
        textView = findViewById(R.id.fname);
        textView2 = findViewById(R.id.lname);
        textView3 = findViewById(R.id.email);
        textView4 = findViewById(R.id.location);
        textView5 = findViewById(R.id.contacts);



        //mAuth = FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance().getReference().child("users");
        postKey = getIntent().getStringExtra("user");



        ref.child(postKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String fname = dataSnapshot.child("fname").getValue().toString();
                    String lname = dataSnapshot.child("lname").getValue().toString();

                    //textView2.setText(model.getDOB());

                    //String email= dataSnapshot.child("weight").getValue().toString();
                    String location= dataSnapshot.child("location").getValue().toString();
                    String mobile = dataSnapshot.child("mobile").getValue().toString();




                    textView.setText(fname);

                    textView2.setText(lname);

                    textView3.setText(location);
                    textView4.setText(mobile);






                } else {
                    Toast.makeText(UserDetail.this, "Post N/A", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


/*
    private String timestampToString(long time) {

        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(time);
        String date = DateFormat.format("dd-MM-yyyy",calendar).toString();
        return date;


    }*/


    }
}