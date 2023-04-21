package com.example.sitters4kidz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ChildcarerHomePage extends AppCompatActivity implements RecyclerViewInterface{

    ArrayList<HomePageSearchItem> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_childcarer_home_page);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Get all inputs
        EditText city_inp = (EditText) findViewById(R.id.city_inp);
        EditText agel_inp = (EditText) findViewById(R.id.age_lower_inp);
        EditText ageu_inp = (EditText) findViewById(R.id.age_upper_inp);

        RecyclerView search_results = findViewById(R.id.childcarer_homepage_search_recyclerview);
        search_results.setLayoutManager(new LinearLayoutManager(this));
        HomePageSearchAdapter adapter = new HomePageSearchAdapter(getApplicationContext(),
                items, this);

        // Execute this code when the 'Search' button is pressed.
        Button search_butt = (Button) findViewById(R.id.search_butt);
        search_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String city = city_inp.getText().toString().toLowerCase();
                String agel = agel_inp.getText().toString();
                String ageu = ageu_inp.getText().toString();

                final String[] q_username = new String[1];

                if (city.isEmpty()){
                    showToast("please enter a city name");
                } else {
                    if (2 <= city.length() && city.length() <= 30 && city.matches("^[a-zA-Z]*$")
                            &&  ageu.isEmpty() && agel.isEmpty() ) {

                        db.collection("users")
                                .whereEqualTo("city", city)
                                .whereEqualTo("user_type", "parent")
                                .get()
                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                                        items.clear();
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            q_username[0] = document.getId();
                                            items.add(new HomePageSearchItem("parent user " +
                                                    q_username[0]));
                                        }
                                    }
                                });

                        // Sets the RecyclerView adapter for the search results.
                        search_results.setAdapter(adapter);

                    } else {
                        showToast("the city/town name is invalid, " +
                                "please enter one between the range of 2-30 " +
                                "characters, and one which only includes " +
                                "characters a-z and A-Z");
                    }

                    if (2 <= city.length() && city.length() <= 30 && city.matches("^[a-zA-Z]*$")
                            && ( ageu.isEmpty() | agel.isEmpty() )) {
                        showToast("the lower or upper bound for child age range has not been " +
                                "entered, please enter both");

                    } else {
                        if (ageu.length() <= 2 && agel.length() <= 2){

                            //TODO: add code here for if age range is entered.
                            //TODO: comment this activity

                        } else {
                            showToast("please enter ages that have 1 or 2 characters");
                        }
                    }
                }
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

    // Takes user to a new page, which is the profile page of the parent user which they clicked on
    // in the RecyclerView of the search results.
    @Override
    public void onClickRecyclerItem(int position) {

        Intent intent = new Intent(ChildcarerHomePage.this, ParentProfilePage.class);
        intent.putExtra("PARENT_USERNAME", items.get(position).getUsername());
        startActivity(intent);

    }

    // A function for generating Toasts. To simplify code, and reduce repetition.
    private void showToast(String text){
        Toast.makeText(ChildcarerHomePage.this, text, Toast.LENGTH_SHORT).show();
    }

}