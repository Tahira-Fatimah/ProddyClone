package com.assignment.proddy;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.assignment.proddy.Fragments.ReflectionFragment;
import com.assignment.proddy.Fragments.insights;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, new ReflectionFragment());
        transaction.commit();

//        new InsertUser(getApplicationContext()).execute(new User("1234", "Fatimah", "123"));
//        new InsertHabit(getApplicationContext()).execute(new Habit("habit 2", "aise hi", "M,T,W", HabitType.FINANCES, 1));
//        new InsertHabitStep(getApplicationContext()).execute(
//                new HabitStep(1,1,"step 1", 30,"haha"),
//                new HabitStep(1,2,"step 2", 30,"haha")
//        );

//        new RetrieveHabitStack(getApplicationContext()).execute();
    }
}