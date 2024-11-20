package com.assignment.proddy.SharedViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.assignment.proddy.Entity.habit.HabitType;

import java.sql.Time;
import java.util.List;

public class HabitSharedViewModel extends AndroidViewModel {

    private final MutableLiveData<String> habitName = new MutableLiveData<>();
    private final MutableLiveData<HabitType> habitType = new MutableLiveData<>();
    private final MutableLiveData<String> habitMotivationMessage = new MutableLiveData<>();
    private final MutableLiveData<List<String>> habitDays = new MutableLiveData<List<String>>();
    private final MutableLiveData<Time> reminderTime = new MutableLiveData<>();
    private final MutableLiveData<Boolean> dontRemindMe = new MutableLiveData<>(Boolean.FALSE);

    public HabitSharedViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Boolean> getDontRemindMe(){
        return this.dontRemindMe;
    }

    public void setDontRemindMe(Boolean dontRemindMe){
        this.dontRemindMe.setValue(dontRemindMe);
    }

    public LiveData<HabitType> getHabitType() {
        return habitType;
    }

    public LiveData<String> getHabitMotivationMessage() {
        return habitMotivationMessage;
    }

    public void setHabitDays(List<String> habitDays){
        this.habitDays.setValue(habitDays);
    }

    public LiveData<List<String>> getHabitDays(){
        return this.habitDays;
    }

    public void setHabitName(String name) {
        habitName.setValue(name);
    }

    public void setHabitType(HabitType habitType){
        this.habitType.setValue(habitType);
    }

    public void setHabitMotivationMessage(String habitMotivationMessage){
        this.habitMotivationMessage.setValue(habitMotivationMessage);
    }

    public LiveData<String> getHabitName() {
        return habitName;
    }

    public void setReminderTime(int hour, int minute, String amPm) {
        if(Boolean.TRUE.equals(dontRemindMe.getValue())){
            reminderTime.setValue(null);
        }
        else{
            int finalHour = hour;

            if ("PM".equals(amPm) && hour != 12) {
                finalHour += 12;
            } else if ("AM".equals(amPm) && hour == 12) {
                finalHour = 0;
            }

            Time reminder = new Time(finalHour, minute, 0);
            reminderTime.setValue(reminder);
        }

    }

    public LiveData<Time> getReminderTime() {
        return reminderTime;
    }


    @Override
    public String toString() {
        return "HabitSharedViewModel{" +
                "habitName='" + (habitName.getValue() != null ? habitName.getValue() : "null") + '\'' +
                ", habitType=" + (habitType.getValue() != null ? habitType.getValue().toString() : "null") +
                ", habitMotivationMessage='" + (habitMotivationMessage.getValue() != null ? habitMotivationMessage.getValue() : "null") + '\'' +
                ", habitDays=" + (habitDays.getValue() != null ? habitDays.getValue().toString() : "[]") +
                ", reminderTime=" + (reminderTime.getValue() != null ? reminderTime.getValue().toString() : "null") +
                '}';
    }


}
