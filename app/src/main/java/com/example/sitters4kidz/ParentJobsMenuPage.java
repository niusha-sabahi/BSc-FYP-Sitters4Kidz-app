package com.example.sitters4kidz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class ParentJobsMenuPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_jobs_menu_page);

        String username = getIntent().getStringExtra("USERNAME");

        // Execute this code when the 'New Job Post' button is pressed,
        // takes user to the parent's version of the 'Search' page.
        Button new_job_post_button = (Button) findViewById(R.id.new_job_post_butt);
        new_job_post_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ParentJobsMenuPage.this,
                        NewJobPostPage.class);
                intent.putExtra("USERNAME", username);
                startActivity(intent);
            }
        });

        // Execute this code when the home button in the left of the bottom tab is pressed,
        // takes user to the parent's version of the 'Search' page.
        ImageButton jobs_button = (ImageButton) findViewById(R.id.home_butt);
        jobs_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ParentJobsMenuPage.this,
                        ParentHomePage.class);
                startActivity(intent);
            }
        });

        // Execute this code when the messages button in the right of the bottom tab is pressed,
        // takes user to the 'Messages' page.
        ImageButton msgs_button = (ImageButton) findViewById(R.id.messages_butt);
        msgs_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ParentJobsMenuPage.this,
                        MessagesMenuPage.class);
                startActivity(intent);
            }
        });

    }
}