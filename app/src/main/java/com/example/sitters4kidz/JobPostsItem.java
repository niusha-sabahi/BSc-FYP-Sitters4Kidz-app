package com.example.sitters4kidz;

public class JobPostsItem {

    String date;
    String start_time;
    String duration_time;

    public JobPostsItem(String date, String start_time, String duration_time) {
        this.date = date;
        this.start_time = start_time;
        this.duration_time = duration_time;
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
}
