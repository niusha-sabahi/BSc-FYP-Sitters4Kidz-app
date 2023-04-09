package com.example.sitters4kidz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

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

                // Takes the user to the 'Home' page.
                if (password.equals(conf_password)) {
                    Intent intent = new Intent(SignUpPage.this,
                            HomePage.class);
                    startActivity(intent);
                }

            }
        });

    }
}