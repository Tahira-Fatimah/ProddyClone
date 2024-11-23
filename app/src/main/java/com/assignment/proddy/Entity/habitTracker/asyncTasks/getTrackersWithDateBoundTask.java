package com.assignment.proddy.Entity.habitTracker.asyncTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.assignment.proddy.Dao.HabitTrackerDao;
import com.assignment.proddy.DatabaseConfig.ProddyDatabaseClient;
import com.assignment.proddy.Entity.habitTracker.HabitTracker;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class getTrackersWithDateBoundTask extends AsyncTask<Void, Void, List<HabitTracker> >{

    private Context context;
    onGetTrackersWithDateBoundListener listener;
    Date startDate;
    Date endDate;
    UUID userID;

    public getTrackersWithDateBoundTask(Context context, onGetTrackersWithDateBoundListener listener, UUID userID, Date startDate, Date endDate){
        this.context = context;
        this.listener = listener;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userID = userID;
    }

    @Override
    protected List<HabitTracker> doInBackground(Void... voids) {
        HabitTrackerDao habitTrackerDao = ProddyDatabaseClient.getInstance(context).proddyDatabase.habitTrackerDao();
        return habitTrackerDao.getTrackersWithDateBound(userID,startDate,endDate);
    }

    @Override
    protected void onPostExecute(List<HabitTracker> habitTrackersForDateBounds) {
        super.onPostExecute(habitTrackersForDateBounds);

        if (listener != null) {
            listener.getTrackersWithDateBoundListener(habitTrackersForDateBounds);
        }
    }
}
