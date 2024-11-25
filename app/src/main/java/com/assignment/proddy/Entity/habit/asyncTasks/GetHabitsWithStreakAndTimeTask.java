package com.assignment.proddy.Entity.habit.asyncTasks;

import android.content.Context;
import android.os.AsyncTask;

import com.assignment.proddy.Dao.HabitDao;
import com.assignment.proddy.DatabaseConfig.ProddyDatabaseClient;
import com.assignment.proddy.ObjectMapping.HabitWithStreakAndTime;

import java.util.List;
import java.util.UUID;

public class GetHabitsWithStreakAndTimeTask extends AsyncTask<UUID,Void, List<HabitWithStreakAndTime>> {

    Context context;
    OnHabitsWithStreaksRetrieved onHabitsWithStreaksRetrieved;

    public GetHabitsWithStreakAndTimeTask(Context context, OnHabitsWithStreaksRetrieved onHabitsWithStreaksRetrieved) {
        this.context = context;
        this.onHabitsWithStreaksRetrieved = onHabitsWithStreaksRetrieved;
    }

    @Override
    protected List<HabitWithStreakAndTime> doInBackground(UUID... uuids) {
        HabitDao habitDao = ProddyDatabaseClient.getInstance(context).proddyDatabase.habitDao();
        return habitDao.getHabitsWithStreak(uuids[0]);
    }

    @Override
    protected void onPostExecute(List<HabitWithStreakAndTime> habitWithStreakAndTimes) {
        super.onPostExecute(habitWithStreakAndTimes);
        this.onHabitsWithStreaksRetrieved.onSuccess(habitWithStreakAndTimes);
    }

    public interface OnHabitsWithStreaksRetrieved{
        void onSuccess(List<HabitWithStreakAndTime> habitWithStreakAndTimes);
    }
}