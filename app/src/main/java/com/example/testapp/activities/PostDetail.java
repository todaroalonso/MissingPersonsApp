package com.example.testapp.activities;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testapp.R;
import com.example.testapp.models.Upload;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class PostDetail extends AppCompatActivity {

    private ImageView imageView;
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
        setContentView(R.layout.activity_post_detail);

        Upload model=(Upload)getIntent().getSerializableExtra("model");
        textView = findViewById(R.id.text_view_name);
        imageView = findViewById(R.id.image_view_upload);
        textView2 = findViewById(R.id.DOB);
        textView3 = findViewById(R.id.weight);
        textView4 = findViewById(R.id.clothes_worn);
        textView5 = findViewById(R.id.contacts);
        textView6 = findViewById(R.id.missing_date);
        textView7 = findViewById(R.id.complexion);


        //mAuth = FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance().getReference().child("uploads");
        postKey = getIntent().getStringExtra("postKey");
        DOB=getIntent().getStringExtra("DOB");


        ref.child(postKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String name = dataSnapshot.child("name").getValue().toString();
                    String DOB = dataSnapshot.child("dob").getValue().toString();

                    //textView2.setText(model.getDOB());

                    String weight= dataSnapshot.child("weight").getValue().toString();
                    String clothes = dataSnapshot.child("clothes").getValue().toString();
                    String contacts = dataSnapshot.child("contacts").getValue().toString();
                    String missingDate = dataSnapshot.child("missingDate").getValue().toString();
                    String complexion = dataSnapshot.child("complexion").getValue().toString();



                    String ImageUrl = dataSnapshot.child("imageUrl").getValue().toString();
                    textView.setText(name);

                    textView2.setText(DOB);

                    textView3.setText(weight);
                    textView4.setText(clothes);
                    textView5.setText(contacts);
                    textView6.setText(missingDate);
                    textView7.setText(complexion);

                     


                    Picasso.with(PostDetail.this).load(ImageUrl).into(imageView);

                } else {
                    Toast.makeText(PostDetail.this, "Post N/A", Toast.LENGTH_SHORT).show();
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