package com.assignment.proddy.Entity.habit.asyncTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.assignment.proddy.DatabaseConfig.ProddyDatabaseClient;
import com.assignment.proddy.Dao.HabitDao;
import com.assignment.proddy.Entity.habit.Habit;

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

    @Override
    protected void onPostExecute(Long aLong) {
        super.onPostExecute(aLong);
        if (aLong!=-1){
            Toast.makeText(context,"Inserted habit with id "+aLong, Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(context,"Failed to Insert habit ", Toast.LENGTH_LONG).show();
        }
    }
}
