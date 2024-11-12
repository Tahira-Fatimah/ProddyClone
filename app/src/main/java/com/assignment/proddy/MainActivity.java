package com.assignment.proddy;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_reflection);
//        new InsertUser(getApplicationContext()).execute(new User("1234", "Fatimah", "123"));
//        new InsertHabit(getApplicationContext()).execute(new Habit("habit 2", "aise hi", "M,T,W", HabitType.FINANCES, 1));
//        new InsertHabitStep(getApplicationContext()).execute(
//                new HabitStep(1,1,"step 1", 30,"haha"),
//                new HabitStep(1,2,"step 2", 30,"haha")
//        );

//        new RetrieveHabitStack(getApplicationContext()).execute();
    }
}