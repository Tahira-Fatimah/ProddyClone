package com.assignment.proddy.Fragments.CreateHabit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.assignment.proddy.R;
import com.assignment.proddy.SharedViewModel.HabitSharedViewModel;
import com.assignment.proddy.SharedViewModel.NavigationViewModel;
import com.assignment.proddy.Utils.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateHabit3 extends Fragment {

    private LinearLayout customDaysLayout;
    HabitSharedViewModel habitSharedViewModel;
    NavigationViewModel navigationViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.create_habit_page_3, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        habitSharedViewModel.setHabitDays(new ArrayList<>());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        customDaysLayout = view.findViewById(R.id.customDaysLayout);
        habitSharedViewModel = new ViewModelProvider(requireActivity()).get(HabitSharedViewModel.class);
        navigationViewModel = new ViewModelProvider(requireActivity()).get(NavigationViewModel.class);

        Button customDaysButton = view.findViewById(R.id.customDaysButton);
        Button everydayButton = view.findViewById(R.id.everydayButton);
        TextView inBetweenOr = view.findViewById(R.id.inBetweenOr);

        customDaysButton.setOnClickListener(v -> {
            everydayButton.setVisibility(View.GONE);
            customDaysButton.setVisibility(View.GONE);
            customDaysLayout.setVisibility(View.VISIBLE);
            inBetweenOr.setVisibility(View.GONE);
        });

        setDayButtonListener(view, R.id.monday);
        setDayButtonListener(view, R.id.tuesday);
        setDayButtonListener(view, R.id.wednesday);
        setDayButtonListener(view, R.id.thursday);
        setDayButtonListener(view, R.id.friday);
        setDayButtonListener(view, R.id.saturday);
        setDayButtonListener(view, R.id.sunday);

        everydayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                habitSharedViewModel.setHabitDays(StringUtils.getAllDays());
                navigationViewModel.triggerNavigation();
            }
        });
    }

    private void setDayButtonListener(View view, int buttonId) {
        Button dayButton = view.findViewById(buttonId);

        dayButton.setOnClickListener(v -> {
            String day = ((Button) v).getText().toString();
            List<String> selectedDays = habitSharedViewModel.getHabitDays().getValue();
            if (selectedDays == null) {
                selectedDays = new ArrayList<>();
            }

            if (selectedDays.contains(day)) {
                selectedDays.remove(day);
                dayButton.setBackgroundTintList(ContextCompat.getColorStateList(getActivity(), R.color.create_habit_days_button));
            } else {
                selectedDays.add(day);
                dayButton.setBackgroundTintList(ContextCompat.getColorStateList(getActivity(), R.color.create_habit_prompt));
            }

            habitSharedViewModel.setHabitDays(selectedDays);
            System.out.println("Selected Days " + selectedDays);
        });
    }
}
