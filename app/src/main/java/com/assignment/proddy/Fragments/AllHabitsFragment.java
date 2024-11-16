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
        RecyclerView habitListIncompleteRecycleView = view.findViewById(R.id.habit_incomplete_recycler_view);
        recyclerView = view.findViewById(R.id.habit_recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        habitListIncompleteRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        itemTouchHelper.attachToRecyclerView(habitListIncompleteRecycleView);

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