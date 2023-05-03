package com.example.sitters4kidz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ParentJobsMenuPage extends AppCompatActivity implements RecyclerViewInterface{

    ArrayList<JobPostsItem> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_jobs_menu_page);

        String username = getIntent().getStringExtra("USERNAME");
        String user_type = getIntent().getStringExtra("USER_TYPE");

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Sets up the RecyclerView
        RecyclerView job_posts = findViewById(R.id.parent_jobs_menu_page_recyclerview);
        JobPostsAdapter adapter = new JobPostsAdapter(getApplicationContext(), items, this);
        job_posts.setLayoutManager(new LinearLayoutManager(this));

        // This search will return all job posts posted by this parent
        db.collection("job_posts")
                .whereEqualTo("parent_username", username)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        // Adds the retrieved job posts into the RecyclerView
                        for (QueryDocumentSnapshot document : task.getResult()) {

                            String job_ID = document.getId();

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

                            items.add(new JobPostsItem(date, start_time, duration_time, job_ID));
                            job_posts.setAdapter(adapter);

                        }

                    }
                });

        // Execute this code when the 'New Job Post' button is pressed,
        // takes user to the parent's version of the 'Search' page.
        Button new_job_post_button = (Button) findViewById(R.id.new_job_post_butt);
        new_job_post_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ParentJobsMenuPage.this,
                        NewJobPostPage.class);
                intent.putExtra("USERNAME", username);
                intent.putExtra("USER_TYPE", user_type);
                startActivity(intent);
            }
        });

        // Execute this code when the home button in the left of the bottom tab is pressed,
        // takes user to the parent's version of the 'Search' page.
        ImageButton jobs_button = (ImageButton) findViewById(R.id.home_butt);
        jobs_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ParentJobsMenuPage.this,
                        ParentHomePage.class);
                intent.putExtra("USERNAME", username);
                intent.putExtra("USER_TYPE", user_type);
                startActivity(intent);
            }
        });

        // Execute this code when the messages button in the right of the bottom tab is pressed,
        // takes user to the 'Messages' page.
        ImageButton msgs_button = (ImageButton) findViewById(R.id.messages_butt);
        msgs_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ParentJobsMenuPage.this,
                        MessagesMenuPage.class);
                intent.putExtra("USERNAME", username);
                intent.putExtra("USER_TYPE", user_type);
                startActivity(intent);
            }
        });

    }

    // When a parent user presses on their own job post, they will be taken to another page called
    // 'JobPostDetailsPage.java' where they can view the child=carers who have offered to work the
    // job and select who they wish to award it to.
    @Override
    public void onClickRecyclerItem(int position) {

        String username = getIntent().getStringExtra("USERNAME");
        String user_type = getIntent().getStringExtra("USER_TYPE");

        Intent intent = new Intent(ParentJobsMenuPage.this, JobPostDetailsPage.class);
        intent.putExtra("JOB_ID", items.get(position).getJob_ID());
        intent.putExtra("USERNAME", username);
        intent.putExtra("USER_TYPE", user_type);
        startActivity(intent);

    }
}