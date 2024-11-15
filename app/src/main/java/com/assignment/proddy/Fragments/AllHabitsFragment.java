package com.assignment.proddy.Fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.assignment.proddy.Adapters.HabitListCompletedAdapter;
import com.assignment.proddy.Adapters.HabitListIncompleteAdapter;
import com.assignment.proddy.Entity.habit.Habit;
import com.assignment.proddy.Entity.habit.HabitType;
import com.assignment.proddy.R;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;


public class AllHabitsFragment extends Fragment {

    private boolean isRecyclerViewVisible = false;
    RecyclerView recyclerView;
    TextView completionStatus;
    Drawable arrowUp ;
    Drawable arrowDown;

    public AllHabitsFragment() {
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getContext() != null) {
            arrowUp = ResourcesCompat.getDrawable(getResources(), R.drawable.arrow_up, null);
            arrowDown = ResourcesCompat.getDrawable(getResources(), R.drawable.arrow_down, null);
        }

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_habits, container, false);
        completionStatus = view.findViewById(R.id.completion_status);
        RecyclerView habitListIncompleteRecycleView = view.findViewById(R.id.habit_incomplete_recycler_view);
        recyclerView = view.findViewById(R.id.habit_recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        habitListIncompleteRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Habit> habits = new ArrayList<>();
        habits.add(new Habit("Morning Exercise", "To stay fit and healthy", "Daily", HabitType.FUN, 1, Time.valueOf("06:30:00")));
        habits.add(new Habit("Morning Walk", "To stay fit and healthy", "Daily", HabitType.PRODUCTIVITY, 1, Time.valueOf("06:30:00")));
        habits.add(new Habit("Morning Pills", "To stay fit and healthy", "Daily", HabitType.HEALTH, 1, Time.valueOf("06:30:00")));

        HabitListCompletedAdapter adapter = new HabitListCompletedAdapter(getContext(), habits);
        HabitListIncompleteAdapter habitListIncompleteAdapter = new HabitListIncompleteAdapter(getContext(), habits);

        habitListIncompleteRecycleView.setAdapter(habitListIncompleteAdapter);
        recyclerView.setAdapter(adapter);

        completionStatus.setOnClickListener(v -> toggleRecyclerViewVisibility());


        return view;
    }

    private void toggleRecyclerViewVisibility() {
        if (isRecyclerViewVisible) {
            recyclerView.animate()
                    .alpha(0f)
                    .setDuration(600)
                    .withEndAction(() -> recyclerView.setVisibility(View.GONE))
                    .start();

            completionStatus.setCompoundDrawablesWithIntrinsicBounds(null, null, arrowUp, null);
        } else {
            recyclerView.setAlpha(0f);
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.animate()
                    .alpha(1f)
                    .setDuration(600)
                    .start();

            if(completionStatus != null){
                completionStatus.setCompoundDrawablesWithIntrinsicBounds(null, null, arrowDown, null);
//
            }
            else{
                System.out.println(" completin status is null");
            }
        }

        // Toggle the state
        isRecyclerViewVisible = !isRecyclerViewVisible;
    }

}