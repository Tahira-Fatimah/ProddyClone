package com.assignment.proddy.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.assignment.proddy.Adapters.HabitCategoryAdapter;
import com.assignment.proddy.Models.HabitCategory;
import com.assignment.proddy.R;
import com.assignment.proddy.ZoomOutPageTransformer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateHabit2 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.create_habit_page_2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewPager2 habitCategoryViewPager = view.findViewById(R.id.habitCategoryViewPager);
        List<HabitCategory> habitCategories = new ArrayList<>(Arrays.asList(
                new HabitCategory(R.drawable.mindfulness, "Mindfulness"),
                new HabitCategory(R.drawable.finances, "Finances"),
                new HabitCategory(R.drawable.learning, "Learning"),
                new HabitCategory(R.drawable.health, "Health"),
                new HabitCategory(R.drawable.mind, "Mind"),
                new HabitCategory(R.drawable.productivity, "Productivity"),
                new HabitCategory(R.drawable.fun, "Fun"),
                new HabitCategory(R.drawable.relationships, "Relationships")
        ));


        HabitCategoryAdapter habitCategoryAdapter = new HabitCategoryAdapter(habitCategories);
        habitCategoryViewPager.setAdapter(habitCategoryAdapter);
        habitCategoryViewPager.setOffscreenPageLimit(3);
        habitCategoryViewPager.setPageTransformer(new ZoomOutPageTransformer());
    }
}