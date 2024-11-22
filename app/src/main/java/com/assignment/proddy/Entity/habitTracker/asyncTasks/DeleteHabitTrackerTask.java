package com.assignment.proddy.Entity.habitTracker.asyncTasks;

import android.content.Context;
import android.os.AsyncTask;

import com.assignment.proddy.Dao.HabitTrackerDao;
import com.assignment.proddy.DatabaseConfig.ProddyDatabaseClient;
import com.assignment.proddy.Entity.habitTracker.HabitTracker;

public class DeleteHabitTrackerTask extends AsyncTask<HabitTracker, Void, Void> {

    private Context context;

    public DeleteHabitTrackerTask(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(HabitTracker... habitTrackers) {
        HabitTrackerDao habitTrackerDao = ProddyDatabaseClient.getInstance(context).proddyDatabase.habitTrackerDao();
        return habitTrackerDao.delete(habitTrackers[0]);
    }
}