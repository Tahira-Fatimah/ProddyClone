package com.assignment.proddy.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.assignment.proddy.Entity.habitStep.HabitStep;
import com.assignment.proddy.R;

import java.util.List;

public class EditHabitStackAdapter extends BaseAdapter {
    private Context context;
    private List<HabitStep> habitSteps;
    private Listener listener;
    private int expandedPosition = -1;

    public interface Listener {
        void onListItemClick();
        void onSaveBtnClick(HabitStep habitStep);
    }

    public EditHabitStackAdapter(Context context, List<HabitStep> habitSteps, Listener listener) {
        this.context = context;
        this.habitSteps = habitSteps;
        this.listener = listener;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.edit_habit_stack_list_item, parent, false);
        }

        HabitStep currentStep = habitSteps.get(position);

        // Find views
        RelativeLayout stepMade = convertView.findViewById(R.id.stepMade);
        FrameLayout newStep = convertView.findViewById(R.id.newStep);
        TextView stepNum = convertView.findViewById(R.id.stepNum);
        TextView stepText = convertView.findViewById(R.id.stepText);
        TextView stepTime = convertView.findViewById(R.id.stepTime);

        // Set step details
        stepNum.setText(currentStep.getHabitStepNum() + ".");
        stepText.setText(currentStep.getHabitStepDescription());
        stepTime.setText(currentStep.getHabitStepTime() + " min");


        // Setup edit mode views
        EditText newStepText = newStep.findViewById(R.id.newStepText);
        EditText newStepEmoji = newStep.findViewById(R.id.newStepEmoji);
        TextView newStepTime = newStep.findViewById(R.id.newStepTime);
        TextView newStepNum = newStep.findViewById(R.id.newStepNum);
        SeekBar seekbarForTime = newStep.findViewById(R.id.seekbarForTime);
        ImageView saveNewStepBtn = newStep.findViewById(R.id.saveNewStepBtn);

        // Set initial visibility and edit mode values
        if (expandedPosition == position) {
            stepMade.setVisibility(View.GONE);
            newStep.setVisibility(View.VISIBLE);

            // Populate edit fields
            newStepText.setText(currentStep.getHabitStepDescription());
            newStepEmoji.setText(currentStep.getHabitStepEmoji());
            newStepTime.setText(currentStep.getHabitStepTime() + " minutes");
            newStepNum.setText(currentStep.getHabitStepNum() + ".");
            seekbarForTime.setMax(60);
            seekbarForTime.setProgress(currentStep.getHabitStepTime());
            seekbarForTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    newStepTime.setText(progress + " minutes");
                    Log.d("SeekbarChanged", "Progress: " + progress);
                }
                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {}

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {}
            });
        } else {
            stepMade.setVisibility(View.VISIBLE);
            newStep.setVisibility(View.GONE);
        }

        // Click listeners
        stepMade.setOnClickListener(v -> {
            // Collapse any previously expanded item
            int previousExpandedPosition = expandedPosition;
            expandedPosition = position;

            // Notify the activity about list item click
            if (listener != null) {
                listener.onListItemClick();
            }

            // Refresh the entire list to update visibility
            notifyDataSetChanged();
        });

        // Save button click listener for edit mode
        saveNewStepBtn.setOnClickListener(v -> {
            // Update the habit step with new values
            currentStep.setHabitStepDescription(newStepText.getText().toString());
            currentStep.setHabitStepEmoji(newStepEmoji.getText().toString());
            currentStep.setHabitStepTime(Integer.parseInt(newStepTime.getText().toString().split(" ")[0]));

            // Notify the activity about save
            if (listener != null) {
                listener.onSaveBtnClick(currentStep);
            }

            // Switch back to view mode
            expandedPosition = -1;
            notifyDataSetChanged();
        });

        return convertView;
    }
}