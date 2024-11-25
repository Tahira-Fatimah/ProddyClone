package com.assignment.proddy.Entity.habitStreak.asyncTasks;

import android.content.Context;
import android.os.AsyncTask;

import com.assignment.proddy.Dao.HabitStreakDao;
import com.assignment.proddy.DatabaseConfig.ProddyDatabaseClient;
import com.assignment.proddy.Entity.habitStreak.HabitStreak;

import java.util.UUID;

public class InsertHabitStreakTask extends AsyncTask <UUID,Void,Integer>{

    Context context;

    public InsertHabitStreakTask(Context context) {
        this.context = context;
    }

    @Override
    protected Integer doInBackground(UUID... uuids) {
        HabitStreakDao habitStreakDao = ProddyDatabaseClient.getInstance(context).proddyDatabase.habitStreakDao();
        habitStreakDao.insert(new HabitStreak(UUID.randomUUID(),uuids[0],0,0,null));
        return 0;
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
    }
}
