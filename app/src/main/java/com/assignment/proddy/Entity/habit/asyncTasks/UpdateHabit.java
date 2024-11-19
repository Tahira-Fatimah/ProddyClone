package com.assignment.proddy.Entity.habit.asyncTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.util.Log;

import com.assignment.proddy.Dao.HabitDao;
import com.assignment.proddy.DatabaseConfig.ProddyDatabaseClient;
import com.assignment.proddy.Entity.habit.Habit;

public class UpdateHabit extends AsyncTask<Habit, Void, Void> {
    private Context context;

    public UpdateHabit(Context context){
        this.context = context;
    }

    @Override
    protected Void doInBackground(Habit... habits) {
        Log.d("EditHabit In Async Task", "Updated Habit: " + habits[0].toString());

        HabitDao habitDao = ProddyDatabaseClient.getInstance(context).proddyDatabase.habitDao();
        habitDao.update(habits[0]);
        return null;
    }
}
