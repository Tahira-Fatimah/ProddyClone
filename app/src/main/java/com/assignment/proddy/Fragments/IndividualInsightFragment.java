package com.assignment.proddy.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.assignment.proddy.Adapters.HabitCalenderAdapter;
import com.assignment.proddy.Adapters.HabitTrackAdapter;
import com.assignment.proddy.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class IndividualInsightFragment extends Fragment {

    GridView calendarGridView;
    GridView dayTrackGridView;

    public IndividualInsightFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_individual_insight, container, false);

        dayTrackGridView = view.findViewById(R.id.habitTrack);
        calendarGridView = view.findViewById(R.id.calendarIndividual);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dayTrackGridView.setAdapter(new HabitTrackAdapter(requireContext(), new String[0]));
        calendarGridView.setAdapter(new HabitCalenderAdapter(requireContext(), new ArrayList<>()));
    }

    private void setGridViewLayout() {
        int dpWidth = 223;
        float scale = getResources().getDisplayMetrics().density;
        int pxWidth = (int) (dpWidth * scale + 0.5f);
        ViewGroup.LayoutParams params = calendarGridView.getLayoutParams();
        params.width = pxWidth;
        calendarGridView.setLayoutParams(params);
    }

}