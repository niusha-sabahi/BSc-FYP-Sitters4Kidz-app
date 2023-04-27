package com.example.sitters4kidz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class SettingsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);

        String username = getIntent().getStringExtra("USERNAME");
        String user_type = getIntent().getStringExtra("USER_TYPE");

        // Execute this code when the 'Log Out' button is pressed.
        Button log_out_butt = (Button) findViewById(R.id.log_out_butt);
        log_out_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Takes the user back to the 'Log In' page.
                Intent intent = new Intent(SettingsPage.this,
                        LogInPage.class);
                startActivity(intent);

            }
        });

        // Execute this code when the back button in the top right corner is pressed,
        // takes user to the 'Home' page.
        ImageButton back_button = (ImageButton) findViewById(R.id.back_butt);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;
                if(user_type == "childcarer") {
                    intent = new Intent(SettingsPage.this,
                            ChildcarerHomePage.class);
                } else{
                    intent = new Intent(SettingsPage.this,
                            ParentHomePage.class);
                }
                intent.putExtra("USER_TYPE", user_type);
                intent.putExtra("USERNAME", username);
                startActivity(intent);

            }
        });

    }
}