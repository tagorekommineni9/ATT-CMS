package com.example.att_cmsmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class UserDashboard extends AppCompatActivity {

    TextView userProfileSettings,userRegisterComplaint ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        userProfileSettings = findViewById(R.id.textViewUserProfileSettings);
        userRegisterComplaint = findViewById(R.id.textViewRegisterComplaint);

        userProfileSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserDashboard.this,EditProfile.class));
            }
        });

        userRegisterComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserDashboard.this,RegisterComplaint.class));
            }
        });
    }

    public void userlogout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity( new Intent(getApplicationContext(),UserLogin.class));
        finish();
    }
}
