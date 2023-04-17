package com.example.sitters4kidz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class ParentHomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_home_page);

        // Get all inputs
        EditText city_inp = (EditText) findViewById(R.id.city_inp);
        EditText payl_inp = (EditText) findViewById(R.id.pay_lower_inp);
        EditText payu_inp = (EditText) findViewById(R.id.pay_upper_inp);

        // Execute this code when the 'Search' button is pressed.
        Button search_butt = (Button) findViewById(R.id.search_butt);
        search_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String city = city_inp.getText().toString();
                String payl = payl_inp.getText().toString();
                String payu = payu_inp.getText().toString();

            }
        });

        // Execute this code when the menu button in the top-left corner is pressed,
        // takes user to the 'Settings' page.
        ImageButton menu_button = (ImageButton) findViewById(R.id.menu_butt);
        menu_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ParentHomePage.this,
                        SettingsPage.class);
                startActivity(intent);
            }
        });

        // Execute this code when the jobs button in the middle of the bottom tab is pressed,
        // takes user to the parent's version of the 'Jobs' page.
        ImageButton jobs_button = (ImageButton) findViewById(R.id.jobs_butt);
        jobs_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ParentHomePage.this,
                        ParentJobsMenuPage.class);
                startActivity(intent);
            }
        });

        // Execute this code when the messages button in the right of the bottom tab is pressed,
        // takes user to the 'Messages' page.
        ImageButton msgs_button = (ImageButton) findViewById(R.id.messages_butt);
        msgs_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ParentHomePage.this,
                        MessagesMenu.class);
                startActivity(intent);
            }
        });

    }
}