package com.assignment.proddy.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.assignment.proddy.Entity.habit.Habit;
import com.assignment.proddy.ObjectMapping.HabitWithTrackers;
import com.assignment.proddy.R;
import com.assignment.proddy.Utils.DrawableUtils;

import java.util.ArrayList;
import java.util.List;

public class HabitListIncompleteAdapter extends RecyclerView.Adapter<HabitListIncompleteAdapter.MyViewHolder>{
    private Context context;
    private List<Habit> habits;

    public HabitListIncompleteAdapter(Context context, List<Habit> habits) {
        this.context = context;
        if(habits == null){
            habits = new ArrayList<>();
        }
        this.habits = habits;
    }

    public void addHabit(Habit habit){
        this.habits.add(habit);
        notifyDataSetChanged();
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
        holder.timeTextView.setText(habit.getReminderTime().toString().substring(0, 5));
        holder.titleTextView.setText(habit.getName());
        holder.iconView.setImageResource(DrawableUtils.getHabitDrawable(habit.getHabitType()));
    }

    @Override
    public int getItemCount() {
        return habits.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView timeTextView;
        public TextView titleTextView;
        public androidx.appcompat.widget.AppCompatButton editView;
        public androidx.appcompat.widget.AppCompatButton markCompletedView;
        public ImageView iconView;

        public MyViewHolder(View itemView) {
            super(itemView);

            timeTextView = itemView.findViewById(R.id.time);
            titleTextView = itemView.findViewById(R.id.title);
            editView = itemView.findViewById(R.id.edit);
            markCompletedView = itemView.findViewById(R.id.markCompleted);
            iconView = itemView.findViewById(R.id.image_icon);


            editView.setOnClickListener(v -> {
                Toast.makeText(v.getContext(), "Edit Button Pressed", Toast.LENGTH_SHORT).show();
            });

            markCompletedView.setOnClickListener(v -> {
                Toast.makeText(v.getContext(), "Completed Button Pressed", Toast.LENGTH_SHORT).show();
            });
        }
    }
}
