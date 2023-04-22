package com.example.sitters4kidz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ChildcarerSignUpXtraPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_childcarer_sign_up_xtra_page);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        EditText charge_rate_inp = (EditText) findViewById(R.id.charge_rate_inp);

        // Grab the data entered by the previous activity.
        String username = getIntent().getStringExtra("USERNAME");
        String password = getIntent().getStringExtra("PASSWORD");
        String city = getIntent().getStringExtra("CITY");
        String user_type = getIntent().getStringExtra("USER_TYPE");

        // Execute this code when the 'Submit' button is pressed.
        Button submit_butt = (Button) findViewById(R.id.submit_sing_up_butt2);
        submit_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Check that the ages array is not empty, and so that at least one age has been
                // entered.
                String charge_rate = charge_rate_inp.getText().toString();
                if (charge_rate.isEmpty()){
                    showToast("please enter a value for how much you charge per hour");
                } else {

                    // creates and adds a new Document to the 'users' Collection,
                    // for the new user account.
                    Map<String, Object> user = new HashMap<>();
                    user.put("password", password);
                    user.put("user_type", user_type);
                    user.put("city", city);
                    user.put("pay_rate", charge_rate);
                    db.collection("users").document(username)
                            .set(user);

                    // Take user to the next page, the 'Childcarer Home Page'.
                    Intent intent;
                    intent = new Intent(ChildcarerSignUpXtraPage.this,
                            ChildcarerHomePage.class);
                    startActivity(intent);

                }
            }
        });

    }

    // A function for generating Toasts. To simplify code, and reduce repetition.
    private void showToast(String text){
        Toast.makeText(ChildcarerSignUpXtraPage.this, text, Toast.LENGTH_SHORT).show();
    }
}