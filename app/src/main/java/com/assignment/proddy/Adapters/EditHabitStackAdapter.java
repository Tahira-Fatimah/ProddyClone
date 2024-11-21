package com.assignment.proddy.Adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.assignment.proddy.Entity.habitStep.HabitStep;
import com.assignment.proddy.R;

import java.util.List;
import java.util.UUID;

public class EditHabitStackAdapter extends BaseAdapter {

    private Context context;
    private List<HabitStep> habitSteps;  // List of HabitStep objects
    private LayoutInflater inflater;

    RelativeLayout stepMadeLayout;
    TextView stepText;
    TextView stepNum;
    TextView stepTime;
    FrameLayout newStepLayout;
    TextView newStepEmoji;
    TextView newStepTime;
    TextView newStepNum;
    EditText newStepText;
    ImageView saveNewStepBtn;
    SeekBar seekbarForTime;
    HabitStep habitStep;


    public EditHabitStackAdapter(Context context, List<HabitStep> habitSteps) {
        this.context = context;
        this.habitSteps = habitSteps;
        this.inflater = LayoutInflater.from(context);
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
            convertView = inflater.inflate(R.layout.edit_habit_stack_list_item, parent, false);
        }

        stepMadeLayout = convertView.findViewById(R.id.stepMade);
        stepText = convertView.findViewById(R.id.stepText);
        stepNum = convertView.findViewById(R.id.stepNum);
        stepTime = convertView.findViewById(R.id.stepTime);

        newStepLayout = convertView.findViewById(R.id.newStep);
        newStepEmoji = convertView.findViewById(R.id.newStepEmoji);
        newStepTime = convertView.findViewById(R.id.newStepTime);
        newStepNum = convertView.findViewById(R.id.newStepNum);
        newStepText = convertView.findViewById(R.id.newStepText);
        saveNewStepBtn = convertView.findViewById(R.id.saveNewStepBtn);
        seekbarForTime = convertView.findViewById(R.id.seekbarForTime);

        habitStep = habitSteps.get(position);

        if (habitSteps.isEmpty()) {
            newStepLayout.setVisibility(View.VISIBLE);
            stepMadeLayout.setVisibility(View.GONE);
            setEmptyNewStepLayout();
        } else {

            stepMadeLayout.setVisibility(View.VISIBLE);
            setStepMadeLayout();

            if (position == 0){
                newStepLayout.setVisibility(View.VISIBLE);
                setEmptyNewStepLayout();
            }
            else{
                newStepLayout.setVisibility(View.GONE);
            }
        }

        stepMadeLayout.setOnClickListener(v -> {
            newStepLayout.setVisibility(View.VISIBLE);
            stepMadeLayout.setVisibility(View.GONE);
            setFilledNewStepLayout();
        });

        saveNewStepBtn.setOnClickListener(v -> {
            if(newStepText.getText().toString() == null || newStepText.getText().toString() == ""){
                insertNewStep();
            }
            else{
                updateStep();
            }
            newStepLayout.setVisibility(View.GONE);
            stepMadeLayout.setVisibility(View.VISIBLE);
            setStepMadeLayout();

        });

        newStepText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (!charSequence.toString().trim().isEmpty()) {
                    saveNewStepBtn.setEnabled(true);
                    saveNewStepBtn.setClickable(true);
                    saveNewStepBtn.setFocusable(true);
                    saveNewStepBtn.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.white)));

                } else {
                    saveNewStepBtn.setEnabled(false);
                    saveNewStepBtn.setClickable(false);
                    saveNewStepBtn.setFocusable(false);
                    saveNewStepBtn.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.edit_habit_stack_dark_card_checkbox)));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        return convertView;
    }

    private void setEmptyNewStepLayout(){
        newStepNum.setText(habitSteps.size()+1);
        newStepTime.setText("0 minutes");
        newStepText.setHint("Step "+ habitSteps.size());
    }

    private void setFilledNewStepLayout(){
        newStepNum.setText(habitStep.getHabitStepNum());
        newStepText.setText(habitStep.getHabitStepDescription());
        newStepEmoji.setText(habitStep.getHabitStepEmoji());
        newStepTime.setText(habitStep.getHabitStepTime() + " minutes");
    }

    private void setStepMadeLayout(){
        stepText.setText(habitStep.getHabitStepDescription());
        stepTime.setText(habitStep.getHabitStepTime() + " minutes");
        stepNum.setText(habitStep.getHabitStepNum());
    }

    private void insertNewStep(){
        String description = newStepText.getText().toString();
        String emoji = newStepEmoji.getText().toString();
        int time = Integer.parseInt(newStepTime.getText().toString().split(" ")[0]);
        int number = habitSteps.size()+1;

        HabitStep newHabitStep = new HabitStep(UUID.randomUUID(), habitStep.getHabitStep_HabitId(), number, description, time, emoji);
//        new insertHabitStepTask(context).insert(newHabitStep);
        habitSteps.add(newHabitStep);
        notifyDataSetChanged();
    }

    private void updateStep(){
        String updatedDescription = newStepText.getText().toString();
        String updatedEmoji = newStepEmoji.getText().toString();
        int updatedTime = Integer.parseInt(newStepTime.getText().toString().split(" ")[0]);

        HabitStep updatedHabitStep = new HabitStep(habitStep.getHabitStepId(), habitStep.getHabitStep_HabitId(), habitStep.getHabitStepNum(), updatedDescription, updatedTime, updatedEmoji);
//        new updateHabitStepTask(context).execute(updatedHabitStep);
        habitSteps.remove(habitStep);
        habitSteps.add(updatedHabitStep);
        notifyDataSetChanged();
    }
}