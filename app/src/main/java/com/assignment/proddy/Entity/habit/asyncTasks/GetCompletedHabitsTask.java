package com.assignment.proddy.Entity.habit.asyncTasks;

import android.content.Context;
import android.os.AsyncTask;

import com.assignment.proddy.Dao.HabitDao;
import com.assignment.proddy.DatabaseConfig.ProddyDatabaseClient;
import com.assignment.proddy.Entity.habit.Habit;
import com.assignment.proddy.ObjectMapping.HabitWithTrackers;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class GetCompletedHabitsTask extends AsyncTask<Void, Void, Habit> {

    private Context context;
    onCompletedHabitsRetrievedListener listener;
    private String userId;

    public GetCompletedHabitsTask(Context context, onCompletedHabitsRetrievedListener listener, String userId){
        this.context = context;
        this.listener = listener;
        this.userId = userId;
    }

    @Override
    protected Habit doInBackground(Void... voids) {
        HabitDao habitDao = ProddyDatabaseClient.getInstance(context).proddyDatabase.habitDao();
        return habitDao.getCompletedHabits(userId);
    }

    @Override
    protected void onPostExecute(Habit habitsWithTrackers) {
        super.onPostExecute(habitsWithTrackers);
        if (listener != null) {
            listener.onCompletedHabitsRetrieved(habitsWithTrackers);
        }
    }

}