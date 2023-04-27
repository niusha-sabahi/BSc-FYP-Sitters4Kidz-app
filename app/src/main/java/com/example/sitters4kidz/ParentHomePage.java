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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ParentHomePage extends AppCompatActivity {

    ArrayList<ParentHomePageSearchItem> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_home_page);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Get all inputs
        EditText city_inp = (EditText) findViewById(R.id.city_inp);
        EditText pay1_inp = (EditText) findViewById(R.id.pay_lower_inp);
        EditText pay2_inp = (EditText) findViewById(R.id.pay_upper_inp);

        // Sets up the RecyclerView for when the results are displayed
        RecyclerView search_results = findViewById(R.id.parent_homepage_search_recyclerview);
        search_results.setLayoutManager(new LinearLayoutManager(this));
        ParentHomePageSearchAdapter adapter = new ParentHomePageSearchAdapter(getApplicationContext(), items);

        String username = getIntent().getStringExtra("USERNAME");
        String user_type = getIntent().getStringExtra("USER_TYPE");

        // Execute this code when the 'Search' button is pressed.
        Button search_butt = (Button) findViewById(R.id.search_butt);
        search_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String city = city_inp.getText().toString().toLowerCase().trim();
                String pay1 = pay1_inp.getText().toString().trim();
                String pay2 = pay2_inp.getText().toString().trim();

                boolean is_city_valid = 2 <= city.length() && city.length() <= 30
                        && city.matches("^[a-zA-Z]*$");

                // Checks that a city name has been entered, and if not, tells user to do so.
                if (city.isEmpty()){
                    showToast("please enter a city name");
                } else {
                    // If no pay range has been entered: Checks if the city name entered is valid,
                    // if not, tells the user to re-enter a valid one.
                    if (is_city_valid && pay1.isEmpty() && pay2.isEmpty() ) {

                        // This search will return all child-carer users which live in the city that
                        // the user has entered.
                        db.collection("users")
                                .whereEqualTo("city", city)
                                .whereEqualTo("user_type", "childcarer")
                                .get()
                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                                        // Adds the retrieved usernames into the RecyclerView
                                        items.clear();
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            String q_username = document.getId();
                                            String email = String.valueOf(document.get("email"));
                                            String rate = String.valueOf(document.get("pay_rate"));

                                            StringBuilder sb = new StringBuilder();
                                            sb.append("£ ");
                                            sb.append(rate);
                                            String rate_text = sb.toString();

                                            items.add(new ParentHomePageSearchItem(q_username, email, rate_text));

                                        }

                                    }
                                });


                        // Sets the RecyclerView adapter for the search results.
                        search_results.setAdapter(adapter);

                        // Checks if the user has only entered one value for the pay range, in which
                        // case, the user is asked to enter both.
                    } else if ( (is_city_valid && pay1.isEmpty() && !pay2.isEmpty()) |
                            (is_city_valid && pay2.isEmpty() && !pay1.isEmpty()) ) {
                        showToast("the lower or upper bound for pay range has not been " +
                                "entered, please enter both");

                        // If the user has chosen to search with pay range also, this code checks
                        // that the city name is valid.
                    } else if (is_city_valid && !pay1.isEmpty() && !pay2.isEmpty()) {

                        // If both pay rate inputs are valid, then perform this search instead,
                        // which also uses the pay limits.
                        if (pay1.length() <= 6 && pay2.length() <= 6) {

                            // This search will return all childcarer users which charge within the
                            // pay range entered. Of course, the profiles returned are again, those
                            // who only live in the city entered by the user also.
                            db.collection("users")
                                    .whereEqualTo("city", city)
                                    .whereEqualTo("user_type", "childcarer")
                                    .get()
                                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                                            // Adds the retrieved usernames into the RecyclerView
                                            items.clear();
                                            for (QueryDocumentSnapshot document : task.getResult()) {

                                                String q_username = document.getId();
                                                String email = String.valueOf(document.get("email"));
                                                String rate = String.valueOf(document.get("pay_rate"));

                                                float int_rate = Float.parseFloat(rate);
                                                float int_pay1 = Float.parseFloat(pay1);
                                                float int_pay2 = Float.parseFloat(pay2);

                                                boolean b1 = int_rate <= int_pay2;
                                                boolean b2 = int_rate >= int_pay1;

                                                if(b1 && b2) {

                                                    StringBuilder sb = new StringBuilder();
                                                    sb.append("£ ");
                                                    sb.append(rate);
                                                    String rate_text = sb.toString();
                                                    items.add(new ParentHomePageSearchItem(q_username, email, rate_text));

                                                }
                                            }

                                        }
                                    });

                            // Sets the RecyclerView adapter for the search results.
                            search_results.setAdapter(adapter);

                            // Tell user to re-enter a valid pay ranges
                        } else {
                            showToast("please enter numbers for the pay range within 6 " +
                                    "characters");
                        }

                        // Tell user to re-enter a valid city name
                    } else {
                        showToast("the city/town name is invalid, " +
                                "please enter one between the range of 2-30 " +
                                "characters, and one which only includes " +
                                "characters a-z and A-Z");
                    }
                }

            }
        });

        /*// Execute this code when the menu button in the top-left corner is pressed,
        // takes user to the 'Settings' page.
        //TODO: debug the settings button on parent home page ERR: java.lang.ClassCastException: androidx.appcompat.widget.AppCompatButton cannot be cast to android.widget.ImageButton
        ImageButton menu_button = (ImageButton) findViewById(R.id.menu_butt_p);
        menu_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ParentHomePage.this,
                        SettingsPage.class);
                intent.putExtra("USERNAME", username);
                intent.putExtra("USER_TYPE", user_type);
                startActivity(intent);
            }
        });*/

        // Execute this code when the jobs button in the middle of the bottom tab is pressed,
        // takes user to the parent's version of the 'Jobs' page.
        ImageButton jobs_button = (ImageButton) findViewById(R.id.jobs_butt);
        jobs_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ParentHomePage.this,
                        ParentJobsMenuPage.class);
                intent.putExtra("USERNAME", username);
                intent.putExtra("USER_TYPE", user_type);
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
                        MessagesMenuPage.class);
                intent.putExtra("USERNAME", username);
                intent.putExtra("USER_TYPE", user_type);
                startActivity(intent);
            }
        });

    }

    // A function for generating Toasts. To simplify code, and reduce repetition.
    private void showToast(String text){
        Toast.makeText(ParentHomePage.this, text, Toast.LENGTH_LONG).show();
    }
}