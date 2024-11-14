package com.assignment.proddy.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.assignment.proddy.Entity.habit.Habit;
import com.assignment.proddy.R;

import java.util.List;

public class HabitListAdapter extends RecyclerView.Adapter<HabitListAdapter.MyViewHolder> {

    private Context context;
    private List<Habit> habits;

    public HabitListAdapter(Context context, List<Habit> habits) {
        this.context = context;
        this.habits = habits;
    }

    // ViewHolder class
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView timeTextView;
        public ImageView iconImageView;
        public TextView titleTextView;
        public ImageView swipeTick;
        public ImageView swipeEdit;

        public MyViewHolder(View itemView) {
            super(itemView);
            timeTextView = itemView.findViewById(R.id.time);
            iconImageView = itemView.findViewById(R.id.image_icon);
            titleTextView = itemView.findViewById(R.id.title);
            swipeTick = itemView.findViewById(R.id.swipe_tick);
            swipeEdit = itemView.findViewById(R.id.swipe_edit);

        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.habit_list_completed_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Habit habit = habits.get(position);
        holder.timeTextView.setText(habit.getReminderTime().toString());
        holder.titleTextView.setText(habit.getName());
    }

    @Override
    public int getItemCount() {
        return habits.size();
    }
}
