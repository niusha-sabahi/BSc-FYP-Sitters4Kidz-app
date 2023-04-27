package com.example.sitters4kidz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ParentSignUpXtraPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_sign_up_xtra_page);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        EditText child_age_inp = (EditText) findViewById(R.id.child_age_inp);
        EditText email_add_inp = (EditText) findViewById(R.id.email_add_inp);

        ArrayList<Integer> ages = new ArrayList<>();
        // Grab the data entered by the previous activity.
        String username = getIntent().getStringExtra("USERNAME");
        String password = getIntent().getStringExtra("PASSWORD");
        String city = getIntent().getStringExtra("CITY");
        String user_type = getIntent().getStringExtra("USER_TYPE");

        // Execute this code every time the 'Add' button is pressed.
        Button add_butt = (Button) findViewById(R.id.add_butt);
        add_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String child_age = child_age_inp.getText().toString().trim();
                Integer child_age_int;

                // Check that the age input is not empty, and if not, adds it to the list of children ages.
                if (child_age.isEmpty()){
                    showToast("some information hasn't been entered, please try again");
                } else {
                    if (child_age_inp.length() <= 2) {

                        child_age_int = Integer.parseInt(child_age);
                        ages.add(child_age_int);

                        showToast("child's age has been added");

                    } else {
                        showToast("please enter a valid age");
                    }
                }
            }
        });

        // Execute this code when the 'Submit' button is pressed.
        Button submit_butt = (Button) findViewById(R.id.submit_sing_up_butt2);
        submit_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email_add = email_add_inp.getText().toString().trim();

                // Check that the ages array is not empty, and so that at least one age has been
                // entered.
                if (ages.isEmpty() | email_add.isEmpty()){
                    showToast("some information is missing, please enter at least one age " +
                            "and an email address to continue");
                } else {

                    // creates and adds a new Document to the 'users' Collection,
                    // for the new user account.
                    Map<String, Object> user = new HashMap<>();
                    user.put("password", password);
                    user.put("user_type", user_type);
                    user.put("city", city);
                    user.put("children", ages);
                    user.put("email", email_add);
                    db.collection("users").document(username)
                            .set(user);

                    showToast("new account created!");

                    // Take user to the next page, the 'Parent Home Page'.
                    Intent intent;
                    intent = new Intent(ParentSignUpXtraPage.this,
                            ParentHomePage.class);
                    intent.putExtra("USERNAME", username);
                    startActivity(intent);

                }
            }
        });

        // Execute this code when the 'Back' button is pressed.
        // Takes the user back to the previous page, the 'Sign-up' page.
        Button back_butt = (Button) findViewById(R.id.back_butt);
        back_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ParentSignUpXtraPage.this,
                        SignUpPage.class);
                startActivity(intent);
            }
        });

    }

    // A function for generating Toasts. To simplify code, and reduce repetition.
    private void showToast(String text){
        Toast.makeText(ParentSignUpXtraPage.this, text, Toast.LENGTH_LONG).show();
    }
}