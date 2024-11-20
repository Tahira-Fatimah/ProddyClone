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
import com.assignment.proddy.SharedViewModel.HabitSingleton;
import com.assignment.proddy.Utils.StringUtils;

import java.sql.Time;
import java.util.List;

public class SetReminder extends AppCompatActivity {

    WheelPicker minWheelPicker, hourWheelPicker, timeWheelPicker;
    String selectedHour;
    String selectedMin;
    String time;
    TextView dontReminTextView, confirmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.edit_habit_create_reminder);

        initViews();
        setHourWheelPicker();
        setMinWheelPicker();
        setTimeWheelPicker();
        defineDontRemindMe();
        defineConfirmBtn();
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
            }
        });
    }

    private void updateReminderTime() {
        if (selectedHour != null && selectedMin != null && time != null) {
            setReminderTime(Integer.parseInt(selectedHour), Integer.parseInt(selectedMin), time);
        }
    }

    private void defineConfirmBtn(){
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateReminderTime();
                finish();
            }
        });
    }

    private void defineDontRemindMe(){
        dontReminTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HabitSingleton.getInstance().setReminderTime(null);
                finish();
            }
        });
    }

    public void setReminderTime(int hour, int minute, String amPm) {
        int finalHour = hour;

        if ("PM".equals(amPm) && hour != 12) {
            finalHour += 12;
        } else if ("AM".equals(amPm) && hour == 12) {
            finalHour = 0;
        }

        Time reminder = new Time(finalHour, minute, 0);
        HabitSingleton.getInstance().setReminderTime(reminder);
    }
}