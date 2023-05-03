package com.example.sitters4kidz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NewJobPostPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_job_post_page);

        String username = getIntent().getStringExtra("USERNAME");
        String user_type = getIntent().getStringExtra("USER_TYPE");

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        EditText date_inp1 = (EditText) findViewById(R.id.date_inp1);
        EditText date_inp2 = (EditText) findViewById(R.id.date_inp2);
        EditText date_inp3 = (EditText) findViewById(R.id.date_inp3);

        EditText start_hour_inp = (EditText) findViewById(R.id.start_hour_inp);
        EditText start_min_inp = (EditText) findViewById(R.id.start_min_inp);
        EditText duration_hour_inp = (EditText) findViewById(R.id.duration_hour_inp);
        EditText duration_min_inp = (EditText) findViewById(R.id.duration_min_inp);

        // Execute this code when the 'Submit' button is pressed.
        Button submit_butt = (Button) findViewById(R.id.submit_new_job_post_butt);
        submit_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String date1 = date_inp1.getText().toString().trim();
                String date2 = date_inp2.getText().toString().trim();
                String date3 = date_inp3.getText().toString().trim();

                String start_hour = start_hour_inp.getText().toString().trim();
                String start_min = start_min_inp.getText().toString().trim();
                String duration_hour = duration_hour_inp.getText().toString().trim();
                String duration_min = duration_min_inp.getText().toString().trim();

                // Mak sure that the inputs are valid and if not, alert the user that the input
                // cannot be accepted and how they should change it.
                if (date1.isEmpty() | date2.isEmpty() | date3.isEmpty() | start_hour.isEmpty()
                        | start_min.isEmpty() | duration_hour.isEmpty() | duration_min.isEmpty()){
                    showToast("some information hasn't been entered, please make sure all " +
                            "fields are provided");
                } else if (date1.length() > 2 | date2.length() > 2 | date3.length() > 2){
                    showToast("please make sure the date is in dd / mm /yy format");
                } else if (start_hour.length() > 2 |  start_min.length() > 2
                        | duration_hour.length() > 2 | duration_min.length() > 2){
                    showToast("please make sure the start time and duration is in hh : mm " +
                            "format");
                } else if(Integer.parseInt(date1) > 31 | Integer.parseInt(date2) > 12
                        | Integer.parseInt(date3) < 23) {
                    showToast("please make sure the date entered is a valid date");
                } else if(Integer.parseInt(start_hour) >= 24 | Integer.parseInt(start_min) >= 60
                        | Integer.parseInt(duration_hour) >= 24 | Integer.parseInt(duration_min) >= 60) {
                    showToast("please make sure the start time and duration entered are valid " +
                            "times ");
                }else {

                    ArrayList<String> applications = new ArrayList<>();

                    // If the input is valid, upload the new Job Post to the 'job_posts' collection
                    // in the database.
                    Map<String, Object> job = new HashMap<>();
                    job.put("parent_username", username);
                    job.put("date_day", Integer.parseInt(date1));
                    job.put("date_month", Integer.parseInt(date2));
                    job.put("date_year", Integer.parseInt(date3));
                    job.put("start_hour", Integer.parseInt(start_hour));
                    job.put("start_min", Integer.parseInt(start_min));
                    job.put("duration_hour", Integer.parseInt(duration_hour));
                    job.put("duration_min", Integer.parseInt(duration_min));
                    job.put("applications", applications);
                    db.collection("job_posts").add(job);

                    // Take user back to the 'Jobs Menu Page'.
                    Intent intent;
                    intent = new Intent(NewJobPostPage.this,
                            ParentJobsMenuPage.class);
                    intent.putExtra("USERNAME", username);
                    intent.putExtra("USER_TYPE", user_type);
                    startActivity(intent);

                    showToast(" the new job post has been added! ");

                }
            }
        });

        // Execute this code when the 'Back' button is pressed.
        // Takes the user back to the previous page, the parent's version of the 'Jobs Menu' page.
        Button back_butt = (Button) findViewById(R.id.back_butt);
        back_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewJobPostPage.this,
                        ParentJobsMenuPage.class);
                intent.putExtra("USERNAME", username);
                intent.putExtra("USER_TYPE", user_type);
                startActivity(intent);
            }
        });

    }

    // A function for generating Toasts. To simplify code, and reduce repetition.
    private void showToast(String text){
        Toast.makeText(NewJobPostPage.this, text, Toast.LENGTH_LONG).show();
    }
}