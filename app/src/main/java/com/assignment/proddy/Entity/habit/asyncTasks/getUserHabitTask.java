package com.assignment.proddy.Entity.habit.asyncTasks;


import android.content.Context;
import android.os.AsyncTask;

import com.assignment.proddy.Dao.HabitDao;
import com.assignment.proddy.DatabaseConfig.ProddyDatabaseClient;
import com.assignment.proddy.Entity.habit.Habit;

import java.util.List;
import java.util.UUID;

public class getUserHabitTask extends AsyncTask<Void, Void, List<Habit> >{

    private Context context;
    onUserHabitsRetrieved listener;
    UUID userID;

    public getUserHabitTask(Context context, onUserHabitsRetrieved listener,  UUID userID){
        this.context = context;
        this.listener = listener;
        this.userID = userID;
    }

    @Override
    protected List<Habit> doInBackground(Void... voids) {
        HabitDao habitDao = ProddyDatabaseClient.getInstance(context).proddyDatabase.habitDao();
        return habitDao.getHabitsForUser(userID);
    }

    @Override
    protected void onPostExecute(List<Habit> habits) {
        super.onPostExecute(habits);

        if (listener != null) {
            listener.onuserHabitsRetrieved(habits);
        }
    }
}
