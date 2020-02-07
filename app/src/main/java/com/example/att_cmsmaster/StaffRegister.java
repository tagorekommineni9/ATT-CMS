package com.example.att_cmsmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class StaffRegister extends AppCompatActivity {
    EditText staffId;
    EditText fullname;
    EditText email;
    EditText phone;
    EditText password;
    EditText confirmPassword;

    Button registerButton;
    TextView loginQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_register);

        //Collaboration Test - Tanya

        staffId = findViewById(R.id.staff_id);
        fullname = findViewById(R.id.fullname);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);

        registerButton = findViewById(R.id.regButton);
        loginQuestion = findViewById(R.id.loginQuestion);


        loginQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(getApplicationContext(),StaffLogin.class);
                startActivity(loginIntent);
            }
        });
    }
}
