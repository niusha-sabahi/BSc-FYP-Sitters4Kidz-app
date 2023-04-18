package com.example.sitters4kidz;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ChildcarerSearchViewHolder extends RecyclerView.ViewHolder {

    TextView parent_usernameView;

    public ChildcarerSearchViewHolder(@NonNull View itemView) {
        super(itemView);

        parent_usernameView = itemView.findViewById(R.id.search_res_parent_username);

    }
}
