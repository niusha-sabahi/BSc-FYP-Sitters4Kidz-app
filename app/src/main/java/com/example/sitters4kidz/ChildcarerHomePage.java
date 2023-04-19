package com.example.sitters4kidz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class ChildcarerHomePage extends AppCompatActivity implements RecyclerViewInterface{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_childcarer_home_page);

        // Get all inputs
        EditText city_inp = (EditText) findViewById(R.id.city_inp);
        EditText agel_inp = (EditText) findViewById(R.id.age_lower_inp);
        EditText ageu_inp = (EditText) findViewById(R.id.age_upper_inp);

        RecyclerView search_results = findViewById(R.id.childcarer_homepage_search_recyclerview);
        search_results.setLayoutManager(new LinearLayoutManager(this));

        // Execute this code when the 'Search' button is pressed.
        Button search_butt = (Button) findViewById(R.id.search_butt);
        search_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String city = city_inp.getText().toString();
                String agel = agel_inp.getText().toString();
                String ageu = ageu_inp.getText().toString();

                ArrayList<HomePageSearchItem> items = new ArrayList<>();
                for ( int i = 0; i < 31; i++){
                    items.add(new HomePageSearchItem("parent user " + i));
                }

                HomePageSearchAdapter adapter = new HomePageSearchAdapter(getApplicationContext(), items);
                search_results.setAdapter(adapter);

            }
        });

        // Execute this code when the menu button in the top-left corner is pressed,
        // takes user to the 'Settings' page.
        ImageButton menu_button = (ImageButton) findViewById(R.id.menu_butt);
        menu_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChildcarerHomePage.this,
                        SettingsPage.class);
                startActivity(intent);
            }
        });

        // Execute this code when the jobs button in the middle of the bottom tab is pressed,
        // takes user to the child-carer's version of the 'Jobs' page.
        ImageButton jobs_button = (ImageButton) findViewById(R.id.jobs_butt);
        jobs_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChildcarerHomePage.this,
                        ChildcarerJobsMenuPage.class);
                startActivity(intent);
            }
        });

        // Execute this code when the messages button in the right of the bottom tab is pressed,
        // takes user to the 'Messages' page.
        ImageButton msgs_button = (ImageButton) findViewById(R.id.messages_butt);
        msgs_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChildcarerHomePage.this,
                        MessagesMenuPage.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onClickRecyclerItem(int position) {

    }
}