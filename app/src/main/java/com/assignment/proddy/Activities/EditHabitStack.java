package com.assignment.proddy.Activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.assignment.proddy.Adapters.EditHabitStackAdapter;
import com.assignment.proddy.Entity.habitStep.HabitStep;
import com.assignment.proddy.Entity.habitStep.asyncTasks.InsertHabitStepTask;
import com.assignment.proddy.Entity.habitStep.asyncTasks.UpdateHabitStepTask;
import com.assignment.proddy.ObjectMapping.HabitStack;
import com.assignment.proddy.R;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class EditHabitStack extends AppCompatActivity {

    HabitStack habitStack;
    ListView habitStepsList;
    TextView overallTime;
    ImageView closeBtn;
    FrameLayout saveBtn;
    List<HabitStep> habitSteps;
    FrameLayout newStepLayoutEditHabitStack;
    TextView newStepEmoji;
    TextView newStepTime;
    TextView newStepNum;
    EditText newStepText;
    ImageView saveNewStepBtn;
    SeekBar seekbarForTime;
    EditHabitStackAdapter adapter;
    int seekBarValue = 0;
//    int habitStepNumber = 1;
    List<HabitStep> insertedSteps = new ArrayList<>();
    List<HabitStep> updatedSteps = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.edit_habit_stack);

//        habitStack = (HabitStack) getIntent().getSerializableExtra("habitStack");
//        Log.d("HabitStack", habitStack.getHabit().toString());

        initViews();
        defineHabitStack();
        defineOnClickListeners();
        defineSaveBtn();
        defineNewStepTextOnClick();
        defineSeekBar();
        refreshAdapter();
//        refreshViews();

    }

    private void initViews(){
        habitStepsList = findViewById(R.id.editHabitStackList);
        overallTime = findViewById(R.id.overallTime);
        closeBtn = findViewById(R.id.editStackCloseBtn);
        saveBtn = findViewById(R.id.editStackSaveBtn);

        newStepLayoutEditHabitStack = findViewById(R.id.newStepEditHabitStack);
        newStepEmoji = findViewById(R.id.newStepEmoji);
        newStepTime = findViewById(R.id.newStepTime);
        newStepNum = findViewById(R.id.newStepNum);
        newStepText = findViewById(R.id.newStepText);
        saveNewStepBtn = findViewById(R.id.saveNewStepBtn);
        seekbarForTime = findViewById(R.id.seekbarForTime);
    }

    private void defineHabitStack(){
        habitStack = (HabitStack) getIntent().getSerializableExtra("habitStack");
//        assert habitStack != null;
        Log.d("HabitStack", habitStack.getHabit().toString());
        List<HabitStep> steps = habitStack.getHabitSteps();
        if (steps==null){
            steps=new ArrayList<>();
        }
        this.habitSteps = steps;
    }

    private void defineOnClickListeners(){
        closeBtn.setOnClickListener(v->{
            finish();
        });

        saveBtn.setOnClickListener(v->{
            for(HabitStep habitStep : insertedSteps){
                new InsertHabitStepTask(this).execute(habitStep);
            }

            for(HabitStep habitStep : updatedSteps){
                new UpdateHabitStepTask(this).execute(habitStep);
            }
            habitStack.setHabitSteps(this.habitSteps);
            finish();
        });
    }

 /*   private void setAdapter(){
        refreshAdapter();
    }*/

    private void refreshAdapter(){
        adapter = new EditHabitStackAdapter(this, habitSteps, new EditHabitStackAdapter.Listener() {
            @Override
            public void onListItemClick() {
                newStepLayoutEditHabitStack.setVisibility(View.GONE);
            }

            @Override
            public void onSaveBtnClick(HabitStep habitStep) {
                updatedSteps.add(habitStep);
                newStepLayoutEditHabitStack.setVisibility(View.VISIBLE);

            }
        });
        habitStepsList.setAdapter(adapter);
    }

    private void insertNewStep(){
        String description = newStepText.getText().toString();
        String emoji = newStepEmoji.getText().toString();
        int time = Integer.parseInt(newStepTime.getText().toString().split(" ")[0]);
        int number = habitSteps.size() + 1;

        HabitStep newHabitStep = new HabitStep(UUID.randomUUID(), habitStack.getHabit().getHabitId(), number, description, time, emoji);
        Log.d("HabitStepInsert", newHabitStep.toString());
        habitSteps.add(newHabitStep);
    }

    private void defineSaveBtn(){
        saveNewStepBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertNewStep();
                insertedSteps.add(habitSteps.get(habitSteps.size() - 1));
                habitSteps.sort(Comparator.comparingInt(HabitStep::getHabitStepNum).reversed());
                refreshAdapter();
                refreshViews();
            }
        });
    }

    private void defineNewStepTextOnClick(){
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
    }

    private void refreshViews(){
        newStepNum.setText(String.valueOf(habitSteps.size() + 1));
        newStepText.setText("");
        newStepEmoji.setText("\uD83D\uDD25");
        newStepTime.setText("0 minutes");
        saveNewStepBtn.setImageResource(R.drawable.check_circle_purple);
        seekbarForTime.setProgress(0);
    }

    private void defineSeekBar() {
        seekbarForTime.setMax(60);

        seekbarForTime.setProgress(0);

        seekbarForTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                newStepTime.setText(progress + " minutes");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

    }
}