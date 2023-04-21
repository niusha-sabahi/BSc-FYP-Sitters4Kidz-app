package com.example.sitters4kidz;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomePageSearchAdapter extends RecyclerView.Adapter<HomePageSearchAdapter.HomePageSearchViewHolder>{

    private final RecyclerViewInterface recyclerViewInterface;

    Context context;
    ArrayList<HomePageSearchItem> items;

    public HomePageSearchAdapter(Context context, ArrayList<HomePageSearchItem> items,
                                 RecyclerViewInterface recyclerViewInterface){
        this.context = context;
        this.items = items;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public HomePageSearchAdapter.HomePageSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.homepage_search_item_view, parent, false);
        return new HomePageSearchAdapter.HomePageSearchViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull HomePageSearchAdapter.HomePageSearchViewHolder holder, int position) {
        holder.username.setText(items.get(position).getUsername());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class HomePageSearchViewHolder extends RecyclerView.ViewHolder {

        TextView username;

        public HomePageSearchViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            username = itemView.findViewById(R.id.search_res_username);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null) {
                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onClickRecyclerItem(position);
                        }

                    }
                }
            });

        }
    }

}
