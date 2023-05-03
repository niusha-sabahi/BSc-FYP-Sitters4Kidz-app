package com.example.sitters4kidz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class JobPostDetailsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_post_details_page);

        String username = getIntent().getStringExtra("USERNAME");
        String user_type = getIntent().getStringExtra("USER_TYPE");
        String job_ID = getIntent().getStringExtra("JOB_ID");

        TextView job_ID_tv = findViewById(R.id.job_post_details_text);
        job_ID_tv.setText(job_ID);

    }
}