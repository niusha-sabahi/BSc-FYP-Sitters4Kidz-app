package com.example.sitters4kidz;

// An object for the search result items in the RecyclerView in 'activity_parent_home_page.xml'.
public class ParentHomePageSearchItem {

    String c_username;
    String email;
    String rate;

    public ParentHomePageSearchItem(String c_username, String email, String rate) {
        this.c_username = c_username;
        this.email = email;
        this.rate = rate;
    }

    public String getC_username() {
        return c_username;
    }

    public String getEmail() {
        return email;
    }

    public String getRate() {
        return rate;
    }
}
