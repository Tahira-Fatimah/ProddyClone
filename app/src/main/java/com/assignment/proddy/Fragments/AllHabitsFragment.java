package com.assignment.proddy.Fragments;

import static java.lang.Math.abs;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.assignment.proddy.Adapters.HabitListCompletedAdapter;
import com.assignment.proddy.Adapters.HabitListIncompleteAdapter;
import com.assignment.proddy.Entity.habit.asyncTasks.GetHabitsTask;
import com.assignment.proddy.Entity.habit.asyncTasks.onHabitsRetrievedListener;
import com.assignment.proddy.Entity.habit.asyncTasks.onIncompleteHabitsRetrievedListener;
import com.assignment.proddy.Entity.habit.asyncTasks.onCompletedHabitsRetrievedListener;
import com.assignment.proddy.ObjectMapping.HabitWithTrackers;
import com.assignment.proddy.R;
import com.assignment.proddy.Utils.AuthUtils;

import java.util.Date;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;


public class AllHabitsFragment extends Fragment implements onHabitsRetrievedListener{

    private boolean isRecyclerViewVisible = false;
    RecyclerView completedHabitListRecyclerView;
    RecyclerView incompleteHabitListRecyclerView;
    HabitListCompletedAdapter habitListCompletedAdapter;
    HabitListIncompleteAdapter habitListIncompleteAdapter;
    TextView completionStatus;
    Drawable arrowUp ;
    Drawable arrowDown;
    Date today = Calendar.getInstance().getTime();

    public AllHabitsFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getContext() != null) {
            arrowUp = ResourcesCompat.getDrawable(getResources(), R.drawable.arrow_up, null);
            arrowDown = ResourcesCompat.getDrawable(getResources(), R.drawable.arrow_down, null);
        }

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_habits, container, false);
        completionStatus = view.findViewById(R.id.completion_status);
        incompleteHabitListRecyclerView = view.findViewById(R.id.habit_incomplete_recycler_view);
        completedHabitListRecyclerView = view.findViewById(R.id.habit_recycler_view);

        completionStatus.setOnClickListener(v -> toggleRecyclerViewVisibility());
        incompleteHabitListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        completedHabitListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        habitListCompletedAdapter = new HabitListCompletedAdapter(getContext(), null);
        habitListIncompleteAdapter = new HabitListIncompleteAdapter(getContext(), null);
        completedHabitListRecyclerView.setAdapter(habitListCompletedAdapter);
        incompleteHabitListRecyclerView.setAdapter(habitListIncompleteAdapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new GetHabitsTask(requireContext(), this,today, UUID.fromString(AuthUtils.getLoggedInUser(getContext())))
                .execute();
    }

    @Override
    public void onHabitsRetrieved(List<HabitWithTrackers> habitsWithTrackers) {
        for (HabitWithTrackers habitWithTrackers : habitsWithTrackers){

            if(habitWithTrackers.getHabitWithTracker()){
                Log.d("habitid", habitWithTrackers.getHabit().toString());
                habitListCompletedAdapter.addHabit(habitWithTrackers.getHabit());
            } else {
                Log.d("habittrackerid", habitWithTrackers.getHabit().toString());
                habitListIncompleteAdapter.addHabit(habitWithTrackers.getHabit());
            }
        }
    }


    private void toggleRecyclerViewVisibility() {
        if (isRecyclerViewVisible) {
            completedHabitListRecyclerView.animate()
                    .alpha(0f)
                    .setDuration(100)
                    .withEndAction(() -> completedHabitListRecyclerView.setVisibility(View.GONE))
                    .start();

            completionStatus.setCompoundDrawablesWithIntrinsicBounds(null, null, arrowDown, null);
        } else {
            completedHabitListRecyclerView.setAlpha(0f);
            completedHabitListRecyclerView.setVisibility(View.VISIBLE);
            completedHabitListRecyclerView.animate()
                    .alpha(1f)
                    .setDuration(600)
                    .start();

            completionStatus.setCompoundDrawablesWithIntrinsicBounds(null, null, arrowUp, null);

        }

        isRecyclerViewVisible = !isRecyclerViewVisible;
    }

}