package com.assignment.proddy.Entity.habit.asyncTasks;

import android.content.Context;
import android.os.AsyncTask;

import com.assignment.proddy.DatabaseConfig.ProddyDatabaseClient;
import com.assignment.proddy.Dao.HabitDao;
import com.assignment.proddy.ObjectMapping.HabitStack;

import java.util.List;

public class RetrieveHabitStack extends AsyncTask<Void, Void, List<HabitStack>> {
    private Context context;

    public RetrieveHabitStack(Context context){
        this.context = context;
    }

    @Override
    protected List<HabitStack> doInBackground(Void... voids) {
        HabitDao habitDao = ProddyDatabaseClient.getInstance(context).proddyDatabase.habitDao();
        return habitDao.getHabitStack();
    }

    @Override
    protected void onPostExecute(List<HabitStack> habitStacks){
        super.onPostExecute(habitStacks);
        for(HabitStack habitStack: habitStacks){
            habitStack.displayHabitStack();
        }
    }
}
