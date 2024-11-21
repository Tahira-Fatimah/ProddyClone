package com.assignment.proddy.Entity.habit.asyncTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.assignment.proddy.DatabaseConfig.ProddyDatabaseClient;
import com.assignment.proddy.Dao.HabitDao;
import com.assignment.proddy.Entity.habit.Habit;
import com.assignment.proddy.ObjectMapping.HabitStack;

import java.util.List;
import java.util.UUID;

public class GetHabitStackTask extends AsyncTask<UUID, Void, HabitStack> {
    private Context context;
    public onHabitStackRetrievedListener listener;

    public GetHabitStackTask(Context context, onHabitStackRetrievedListener listener){
        this.context = context;
        this.listener=listener;
    }

    @Override
    protected HabitStack doInBackground(UUID... uuids) {
        HabitDao habitDao = ProddyDatabaseClient.getInstance(context).proddyDatabase.habitDao();
        return habitDao.getHabitStack(uuids[0]);
    }

    @Override
    protected void onPostExecute(HabitStack habitStack){
        super.onPostExecute(habitStack);
        listener.onHabitStackRetrieved(habitStack);
    }
}
