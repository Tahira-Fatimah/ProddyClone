package com.assignment.proddy.Fragments.StartReflection;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.assignment.proddy.Adapters.AllHabitsInReflectionAdapter;
import com.assignment.proddy.Entity.habit.Habit;
import com.assignment.proddy.Entity.habit.asyncTasks.GetHabitsTask;
import com.assignment.proddy.Entity.habit.asyncTasks.GetUserHabitTask;
import com.assignment.proddy.Entity.habit.asyncTasks.onHabitsRetrievedListener;
import com.assignment.proddy.Entity.reflection.ReflectionActivities;
import com.assignment.proddy.Entity.reflection.ReflectionFeelings;
import com.assignment.proddy.ObjectMapping.HabitWithTracker;
import com.assignment.proddy.R;
import com.assignment.proddy.SharedViewModel.ReflectionSharedViewModel;
import com.assignment.proddy.Utils.AuthUtils;
import com.assignment.proddy.Utils.DateUtils;
import com.assignment.proddy.Utils.StringUtils;

import java.sql.Ref;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public class StartReflection3 extends Fragment {
    SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
    ReflectionSharedViewModel reflectionSharedViewModel;
    GridLayout gridLayout;
    List<LinearLayout> activitiesLinearLayouts;
    ListView habitsListView;
    AllHabitsInReflectionAdapter allHabitsInReflectionAdapter;
    TextView reflectionDateView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.create_reflection_page_3, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        reflectionSharedViewModel = new ViewModelProvider(requireActivity()).get(ReflectionSharedViewModel.class);
        initViews(view);
        defineActivitiesLinearLayouts();
        if (reflectionSharedViewModel.getReflectionActivitiesList().getValue() != null &&
                !reflectionSharedViewModel.getReflectionActivitiesList().getValue().isEmpty()) {
            renderInitValues();
        }
        getAllHabitsForUser();
        reflectionDateView.setText("Your Habits on " + dateFormat.format(reflectionSharedViewModel.getReflectionCreationDate().getValue()));

    }

    private void initViews(View view){
        reflectionDateView = view.findViewById(R.id.reflectionDateView);
        habitsListView = view.findViewById(R.id.habitsListView);
        gridLayout = view.findViewById(R.id.activitiesGridLayout);
        activitiesLinearLayouts = getTextViewsFromGridLayout(gridLayout);
    }

    private List<LinearLayout> getTextViewsFromGridLayout(GridLayout gridLayout) {
        List<LinearLayout> linearLayoutList = new ArrayList<>();
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            View child = gridLayout.getChildAt(i);
            if (child instanceof LinearLayout) {
                linearLayoutList.add((LinearLayout) child);
            }
        }
        return linearLayoutList;
    }

    private void defineActivitiesLinearLayouts(){
        for(LinearLayout linearLayout: activitiesLinearLayouts){
            defineActivityLinearLayoutOnClick(linearLayout);
        }
    }

    private void defineActivityLinearLayoutOnClick(LinearLayout linearLayout){
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = linearLayout.getContentDescription().toString();
                ReflectionActivities reflectionActivities = ReflectionActivities.fromString(text);

                List<ReflectionActivities> reflectionActivitiesList = reflectionSharedViewModel.getReflectionActivitiesList().getValue();

                if(reflectionActivitiesList == null){
                    reflectionActivitiesList = new ArrayList<>();
                }
                else{
                    if(reflectionActivitiesList.contains(reflectionActivities)){
                        reflectionActivitiesList.remove(reflectionActivities);
                        linearLayout.setBackground(null);
                        
                    } else{
                        reflectionActivitiesList.add(reflectionActivities);
                        linearLayout.setBackgroundResource(R.drawable.reflection_activity_selected_bg);
                    }
                }

                reflectionSharedViewModel.setReflectionActivitiesList(reflectionActivitiesList);
                Log.d("ReflectionActivity" , reflectionSharedViewModel.toString());
            }
        });
    }

    private void renderInitValues(){
        for(LinearLayout linearLayout:activitiesLinearLayouts){
            ReflectionActivities reflectionActivity = ReflectionActivities.fromString((String) linearLayout.getContentDescription());
            if(reflectionSharedViewModel.getReflectionActivitiesList().getValue().contains(reflectionActivity)){
                linearLayout.setBackgroundResource(R.drawable.reflection_activity_selected_bg);
            }
        }
    }

    private void getAllHabitsForUser(){
        new GetHabitsTask(getContext(), new onHabitsRetrievedListener() {
            @Override
            public void onHabitsRetrieved(List<HabitWithTracker> habitWithTrackers) {
                allHabitsInReflectionAdapter = new AllHabitsInReflectionAdapter(getContext(), habitWithTrackers, reflectionSharedViewModel);
                habitsListView.setAdapter(allHabitsInReflectionAdapter);
            }
        }, DateUtils.getToday(), UUID.fromString(AuthUtils.getLoggedInUser(getContext()))).execute();
    }
}