package com.assignment.proddy.Entity.habit.asyncTasks;

import android.content.Context;
import android.os.AsyncTask;

import com.assignment.proddy.Dao.HabitDao;
import com.assignment.proddy.DatabaseConfig.ProddyDatabaseClient;
import com.assignment.proddy.Entity.habit.Habit;
import com.assignment.proddy.ObjectMapping.HabitWithTrackers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class GetIncompleteHabitsTask extends AsyncTask<Void, Void, List<HabitWithTrackers>> {

    private Context context;
    onIncompleteHabitsRetrievedListener listener;
    private UUID userId;
    private Date today;

    public GetIncompleteHabitsTask(Context context, onIncompleteHabitsRetrievedListener listener, UUID userId, Date today){
        this.context = context;
        this.listener = listener;
        this.userId = userId;
        this.today = today;
    }

    @Override
    protected List<HabitWithTrackers> doInBackground(Void... voids) {
        HabitDao habitDao = ProddyDatabaseClient.getInstance(context).proddyDatabase.habitDao();
        return new ArrayList<>();
    }

    @Override
    protected void onPostExecute(List<HabitWithTrackers> habitsWithTrackers) {
        super.onPostExecute(habitsWithTrackers);
        if (listener != null) {
            listener.onIncompleteHabitsRetrieved(habitsWithTrackers);
        }
    }

}
