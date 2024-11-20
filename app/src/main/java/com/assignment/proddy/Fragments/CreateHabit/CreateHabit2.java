package com.assignment.proddy.Fragments.CreateHabit;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.assignment.proddy.Adapters.HabitCategoryAdapter;
import com.assignment.proddy.Entity.habit.HabitType;
import com.assignment.proddy.Models.HabitCategory;
import com.assignment.proddy.R;
import com.assignment.proddy.SharedViewModel.HabitSharedViewModel;
import com.assignment.proddy.Utils.StringUtils;
import com.assignment.proddy.ZoomOutPageTransformer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateHabit2 extends Fragment {

    HabitSharedViewModel habitSharedViewModel;
    ViewPager2 habitCategoryViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.create_habit_page_2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        habitSharedViewModel = new ViewModelProvider(requireActivity()).get(HabitSharedViewModel.class);
        habitCategoryViewPager = view.findViewById(R.id.habitCategoryViewPager);
        defineHabitTypeViewPager();

    }

    private void defineHabitTypeViewPager(){
        List<HabitCategory> habitCategories = StringUtils.getHabitCategories();
        HabitCategoryAdapter habitCategoryAdapter = new HabitCategoryAdapter(habitCategories, R.layout.habit_category_item);
        habitCategoryViewPager.setAdapter(habitCategoryAdapter);
        habitCategoryViewPager.setOffscreenPageLimit(3);
        habitCategoryViewPager.setPageTransformer(new ZoomOutPageTransformer());

        habitCategoryViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                HabitCategory selectedCategory = habitCategories.get(position % habitCategories.size());
                habitSharedViewModel.setHabitType(selectedCategory.getHabitType());
            }
        });
    }
}