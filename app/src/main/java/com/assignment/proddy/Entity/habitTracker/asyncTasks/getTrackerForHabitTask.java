package com.assignment.proddy.Entity.habitTracker.asyncTasks;

import android.content.Context;
import android.os.AsyncTask;

import com.assignment.proddy.Dao.HabitTrackerDao;
import com.assignment.proddy.DatabaseConfig.ProddyDatabaseClient;
import com.assignment.proddy.Entity.habitTracker.HabitTracker;
import com.assignment.proddy.ObjectMapping.HabitWithTracker;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class getTrackerForHabitTask extends AsyncTask<Void, Void, List<HabitTracker> >{

    private Context context;
    onGetTrackerForHabit listener;
    Date startDate;
    Date endDate;
    UUID userID;
    UUID habitId;

    public getTrackerForHabitTask(Context context, onGetTrackerForHabit listener, UUID userID, UUID habitId, Date startDate, Date endDate){
        this.context = context;
        this.listener = listener;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userID = userID;
        this.habitId = habitId;
    }

    @Override
    protected List<HabitTracker> doInBackground(Void... voids) {
        HabitTrackerDao habitTrackerDao = ProddyDatabaseClient.getInstance(context).proddyDatabase.habitTrackerDao();
        return habitTrackerDao.getTrackersForHabitWithDateBound(userID,habitId,startDate,endDate);
    }

    @Override
    protected void onPostExecute(List<HabitTracker> habitTrackersForHabit) {
        super.onPostExecute(habitTrackersForHabit);

        if (listener != null) {
            listener.ongetTrackerForHabit(habitTrackersForHabit);
        }
    }
}
