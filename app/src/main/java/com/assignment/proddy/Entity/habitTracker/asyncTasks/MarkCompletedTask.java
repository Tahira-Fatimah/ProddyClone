package com.assignment.proddy.Entity.habitTracker.asyncTasks;


import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.assignment.proddy.Dao.HabitTrackerDao;
import com.assignment.proddy.DatabaseConfig.ProddyDatabaseClient;
import com.assignment.proddy.Entity.habitTracker.HabitTracker;

public class MarkCompletedTask extends AsyncTask<Integer, Void, Void> {

    private Context context;

    public MarkCompletedTask(Context context){
        this.context = context;
    }

    @Override
    protected Void doInBackground(Integer... integers) {
        HabitTrackerDao habitTrackerDao = ProddyDatabaseClient.getInstance(context).proddyDatabase.habitTrackerDao();
        return habitTrackerDao.markCompleted(integers[0]); //id of habit tracker to update
    }
}