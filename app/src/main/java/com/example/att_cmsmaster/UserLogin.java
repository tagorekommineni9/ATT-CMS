package com.example.att_cmsmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UserLogin extends AppCompatActivity {



    TextView userRegisterQuestion;
    TextView staffLoginQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

;

        userRegisterQuestion = findViewById(R.id.userRegisterQuestion);
        staffLoginQuestion = findViewById(R.id.staffLoginQuestion);

        userRegisterQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerQuesIntent = new Intent(v.getContext(),UserRegister.class);
                startActivity(registerQuesIntent);
            }
        });

        staffLoginQuestion .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent staffLoginQuesIntent = new Intent(getApplicationContext(),StaffLogin.class);
                startActivity(staffLoginQuesIntent);
            }
        });


    }
}
