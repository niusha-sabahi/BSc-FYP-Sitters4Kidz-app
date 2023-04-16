package com.example.sitters4kidz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

        RadioGroup rgroup_usertype = findViewById(R.id.user_type_radio_group);

        // Execute this code when the 'Submit' button is pressed.
        Button submit_butt = (Button) findViewById(R.id.submit_sing_up_butt);
        submit_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = username_inp.getText().toString();
                String password = password_inp.getText().toString();
                String conf_password = confirm_password_inp.getText().toString();

                // Check that all fields have been entered
                if (username.isEmpty() | password.isEmpty() | conf_password.isEmpty()) {
                    showToast("some information hasn't been entered, please try again");
                } else {

                    // Retrieve data for the document by the name of the username entered, in the 'users' collection.
                    DocumentReference doc_ref = db.collection("users")
                            .document(username);
                    doc_ref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                            // Check if the username the user has chosen, has already been taken, and if so,
                            // ask them to pick a different one.
                            if (task.getResult().exists()) {
                                showToast("an account with this username already exists :(, " +
                                        "please try a different one");
                            } else {

                                // Checks if the password and its confirmation entered match before adding
                                // it to the database as the user's password.
                                if (password.equals(conf_password)) {

                                    // Checks if the length of the username and password entered are in
                                    // the right range.
                                    if (3 <= username.length() && username.length() <= 15) {
                                        if (8 <= password.length() && password.length() <= 16) {

                                            int rad_ID = rgroup_usertype.getCheckedRadioButtonId();
                                            RadioButton rbutton_usertype = findViewById(rad_ID);
                                            String user_type = rbutton_usertype.getText().toString();

                                            if (user_type.equals("Child-carer")) {
                                                user_type = "childcarer";
                                            } else {
                                                user_type = "parent";
                                            }

                                            // creates and adds a new Document to the 'users' Collection,
                                            // for the new user account.
                                            Map<String, Object> user = new HashMap<>();
                                            user.put("password", password);
                                            user.put("user_type", user_type);
                                            db.collection("users").document(username)
                                                    .set(user);

                                            // Takes the user to the 'Home' page, which is different for
                                            // parent and child-carer users.
                                            Intent intent;
                                            if (user_type.equals("parent")) {
                                                intent = new Intent(SignUpPage.this,
                                                        ParentHomePage.class);
                                            } else {
                                                intent = new Intent(SignUpPage.this,
                                                        ChildcarerHomePage.class);
                                            }
                                            startActivity(intent);

                                        } else {
                                            showToast("the password is too short, or too long, " +
                                                    "please enter one between the range of 8-16 " +
                                                    "characters");
                                        }
                                    } else {
                                        showToast("the username is too short, or too long, " +
                                                "please enter one between the range of 3-15 " +
                                                "characters");
                                    }

                                } else {
                                    showToast("the passwords entered don't match, please" +
                                            "re-enter them");
                                }

                            }


                        }
                    });
                }
            }
        });

    }

    // A function for generating Toasts. To simplify code, and reduce repetition.
    private void showToast(String text){
        Toast.makeText(SignUpPage.this, text, Toast.LENGTH_SHORT).show();
    }

}