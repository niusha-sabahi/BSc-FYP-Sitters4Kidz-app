package com.example.sitters4kidz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class JobPostsAdapter extends RecyclerView.Adapter<JobPostsAdapter.JobPostsViewHolder>{

    private final RecyclerViewInterface recyclerViewInterface;

    Context context;
    ArrayList<JobPostsItem> items_j;

    public JobPostsAdapter(Context context, ArrayList<JobPostsItem> items_j,
                           RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.items_j = items_j;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    // Inflates the layout, giving it a desired look using the layout created for the individual
    // views in the RecyclerView called 'job_posts_item_view.xml'.
    @NonNull
    @Override
    public JobPostsAdapter.JobPostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.job_posts_item_view, parent, false);
        return new JobPostsAdapter.JobPostsViewHolder(view, recyclerViewInterface);
    }

    // Assigns values to each View in the job_posts_item_view.xml layout.
    @Override
    public void onBindViewHolder(@NonNull JobPostsAdapter.JobPostsViewHolder holder, int position) {
        holder.job_post_text.setText("JOB POST");
        holder.post_date.setText(items_j.get(position).getDate());
        holder.post_start.setText(items_j.get(position).getStart_time());
        holder.post_duration.setText(items_j.get(position).getDuration_time());
    }

    // Gets the number of items that need to be added to the RecyclerView.
    @Override
    public int getItemCount() {
        return items_j.size();
    }

    // Gets all the views in the job_posts_item_view.xml layout created for the list items.
    public static class JobPostsViewHolder extends RecyclerView.ViewHolder{

        TextView job_post_text, post_date, post_start, post_duration;

        public JobPostsViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface){
            super(itemView);

            job_post_text = itemView.findViewById(R.id.job_post_text);
            post_date = itemView.findViewById(R.id.post_date);
            post_start = itemView.findViewById(R.id.post_start);
            post_duration = itemView.findViewById(R.id.post_duration);

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
