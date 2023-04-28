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

public class LogInPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_page);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Get all inputs
        EditText username_inp = (EditText) findViewById(R.id.username_inp);
        EditText password_inp = (EditText) findViewById(R.id.password_inp);

        // Execute this code when the 'Sign Up' button is pressed, takes user to the 'Sign Up' page.
        Button sign_up_butt = (Button) findViewById(R.id.sign_up_butt);
        sign_up_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogInPage.this,
                        SignUpPage.class);
                startActivity(intent);
            }
        });

        // Execute this code when the 'Log In' button is pressed.
        Button log_in_butt = (Button) findViewById(R.id.log_in_butt);
        log_in_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = username_inp.getText().toString().trim();
                String password = password_inp.getText().toString().trim();

                if(username.isEmpty() | password.isEmpty()) {
                    showToast("some information hasn't been entered, please try again");

                } else {
                    // Retrieve data for the document by the name of the username entered, in the
                    // 'users' collection.
                    DocumentReference doc_ref = db.collection("users")
                            .document(username);
                    doc_ref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            //if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    // If the document exists, load the 'password' field for that
                                    // document into the 'actual_pass' variable. This stores the
                                    // actual password linked to the username entered.
                                    String actual_pass = document.getData().get("password").toString();
                                    String user_type = document.getData().get("user_type").toString();

                                    // If the password entered is the same as the expected password
                                    // for the username entered, enter the 'Home' page, which is
                                    // different for parent and child-carer users.
                                    if (password.equals(actual_pass)) {
                                        Intent intent;
                                        if (user_type.equals("parent")) {
                                            intent = new Intent(LogInPage.this,
                                                    ParentHomePage.class);
                                        } else {
                                            intent = new Intent(LogInPage.this,
                                                    ChildcarerHomePage.class);
                                        }
                                        intent.putExtra("USERNAME", username);
                                        intent.putExtra("USER_TYPE", user_type);
                                        startActivity(intent);
                                    } else {
                                        showToast("the wrong password was entered, " +
                                                "please try again :)");
                                    }
                                } else {
                                    showToast("the wrong username was entered, " +
                                            "please try again :)");
                                }
                        }
                    });
                }
            }
        });
    }
    // A function for generating Toasts. To simplify code, and reduce repetition.
    private void showToast(String text){
        Toast.makeText(LogInPage.this, text, Toast.LENGTH_LONG).show();
    }

}