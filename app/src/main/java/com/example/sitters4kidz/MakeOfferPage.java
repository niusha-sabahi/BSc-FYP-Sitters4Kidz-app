package com.example.sitters4kidz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MakeOfferPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_offer_page);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        String parent_username = getIntent().getStringExtra("PARENT_USERNAME");
        String username = getIntent().getStringExtra("USERNAME");
        String user_type = getIntent().getStringExtra("USER_TYPE");
        String job_ID = getIntent().getStringExtra("JOB_ID");

        TextView job_parent = findViewById(R.id.job_parent);
        StringBuilder sb1 = new StringBuilder();
        sb1.append("parent username: ");
        sb1.append(parent_username);
        String job_parent_text = sb1.toString();
        job_parent.setText(job_parent_text);

        // Retrieves the data needed for the job post based on their ID.
        db.collection("job_posts")
                .whereEqualTo(FieldPath.documentId(), job_ID)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        // Retrieve the job post details, format the strings and display the
                        // data in the relevant views.
                        for (QueryDocumentSnapshot document : task.getResult()) {

                            TextView job_date = findViewById(R.id.job_date);
                            TextView job_start = findViewById(R.id.job_start);
                            TextView job_duration = findViewById(R.id.job_duration);

                            StringBuilder sb2 = new StringBuilder();
                            Long date_dayL = (Long) document.get("date_day");
                            int date_day = date_dayL.intValue();
                            Long date_monthL = (Long) document.get("date_month");
                            int date_month = date_monthL.intValue();
                            Long date_yearL = (Long) document.get("date_year");
                            int date_year = date_yearL.intValue();
                            sb2.append("date: ");
                            sb2.append(Integer.toString(date_day));
                            sb2.append(" / ");
                            sb2.append(Integer.toString(date_month));
                            sb2.append(" / ");
                            sb2.append(Integer.toString(date_year));
                            String date = sb2.toString();
                            job_date.setText(date);


                            StringBuilder sb3 = new StringBuilder();
                            Long start_hourL = (Long) document.get("start_hour");
                            int start_hour = start_hourL.intValue();
                            Long start_minL = (Long) document.get("start_min");
                            int start_min = start_minL.intValue();
                            sb3.append("job start time is ");
                            sb3.append(Integer.toString(start_hour));
                            sb3.append(" : ");
                            sb3.append(Integer.toString(start_min));
                            String start_time = sb3.toString();
                            job_start.setText(start_time);

                            StringBuilder sb4 = new StringBuilder();
                            Long duration_hourL = (Long) document.get("duration_hour");
                            int duration_hour = duration_hourL.intValue();
                            Long duration_minL = (Long) document.get("duration_min");
                            int duration_min = duration_minL.intValue();
                            sb4.append("job duration time is ");
                            sb4.append(Integer.toString(duration_hour));
                            sb4.append(" : ");
                            sb4.append(Integer.toString(duration_min));
                            String duration_time = sb4.toString();
                            job_duration.setText(duration_time);

                        }
                    }
                });

        // Execute this code when the 'Make Offer' button is pressed. Which adds the user's username
        // to the array of child-carer usernames which have applied to this job.
        Button make_offer_butt = (Button) findViewById(R.id.make_offer_butt);
        make_offer_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Check if the user is already in the 'applications' list for this job, and if
                // they're not, add them to it.
                DocumentReference doc_ref = db
                        .collection("job_posts")
                        .document(job_ID);
                doc_ref.get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {

                                // Get the array of applicants
                                ArrayList<String> applicants = (ArrayList<String>) documentSnapshot.get("applications");

                                // Check if the user is already in the list, if not, then add them
                                if (applicants != null && applicants.contains(username)) {
                                    showToast("you are already in the list");
                                } else {

                                    applicants.add(username);

                                    doc_ref.update("applications", applicants);

                                    /*Map<String, Object> updated_apps = new HashMap<>();
                                    updated_apps.put("applications", applicants);
                                    db.collection("job_posts").document(job_ID)
                                            .set(updated_apps);*/

                                    showToast("you have applied to work this job!");

                                }
                            }
                        });

            }
        });

        // Execute this code when the 'Back' button is pressed. Taking the user back to that
        // parent's profile.
        Button back_butt = (Button) findViewById(R.id.back_butt_mop);
        back_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MakeOfferPage.this,
                        ParentProfilePage.class);
                intent.putExtra("PARENT_USERNAME", parent_username);
                intent.putExtra("USERNAME", username);
                intent.putExtra("USER_TYPE", user_type);
                startActivity(intent);

            }
        });

    }
    // A function for generating Toasts. To simplify code, and reduce repetition.
    private void showToast(String text){
        Toast.makeText(MakeOfferPage.this, text, Toast.LENGTH_LONG).show();
    }
}