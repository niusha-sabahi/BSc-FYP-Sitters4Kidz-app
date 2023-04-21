package com.example.sitters4kidz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ParentProfilePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_profile_page);

        String parent_username = getIntent().getStringExtra("PARENT_USERNAME");

        TextView parent_username_tv = findViewById(R.id.parent_username);

        parent_username_tv.setText(parent_username);

    }
}