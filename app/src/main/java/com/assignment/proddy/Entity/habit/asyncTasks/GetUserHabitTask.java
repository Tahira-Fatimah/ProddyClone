package com.assignment.proddy.Entity.habit.asyncTasks;


import android.content.Context;
import android.os.AsyncTask;

import com.assignment.proddy.Dao.HabitDao;
import com.assignment.proddy.DatabaseConfig.ProddyDatabaseClient;
import com.assignment.proddy.Entity.habit.Habit;

import java.util.List;
import java.util.UUID;

public class GetUserHabitTask extends AsyncTask<Void, Void, List<Habit> >{

    private Context context;
    private onUserHabitsRetrieved listener;
    UUID userID;

    public GetUserHabitTask(Context context, UUID userID, onUserHabitsRetrieved listener){
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
            listener.onSuccess(habits);
        }
    }

    public interface onUserHabitsRetrieved {
       void onSuccess(List<Habit> userHabits);
    }

}
