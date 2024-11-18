package com.assignment.proddy.Fragments.CreateHabit;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aigestudio.wheelpicker.WheelPicker;
import com.assignment.proddy.R;
import com.assignment.proddy.SharedViewModel.HabitSharedViewModel;
import com.assignment.proddy.SharedViewModel.NavigationViewModel;
import com.assignment.proddy.Utils.IntegerUtils;
import com.assignment.proddy.Utils.StringUtils;

import java.util.List;

public class CreateHabit5 extends Fragment {

    String selectedHour;
    String selectedMin;
    String time;
    HabitSharedViewModel habitSharedViewModel;
    NavigationViewModel navigationViewModel;
    TextView dontReminTextView;

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

        defineHourWheelPicker(view);
        defineMinWheelPicker(view);
        defineTimeWheelPicker(view);
        updateReminderTime();

        dontReminTextView = view.findViewById(R.id.dontRemindMe);
        defineDontRemindTextView();


    }

    private void defineDontRemindTextView(){
        dontReminTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                habitSharedViewModel.setDontRemindMe(Boolean.TRUE);
                updateReminderTime();
                navigationViewModel.triggerNavigation();

            }
        });
    }

    private void defineHourWheelPicker(View view){
        WheelPicker hourWheelPicker = view.findViewById(R.id.hourWheelPicker);
        List<String> hourData = StringUtils.getHourData();
        selectedHour = hourData.get(0);
        hourWheelPicker.setData(hourData);
        hourWheelPicker.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
            @Override
            public void onItemSelected(WheelPicker picker, Object data, int position) {
                selectedHour = (String) data;
                updateReminderTime();
            }
        });
    }

    private void defineMinWheelPicker(View view){
        WheelPicker minWheelPicker = view.findViewById(R.id.minWheelPicker);
        List<String> minData = StringUtils.getMinData();
        selectedMin = minData.get(0);
        minWheelPicker.setData(minData);
        minWheelPicker.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
            @Override
            public void onItemSelected(WheelPicker picker, Object data, int position) {
                selectedMin = (String) data;
                updateReminderTime();
            }
        });
    }

    private void defineTimeWheelPicker(View view){

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
    }

    private void updateReminderTime() {
        if (selectedHour != null && selectedMin != null && time != null) {
            habitSharedViewModel.setReminderTime(Integer.parseInt(selectedHour), Integer.parseInt(selectedMin), time);
        }
    }
}