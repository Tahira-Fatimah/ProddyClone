package com.assignment.proddy.Fragments.CreateHabit;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aigestudio.wheelpicker.WheelPicker;
import com.aigestudio.wheelpicker.widgets.WheelDatePicker;
import com.assignment.proddy.R;
import com.assignment.proddy.SharedViewModel.HabitSharedViewModel;
import com.assignment.proddy.SharedViewModel.NavigationViewModel;
import com.assignment.proddy.Utils.IntegerUtils;
import com.assignment.proddy.Utils.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateHabit5 extends Fragment {

    Integer selectedHour;
    Integer selectedMin;
    String time;
    Boolean dontRemindMe = false;
    HabitSharedViewModel habitSharedViewModel;
    NavigationViewModel navigationViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.create_habit_page_5, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navigationViewModel = new ViewModelProvider(requireActivity()).get(NavigationViewModel.class);
        habitSharedViewModel = new ViewModelProvider(requireActivity()).get(HabitSharedViewModel.class);
        WheelPicker hourWheelPicker = view.findViewById(R.id.hourWheelPicker);
        List<Integer> hourData = IntegerUtils.getHourData();
        selectedHour = hourData.get(0);
        hourWheelPicker.setData(hourData);
        hourWheelPicker.setIndicator(true);
        hourWheelPicker.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
            @Override
            public void onItemSelected(WheelPicker picker, Object data, int position) {
                selectedHour = (Integer) data;
                updateReminderTime();
            }
        });

        WheelPicker minWheelPicker = view.findViewById(R.id.minWheelPicker);
        List<Integer> minData = IntegerUtils.getMinData();
        selectedMin = minData.get(0);
        minWheelPicker.setData(minData);
        minWheelPicker.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
            @Override
            public void onItemSelected(WheelPicker picker, Object data, int position) {
                selectedMin = (Integer) data;
                updateReminderTime();
            }
        });

        WheelPicker timeWheelPicker = view.findViewById(R.id.timeWheelPicker);
        List<String> timeData = StringUtils.getTimeData();
        time = timeData.get(0);
        timeWheelPicker.setData(timeData);
        timeWheelPicker.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
            @Override
            public void onItemSelected(WheelPicker picker, Object data, int position) {
                time = (String) data;
                updateReminderTime();
            }
        });

        TextView textView = view.findViewById(R.id.dontRemindMe);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dontRemindMe = true;
                navigationViewModel.triggerNavigation();
            }
        });

    }

    private void updateReminderTime() {
        if (selectedHour != null && selectedMin != null && time != null) {
            habitSharedViewModel.setReminderTime(selectedHour, selectedMin, time, dontRemindMe);
        }
    }
}