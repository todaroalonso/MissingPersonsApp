package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RegisterMissingPerson extends AppCompatActivity {

    EditText dp1;
    Calendar calendar;
    Button mapbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_missing_person);

        dp1 = findViewById(R.id.dp);
        mapbtn=findViewById(R.id.location);


        Spinner spinnerH = findViewById(R.id.height);
        Spinner spinnerC = findViewById(R.id.eye_colour);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.colours, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.height, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);


        spinnerC.setAdapter(adapter);
        spinnerH.setAdapter(adapter2);


        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                updateCalendar();

            }

            private void updateCalendar() {
                String Format = "dd/MM/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(Format, Locale.UK);

                dp1.setText(sdf.format(calendar.getTime()));
            }
        };

        dp1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(RegisterMissingPerson.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

       mapbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getApplicationContext(),Locater.class);
                startActivity(intent);
            }
        });





    }
   /* private void openMap() {
        Intent intent = new Intent(this, Locater.class);
        startActivity(intent);
    }*/

}

