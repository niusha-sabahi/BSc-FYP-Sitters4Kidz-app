package com.example.sitters4kidz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ChildcarerJobsMenuPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_childcarer_jobs_menu_page);

        String username = getIntent().getStringExtra("USERNAME");
        String user_type = getIntent().getStringExtra("USER_TYPE");

        // Execute this code when the messages button in the right of the bottom tab is pressed,
        // takes user to the 'Messages' page.
        ImageButton msgs_button = (ImageButton) findViewById(R.id.messages_butt);
        msgs_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChildcarerJobsMenuPage.this,
                        MessagesMenuPage.class);
                intent.putExtra("USERNAME", username);
                intent.putExtra("USER_TYPE", user_type);
                startActivity(intent);
            }
        });

        // Execute this code when the home button in the left of the bottom tab is pressed,
        // takes user to the Child-carer's 'Home' page.
        ImageButton home_button = (ImageButton) findViewById(R.id.home_butt);
        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChildcarerJobsMenuPage.this,
                        ChildcarerHomePage.class);
                intent.putExtra("USERNAME", username);
                intent.putExtra("USER_TYPE", user_type);
                startActivity(intent);
            }
        });

    }
}