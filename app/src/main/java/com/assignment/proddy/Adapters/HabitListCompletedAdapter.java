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

import java.util.ArrayList;
import java.util.List;

public class HabitListCompletedAdapter extends RecyclerView.Adapter<HabitListCompletedAdapter.MyViewHolder> {

    private Context context;
    private List<Habit> habits;

    public HabitListCompletedAdapter(Context context, List<Habit> habits) {
        this.context = context;
        if(habits == null){
            habits = new ArrayList<Habit>();
        }
        this.habits = habits;
    }

    public void addHabit(Habit habit){
        this.habits.add(habit);
        notifyDataSetChanged();
    }

    public void empty(){
        this.habits.clear();
        notifyDataSetChanged();
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
        Habit habit = habits.get(position);
        holder.titleTextView.setText(habit.getHabitName());
    }

    @Override
    public int getItemCount() {
        return habits.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.title);
        }
    }
}
