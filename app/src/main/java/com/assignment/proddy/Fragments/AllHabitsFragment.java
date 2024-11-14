package com.assignment.proddy.Fragments;

import android.graphics.Canvas;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.assignment.proddy.Adapters.HabitListAdapter;
import com.assignment.proddy.Entity.habit.Habit;
import com.assignment.proddy.Entity.habit.HabitType;
import com.assignment.proddy.R;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;


public class AllHabitsFragment extends Fragment {

    public AllHabitsFragment() {
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_habits, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.habit_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Habit> habits = new ArrayList<>();

        habits.add(new Habit("Morning Exercise", "To stay fit and healthy", "Daily", HabitType.FUN, 1, Time.valueOf("06:30:00")));
        habits.add(new Habit("Morning Walk", "To stay fit and healthy", "Daily", HabitType.PRODUCTIVITY, 1, Time.valueOf("06:30:00")));
        habits.add(new Habit("Morning Pills", "To stay fit and healthy", "Daily", HabitType.HEALTH, 1, Time.valueOf("06:30:00")));

        HabitListAdapter adapter = new HabitListAdapter(getContext(), habits);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false; // We don't want move events in this case
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                HabitListAdapter.MyViewHolder holder = (HabitListAdapter.MyViewHolder) viewHolder;

                if (direction == ItemTouchHelper.LEFT) {
                    holder.swipeTick.setVisibility(View.VISIBLE);
                    holder.swipeEdit.setVisibility(View.GONE);
                } else if (direction == ItemTouchHelper.RIGHT) {
                    holder.swipeEdit.setVisibility(View.VISIBLE);
                    holder.swipeTick.setVisibility(View.GONE);
                }

                // Reset item after showing buttons (can also hide buttons based on action)
                recyclerView.getAdapter().notifyItemChanged(viewHolder.getAdapterPosition());
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                View itemView = viewHolder.itemView;

                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    // Fade out the content as it is swiped
                    float alpha = 1.0f - Math.abs(dX) / (float) itemView.getWidth();
                    itemView.setAlpha(alpha);
                    itemView.setTranslationX(dX);
                } else {
                    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                }
            }
        });

        itemTouchHelper.attachToRecyclerView(recyclerView);

        return view;
    }
}