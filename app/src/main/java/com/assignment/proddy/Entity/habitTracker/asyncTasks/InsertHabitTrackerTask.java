package com.assignment.proddy.Entity.habitTracker.asyncTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.assignment.proddy.Dao.HabitTrackerDao;
import com.assignment.proddy.DatabaseConfig.ProddyDatabaseClient;
import com.assignment.proddy.Entity.habitTracker.HabitTracker;

public class InsertHabitTrackerTask extends AsyncTask<HabitTracker, Void, Long> {

    private Context context;

    public InsertHabitTrackerTask(Context context){
        this.context = context;
    }

    @Override
    protected Long doInBackground(HabitTracker... habitTrackers) {
        HabitTrackerDao habitTrackerDao = ProddyDatabaseClient.getInstance(context).proddyDatabase.habitTrackerDao();
        return habitTrackerDao.insert(habitTrackers[0]);
    }

    @Override
    protected void onPostExecute(Long aLong) {
        super.onPostExecute(aLong);
        if (aLong!=-1){
            Toast.makeText(context,"Inserted habitTracker with id "+aLong, Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(context,"Failed to Insert habitTracker", Toast.LENGTH_LONG).show();
        }
    }
}