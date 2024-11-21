package com.assignment.proddy.Activities;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.assignment.proddy.Adapters.EditHabitStackAdapter;
import com.assignment.proddy.Entity.habit.Habit;
import com.assignment.proddy.Entity.habitStep.HabitStep;
import com.assignment.proddy.ObjectMapping.HabitStack;
import com.assignment.proddy.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EditHabitStack extends AppCompatActivity {

    HabitStack habitStack;
    ListView habitStepsList;
    TextView overallTime;
    ImageView closeBtn;
    FrameLayout saveBtn;
    List<HabitStep> habitSteps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.edit_habit_stack);

        initViews();
        defineHabitStack();
        defineOnClickListeners();
        setAdapter();

    }

    private void initViews(){
        habitStepsList = findViewById(R.id.editHabitStackList);
        overallTime = findViewById(R.id.overallTime);
        closeBtn = findViewById(R.id.editStackCloseBtn);
        saveBtn = findViewById(R.id.editStackSaveBtn);
    }

    private void defineHabitStack(){
        habitStack = (HabitStack) getIntent().getSerializableExtra("habitStack");
        assert habitStack != null;
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
//            yahan par db main habitSteps update and insert
            habitStack.setHabitSteps(this.habitSteps);
        });
    }

    private void setAdapter(){
        Collections.reverse(habitSteps);
        EditHabitStackAdapter adapter = new EditHabitStackAdapter(this, habitSteps);
        habitStepsList.setAdapter(adapter);
    }
}