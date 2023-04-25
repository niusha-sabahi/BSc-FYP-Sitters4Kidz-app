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

    Context context;
    ArrayList<JobPostsItem> items_j;

    public JobPostsAdapter(Context context, ArrayList<JobPostsItem> items_j) {
        this.context = context;
        this.items_j = items_j;
    }

    @NonNull
    @Override
    public JobPostsAdapter.JobPostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.job_posts_item_view, parent, false);
        return new JobPostsAdapter.JobPostsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobPostsAdapter.JobPostsViewHolder holder, int position) {
        holder.job_post_text.setText("JOB POST");
        holder.post_date.setText(items_j.get(position).getDate());
        holder.post_start.setText(items_j.get(position).getStart_time());
        holder.post_duration.setText(items_j.get(position).getDuration_time());
    }

    @Override
    public int getItemCount() {
        return items_j.size();
    }

    public static class JobPostsViewHolder extends RecyclerView.ViewHolder{

        TextView job_post_text, post_date, post_start, post_duration;

        public JobPostsViewHolder(@NonNull View itemView){
            super(itemView);

            job_post_text = itemView.findViewById(R.id.job_post_text);
            post_date = itemView.findViewById(R.id.post_date);
            post_start = itemView.findViewById(R.id.post_start);
            post_duration = itemView.findViewById(R.id.post_duration);

        }

    }

}
