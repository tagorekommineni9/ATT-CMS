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

import retrofit2.Retrofit;

public class RegisterComplaint extends AppCompatActivity {

    private Retrofit retrofit;
    private String BASE_URL = "http://192.168.1.22:3033";

    EditText location,complaintDetails;
    RadioGroup categoryType, complaintType;
    TextView submitComplaint;

    //FirebaseAuth fAuth;

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


        // fAuth = FirebaseAuth.getInstance();

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

                //System.out.println("Inside on lcick e Calling ******** ");
                final HashMap<String,String>map =new HashMap<>();
                map.put("location",mlocation);
                map.put("category",mcategorytype);
                map.put("comptype",mcomplainttype);
                map.put("compdetails",mcomplaintdetails);
                //Network network = new Network();
                //network.networkCall(map,"http://172.18.99.225:3012/register");
                Thread thread = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try {

                            try {

                               // System.out.println("Calling ******** ");
                                URL url = new URL("http://192.168.1.22:3033/registercomplaint");
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


                                int responseCode = client.getResponseCode();

                                Toast.makeText(RegisterComplaint.this,"Complaint filed successfully !",Toast.LENGTH_LONG).show();
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



 /* FirebaseFirestore ffdb = FirebaseFirestore.getInstance();

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

 */
            }
        });
    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder feedback = new StringBuilder();
        String f = "r";
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (f.equals("r"))
                f = "fuyf";
            else
                feedback.append("&");

            feedback.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            feedback.append("=");
            feedback.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return feedback.toString();
    }
}