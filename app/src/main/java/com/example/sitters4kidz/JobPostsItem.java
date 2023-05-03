package com.example.sitters4kidz;

// An object for the items in the RecyclerViews displaying any list of job posts.
public class JobPostsItem {

    String date;
    String start_time;
    String duration_time;
    String job_ID;

    public JobPostsItem(String date, String start_time, String duration_time, String job_ID) {
        this.date = date;
        this.start_time = start_time;
        this.duration_time = duration_time;
        this.job_ID = job_ID;
    }

    public String getDate() {
        return date;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getDuration_time() {
        return duration_time;
    }

    public String getJob_ID() {
        return job_ID;
    }
}
