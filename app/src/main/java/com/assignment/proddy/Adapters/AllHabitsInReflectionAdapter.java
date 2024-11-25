package com.assignment.proddy.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.assignment.proddy.Entity.habit.Habit;
import com.assignment.proddy.ObjectMapping.HabitWithTracker;
import com.assignment.proddy.R;
import com.assignment.proddy.SharedViewModel.ReflectionSharedViewModel;
import com.assignment.proddy.Utils.DateUtils;
import com.assignment.proddy.Utils.DrawableUtils;

import java.util.Date;
import java.util.List;

public class AllHabitsInReflectionAdapter extends BaseAdapter {

    private Context context;
    private List<HabitWithTracker> habitsWithTrackers;
    private ReflectionSharedViewModel reflectionSharedViewModel;

    public AllHabitsInReflectionAdapter(Context context, List<HabitWithTracker> habitsWithTrackers, ReflectionSharedViewModel reflectionSharedViewModel) {
        this.context = context;
        this.habitsWithTrackers = habitsWithTrackers;
        this.reflectionSharedViewModel = reflectionSharedViewModel;
    }

    @Override
    public int getCount() {
        return habitsWithTrackers.size();
    }

    @Override
    public Object getItem(int position) {
        return habitsWithTrackers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.reflection_habit_list_item, parent, false);
        }

        HabitWithTracker habitWithTracker = habitsWithTrackers.get(position);

        ImageView icon = convertView.findViewById(R.id.habitCategory);
        TextView name = convertView.findViewById(R.id.habitTitle);
        CheckBox checkBox = convertView.findViewById(R.id.checkbox);

        icon.setImageResource(DrawableUtils.getHabitDrawable(habitWithTracker.getHabitType()));
        name.setText(habitWithTracker.getHabitName());
        if(habitWithTracker.getHabitTracker() != null || !(DateUtils.getDateOnly(DateUtils.getToday()).equals(DateUtils.getDateOnly(reflectionSharedViewModel.getReflectionCreationDate().getValue())))){
            checkBox.setVisibility(View.GONE);
        }

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(habitExistsInSharedViewModel(habitWithTracker.getHabit())){
                    Log.d("Removed", "yes");
                    reflectionSharedViewModel.getHabits().getValue().remove(habitWithTracker.getHabit());
                } else{
                    Log.d("Added", "yes");
                    reflectionSharedViewModel.setHabit(habitWithTracker.getHabit());
                }
            }
        });

        return convertView;
    }

    private boolean habitExistsInSharedViewModel(Habit habit){
        for(Habit habit1: reflectionSharedViewModel.getHabits().getValue()){

            if(habit1.getHabitId() == (habit.getHabitId())){
                Log.d("habithaha", habit1.toString() + "  " +  habit.toString());
                return true;
            }
        }
        return false;
    }
}
