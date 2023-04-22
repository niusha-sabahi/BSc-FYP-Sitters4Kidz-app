package com.example.sitters4kidz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.FirebaseFirestore;

public class NewJobPostPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_job_post_page);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        EditText date_inp = (EditText) findViewById(R.id.date_inp);
        EditText start_hour_inp = (EditText) findViewById(R.id.start_hour_inp);
        EditText start_min_inp = (EditText) findViewById(R.id.start_min_inp);
        EditText duration_hour_inp = (EditText) findViewById(R.id.duration_hour_inp);
        EditText duration_min_inp = (EditText) findViewById(R.id.duration_min_inp);

        // Execute this code when the 'Submit' button is pressed.
        Button submit_butt = (Button) findViewById(R.id.submit_new_job_post_butt);
        submit_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String date = date_inp.getText().toString();
                Integer start_hour = Integer.parseInt(start_hour_inp.getText().toString());
                Integer start_min = Integer.parseInt(start_min_inp.getText().toString());
                Integer duration_hour = Integer.parseInt(duration_hour_inp.getText().toString());
                Integer duration_min = Integer.parseInt(duration_min_inp.getText().toString());

                // TODO: input validation
                // TODO: create a new collection for jobs
                // TODO: upload data here to db

            }
        });
    }
}