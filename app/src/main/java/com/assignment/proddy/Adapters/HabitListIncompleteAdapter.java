package com.assignment.proddy.Adapters;

import static java.lang.Math.abs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
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
import com.google.android.material.transition.Hold;

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

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull HabitListIncompleteAdapter.MyViewHolder holder, int position) {
        Habit habit = habits.get(position);

        // Bind data to the views
        holder.timeTextView.setText(habit.getReminderTime().toString().substring(0, 5));
        holder.titleTextView.setText(habit.getName());
        holder.iconView.setImageResource(DrawableUtils.getHabitDrawable(habit.getHabitType()));
        holder.item_content.setOnTouchListener(new View.OnTouchListener() {
            float initialX = 0;
            float initialTranslation = 0;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        initialX = event.getRawX();
                        initialTranslation = holder.item_content.getTranslationX();
                        break;

                    case MotionEvent.ACTION_MOVE:
                        break;

                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                       float finalX = initialX-event.getRawX();
                       if(abs(finalX) > 100){
                           View item_content = holder.item_content;
                           View right_button = holder.swipe_left_background;
                           View left_button = holder.swipe_right_background;
                           float item_content_translation = item_content.getTranslationX();
                           if(finalX > 0){
                               if (item_content_translation != 0 && item_content_translation != -300) {
                                   item_content.animate().translationX(0).setDuration(300).start();
                                   left_button.animate().translationX(-270).setDuration(300).start();
                               } else if (item_content_translation == 0){
                                   right_button.animate().translationX(0).setDuration(300).start();
                                   item_content.animate().translationX(-300).setDuration(300).start();
                               }
                           } else {
                               if (item_content_translation != 0 && item_content_translation != 300) {
                                   item_content.animate().translationX(0).setDuration(300).start();
                                   right_button.animate().translationX(270).setDuration(300).start();
                               } else if(item_content_translation == 0){
                                   left_button.animate().translationX(0).setDuration(300).start();
                                   item_content.animate().translationX(300).setDuration(300).start();
                               }
                           }
                       }
                       break;
                }
                return true;
            }
        });


        holder.editView.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), "Edit Button Pressed for" + String.valueOf(habit.getId()), Toast.LENGTH_SHORT).show();
        });

        holder.markCompletedView.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), "Completed Button Pressed", Toast.LENGTH_SHORT).show();
        });

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
        public LinearLayout item_content;
        public LinearLayout swipe_right_background;
        public LinearLayout swipe_left_background;
        public FrameLayout item_uncomplete;

        public MyViewHolder(View itemView) {
            super(itemView);

            timeTextView = itemView.findViewById(R.id.time);
            titleTextView = itemView.findViewById(R.id.title);
            editView = itemView.findViewById(R.id.edit);
            markCompletedView = itemView.findViewById(R.id.markCompleted);
            iconView = itemView.findViewById(R.id.image_icon);
            item_content = itemView.findViewById(R.id.item_content);
            swipe_right_background = itemView.findViewById(R.id.swipe_right_background);
            swipe_left_background = itemView.findViewById(R.id.swipe_left_background);
            item_uncomplete = itemView.findViewById(R.id.item_uncomplete);

        }
    }
}
