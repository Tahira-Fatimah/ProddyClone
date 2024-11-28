package com.assignment.proddy.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Update;
import androidx.viewpager2.widget.ViewPager2;

import com.assignment.proddy.Adapters.StartReflectionAdapter;
import com.assignment.proddy.Entity.habit.Habit;
import com.assignment.proddy.Entity.habitStreak.asyncTasks.SetHabitStreakTask;
import com.assignment.proddy.Entity.habitTracker.HabitTracker;
import com.assignment.proddy.Entity.habitTracker.asyncTasks.InsertHabitTrackerTask;
import com.assignment.proddy.Entity.reflection.Reflection;
import com.assignment.proddy.Entity.reflection.asyncTask.UpdateReflectionTask;
import com.assignment.proddy.Fragments.StartReflection.StartReflection1;
import com.assignment.proddy.Fragments.StartReflection.StartReflection2;
import com.assignment.proddy.Fragments.StartReflection.StartReflection3;
import com.assignment.proddy.Fragments.StartReflection.StartReflection4;
import com.assignment.proddy.Entity.reflection.asyncTask.InsertReflectionTask;
import com.assignment.proddy.R;
import com.assignment.proddy.SharedViewModel.ReflectionNavigationViewModel;
import com.assignment.proddy.SharedViewModel.ReflectionSharedViewModel;
import com.assignment.proddy.Utils.AuthUtils;
import com.assignment.proddy.Utils.DateUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public class StartReflection extends AppCompatActivity {

    List<Fragment> createReflectionFragments;
    StartReflectionAdapter startReflectionAdapter;
    ViewPager2 startReflectionViewPager;
    ReflectionSharedViewModel reflectionSharedViewModel;
    ReflectionNavigationViewModel reflectionNavigationViewModel;
    FloatingActionButton backBtn, nextBtn;
    Reflection receivedReflection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_reflection);
        receivedReflection = (Reflection) getIntent().getSerializableExtra("Reflection");
        initViews();
        initViewModels();
        initFragments();
        observeNavigationViewModel();
        startReflectionViewPager = findViewById(R.id.startReflectionViewPager);
        defineReflectionViewPager();
        defineNextBtn();
        defineBackBtn();
        defineStartReflectionAdapter();

        if(receivedReflection != null){
            reflectionSharedViewModel.setReflectionData(receivedReflection);
            if(receivedReflection.getReflectionFeelingRate() == 0){
                reflectionSharedViewModel.setReflectionFeelingRate(3);
            }
        }
    }

    private void initViews(){
        backBtn = findViewById(R.id.backBtn);
        nextBtn = findViewById(R.id.nextBtn);
    }

    private void defineNextBtn(){
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(startReflectionViewPager.getCurrentItem() == createReflectionFragments.size() -1){
                    if (receivedReflection.getReflectionId() == null) {
                        insertRelection();
                    } else {
                        updateReflection();
                    }
                    markHabitsCompleted();
                } else{
                    startReflectionViewPager.setCurrentItem(startReflectionViewPager.getCurrentItem() + 1);
                }
            }
        });
    }

    private void updateReflection() {
        Reflection reflection = getReflectionFromSharedViewModel(receivedReflection.getReflectionId());
        new UpdateReflectionTask(StartReflection.this, new UpdateReflectionTask.onUpdateReflectionListener() {
            @Override
            public void onSuccess() {
                setActivityResult(reflection);
            }
        }).execute(reflection);
    }

    private void insertRelection() {
        Reflection reflection = getReflectionFromSharedViewModel(null);
        new InsertReflectionTask(StartReflection.this, new InsertReflectionTask.onInsertReflectionListener() {
            @Override
            public void onSuccess() {
                reflection.setReflectionCreationDate(receivedReflection.getReflectionCreationDate());
                setActivityResult(reflection);
            }
        }).execute(reflection);
    }

    private void setActivityResult(Reflection reflection){
        Intent resultIntent = new Intent();
        resultIntent.putExtra("Reflection", reflection);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    private Reflection getReflectionFromSharedViewModel(UUID reflectionId){
        if(reflectionId == null){
            reflectionId = UUID.randomUUID();
        }
        return new Reflection(reflectionId, UUID.fromString(AuthUtils.getLoggedInUser(StartReflection.this)),
                reflectionSharedViewModel.getReflectionFeelingsList().getValue(), reflectionSharedViewModel.getReflectionFeelingRate().getValue(),
                reflectionSharedViewModel.getReflectionActivitiesList().getValue(), reflectionSharedViewModel.getReflectionThoughts().getValue(),
                DateUtils.getDateOnly(receivedReflection.getReflectionCreationDate()));
    }

    private void defineBackBtn(){
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startReflectionViewPager.setCurrentItem(startReflectionViewPager.getCurrentItem() - 1);
            }
        });
    }

    private void initViewModels(){
        reflectionSharedViewModel = new ViewModelProvider(this).get(ReflectionSharedViewModel.class);
        reflectionNavigationViewModel = new ViewModelProvider(this).get(ReflectionNavigationViewModel.class);
    }

    private void observeNavigationViewModel(){
        reflectionNavigationViewModel.getNavigateToNextFragment().observe(this, shouldNavigate -> {
            if (shouldNavigate != null && shouldNavigate) {
                startReflectionViewPager.setCurrentItem(startReflectionViewPager.getCurrentItem() + 1);
                reflectionNavigationViewModel.resetNavigation();
            }
        });

        reflectionNavigationViewModel.getEndActivity().observe(this, shouldEndActivity -> {
            if (shouldEndActivity != null && shouldEndActivity) {
                finish();
            }
        });
    }

    private void initFragments(){
        createReflectionFragments = List.of(
                new StartReflection1(),
                new StartReflection2(),
                new StartReflection3(),
                new StartReflection4()
        );
    }

    private void defineStartReflectionAdapter(){
        startReflectionAdapter = new StartReflectionAdapter(this, createReflectionFragments);
        startReflectionViewPager.setAdapter(startReflectionAdapter);
    }

    private void defineReflectionViewPager(){
        startReflectionViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if(position == 0){
                    backBtn.setVisibility(View.GONE);
                    nextBtn.setVisibility(View.GONE);
                } else{
                    backBtn.setVisibility(View.VISIBLE);
                    nextBtn.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void markHabitsCompleted(){
        for(Habit habit : reflectionSharedViewModel.getHabits().getValue()){
            new InsertHabitTrackerTask(StartReflection.this).execute(new HabitTracker(UUID.randomUUID(),habit.getHabitId(), DateUtils.getDateOnly(DateUtils.getToday()),true));
            new SetHabitStreakTask(StartReflection.this).execute(habit.getHabitId());
        }
    }

}