package com.assignment.proddy.Entity.habit;

import android.content.Context;
import android.os.AsyncTask;

import com.assignment.proddy.DatabaseConfig.ProddyDatabaseClient;
import com.assignment.proddy.Dao.HabitDao;

public class InsertHabit extends AsyncTask<Habit, Void, Long> {

    private Context context;

    public InsertHabit(Context context){
        this.context = context;
    }

    @Override
    protected Long doInBackground(Habit... habits) {
        HabitDao habitDao = ProddyDatabaseClient.getInstance(context).proddyDatabase.habitDao();
        return habitDao.insert(habits[0]);
    }
}
