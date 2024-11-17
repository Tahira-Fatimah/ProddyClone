package com.assignment.proddy.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.assignment.proddy.Entity.habit.Habit;
import com.assignment.proddy.ObjectMapping.HabitWithTrackers;
import com.assignment.proddy.R;

import java.util.List;

public class HabitListIncompleteAdapter extends RecyclerView.Adapter<HabitListIncompleteAdapter.MyViewHolder>{
    private Context context;
    private List<HabitWithTrackers> habitsWithTrackers;

    public HabitListIncompleteAdapter(Context context, List<HabitWithTrackers> habitsWithTrackers) {
        this.context = context;
        this.habitsWithTrackers = habitsWithTrackers;
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
        HabitWithTrackers habitWithTrackers = habitsWithTrackers.get(position);

        // Bind data to the views
        holder.timeTextView.setText(habitWithTrackers.getHabit().getReminderTime().toString());
        holder.titleTextView.setText(habitWithTrackers.getHabit().getName());

        holder.markCompletedView.setOnClickListener(v->{
            if (holder.swipeLeftBgView.getVisibility()==View.VISIBLE) {
                Log.d("HabitAdapter", "Mark Completed clicked at position: " + position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return habitsWithTrackers.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView timeTextView;
        public TextView titleTextView;
        public androidx.appcompat.widget.AppCompatButton editView;
        public androidx.appcompat.widget.AppCompatButton markCompletedView;
        public LinearLayout swipeLeftBgView;


        public MyViewHolder(View itemView) {
            super(itemView);

            timeTextView = itemView.findViewById(R.id.time);
            titleTextView = itemView.findViewById(R.id.title);
            editView = itemView.findViewById(R.id.edit);
            markCompletedView = itemView.findViewById(R.id.markCompleted);
            swipeLeftBgView = itemView.findViewById(R.id.swipe_left_background);

        }
    }
}
