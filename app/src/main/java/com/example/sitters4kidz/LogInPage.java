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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class LogInPage extends AppCompatActivity {

    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_page);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        EditText username_inp;
        EditText password_inp;

        Button log_in_butt;
        //Button sig_nup_butt;

        //get inputs
        username_inp = (EditText) findViewById(R.id.username_inp);
        password_inp = (EditText) findViewById(R.id.password_inp);

        log_in_butt = (Button) findViewById(R.id.log_in_butt);
        log_in_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = username_inp.getText().toString();
                String password = password_inp.getText().toString();

                boolean user_found = false;


                //move to home page when a log in match is found
                Intent intent = new Intent(LogInPage.this,
                        HomePage.class);
                startActivity(intent);

                if(user_found == false){
                    showToast("the wrong password or username were entered. " +
                            "please try again :).");
                }









                // Deletes a Document based on ID in a Collection
                /*db.collection("cities").document("LA")
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("S", "DocumentSnapshot successfully deleted!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("F", "Error deleting document", e);
                            }
                        });*/

                /*
                // creates and adds a new Document to a Collection with a specific ID
                Map<String, Object> city = new HashMap<>();
                city.put("name", "Los Angeles");
                city.put("state", "CA");
                city.put("country", "USA");

                db.collection("cities").document("LA")
                        .set(city)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("S", "DocumentSnapshot successfully written!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("F", "Error writing document", e);
                            }
                        });*/

            }
        });

    }

    // A function for generating Toasts. To simplify code, and reduce repetition.
    private void showToast(String text){
        Toast.makeText(LogInPage.this, text, Toast.LENGTH_SHORT).show();
    }

}