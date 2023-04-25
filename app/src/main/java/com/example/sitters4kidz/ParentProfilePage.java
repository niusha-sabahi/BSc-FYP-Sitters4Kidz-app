package com.example.sitters4kidz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ParentProfilePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_profile_page);

        FirebaseFirestore db = FirebaseFirestore.getInstance();


        ArrayList<JobPostsItem> items = new ArrayList<>();

        // Sets up the RecyclerView
        RecyclerView job_posts = findViewById(R.id.job_posts_recyclerview);
        JobPostsAdapter adapter = new JobPostsAdapter(getApplicationContext(), items);
        //job_posts.setAdapter(adapter);
        job_posts.setLayoutManager(new LinearLayoutManager(this));

        String parent_username = getIntent().getStringExtra("PARENT_USERNAME");

        TextView parent_username_tv = findViewById(R.id.parent_username);
        TextView child_ages_tv = findViewById(R.id.child_ages);
        TextView email_tv = findViewById(R.id.email);

        // Sets the username at the top of the page to that of the parent user clicked on.
        parent_username_tv.setText(parent_username);

        final String[] child_ages_text = new String[1];
        final String[] num_of_children_text = new String[1];

        // Retrieves the data needed for the parent profile based on their ID.
        db.collection("users")
                .whereEqualTo(FieldPath.documentId(), parent_username)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        for (QueryDocumentSnapshot document : task.getResult()) {

                            // Retrieves the 'children' field from the document
                            List<Long> child_ages_db = (List<Long>) document.get("children");
                            List<Integer> child_ages_int = new ArrayList<>(child_ages_db.size());

                            int num_of_children = child_ages_db.size();
                            num_of_children_text[0] = Integer.toString(num_of_children);

                            // Fills a new list with type int of all the ages from the original
                            // list, after converting each item into an int type from a long type
                            for (Long age : child_ages_db) {
                                child_ages_int.add(age.intValue());
                            }

                            // Creates a formatted string do display the information and assigns it
                            // to the relevant EditText
                            StringBuilder sb = new StringBuilder();
                            sb.append(num_of_children_text[0]);
                            sb.append(" kids: \n");

                            for (Integer intValue : child_ages_int) {
                                sb.append(intValue).append(", ");
                            }

                            sb.append(" years old");
                            child_ages_text[0] = sb.toString();
                            child_ages_tv.setText(child_ages_text[0]);

                            String email = (String) document.get("email");
                            email_tv.setText(email);
                        }
                    }
                });

        // This search will return all job posts posted by this parent
        db.collection("job_posts")
                .whereEqualTo("parent_username", parent_username)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        // Adds the retrieved job posts into the RecyclerView
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Log.e("////////////////////////////// RECYCLER VIEW ", "item found!");

                            StringBuilder sb2 = new StringBuilder();
                            Long date_dayL = (Long) document.get("date_day");
                            int date_day = date_dayL.intValue();
                            Long date_monthL = (Long) document.get("date_month");
                            int date_month = date_monthL.intValue();
                            Long date_yearL = (Long) document.get("date_year");
                            int date_year = date_yearL.intValue();
                            sb2.append(Integer.toString(date_day));
                            sb2.append(" / ");
                            sb2.append(Integer.toString(date_month));
                            sb2.append(" / ");
                            sb2.append(Integer.toString(date_year));
                            String date = sb2.toString();

                            StringBuilder sb3 = new StringBuilder();
                            Long start_hourL = (Long) document.get("start_hour");
                            int start_hour = start_hourL.intValue();
                            Long start_minL = (Long) document.get("start_min");
                            int start_min = start_minL.intValue();
                            sb3.append(" start time ");
                            sb3.append(Integer.toString(start_hour));
                            sb3.append(" : ");
                            sb3.append(Integer.toString(start_min));
                            String start_time = sb3.toString();

                            StringBuilder sb4 = new StringBuilder();
                            Long duration_hourL = (Long) document.get("duration_hour");
                            int duration_hour = duration_hourL.intValue();
                            Long duration_minL = (Long) document.get("duration_min");
                            int duration_min = duration_minL.intValue();
                            sb4.append(" duration ");
                            sb4.append(Integer.toString(duration_hour));
                            sb4.append(" : ");
                            sb4.append(Integer.toString(duration_min));
                            String duration_time = sb4.toString();

                            items.add(new JobPostsItem(date, start_time, duration_time));
                            Log.e("//////////////////////////////// ", Integer.toString(items.size()));
                            Log.e("//////////////////////////////// ", date);
                            Log.e("//////////////////////////////// ", start_time);
                            Log.e("//////////////////////////////// ", duration_time);

                            job_posts.setAdapter(adapter);

                        }

                    }
                });

        // Execute this code when the back button in the top left corner is pressed. It will take
        // the user back to the 'Childcarer Home' page.
        ImageButton back_butt = (ImageButton) findViewById(R.id.back_butt);
        back_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ParentProfilePage.this,
                        ChildcarerHomePage.class);
                startActivity(intent);
            }
        });
    }
}