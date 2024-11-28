package com.assignment.proddy.Activities;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.assignment.proddy.Adapters.HabitStepsListAdapter;
import com.assignment.proddy.Entity.habit.Habit;
import com.assignment.proddy.Entity.habit.asyncTasks.GetHabitStackTask;
import com.assignment.proddy.Entity.habit.asyncTasks.onHabitStackRetrievedListener;
import com.assignment.proddy.Entity.habitStep.HabitStep;
import com.assignment.proddy.ObjectMapping.HabitStack;
import com.assignment.proddy.R;
import com.assignment.proddy.Utils.DrawableUtils;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class HabitStepsListActivity extends AppCompatActivity implements onHabitStackRetrievedListener {

    LinearLayout habitStepsLayout;
    LinearLayout createStackLayout;
    FrameLayout stackExistsLayout;
    FrameLayout plusbtn;
    ListView habitStepsListView;
    TextView habitNameTextView;
    TextView totalTimeTextView;
    ImageView habitTypeImageView;
    ImageView editbtn;
    ImageView closebtn;
    List<HabitStep> habitSteps;
    Habit habit;
    HabitStack habitStack;
    ImageView day1;
    ImageView day2;
    ImageView day3;
    ImageView day4;
    ImageView day5;
    List<ImageView> checkboxes;
    int EDIT_HABIT_REQUEST_CODE = 104;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.habit_steps_list);

        initViews();
        defineBtnClickListeners();
        defineDayBtnOnClickListeners();
        DefineHabit();
        defineHabitStack();
    }

    @Override
    protected void onResume(){
        super.onResume();
        actionsOnResume();
        defineHabitStack();
    }

    private void actionsOnResume(){
        habitNameTextView.setText(habit.getHabitName().toString());
    }

    private void initViews(){
        createStackLayout = findViewById(R.id.createStack);
        stackExistsLayout = findViewById(R.id.stackExists);
        habitStepsLayout = findViewById(R.id.habitStepsLayout);
        plusbtn = findViewById(R.id.plusbtn);
        habitStepsListView = findViewById(R.id.habitStepsList);
        habitNameTextView = findViewById(R.id.habitNameText);
        totalTimeTextView = findViewById(R.id.totalStackTime);
        habitTypeImageView = findViewById(R.id.habitTypeIcon);
        editbtn = findViewById(R.id.editbtn);
        closebtn = findViewById(R.id.closebtn);
        day1 = findViewById(R.id.day1);
        day2 = findViewById(R.id.day2);
        day3 = findViewById(R.id.day3);
        day4 = findViewById(R.id.day4);
        day5 = findViewById(R.id.day5);
        checkboxes = List.of(day1, day2, day3, day4, day5);
    }

    private void DefineHabit(){
        habit = (Habit) getIntent().getSerializableExtra("habit");
        assert habit != null;
        habitNameTextView.setText(habit.getHabitName());
        habitTypeImageView.setImageResource(DrawableUtils.getHabitDrawable(habit.getHabitType()));

        //set visibility of checkboxes
        List<String> days = getLast5Days();
        List<Integer> matchingIndexes = findMatchingIndexes(days, habit.getHabitDays());
        for (int i = 0; i < checkboxes.size(); i++) {
            if (matchingIndexes.contains(i)) {
                checkboxes.get(i).setVisibility(View.VISIBLE);
            } else {
                checkboxes.get(i).setVisibility(View.INVISIBLE);
            }
        }
    }

    private void defineHabitStack(){
        new GetHabitStackTask(this, this).execute(habit.getHabitId());
    }

    @Override
    public void onHabitStackRetrieved(HabitStack habitStack) {
        this.habitStack = habitStack;
        habitSteps = habitStack.getHabitSteps();

        int time = 0;
        for(HabitStep habitStep: habitSteps){
            time += habitStep.getHabitStepTime();
        }
        totalTimeTextView.setText(String.valueOf(time) + " minutes");


        if (habitSteps==null || habitSteps.isEmpty()){
            createStackLayout.setVisibility(View.VISIBLE);
            stackExistsLayout.setVisibility(View.GONE);
        }
        else{
            stackExistsLayout.setVisibility(View.VISIBLE);
            createStackLayout.setVisibility(View.GONE);
            HabitStepsListAdapter adapter = new HabitStepsListAdapter(this, habitSteps);
            habitStepsListView.setAdapter(adapter);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == EDIT_HABIT_REQUEST_CODE && resultCode == RESULT_OK) {
            habit = (Habit) data.getSerializableExtra("UpdatedHabit");
        }
    }


    private void defineBtnClickListeners(){
        editbtn.setOnClickListener(v->{
            Intent intent = new Intent(HabitStepsListActivity.this, EditHabit.class);
            intent.putExtra("Habit", habit);
            startActivityForResult(intent, EDIT_HABIT_REQUEST_CODE);
        });

        closebtn.setOnClickListener(v->{
            finish();
        });

        habitStepsLayout.setOnClickListener(v->{
            Intent intent = new Intent(HabitStepsListActivity.this, EditHabitStack.class);
            intent.putExtra("habitStack", habitStack);
            startActivity(intent);
        });

        plusbtn.setOnClickListener(v->{
            Log.d("HabitSTack", "Habit stack");
            Intent intent = new Intent(HabitStepsListActivity.this, EditHabitStack.class);
            intent.putExtra("habitStack", habitStack);
            startActivity(intent);
        });
    }

    private void defineDayBtnOnClickListeners(){
        for (ImageView checkbox : checkboxes) {
            checkbox.setTag("checked");
        }
        for(int i=0; i<4; i++) {
            int finalI = i;
            checkboxes.get(i).setOnClickListener(v -> {
                if ("checked".equals(checkboxes.get(finalI).getTag())){
                    checkboxes.get(finalI).setBackground(ContextCompat.getDrawable(this, R.drawable.cross_box));
                    checkboxes.get(finalI).setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.edit_habit_stack_crossbox_red));
                    checkboxes.get(finalI).setTag("crossed");
                    // TODO: mark habit as incomplete for selected day
                } else {
                    checkboxes.get(finalI).setBackground(ContextCompat.getDrawable(this, R.drawable.check_box));
                    checkboxes.get(finalI).setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.white));
                    checkboxes.get(finalI).setTag("checked");
                    // TODO: mark habit as completed for selected day
                }
            });
        }

        day5.setTag("checked");
        day5.setOnClickListener(v->{
            if ("checked".equals(day5.getTag())) {
                day5.setBackground(ContextCompat.getDrawable(this, R.drawable.square_outline));
                day5.setTag("crossed");
                // TODO: mark habit as incomplete for selected day
            } else {
                day5.setBackground(ContextCompat.getDrawable(this, R.drawable.check_box));
                day5.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.edit_habit_stack_checkbox_yellow));
                day5.setTag("checked");
                // TODO: mark habit as completed for selected day
            }

        });

    }

    public static List<String> getLast5Days() {
        List<String> days = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();

        for (int i = 4; i >= 0; i--) {
            calendar.add(Calendar.DATE, -i); // Move back by i days
            String dayName = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(calendar.getTime());
            days.add(dayName);

            calendar.add(Calendar.DATE, i);
        }
        return days;
    }

    public static List<Integer> findMatchingIndexes(List<String> days, List<String> habitDays) {
        List<Integer> indexes = new ArrayList<>();

        for (int i = 0; i < days.size(); i++) {
            if (habitDays.contains(days.get(i))) {
                indexes.add(i);
            }
        }
        return indexes;
    }
}





