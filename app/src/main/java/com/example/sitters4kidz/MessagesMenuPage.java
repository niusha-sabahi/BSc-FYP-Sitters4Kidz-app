package com.example.sitters4kidz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MessagesMenuPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages_menu);

        String username = getIntent().getStringExtra("USERNAME");
        String user_type = getIntent().getStringExtra("USER_TYPE");

        // Execute this code when the home button in the bottom tab is pressed,
        // takes user to the 'Home' page.
        ImageButton home_button = (ImageButton) findViewById(R.id.home_butt);
        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;
                if(user_type.equals("childcarer")) {
                    intent = new Intent(MessagesMenuPage.this,
                            ChildcarerHomePage.class);
                } else{
                    intent = new Intent(MessagesMenuPage.this,
                            ParentHomePage.class);
                }
                intent.putExtra("USER_TYPE", user_type);
                intent.putExtra("USERNAME", username);
                startActivity(intent);

            }
        });

        // Execute this code when the jobs button in middle of the bottom tab is pressed,
        // takes user to the 'Jobs Menu' page.
        ImageButton jobs_button = (ImageButton) findViewById(R.id.jobs_butt);
        jobs_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;
                if(user_type.equals("childcarer")) {
                    intent = new Intent(MessagesMenuPage.this,
                            ChildcarerJobsMenuPage.class);
                } else{
                    intent = new Intent(MessagesMenuPage.this,
                            ParentJobsMenuPage.class);
                }
                intent.putExtra("USER_TYPE", user_type);
                intent.putExtra("USERNAME", username);
                startActivity(intent);

            }
        });

    }
}