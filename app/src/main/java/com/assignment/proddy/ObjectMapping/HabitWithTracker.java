package com.assignment.proddy.ObjectMapping;

import androidx.annotation.NonNull;
import androidx.room.TypeConverters;

import com.assignment.proddy.Entity.habit.Habit;
import com.assignment.proddy.Entity.habit.HabitType;
import com.assignment.proddy.Entity.habit.HabitTypeConverter;
import com.assignment.proddy.Entity.habitTracker.HabitTracker;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class HabitWithTracker {

        // Attributes from Habit
        @NonNull
        private UUID habitId;
        private UUID habit_UserId;
        private String habitName;
        private String habitReason;
        private List<String> habitDays;
        @TypeConverters(HabitTypeConverter.class)
        private HabitType habitType;
        private Time habitReminderTime;

        // Attributes from HabitTracker
        @NonNull
        private UUID habitTrackerId;
        private UUID habitTracker_HabitId;
        private Date habitTrackerDate;
        private Boolean habitTrackerStatus;

        public HabitWithTracker(@NonNull UUID habitId, UUID habit_UserId, String habitName, String habitReason, List<String> habitDays, HabitType habitType, Time habitReminderTime, @NonNull UUID habitTrackerId, UUID habitTracker_HabitId, Date habitTrackerDate, Boolean habitTrackerStatus) {
                this.habitId = habitId;
                this.habit_UserId = habit_UserId;
                this.habitName = habitName;
                this.habitReason = habitReason;
                this.habitDays = habitDays;
                this.habitType = habitType;
                this.habitReminderTime = habitReminderTime;
                this.habitTrackerId = habitTrackerId;
                this.habitTracker_HabitId = habitTracker_HabitId;
                this.habitTrackerDate = habitTrackerDate;
                this.habitTrackerStatus = habitTrackerStatus;
        }

        public Habit getHabit(){
                return new Habit(getHabitId(),getHabitName(),getHabitReason(),getHabitType(),getHabit_UserId(),getHabitReminderTime(),getHabitDays());
        }

        public HabitTracker getHabitTracker() {
                if(this.habitTracker_HabitId == null) {
                        return null;
                }
                return new HabitTracker(getHabitTrackerId(),getHabitTracker_HabitId(),getHabitTrackerDate(),getHabitTrackerStatus());
        }

        @NonNull
        public UUID getHabitId() {
                return habitId;
        }

        public void setHabitId(@NonNull UUID habitId) {
                this.habitId = habitId;
        }

        public UUID getHabit_UserId() {
                return habit_UserId;
        }

        public void setHabit_UserId(UUID habit_UserId) {
                this.habit_UserId = habit_UserId;
        }

        public String getHabitName() {
                return habitName;
        }

        public void setHabitName(String habitName) {
                this.habitName = habitName;
        }

        public String getHabitReason() {
                return habitReason;
        }

        public void setHabitReason(String habitReason) {
                this.habitReason = habitReason;
        }

        public List<String> getHabitDays() {
                return habitDays;
        }

        public void setHabitDays(List<String> habitDays) {
                this.habitDays = habitDays;
        }

        public HabitType getHabitType() {
                return habitType;
        }

        public void setHabitType(HabitType habitType) {
                this.habitType = habitType;
        }

        public Time getHabitReminderTime() {
                return habitReminderTime;
        }

        public void setHabitReminderTime(Time habitReminderTime) {
                this.habitReminderTime = habitReminderTime;
        }

        @NonNull
        public UUID getHabitTrackerId() {
                return habitTrackerId;
        }

        public void setHabitTrackerId(@NonNull UUID habitTrackerId) {
                this.habitTrackerId = habitTrackerId;
        }

        public UUID getHabitTracker_HabitId() {
                return habitTracker_HabitId;
        }

        public void setHabitTracker_HabitId(UUID habitTracker_HabitId) {
                this.habitTracker_HabitId = habitTracker_HabitId;
        }

        public Date getHabitTrackerDate() {
                return habitTrackerDate;
        }

        public void setHabitTrackerDate(Date habitTrackerDate) {
                this.habitTrackerDate = habitTrackerDate;
        }

        public Boolean getHabitTrackerStatus() {
                return habitTrackerStatus;
        }

        public void setHabitTrackerStatus(Boolean habitTrackerStatus) {
                this.habitTrackerStatus = habitTrackerStatus;
        }
}
