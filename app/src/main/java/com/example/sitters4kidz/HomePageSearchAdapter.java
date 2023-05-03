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

    // Inflates the layout, giving it a desired look using the layout created for the individual
    // views in the RecyclerView called 'homepage_search_item_view.xml'.
    @NonNull
    @Override
    public HomePageSearchAdapter.HomePageSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.homepage_search_item_view, parent, false);
        return new HomePageSearchAdapter.HomePageSearchViewHolder(view, recyclerViewInterface);
    }

    // Assigns values to each View in the homepage_search_item_view.xml layout.
    @Override
    public void onBindViewHolder(@NonNull HomePageSearchAdapter.HomePageSearchViewHolder holder, int position) {
        holder.username.setText(items.get(position).getUsername());
    }

    // Gets the number of items that need to be added to the RecyclerView.
    @Override
    public int getItemCount() {
        return items.size();
    }

    // Gets all the views in the homepage_search_item_view.xml layout created for the list items.
    public static class HomePageSearchViewHolder extends RecyclerView.ViewHolder {

        TextView username;

        public HomePageSearchViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            username = itemView.findViewById(R.id.search_res_username);

            // An onClickListener for when an item is clicked on in the RecyclerView.
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
