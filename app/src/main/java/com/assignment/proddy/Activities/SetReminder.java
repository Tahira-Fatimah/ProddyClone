package com.assignment.proddy.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.aigestudio.wheelpicker.WheelPicker;
import com.assignment.proddy.R;
import com.assignment.proddy.SharedViewModel.HabitSharedViewModel;
import com.assignment.proddy.Utils.StringUtils;

import java.util.List;

public class SetReminder extends AppCompatActivity {

    WheelPicker minWheelPicker, hourWheelPicker, timeWheelPicker;
    String selectedHour;
    String selectedMin;
    String time;
    TextView dontReminTextView, confirmBtn;
    HabitSharedViewModel habitSharedViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.edit_habit_create_reminder);

        initViews();
        setHourWheelPicker();
        setMinWheelPicker();
        setTimeWheelPicker();
        habitSharedViewModel = new ViewModelProvider(
                this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())
        ).get(HabitSharedViewModel.class);
    }

    private void initViews(){
        minWheelPicker = findViewById(R.id.minWheelPicker);
        timeWheelPicker = findViewById(R.id.timeWheelPicker);
        hourWheelPicker = findViewById(R.id.hourWheelPicker);
        dontReminTextView = findViewById(R.id.dontRemindMe);
        confirmBtn = findViewById(R.id.confirmBtn);
    }

    private void setMinWheelPicker(){
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

    private void setHourWheelPicker(){
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

    private void setTimeWheelPicker(){
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

    private void defineConfirmBtn(){
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void defineDontRemindMe(){
        dontReminTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                habitSharedViewModel.setDontRemindMe(Boolean.TRUE);
                updateReminderTime();
                finish();
            }
        });
    }
}