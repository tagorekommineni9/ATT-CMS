package com.example.att_cmsmaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;

public class EditProfile extends AppCompatActivity {

    EditText oldEmail, newEmail, conformNewEmail, password;
    Button changeEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        oldEmail = findViewById(R.id.old_email);
        newEmail = findViewById(R.id.new_email);
        conformNewEmail = findViewById(R.id.confirm_newEmail);
        password = findViewById(R.id.current_pass);

        changeEmail = findViewById(R.id.btnChangeEmail);


        final String email=getIntent().getStringExtra("email");
        final String pass=getIntent().getStringExtra("pass");


        changeEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pass.equals(password.getText().toString())){
                    if(email.equals(oldEmail.getText().toString())){
                        if((newEmail.getText().toString()).equals(conformNewEmail.getText().toString())){
                            final HashMap<String,String> map =new HashMap<>();
                            map.put("pass",pass);
                            map.put("email",newEmail.getText().toString());
                            Network network = new Network();
                            network.networkCall(map,"http://192.168.1.22:3033/updateprofile");
                            Intent i =new Intent(EditProfile.this,UserDashboard.class);
                            i.putExtra("email",newEmail.getText().toString());

                            startActivity(i);
                        }
                    }
                }
            }
        });
/*
        changeEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                if (!(newEmail.getText().toString()).equals(conformNewEmail.getText().toString())) {
                    Toast.makeText(EditProfile.this, "Make sure you conform the new email !", Toast.LENGTH_LONG).show();
                    return;
                }


                AuthCredential credential= EmailAuthProvider.getCredential(user.getEmail(),password.getText().toString());


                user.reauthenticateAndRetrieveData(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            user.updateEmail(newEmail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(EditProfile.this, "Your email updated successfully !", Toast.LENGTH_LONG).show();
                                    }
                                    else{
                                        Toast.makeText(EditProfile.this, "Your new email failed to update!", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                            /* if (newEmail.equals(conformNewEmail)) {
                                 user.updateEmail(String.valueOf(newEmail));
                                 Toast.makeText(EditProfile.this, "Your email updated successfully !", Toast.LENGTH_LONG).show();
                             } else {
                                 Toast.makeText(EditProfile.this, "Make sure you conform the new email !", Toast.LENGTH_LONG).show();
                             } */
            /*            }
                        else{
                            Toast.makeText(EditProfile.this," Error Authenticating ! " + task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });

                 /*addOnFailureListener(new OnFailureListener() {
                     @Override
                     public void onFailure(@NonNull Exception e) {
                         Toast.makeText(EditProfile.this,"Please enter the correct password !",Toast.LENGTH_LONG).show();
                     }
                 });

              /*  if(newEmail.equals(conformNewEmail)) {
                    user.updateEmail(String.valueOf(newEmail)).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(EditProfile.this,"Your email updated successfully !",Toast.LENGTH_LONG).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(EditProfile.this,"Failed to update your email!",Toast.LENGTH_LONG).show();
                        }
                    });
                }
                else {
                    Toast.makeText(EditProfile.this,"Error occurred!" ,Toast.LENGTH_LONG).show();
                } */
            }







}
