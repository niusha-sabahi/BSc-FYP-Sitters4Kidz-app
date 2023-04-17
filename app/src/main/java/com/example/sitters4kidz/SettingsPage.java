package com.example.sitters4kidz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);

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

    }
}