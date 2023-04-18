package com.example.sitters4kidz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChildcarerSearchAdapter extends RecyclerView.Adapter<ChildcarerSearchViewHolder> {

    Context context;
    List<ChildcarerSearchItem> items;

    public ChildcarerSearchAdapter(Context context, List<ChildcarerSearchItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ChildcarerSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChildcarerSearchViewHolder(LayoutInflater.from(context).inflate(R.layout.childcarer_search_item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChildcarerSearchViewHolder holder, int position) {
        holder.parent_usernameView.setText(items.get(position).getUsername());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
