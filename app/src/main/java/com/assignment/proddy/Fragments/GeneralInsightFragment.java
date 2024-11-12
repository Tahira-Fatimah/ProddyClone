package com.assignment.proddy.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.assignment.proddy.Adapters.HabitCalenderAdapter;
import com.assignment.proddy.Adapters.HabitTrackAdapter;
import com.assignment.proddy.R;

import java.util.ArrayList;
import java.util.List;

public class GeneralInsightFragment extends Fragment {

    public GeneralInsightFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_habits_general, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Calender
        GridView habitGridView = view.findViewById(R.id.calenderGeneral);
        List<String> calendarItems = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            calendarItems.add(String.valueOf(i));
        }
        HabitCalenderAdapter adapter = new HabitCalenderAdapter(requireContext(), calendarItems);
        habitGridView.setAdapter(adapter);
    }
}