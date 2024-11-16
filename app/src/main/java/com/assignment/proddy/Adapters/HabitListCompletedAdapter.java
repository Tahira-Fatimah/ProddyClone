package com.assignment.proddy.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.assignment.proddy.Entity.habit.Habit;
import com.assignment.proddy.ObjectMapping.HabitWithTrackers;
import com.assignment.proddy.R;

import java.util.List;

public class HabitListCompletedAdapter extends RecyclerView.Adapter<HabitListCompletedAdapter.MyViewHolder> {

    private Context context;
    private List<HabitWithTrackers> habitsWithTrackers;

    public HabitListCompletedAdapter(Context context, List<HabitWithTrackers> habitsWithTrackers) {
        this.context = context;
        this.habitsWithTrackers = habitsWithTrackers;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the habit item layout
        View view = LayoutInflater.from(context).inflate(R.layout.habit_list_completed_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        HabitWithTrackers habitWithTrackers = habitsWithTrackers.get(position);

        // Bind data to the views
        holder.timeTextView.setText(habitWithTrackers.getHabit().getReminderTime().toString());
        holder.titleTextView.setText(habitWithTrackers.getHabit().getName());
    }

    @Override
    public int getItemCount() {
        return habitsWithTrackers.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView timeTextView;
        public TextView titleTextView;

        public MyViewHolder(View itemView) {
            super(itemView);

            timeTextView = itemView.findViewById(R.id.time);
            titleTextView = itemView.findViewById(R.id.title);
        }
    }
}
