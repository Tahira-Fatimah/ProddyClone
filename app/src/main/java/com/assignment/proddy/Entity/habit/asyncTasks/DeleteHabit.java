package com.assignment.proddy.Entity.habit.asyncTasks;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Delete;

import com.assignment.proddy.Dao.HabitDao;
import com.assignment.proddy.DatabaseConfig.ProddyDatabaseClient;
import com.assignment.proddy.Entity.habit.Habit;

public class DeleteHabit extends AsyncTask<Habit, Void, Void> {

    private Context context;
    public DeleteHabit(Context context){
        this.context = context;
    }

    @Override
    protected Void doInBackground(Habit... habits) {
        HabitDao habitDao = ProddyDatabaseClient.getInstance(context).proddyDatabase.habitDao();
        habitDao.delete(habits[0]);
        return null;
    }
}
