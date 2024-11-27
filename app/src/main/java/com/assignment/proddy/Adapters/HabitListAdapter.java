package com.assignment.proddy.Adapters;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.assignment.proddy.Activities.HabitStepsListActivity;
import com.assignment.proddy.Entity.habit.Habit;
import com.assignment.proddy.R;
import com.assignment.proddy.Utils.DrawableUtils;

import java.util.ArrayList;
import java.util.List;

public class HabitListAdapter extends RecyclerView.Adapter<HabitListAdapter.MyViewHolder>{
    private Context context;
    private List<Habit> habits;


    public HabitListAdapter(Context context, List<Habit> habits) {
        this.context = context;
        this.habits = habits;
    }

    public void addHabit(Habit habit){
        this.habits.add(habit);
        Log.d("abcdefghijklmnopqrstuvwxy",String.valueOf(this.habits.size()));
        notifyDataSetChanged();
    }

    public void empty(){
        this.habits.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HabitListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the habit item layout
        View view = LayoutInflater.from(context).inflate(R.layout.habit_list_item, parent, false);
        return new HabitListAdapter.MyViewHolder(view);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull HabitListAdapter.MyViewHolder holder, int position) {
        Habit habit = habits.get(position);

        // Bind data to the views
        holder.titleTextView.setText(habit.getHabitName());
        holder.iconView.setImageResource(DrawableUtils.getHabitDrawable(habit.getHabitType()));


        holder.item_content.setOnClickListener(v -> {
            Intent intent = new Intent(context, HabitStepsListActivity.class);
            intent.putExtra("habit", habit);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return habits.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public ImageView iconView;
        public LinearLayout item_content;

        public MyViewHolder(View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.title_habit);
            iconView = itemView.findViewById(R.id.image_icon_habit);
            item_content = itemView.findViewById(R.id.item_content_habit);

        }
    }
}
