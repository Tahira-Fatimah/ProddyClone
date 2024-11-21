package com.assignment.proddy.Adapters;

//import static androidx.appcompat.graphics.drawable.DrawableContainerCompat.Api21Impl.getResources;

//import static androidx.appcompat.graphics.drawable.DrawableContainerCompat.Api21Impl.getResources;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class EditHabitStackAdapter extends BaseAdapter {

    private Context context;
    private List<HabitStep> habitSteps;  // List of HabitStep objects
    private LayoutInflater inflater;
    private Listener listener;

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
    HabitStep updatedHabitStep;
    int seekBarValue = 0;



    public EditHabitStackAdapter(Context context, List<HabitStep> habitSteps, Listener listener) {
        this.context = context;
        this.habitSteps = habitSteps;
        this.inflater = LayoutInflater.from(context);
        this.listener = listener;
    }


    @Override
    public int getCount() {
//        if (habitSteps.isEmpty()) {
//            return 1;
//        }
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
        int seekBarValue = 0;
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

        habitStep = (HabitStep) getItem(position);
        newStepLayout.setVisibility(View.GONE);
        stepMadeLayout.setVisibility(View.VISIBLE);
        setStepMadeLayout(position);

        stepMadeLayout.setOnClickListener(v -> {
            Log.d("Step", "STep made layour clicked");
            newStepLayout.setVisibility(View.VISIBLE);
            stepMadeLayout.setVisibility(View.GONE);
            newStepLayout.bringToFront();
            setFilledNewStepLayout();
            listener.onListItemClick();
        });

        saveNewStepBtn.setOnClickListener(v -> {
            updateStep();
            newStepLayout.setVisibility(View.GONE);
            stepMadeLayout.setVisibility(View.VISIBLE);
            listener.onSaveBtnClick(updatedHabitStep);
            notifyDataSetChanged();
        });

        newStepText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (!charSequence.toString().trim().isEmpty()) {
                    System.out.println("Text changing " + String.valueOf(charSequence));
                    saveNewStepBtn.setEnabled(true);
                    saveNewStepBtn.setClickable(true);
                    saveNewStepBtn.setFocusable(true);
                    saveNewStepBtn.setImageResource(R.drawable.check_circle_white);
                } else {
                    saveNewStepBtn.setEnabled(false);
                    saveNewStepBtn.setClickable(false);
                    saveNewStepBtn.setFocusable(false);
                    saveNewStepBtn.setImageResource(R.drawable.check_circle_purple);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        return convertView;
    }

    private void setFilledNewStepLayout(){
        newStepNum.setText(String.valueOf(habitStep.getHabitStepNum()));
        newStepText.setText(habitStep.getHabitStepDescription());
        newStepEmoji.setText(habitStep.getHabitStepEmoji());
        newStepTime.setText(habitStep.getHabitStepTime() + " minutes");
    }

    private void setStepMadeLayout(int position){
        HabitStep habitStep = habitSteps.get(position);
        stepText.setText(habitStep.getHabitStepDescription());
        stepTime.setText(habitStep.getHabitStepTime() + " minutes");
        stepNum.setText(String.valueOf(habitStep.getHabitStepNum()));
    }



    private void updateStep(){
        String updatedDescription = newStepText.getText().toString();
        String updatedEmoji = newStepEmoji.getText().toString();
        int updatedTime = Integer.parseInt(newStepTime.getText().toString().split(" ")[0]);

        updatedHabitStep = new HabitStep(habitStep.getHabitStepId(), habitStep.getHabitStep_HabitId(), habitStep.getHabitStepNum(), updatedDescription, seekBarValue, updatedEmoji);
        habitSteps.remove(habitStep);
        habitSteps.add(updatedHabitStep);

    }

    public interface Listener{
        void onListItemClick();
        void onSaveBtnClick(HabitStep habitStep);
    }

    private void defineSeekBar() {
        seekbarForTime.setMax(60);

        seekbarForTime.setProgress(seekBarValue);

        seekbarForTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarValue = progress;
                newStepTime.setText(progress + " minutes");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekBarValue = seekBar.getProgress();
            }
        });

    }

}