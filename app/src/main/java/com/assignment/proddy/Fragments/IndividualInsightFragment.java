package com.assignment.proddy.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.assignment.proddy.Adapters.IndividualInsightsAdapter;
import com.assignment.proddy.Entity.habit.asyncTasks.GetHabitsWithStreakAndTimeTask;
import com.assignment.proddy.Entity.habitTracker.HabitTracker;
import com.assignment.proddy.Entity.habitTracker.asyncTasks.getTrackerForHabitTask;
import com.assignment.proddy.Entity.habitTracker.asyncTasks.onGetTrackerForHabit;
import com.assignment.proddy.ObjectMapping.HabitInsightData;
import com.assignment.proddy.ObjectMapping.HabitWithStreakAndTime;
import com.assignment.proddy.R;
import com.assignment.proddy.Utils.AuthUtils;
import com.assignment.proddy.Utils.DateUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


public class IndividualInsightFragment extends Fragment {

    ViewPager2 insightContainer;
    TextView iconLeft;
    TextView iconRight;

    IndividualInsightsAdapter insightsAdapter;

    List<HabitWithStreakAndTime> habitWithStreakAndTimes;
    List<HabitTracker> habitTrackerData;
    Map<UUID,HabitInsightData> habitInsightDataMap;
    List<HabitInsightData> habitInsightData;

    public IndividualInsightFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_individual_insight, container, false);

        insightContainer = view.findViewById(R.id.viewPagerII);
        insightContainer.setPageTransformer(new CustomPageTransformer());

        iconLeft = view.findViewById(R.id.left_arrow);
        iconRight = view.findViewById(R.id.right_arrow);

        insightsAdapter = new IndividualInsightsAdapter(getContext(),new ArrayList<>());

        insightContainer.setAdapter(insightsAdapter);

        defineLeftArrow();
        defineRightArrow();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        fetchHabitsWithStreak();
    }

    public void fetchHabitsWithStreak(){
        new GetHabitsWithStreakAndTimeTask(getContext(), new GetHabitsWithStreakAndTimeTask.OnHabitsWithStreaksRetrieved() {
            @Override
            public void onSuccess(List<HabitWithStreakAndTime> habitWithStreakAndTime) {
                habitWithStreakAndTimes = habitWithStreakAndTime;
                for(HabitWithStreakAndTime h: habitWithStreakAndTimes){
                    Log.d("INSIGHT DATA",h.toString());
                }
                fetchHabitTrackers();
            }
        }).execute(UUID.fromString(AuthUtils.getLoggedInUser(getContext())));
    }

    public void fetchHabitTrackers(){
        habitTrackerData = new ArrayList<>();
        for (HabitWithStreakAndTime habit: habitWithStreakAndTimes) {
            new getTrackerForHabitTask(getContext(), new onGetTrackerForHabit() {
                @Override
                public void ongetTrackerForHabit(List<HabitTracker> habitTrackersForHabit) {
                    habitTrackerData.addAll(habitTrackersForHabit);
                }
            }, UUID.fromString(AuthUtils.getLoggedInUser(getContext())),habit.getHabitId(),
                    DateUtils.getCurrentMonthStart(), DateUtils.getCurrentMonthEnd());
        }
        mapHabitInsightData();
    }

    public void mapHabitInsightData(){
        habitInsightDataMap = new HashMap<>();
        for (HabitWithStreakAndTime habit: habitWithStreakAndTimes) {
            habitInsightDataMap.put(habit.getHabitId(),new HabitInsightData(habit, new ArrayList<>()));
        }

        for (HabitTracker habitTracker: habitTrackerData){
            HabitInsightData habitInsightData = habitInsightDataMap.get(habitTracker.getHabitTracker_HabitId());
            if (habitInsightData != null) {
                habitInsightData.addTrackerToList(habitTracker);
            }
        }
        setAdapter();
    }

    public void setAdapter(){
        insightsAdapter.updateContent(new ArrayList<>(habitInsightDataMap.values()));
    }

    public void defineLeftArrow(){
        iconLeft.setOnClickListener(v -> {
            int currentItem = insightContainer.getCurrentItem();
            insightContainer.setCurrentItem((currentItem + 1) % insightsAdapter.getItemCount());
        });
    }

    public void defineRightArrow(){
        iconRight.setOnClickListener(v -> {
            int currentItem = insightContainer.getCurrentItem();
            insightContainer.setCurrentItem((currentItem + 1) % insightsAdapter.getItemCount());
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    public class CustomPageTransformer implements ViewPager2.PageTransformer {
        @Override
        public void transformPage(View page, float position) {
            float speedFactor = 0.5f; // Adjust this to slow down transitions

            if (position <= -1 || position >= 1) {
                // Pages out of screen
                page.setScaleX(0.9f); // Slightly larger minimum scale for smoother effect
                page.setScaleY(0.9f);
            } else if (position < 0) {
                // Current page slides out to the left
                page.setTranslationX(position * page.getWidth() * speedFactor);
                page.setScaleX(1 + position * 0.1f); // Less aggressive shrink
                page.setScaleY(1 + position * 0.1f);
            } else {
                // Next page slides in from the right
                page.setTranslationX(position * page.getWidth() * speedFactor);
                page.setScaleX(0.9f + (1 - position) * 0.1f); // Less aggressive growth
                page.setScaleY(0.9f + (1 - position) * 0.1f);
            }
        }
    }



}