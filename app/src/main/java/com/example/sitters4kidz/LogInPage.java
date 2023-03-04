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

        //EditText username_inp;
        //EditText password_inp;

        Button log_in_butt;
        //Button sig_nup_butt;

        //get inputs
        //username_inp = (EditText) findViewById(R.id.username_inp);
        //password_inp = (EditText) findViewById(R.id.password_inp);

        log_in_butt = (Button) findViewById(R.id.log_in_butt);
        log_in_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Deletes a Document based on ID in a Collection
                db.collection("users").document("gBJHaU8WBtQX8tYu8Ow6")
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
                        });

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
                        });

/*
                // Create a new user with a first and last name
                Map<String, Object> user = new HashMap<>();
                user.put("first", "Ada");
                user.put("last", "Lovelace");
                user.put("born", 1815);

                // Add a new document with a generated ID
                db.collection("users")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d("Success: ", "DocumentSnapshot added with ID: " + documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("Failure: ", "Error adding document", e);
                            }
                        });*/


                /*Map<String, Object> users = new HashMap<>();
                users.put("firstName", "Niusha");
                users.put("lastName", "Sabahi");

                db.collection("users").add(users);*/

                /*firestore.collection("users").add(users).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_LONG).show();
                    }
                });*/

            }
        });

    }
}