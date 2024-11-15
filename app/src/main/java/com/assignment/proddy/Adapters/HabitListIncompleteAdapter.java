package com.assignment.proddy.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.assignment.proddy.Entity.habit.Habit;
import com.assignment.proddy.R;

import java.util.List;

public class HabitListIncompleteAdapter extends RecyclerView.Adapter<HabitListIncompleteAdapter.MyViewHolder>{
    private Context context;
    private List<Habit> habits;

    public HabitListIncompleteAdapter(Context context, List<Habit> habits) {
        this.context = context;
        this.habits = habits;
    }

    @NonNull
    @Override
    public HabitListIncompleteAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the habit item layout
        View view = LayoutInflater.from(context).inflate(R.layout.habit_list_uncompleted_item, parent, false);
        return new HabitListIncompleteAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HabitListIncompleteAdapter.MyViewHolder holder, int position) {
        Habit habit = habits.get(position);

        // Bind data to the views
        holder.timeTextView.setText(habit.getReminderTime().toString());
        holder.titleTextView.setText(habit.getName());
    }

    @Override
    public int getItemCount() {
        return habits.size();
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
