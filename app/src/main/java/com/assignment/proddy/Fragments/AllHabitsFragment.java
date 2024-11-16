package com.assignment.proddy.Fragments;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.assignment.proddy.Adapters.HabitListCompletedAdapter;
import com.assignment.proddy.Adapters.HabitListIncompleteAdapter;
import com.assignment.proddy.Entity.habit.Habit;
import com.assignment.proddy.Entity.habit.HabitType;
import com.assignment.proddy.Entity.habit.asyncTasks.GetCompletedHabitsTask;
import com.assignment.proddy.Entity.habit.asyncTasks.GetHabitsTask;
import com.assignment.proddy.Entity.habit.asyncTasks.GetIncompleteHabitsTask;
import com.assignment.proddy.Entity.habit.asyncTasks.InsertHabit;
import com.assignment.proddy.Entity.habit.asyncTasks.onHabitsRetrievedListener;
import com.assignment.proddy.Entity.habit.asyncTasks.onIncompleteHabitsRetrievedListener;
import com.assignment.proddy.Entity.habit.asyncTasks.onCompletedHabitsRetrievedListener;
import com.assignment.proddy.Entity.habitTracker.HabitTracker;
import com.assignment.proddy.Entity.habitTracker.asyncTasks.InsertHabitTrackerTask;
import com.assignment.proddy.Entity.user.InsertUser;
import com.assignment.proddy.Entity.user.User;
import com.assignment.proddy.ObjectMapping.HabitWithTrackers;
import com.assignment.proddy.R;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.List;


public class AllHabitsFragment extends Fragment implements onHabitsRetrievedListener, onIncompleteHabitsRetrievedListener, onCompletedHabitsRetrievedListener{

    private boolean isRecyclerViewVisible = false;
    RecyclerView recyclerView;
    RecyclerView habitListIncompleteRecycleView;
    TextView completionStatus;
    Drawable arrowUp ;
    Drawable arrowDown;
    Date today = Calendar.getInstance().getTime();


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
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false; // Not used for swipe
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                // Prevent permanent swipe action, restore item to normal position
                recyclerView.getAdapter().notifyItemChanged(viewHolder.getAdapterPosition());
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                    float dX, float dY, int actionState, boolean isCurrentlyActive) {
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    View itemView = viewHolder.itemView;

                    // Get the left and right button layouts
                    View leftButtonLayout = itemView.findViewById(R.id.swipe_left_background);
                    View rightButtonLayout = itemView.findViewById(R.id.swipe_right_background);
                    View itemContent = itemView.findViewById(R.id.item_content);

                    // Limit swipe to half the width
                    float maxSwipeDistance = (float) itemView.getWidth() / 3;
                    float clampedDX = Math.max(-maxSwipeDistance, Math.min(dX, maxSwipeDistance));

                    // Slide the main content
                    itemContent.setTranslationX(clampedDX);

                    // Handle right swipe
                    if (clampedDX > 0) {
                        rightButtonLayout.setVisibility(View.VISIBLE);
                        rightButtonLayout.setTranslationX(clampedDX - rightButtonLayout.getWidth() - 50);
                        leftButtonLayout.setVisibility(View.GONE);
                    }
                    // Handle left swipe
                    else if (clampedDX < 0) {
                        leftButtonLayout.setVisibility(View.VISIBLE);
                        leftButtonLayout.setTranslationX(clampedDX + leftButtonLayout.getWidth() + 50);
                        rightButtonLayout.setVisibility(View.GONE);
                    }
                } else {
                    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                }
            }



        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);


        View view = inflater.inflate(R.layout.fragment_habits, container, false);
        completionStatus = view.findViewById(R.id.completion_status);
        habitListIncompleteRecycleView = view.findViewById(R.id.habit_incomplete_recycler_view);
        recyclerView = view.findViewById(R.id.habit_recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        habitListIncompleteRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        itemTouchHelper.attachToRecyclerView(habitListIncompleteRecycleView);


//        List<Habit> habits = new ArrayList<>();
//        habits.add(new Habit("Morning Exercise", "To stay fit and healthy", "Daily", HabitType.FUN, 1, Time.valueOf("06:30:00")));
//        habits.add(new Habit("Morning Walk", "To stay fit and healthy", "Daily", HabitType.PRODUCTIVITY, 1, Time.valueOf("06:30:00")));
//        habits.add(new Habit("Morning Pills", "To stay fit and healthy", "Daily", HabitType.HEALTH, 1, Time.valueOf("06:30:00")));
//
//        new InsertUser(requireContext()).execute(new User("Saim", "saim@gmail.com", "123"));
//        new InsertHabit(requireContext()).execute(habits.get(0));
//        new InsertHabit(requireContext()).execute(habits.get(1));
//        new InsertHabit(requireContext()).execute(habits.get(2));
//
//        new GetHabitsTask(requireContext(), this).execute();
//
//        List<HabitTracker> habitTrackers = new ArrayList<>();
//        habitTrackers.add(new HabitTracker(1, today, false));
//        habitTrackers.add(new HabitTracker(2, today, false));
//        habitTrackers.add(new HabitTracker(3, today, true));
//
//        new InsertHabitTrackerTask(requireContext()).execute(habitTrackers.get(0));
//        new InsertHabitTrackerTask(requireContext()).execute(habitTrackers.get(1));
//        new InsertHabitTrackerTask(requireContext()).execute(habitTrackers.get(2));

        new GetCompletedHabitsTask(requireContext(), this, 1, today).execute();
        new GetIncompleteHabitsTask(requireContext(), this, 1, today).execute();

        completionStatus.setOnClickListener(v -> toggleRecyclerViewVisibility());

        return view;
    }

    @Override
    public void onHabitsRetrieved(List<Habit> habits) {
    }

    @Override
    public void onCompletedHabitsRetrieved(List<HabitWithTrackers> habitsWithTrackers) {
        HabitListCompletedAdapter adapter = new HabitListCompletedAdapter(getContext(), habitsWithTrackers);
        Toast.makeText(requireContext(), "date "+ today.toString(), Toast.LENGTH_LONG).show();
        Toast.makeText(requireContext(), "size "+ habitsWithTrackers.size(), Toast.LENGTH_LONG).show();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onIncompleteHabitsRetrieved(List<HabitWithTrackers> habitsWithTrackers) {
        HabitListIncompleteAdapter habitListIncompleteAdapter = new HabitListIncompleteAdapter(getContext(), habitsWithTrackers);
        habitListIncompleteRecycleView.setAdapter(habitListIncompleteAdapter);

    }

    private void toggleRecyclerViewVisibility() {
        if (isRecyclerViewVisible) {
            recyclerView.animate()
                    .alpha(0f)
                    .setDuration(100)
                    .withEndAction(() -> recyclerView.setVisibility(View.GONE))
                    .start();

            completionStatus.setCompoundDrawablesWithIntrinsicBounds(null, null, arrowDown, null);
        } else {
            recyclerView.setAlpha(0f);
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.animate()
                    .alpha(1f)
                    .setDuration(600)
                    .start();

            completionStatus.setCompoundDrawablesWithIntrinsicBounds(null, null, arrowUp, null);

        }

        isRecyclerViewVisible = !isRecyclerViewVisible;
    }


}