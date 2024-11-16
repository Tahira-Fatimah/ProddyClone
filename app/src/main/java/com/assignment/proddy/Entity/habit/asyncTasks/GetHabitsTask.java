package com.assignment.proddy.Entity.habit.asyncTasks;


import android.content.Context;
import android.os.AsyncTask;

import com.assignment.proddy.Dao.HabitDao;
import com.assignment.proddy.DatabaseConfig.ProddyDatabaseClient;
import com.assignment.proddy.Entity.habit.Habit;

import java.util.List;

public class GetHabitsTask extends AsyncTask<Void, Void, List<Habit>> {

    private Context context;
    onHabitsRetrievedListener listener;

    public GetHabitsTask(Context context, onHabitsRetrievedListener listener){
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected List<Habit> doInBackground(Void... voids) {
        HabitDao habitDao = ProddyDatabaseClient.getInstance(context).proddyDatabase.habitDao();
        return habitDao.getHabits();
    }

    @Override
    protected void onPostExecute(List<Habit> habits) {
        super.onPostExecute(habits);

        if (listener != null) {
            listener.onHabitsRetrieved(habits);
        }
    }
}
