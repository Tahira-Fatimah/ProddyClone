package com.assignment.proddy.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.assignment.proddy.Entity.habitStep.HabitStep;
import com.assignment.proddy.R;

import java.util.List;

public class HabitStepsListAdapter extends BaseAdapter {

    private Context context;
    private List<HabitStep> habitSteps;

    public HabitStepsListAdapter(Context context, List<HabitStep> habitSteps) {
        this.context = context;
        this.habitSteps = habitSteps;
    }

    @Override
    public int getCount() {
        return habitSteps.size();
    }

    @Override
    public Object getItem(int position) {
        return habitSteps.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.habit_step, parent, false);
        }

        TextView emojiView = convertView.findViewById(R.id.habitStepEmoji);
        TextView stepDescription = convertView.findViewById(R.id.habitStepText);
        TextView stepTime = convertView.findViewById(R.id.habitStepTime);

        HabitStep habitStep = habitSteps.get(position);

        stepDescription.setText(habitStep.getHabitStepDescription());
        stepTime.setText(habitStep.getHabitStepTime() + " min.");
        emojiView.setText(habitStep.getHabitStepEmoji());

        return convertView;
    }
}