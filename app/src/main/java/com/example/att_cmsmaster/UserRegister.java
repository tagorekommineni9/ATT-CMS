package com.example.att_cmsmaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserRegister extends AppCompatActivity {

    EditText mfullname;
    EditText memail;
    EditText mphone;
    EditText mpassword;
    EditText mconfirmpassword;

    TextView userLoginQuestion;

    Button userRegister;

    FirebaseAuth  fAuth;
    ProgressBar progressBar;

    //FirebaseDatabase database = FirebaseDatabase.getInstance();
   // DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        setTitle("User Registration");

        mfullname = findViewById(R.id.fullname);
        memail = findViewById(R.id.email);
        mphone = findViewById(R.id.phone);
        mpassword = findViewById(R.id.password);
        mconfirmpassword = findViewById(R.id.confirmPassword);

        userRegister = findViewById(R.id.regButton);

        userLoginQuestion = findViewById(R.id.loginQuestion);

        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBarUserRegister);

        if(fAuth.getCurrentUser() != null){
            startActivity( new Intent(getApplicationContext(),UserDashboard.class));
            finish();
        }

        userRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = memail.getText().toString().trim();
                String password = mpassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    memail.setError("Email is Required!");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    mpassword.setError("Password is Required!");
                    return;
                }

                if(password.length() <6){
                    mpassword.setError("Password must be at least 6 characters");
                    return;
                }

                if(!mpassword.getText().toString().equals(mconfirmpassword.getText().toString())){
                    mconfirmpassword.setError("Please make sure your passwords match");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                // regsitering the user in firebase

                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(UserRegister.this," Yay! You are registered.",Toast.LENGTH_SHORT).show();
                            startActivity( new Intent(getApplicationContext(),UserDashboard.class));
                        }
                        else{
                            Toast.makeText(UserRegister.this," Error ! " + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        userLoginQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent uLoginIntent =  new Intent(v.getContext(),UserLogin.class);
                startActivity(uLoginIntent);
            }
        });

    }


  /*  private void writeNewUser(String userId, String name, String email, String phone, String password) {
        User user = new User(name, email, phone, password);

        myRef.child("users").child(userId).setValue(user);
    }  */


}
