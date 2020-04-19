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
    private Retrofit retrofit;
    // private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://192.168.1.22:3033";


    EditText memail;
    EditText mpassword;
    Button mloginbtn;

    /* FirebaseAuth fAuth; */
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
        /* fAuth = FirebaseAuth.getInstance(); */
        mprogressbar = findViewById(R.id.progressBarUserLogin);

        mloginbtn = findViewById(R.id.userloginButton);

        userRegisterQuestion = findViewById(R.id.userRegisterQuestion);
        staffLoginQuestion = findViewById(R.id.staffLoginQuestion);

        mloginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String , String > map =new HashMap<>();
                map.put("email", memail.getText().toString());
                map.put("pass",mpassword.getText().toString());



                Thread thread = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try {

                            try {
                                HashMap<String , String >map =new HashMap<>();
                                map.put("email", memail.getText().toString());
                                map.put("pass",mpassword.getText().toString());

                                URL url = new URL("http://192.168.1.22:3033/login");
                                HttpURLConnection client = null;
                                client = (HttpURLConnection) url.openConnection();
                                client.setRequestMethod("POST");
                                client.setDoInput(true);
                                client.setDoOutput(true);

                                OutputStream os = client.getOutputStream();
                                BufferedWriter writer = new BufferedWriter(
                                        new OutputStreamWriter(os, "UTF-8"));
                                writer.write(getPostDataString(map));

                                writer.flush();
                                writer.close();
                                os.close();
                                BufferedReader br;

                                if (200 <= client.getResponseCode() && client.getResponseCode() <= 299) {
                                    br = new BufferedReader(new InputStreamReader(client.getInputStream()));

                                } else {
                                    br = new BufferedReader(new InputStreamReader(client.getErrorStream()));

                                }
                                String content = br.readLine();

                                if(content.contains("Valid user")){
                                    String e1=memail.getText().toString();
                                    Intent i=new Intent(UserLogin.this,UserDashboard.class);
                                    i.putExtra("email",e1);
                                    // Toast.makeText(MainActivity.this,"Login Success",Toast.LENGTH_LONG).show();



                                    startActivity(i);
                                }
                                else{
                                    // Toast.makeText(MainActivity.this,"Wrong Credentials",Toast.LENGTH_LONG).show();

                                }
                                int responseCode = client.getResponseCode();

                            }
                            catch (Exception e){
                                e.printStackTrace();
                                System.out.println("ERROR ******** "+e.getMessage());
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

                thread.start();
            }
        });






 /* mloginbtn.setOnClickListener(new View.OnClickListener() {
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

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder feedback = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                feedback.append("&");

            feedback.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            feedback.append("=");
            feedback.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return feedback.toString();
    }


}