package com.example.att_cmsmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class StaffLogin extends AppCompatActivity {
    EditText adminID;
    EditText adminPassword;

    Button adminLoginButton;
    TextView staffRegisterQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_login);

        setTitle("Staff Login");

        adminID = findViewById(R.id.adminID);
        adminPassword = findViewById(R.id.adminPass);

        adminLoginButton = findViewById(R.id.adminLgnButton);
        staffRegisterQuestion = findViewById(R.id.staff_reg_question);

        staffRegisterQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent staffRegIntent = new Intent(getApplicationContext(), StaffRegister.class);
                startActivity(staffRegIntent);

            }
        });
    }
}
