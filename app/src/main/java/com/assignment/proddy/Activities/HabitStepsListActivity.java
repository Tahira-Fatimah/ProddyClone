package com.assignment.proddy.Activities;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
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

import java.util.ArrayList;
import java.util.List;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.habit_steps_list);

        initViews();
        defineBtnClickListeners();
        DefineHabit();
        defineHabitStack();
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
    }

    private void DefineHabit(){
        habit = (Habit) getIntent().getSerializableExtra("habit");
        assert habit != null;
        habitNameTextView.setText(habit.getHabitName());
    }

    private void defineHabitStack(){
        new GetHabitStackTask(this, this).execute(habit.getHabitId());
    }

    @Override
    public void onHabitStackRetrieved(HabitStack habitStack) {
        this.habitStack = habitStack;
        habitSteps = habitStack.getHabitSteps();

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

    private void defineBtnClickListeners(){
        editbtn.setOnClickListener(v->{
            Intent intent = new Intent(HabitStepsListActivity.this, EditHabit.class);
            startActivity(intent);
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
            Intent intent = new Intent(HabitStepsListActivity.this, EditHabitStack.class);
            intent.putExtra("habitStack", habitStack);
            startActivity(intent);
        });
    }
}
