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
    private List<HabitStep> habitSteps;
    private LayoutInflater inflater;
    private Listener listener;

    public EditHabitStackAdapter(Context context, List<HabitStep> habitSteps, Listener listener) {
        this.context = context;
        this.habitSteps = habitSteps;
        this.inflater = LayoutInflater.from(context);
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
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        final int[] seekBarValue = {0}; // Local variable for seekbar value
        final String[] updatedDescription = {null}; // Local variable for description

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.edit_habit_stack_list_item, parent, false);
            holder = new ViewHolder();

            holder.stepMadeLayout = convertView.findViewById(R.id.stepMade);
            holder.stepText = convertView.findViewById(R.id.stepText);
            holder.stepNum = convertView.findViewById(R.id.stepNum);
            holder.stepTime = convertView.findViewById(R.id.stepTime);
            holder.newStepLayout = convertView.findViewById(R.id.newStep);
            holder.newStepEmoji = convertView.findViewById(R.id.newStepEmoji);
            holder.newStepTime = convertView.findViewById(R.id.newStepTime);
            holder.newStepNum = convertView.findViewById(R.id.newStepNum);
            holder.newStepText = convertView.findViewById(R.id.newStepText);
            holder.saveNewStepBtn = convertView.findViewById(R.id.saveNewStepBtn);
            holder.seekbarForTime = convertView.findViewById(R.id.seekbarForTime);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        HabitStep habitStep = habitSteps.get(position);
        seekBarValue[0] = habitStep.getHabitStepTime(); // Initialize with current step time
        updatedDescription[0] = habitStep.getHabitStepDescription(); // Initialize with current description

        holder.newStepLayout.setVisibility(View.GONE);
        holder.stepMadeLayout.setVisibility(View.VISIBLE);

        // Set initial values
        setStepMadeLayout(holder, habitStep);

        // Setup seekbar
        holder.seekbarForTime.setMax(60);
        holder.seekbarForTime.setProgress(seekBarValue[0]);
        holder.seekbarForTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarValue[0] = progress;
                holder.newStepTime.setText(progress + " minutes");
                Log.d("SeekbarChanged", "Progress: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // Step made layout click listener
        holder.stepMadeLayout.setOnClickListener(v -> {
            holder.newStepLayout.setVisibility(View.VISIBLE);
            holder.stepMadeLayout.setVisibility(View.GONE);
            holder.newStepLayout.bringToFront();
            setFilledNewStepLayout(holder, habitStep);
            seekBarValue[0] = habitStep.getHabitStepTime();
            holder.seekbarForTime.setProgress(seekBarValue[0]);
            listener.onListItemClick();
        });

        // Save button click listener
        holder.saveNewStepBtn.setOnClickListener(v -> {
            HabitStep updatedStep = new HabitStep(
                    habitStep.getHabitStepId(),
                    habitStep.getHabitStep_HabitId(),
                    habitStep.getHabitStepNum(),
                    updatedDescription[0],
                    seekBarValue[0],
                    holder.newStepEmoji.getText().toString()
            );

            habitSteps.set(position, updatedStep);
            holder.newStepLayout.setVisibility(View.GONE);
            holder.stepMadeLayout.setVisibility(View.VISIBLE);
            setStepMadeLayout(holder, updatedStep);
            listener.onSaveBtnClick(updatedStep);
            notifyDataSetChanged();
        });

        // Text watcher for description updates
        holder.newStepText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean isEmpty = s.toString().trim().isEmpty();
                holder.saveNewStepBtn.setEnabled(!isEmpty);
                holder.saveNewStepBtn.setClickable(!isEmpty);
                holder.saveNewStepBtn.setFocusable(!isEmpty);
                holder.saveNewStepBtn.setImageResource(isEmpty ?
                        R.drawable.check_circle_purple :
                        R.drawable.check_circle_white);
            }

            @Override
            public void afterTextChanged(Editable s) {
                updatedDescription[0] = s.toString();
                Log.d("Description", "Updated: " + updatedDescription[0]);
            }
        });

        return convertView;
    }

    private void setStepMadeLayout(ViewHolder holder, HabitStep habitStep) {
        holder.stepText.setText(habitStep.getHabitStepDescription());
        holder.stepTime.setText(habitStep.getHabitStepTime() + " minutes");
        holder.stepNum.setText(String.valueOf(habitStep.getHabitStepNum()));
    }

    private void setFilledNewStepLayout(ViewHolder holder, HabitStep habitStep) {
        holder.newStepNum.setText(String.valueOf(habitStep.getHabitStepNum()));
        holder.newStepText.setText(habitStep.getHabitStepDescription());
        holder.newStepEmoji.setText(habitStep.getHabitStepEmoji());
        holder.newStepTime.setText(habitStep.getHabitStepTime() + " minutes");
        holder.seekbarForTime.setProgress(habitStep.getHabitStepTime());
    }

    private static class ViewHolder {
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
    }

    public interface Listener {
        void onListItemClick();
        void onSaveBtnClick(HabitStep habitStep);
    }
}