package com.assignment.proddy.Entity.habit.asyncTasks;


import android.content.Context;
import android.os.AsyncTask;

import com.assignment.proddy.Dao.HabitDao;
import com.assignment.proddy.DatabaseConfig.ProddyDatabaseClient;
import com.assignment.proddy.Entity.habit.Habit;
import com.assignment.proddy.ObjectMapping.HabitWithTrackers;

import java.util.Date;
import java.util.List;

public class GetHabitsTask extends AsyncTask<Void, Void, List<HabitWithTrackers> >{

    private Context context;
    onHabitsRetrievedListener listener;
    Date today;
    int userID;

    public GetHabitsTask(Context context, onHabitsRetrievedListener listener, Date today, int userID){
        this.context = context;
        this.listener = listener;
        this.today = today;
        this.userID = userID;
    }

    @Override
    protected List<HabitWithTrackers> doInBackground(Void... voids) {
        HabitDao habitDao = ProddyDatabaseClient.getInstance(context).proddyDatabase.habitDao();
        return habitDao.getHabits(userID,today);
    }

    @Override
    protected void onPostExecute(List<HabitWithTrackers> habitsWithTrackers) {
        super.onPostExecute(habitsWithTrackers);

        if (listener != null) {
            listener.onHabitsRetrieved(habitsWithTrackers);
        }
    }
}
