package com.example.testapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testapp.R;

public class WelcomePage extends AppCompatActivity {
    private Button mMissing_reg;
    private Button user;
    private Button test;
    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
        mMissing_reg=findViewById(R.id.missing_reg);
        login=findViewById(R.id.alreadyuser);
        user=findViewById(R.id.user_reg);
        //test=findViewById(R.id.test);
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
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginActivity();
            }
        });
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAdminImgActivity();
            }
        });
    }


    private void openMissingRegActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    private void openUserRegActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
    private void openLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    private void openAdminImgActivity() {
        Intent intent = new Intent(this, AdminActivity.class);
        startActivity(intent);
    }
}