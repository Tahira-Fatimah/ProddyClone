package com.assignment.proddy.SharedViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.assignment.proddy.Entity.habit.Habit;
import com.assignment.proddy.Entity.reflection.Reflection;
import com.assignment.proddy.Entity.reflection.ReflectionActivities;
import com.assignment.proddy.Entity.reflection.ReflectionFeelings;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ReflectionSharedViewModel extends AndroidViewModel {

    private final MutableLiveData<UUID> reflectionId = new MutableLiveData<>();
    private final MutableLiveData<UUID> reflectionUserId = new MutableLiveData<>();
    private final MutableLiveData<List<ReflectionFeelings>> reflectionFeelingsList = new MutableLiveData<>();
    private final MutableLiveData<Integer> reflectionFeelingRate = new MutableLiveData<>(3);
    private final MutableLiveData<List<ReflectionActivities>> reflectionActivitiesList = new MutableLiveData<>();
    private final MutableLiveData<String> reflectionThoughts = new MutableLiveData<>();
    private final MutableLiveData<Date> reflectionCreationDate = new MutableLiveData<>();
    private final MutableLiveData<List<Habit>> completedHabits = new MutableLiveData<>(new ArrayList<>());

    public ReflectionSharedViewModel(@NonNull Application application) {
        super(application);
    }

    // Getter and Setter for reflectionId
    public LiveData<UUID> getReflectionId() {
        return reflectionId;
    }

    public LiveData<List<Habit>> getHabits(){
        return completedHabits;
    }

    public void setHabit(Habit habit){
        completedHabits.getValue().add(habit);
    }

    public void setReflectionId(UUID id) {
        this.reflectionId.setValue(id);
    }

    // Getter and Setter for reflectionUserId
    public LiveData<UUID> getReflectionUserId() {
        return reflectionUserId;
    }

    public void setReflectionUserId(UUID userId) {
        this.reflectionUserId.setValue(userId);
    }

    // Getter and Setter for reflectionFeelingsList
    public LiveData<List<ReflectionFeelings>> getReflectionFeelingsList() {
        return reflectionFeelingsList;
    }

    public void setReflectionFeelingsList(List<ReflectionFeelings> feelingsList) {
        this.reflectionFeelingsList.setValue(feelingsList);
    }

    // Getter and Setter for reflectionFeelingRate
    public LiveData<Integer> getReflectionFeelingRate() {
        return reflectionFeelingRate;
    }

    public void setReflectionFeelingRate(Integer feelingRate) {
        this.reflectionFeelingRate.setValue(feelingRate);
    }

    // Getter and Setter for reflectionActivitiesList
    public LiveData<List<ReflectionActivities>> getReflectionActivitiesList() {
        return reflectionActivitiesList;
    }

    public void setReflectionActivitiesList(List<ReflectionActivities> activitiesList) {
        this.reflectionActivitiesList.setValue(activitiesList);
    }

    // Getter and Setter for reflectionThoughts
    public LiveData<String> getReflectionThoughts() {
        return reflectionThoughts;
    }

    public void setReflectionThoughts(String thoughts) {
        this.reflectionThoughts.setValue(thoughts);
    }

    public void setReflectionCreationDate(Date reflectionCreationDate) {
        this.reflectionCreationDate.setValue(reflectionCreationDate);
    }

    public LiveData<Date> getReflectionCreationDate(){
        return reflectionCreationDate;
    }

    // Method to populate the ViewModel from a Reflection object
    public void setReflectionData(Reflection reflection) {
        setReflectionId(reflection.getReflectionId());
        setReflectionUserId(reflection.getReflection_UserId());
        setReflectionFeelingsList(reflection.getReflectionFeelingsList());
        setReflectionFeelingRate(reflection.getReflectionFeelingRate());
        setReflectionActivitiesList(reflection.getReflectionActivitiesList());
        setReflectionThoughts(reflection.getReflectionThoughts());
        setReflectionCreationDate(reflection.getReflectionCreationDate());
    }


    @Override
    public String toString() {
        return "ReflectionSharedViewModel{" +
                "reflectionId=" + (reflectionId.getValue() != null ? reflectionId.getValue().toString() : "null") +
                ", reflectionUserId=" + (reflectionUserId.getValue() != null ? reflectionUserId.getValue().toString() : "null") +
                ", reflectionFeelingsList=" + (reflectionFeelingsList.getValue() != null ? reflectionFeelingsList.getValue().toString() : "[]") +
                ", reflectionFeelingRate=" + (reflectionFeelingRate.getValue() != null ? reflectionFeelingRate.getValue() : 0) +
                ", reflectionActivitiesList=" + (reflectionActivitiesList.getValue() != null ? reflectionActivitiesList.getValue().toString() : "[]") +
                ", reflectionThoughts='" + (reflectionThoughts.getValue() != null ? reflectionThoughts.getValue() : "null") + '\'' +
                '}';
    }
}
