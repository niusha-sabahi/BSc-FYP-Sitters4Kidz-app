package com.example.sitters4kidz;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class LogInPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_page);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        //FirebaseAuth f_auth = FirebaseAuth.getInstance();

        EditText username_inp = (EditText) findViewById(R.id.username_inp);
        EditText password_inp = (EditText) findViewById(R.id.password_inp);

        //Button sig_nup_butt;

        // Execute this code when the 'Log In' button is pressed.
        Button log_in_butt = (Button) findViewById(R.id.log_in_butt);
        log_in_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = username_inp.getText().toString();
                String password = password_inp.getText().toString();

                // Retrieve data for the document by the name of the username entered in the 'user_logins' collection.
                DocumentReference doc_ref = db.collection("user_logins")
                        .document(username);
                doc_ref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        String actual_pass;

                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                // If the document retrieval is successful and the document exists, load the 'password' field
                                // for that document into the 'actual_pass' variable. This stores the actual password linked
                                // to the username entered.
                                actual_pass = document.getData().get("password").toString();
                                // If the password entered is the same as the expected password for the username entered,
                                // enter the next activity (HomePage).
                                if (password.equals(actual_pass)){
                                    Intent intent = new Intent(LogInPage.this,
                                            HomePage.class);
                                    startActivity(intent);
                                } else {
                                    showToast("the wrong password was entered. " +
                                            "please try again :).");
                                }
                            } else {
                                showToast("the wrong username was entered. " +
                                        "please try again :).");
                            }
                        } else {
                            Log.d("MSG", "get failed with ", task.getException());
                        }
                    }
                });





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