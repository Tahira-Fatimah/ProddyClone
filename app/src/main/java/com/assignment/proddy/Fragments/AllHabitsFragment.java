package com.assignment.proddy.Fragments;

import static java.lang.Math.abs;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.assignment.proddy.Activities.HabitListActivity;
import com.assignment.proddy.Activities.Settings;
import com.assignment.proddy.Adapters.HabitListCompletedAdapter;
import com.assignment.proddy.Adapters.HabitListIncompleteAdapter;
import com.assignment.proddy.Entity.habit.asyncTasks.GetHabitsTask;
import com.assignment.proddy.Entity.habit.asyncTasks.onHabitsRetrievedListener;
import com.assignment.proddy.Entity.user.asyncTasks.GetUserHabitCount;
import com.assignment.proddy.MainActivity;
import com.assignment.proddy.ObjectMapping.HabitWithTracker;
import com.assignment.proddy.Entity.user.asyncTasks.onUserHabitCountRetrieved;
import com.assignment.proddy.R;
import com.assignment.proddy.Utils.AuthUtils;
import com.assignment.proddy.Utils.DateUtils;

import java.util.List;
import java.util.Objects;
import java.util.UUID;


public class AllHabitsFragment extends Fragment implements onHabitsRetrievedListener, HabitListIncompleteAdapter.OnButtonClickListener, onUserHabitCountRetrieved{

    private boolean isRecyclerViewVisible = false;
    RecyclerView completedHabitListRecyclerView;
    RecyclerView incompleteHabitListRecyclerView;
    HabitListCompletedAdapter habitListCompletedAdapter;
    HabitListIncompleteAdapter habitListIncompleteAdapter;
    LinearLayout settings;
    LinearLayout hasHabit;
    LinearLayout noHabit;
    TextView completionStatus;
    TextView todayText;
    TextView openHabits;
    LinearLayout completedToday;
    Drawable arrowUp ;
    Drawable arrowDown;

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
        hasHabit = view.findViewById(R.id.hasHabit);
        noHabit = view.findViewById(R.id.noHabit);
        settings = view.findViewById(R.id.settings);
        todayText= view.findViewById(R.id.todayText);
        openHabits = view.findViewById(R.id.openHabits);
        completionStatus = view.findViewById(R.id.completion_status);
        completedToday = view.findViewById(R.id.completedToday);
        incompleteHabitListRecyclerView = view.findViewById(R.id.habit_incomplete_recycler_view);
        completedHabitListRecyclerView = view.findViewById(R.id.habit_recycler_view);

        setSettingsOnClickListener();
        setOpenHabitsListener();
        completionStatus.setOnClickListener(v -> toggleRecyclerViewVisibility());
        incompleteHabitListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        completedHabitListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        habitListCompletedAdapter = new HabitListCompletedAdapter(getContext(), null);
        habitListIncompleteAdapter = new HabitListIncompleteAdapter(this,getContext(), null);
        completedHabitListRecyclerView.setAdapter(habitListCompletedAdapter);
        incompleteHabitListRecyclerView.setAdapter(habitListIncompleteAdapter);

        return view;
    }

    private void setOpenHabitsListener(){
        openHabits.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), HabitListActivity.class);
            startActivity(intent);
            getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        });
    }

    private void setSettingsOnClickListener() {
        settings.setOnClickListener(v -> getContext().startActivity(new Intent(getContext(), Settings.class)));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onHabitsRetrieved(List<HabitWithTracker> habitsWithTrackers) {
        emptyHabitLists();
        int completedCount = 0;
        int incompletedCount = 0;
        for (HabitWithTracker habitWithTrackers : habitsWithTrackers){
            Log.d("habitsWithTrackers LENNN", String.valueOf(habitsWithTrackers.size()));
            if(habitWithTrackers.getHabitTracker() != null){
                Log.d("habittrackerid", habitWithTrackers.getHabitTracker().toString());
                habitListCompletedAdapter.addHabit(habitWithTrackers.getHabit());
                completedCount++;
            } else {
                Log.d("habittrackernull", habitWithTrackers.getHabit().toString());
                habitListIncompleteAdapter.addHabit(habitWithTrackers.getHabit());
                incompletedCount++;
            }
        }
        setCompletionStatus(completedCount);
        setIsCompletedToday(incompletedCount);
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

    @Override
    public void onResume() {
        super.onResume();
        if(Objects.equals(AuthUtils.getLoggedInUser(getContext()), "blank")){
            Intent intent = new Intent(requireContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            requireActivity().finish();
        } else {
            fetchHabitLists();
            getUserHabitsCount();
        }
    }

    public void fetchHabitLists() {
        new GetHabitsTask(requireContext(), this, DateUtils.getDateOnly(DateUtils.getToday()), UUID.fromString(AuthUtils.getLoggedInUser(getContext())))
                .execute();
    }

    public void emptyHabitLists(){
        habitListIncompleteAdapter.empty();
        habitListCompletedAdapter.empty();

    }

    @Override
    public void onButtonClick() {
        fetchHabitLists();
    }

    public void setCompletionStatus(int completedCount){
        if(completedCount>0) {
            completionStatus.setVisibility(View.VISIBLE);
            completionStatus.setText(String.valueOf(completedCount) + " COMPLETED TODAY");
        } else {
            completionStatus.setVisibility(View.GONE);
        }
    }

    public void setIsCompletedToday(int incompletedCount){
        if(incompletedCount==0) {
            completedToday.setVisibility(View.VISIBLE);
            todayText.setVisibility(View.GONE);
        } else {
            completedToday.setVisibility(View.GONE);
            todayText.setVisibility(View.VISIBLE);
        }
    }

    public void setViewState(int habitCount){
        if(habitCount == 0){
            hasHabit.setVisibility(View.GONE);
            noHabit.setVisibility(View.VISIBLE);
        } else {
            hasHabit.setVisibility(View.VISIBLE);
            noHabit.setVisibility(View.GONE);
        }
    }

    public void getUserHabitsCount(){
        new GetUserHabitCount(requireContext(),this).execute(UUID.fromString(AuthUtils.getLoggedInUser(getContext()).toString()));
    }

    @Override
    public void userHabitCountRetrieved(int count) {
        setViewState(count);
    }
}