package com.assignment.proddy.Activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.assignment.proddy.Adapters.HabitListAdapter;
import com.assignment.proddy.Entity.habit.Habit;
import com.assignment.proddy.Entity.habit.asyncTasks.GetUserHabitTask;
import com.assignment.proddy.Entity.habit.asyncTasks.onHabitsRetrievedListener;
import com.assignment.proddy.Entity.habit.asyncTasks.onUserHabitsRetrieved;
import com.assignment.proddy.R;
import com.assignment.proddy.Utils.AuthUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HabitListActivity extends AppCompatActivity {

    ImageView closeButton;
    RecyclerView habitListRecycler;

    HabitListAdapter habitListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_habits_activity);

        closeButton = findViewById(R.id.cancel_habit);
        closeButton.setOnClickListener(v -> finish());
        habitListRecycler = findViewById(R.id.list_habits);
        habitListRecycler.setLayoutManager(new LinearLayoutManager(this));


        habitListAdapter = new HabitListAdapter(this,new ArrayList<>());
        habitListRecycler.setAdapter(habitListAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        
        new GetUserHabitTask(this, UUID.fromString(AuthUtils.getLoggedInUser(this)), new GetUserHabitTask.onUserHabitsRetrieved() {
            @Override
            public void onSuccess(List<Habit> userHabits) {
                habitListAdapter.empty();
                for(Habit h: userHabits){
                    habitListAdapter.addHabit(h);
                }
            }
        }).execute();
    }
}