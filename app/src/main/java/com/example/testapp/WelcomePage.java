package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomePage extends AppCompatActivity {
    private Button mMissing_reg;
    private Button user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
        mMissing_reg=findViewById(R.id.missing_reg);
        user=findViewById(R.id.user_reg);
        mMissing_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMissingRegActivity();
            }
        });
       user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUserRegActivity();
            }
        });
    }


    private void openMissingRegActivity() {
        Intent intent = new Intent(this, RegisterMissingPerson.class);
        startActivity(intent);
    }
    private void openUserRegActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}