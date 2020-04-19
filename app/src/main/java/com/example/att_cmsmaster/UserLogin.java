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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class UserLogin extends AppCompatActivity {



    EditText memail;
    EditText mpassword;
    Button mloginbtn;

/*    FirebaseAuth fAuth; */
    ProgressBar mprogressbar;

    TextView userRegisterQuestion;
    TextView staffLoginQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);



        setTitle("User Login");

        memail= findViewById(R.id.userloginemail);
        mpassword = findViewById(R.id.userloginpassword);
 /*       fAuth = FirebaseAuth.getInstance(); */
        mprogressbar = findViewById(R.id.progressBarUserLogin);

        mloginbtn = findViewById(R.id.userloginButton);

        userRegisterQuestion = findViewById(R.id.userRegisterQuestion);
        staffLoginQuestion = findViewById(R.id.staffLoginQuestion);
        




     /*   mloginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temail = memail.getText().toString().trim();
                String tpassword = mpassword.getText().toString().trim();

                if (TextUtils.isEmpty(temail)) {
                    memail.setError("Email is Required.");
                    return;
                }

                if (TextUtils.isEmpty(tpassword)) {
                    mpassword.setError("Password is Required.");
                    return;
                }

                if (tpassword.length() < 6) {
                    mpassword.setError("Password Must be >= 6 Characters");
                    return;
                }

                mprogressbar.setVisibility(View.VISIBLE);

                // authenticate the user

                try {
                    if (mpassword.length() > 0 && memail.length() > 0) {
                        fAuth.signInWithEmailAndPassword(temail, tpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(UserLogin.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                                    mprogressbar.setVisibility(View.GONE);
                                    startActivity(new Intent(getApplicationContext(), UserDashboard.class));
                                    finish();

                                } else {
                                    Toast.makeText(UserLogin.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    mprogressbar.setVisibility(View.GONE);
                                }

                            }
                        });
                    } else {
                        Toast.makeText(
                                UserLogin.this, "Fill All Fields", Toast.LENGTH_LONG).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

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
        }); */


    }




}
