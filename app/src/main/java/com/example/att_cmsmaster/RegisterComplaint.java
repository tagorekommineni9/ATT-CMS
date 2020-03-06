package com.example.att_cmsmaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class RegisterComplaint extends AppCompatActivity {

    EditText location,complaintDetails;
    RadioGroup categoryType, complaintType;
    TextView submitComplaint;

    FirebaseAuth fAuth;

    RadioButton radioButtonCategory, radioButtonComplaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_complaint);

        location = findViewById(R.id.editTextLocation);
        complaintDetails = findViewById(R.id.editTextMessage);
        categoryType = findViewById(R.id.radiogroupCategoryType);
        complaintType = findViewById(R.id.radiogroupComplaintType);
        submitComplaint = findViewById(R.id.btnSubmitComplaint);


        fAuth = FirebaseAuth.getInstance();

        submitComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedCatType = categoryType.getCheckedRadioButtonId();
                radioButtonCategory = (RadioButton) findViewById(selectedCatType);

                int selectedComType = complaintType.getCheckedRadioButtonId();
                radioButtonComplaint = findViewById(selectedComType);

                final String mlocation = location.getText().toString();
                final String mcategorytype = radioButtonCategory.getText().toString();
                final String mcomplainttype = radioButtonComplaint.getText().toString();
                final String mcomplaintdetails = complaintDetails.getText().toString();

                FirebaseFirestore ffdb = FirebaseFirestore.getInstance();

                HashMap<String, Object> complaint= new HashMap<>();
                complaint.put("location",mlocation);
                complaint.put("categorytype",mcategorytype);
                complaint.put("complainttype",mcomplainttype);
                complaint.put("complaintdetails",mcomplaintdetails);

                ffdb.collection("Complaints")
                        .add(complaint)
                        .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                Toast.makeText(RegisterComplaint.this,"Complaint filed successfully !",Toast.LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterComplaint.this,"Error occurred in filing your complaint",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
