package com.example.sitters4kidz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        EditText username_inp = (EditText) findViewById(R.id.username_inp2);
        EditText password_inp = (EditText) findViewById(R.id.password_inp2);
        EditText confirm_password_inp = (EditText) findViewById(R.id.confirm_password_inp);

        // Execute this code when the 'Submit' button is pressed.
        Button sign_up_butt = (Button) findViewById(R.id.sign_up_butt);
        sign_up_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = username_inp.getText().toString();
                String password = password_inp.getText().toString();
                String conf_password = confirm_password_inp.getText().toString();


                DocumentReference doc_ref = db.collection("user_logins")
                        .document(username);
                doc_ref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        // Check if the username the user has chosen, has already been taken, and if so,
                        // ask them to pick a different one.
                        if(task.getResult().exists()) {
                            showToast("an account with this username already exists :(, " +
                                    "please try a different one.");
                            showToast("second msg ///////////////////");
                        } else {

                            // Checks if the password and its confirmation entered match before adding
                            // it to the database as the user's password.
                            if (password.equals(conf_password)) {

                                // creates and adds a new Document to the 'user_logins' Collection,
                                // for the new user account.
                                Map<String, Object> user = new HashMap<>();
                                user.put("password", password);
                                db.collection("user_logins").document(username)
                                        .set(user);

                                // Takes the user to the 'Home' page.
                                Intent intent = new Intent(SignUpPage.this,
                                        HomePage.class);
                                startActivity(intent);
                            }

                        }


                    }
                });

            }
        });

    }

    // A function for generating Toasts. To simplify code, and reduce repetition.
    private void showToast(String text){
        Toast.makeText(SignUpPage.this, text, Toast.LENGTH_SHORT).show();
    }

}