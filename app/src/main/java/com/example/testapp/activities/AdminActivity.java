package com.example.testapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testapp.R;

public class AdminActivity extends AppCompatActivity {

    private TextView users;
    private TextView missingpersons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        users=findViewById(R.id.v_users);
        missingpersons=findViewById(R.id.v_mp);

        users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAdminUsers();

            }
        });


        missingpersons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAdminImages();

            }
        });
    }

    private void openAdminImages() {
        Intent intent = new Intent(this, AdminImages.class);
        startActivity(intent);
    }
    private void openAdminUsers() {
        Intent intent = new Intent(this, AdminUsers.class);
        startActivity(intent);
    }
}