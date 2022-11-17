package com.example.testapp.activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testapp.R;
import com.example.testapp.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseFirestore fStore;
    private EditText email, password,password2;
    private EditText fname;
    private EditText lname;
    private EditText location;
    private EditText mobile;
    private Button btnRegister;
    private TextView textLogin;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.register_email);
        password = findViewById(R.id.register_password);
        password2 = findViewById(R.id.confirm_password);
        btnRegister = findViewById(R.id.register);
        textLogin = findViewById(R.id.text_login);
        fStore = FirebaseFirestore.getInstance();
        fname=findViewById(R.id.f_name);
        lname=findViewById(R.id.l_name);
        location=findViewById(R.id.location);
        mobile=findViewById(R.id.phone);


        mStorageRef= FirebaseStorage.getInstance().getReference("users");
        mDatabaseRef= FirebaseDatabase.getInstance().getReference("users");


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
                uploadDetails();
            }
        });

        textLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }


    private void Register() {
        String user = email.getText().toString().trim();
        String pass = password.getText().toString().trim();
        String pass2 = password2.getText().toString().trim();
        if (user.isEmpty()) {
            email.setError("Email can not be empty");
        }
        if (pass.isEmpty()) {
            password.setError("Password can not be empty");
        }
        /*
        if (pass!=pass2) {
            password2.setError("Passwords do not match");
        }*/
        else {
            mAuth.createUserWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        //getting info on the current user from the auth platform
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(RegisterActivity.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                        DocumentReference df = fStore.collection("Users").document(user.getUid());
                        //key-value pairs to store in the FireStore Db
                        Map<String, Object> userInfo = new HashMap<>();
                        userInfo.put("isGeneralUser", "1");

                        df.set(userInfo);
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

                    } else {
                        Toast.makeText(RegisterActivity.this, "Registration Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        int isUser = 1;

    }

    private void uploadDetails() {
        int isUser=1;
        User user = new User(fname.getText().toString().trim(),lname.getText().toString().trim(),location.getText().toString().trim(),
                mobile.getText().toString().trim());

        //pass new entry to the DB with a unique ID
        String uploadId = mDatabaseRef.push().getKey();
        //pushing values to the DB with the unique ID + values in the Upload model class
        mDatabaseRef.child(uploadId).setValue(user);
    }
}