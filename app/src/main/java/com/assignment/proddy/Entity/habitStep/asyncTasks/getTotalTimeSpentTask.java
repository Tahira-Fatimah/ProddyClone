package com.assignment.proddy.Entity.habitStep.asyncTasks;

import android.content.Context;
import android.os.AsyncTask;

import com.assignment.proddy.DatabaseConfig.ProddyDatabaseClient;
import com.assignment.proddy.Dao.HabitStepDao;
import com.assignment.proddy.Entity.habitStep.HabitStep;

import java.util.UUID;

public class getTotalTimeSpentTask extends AsyncTask<UUID, Void, Integer> {

    private Context context;
    private OnGetTotalTimeSpent onGetTotalTimeSpent;

    public getTotalTimeSpentTask(Context context, OnGetTotalTimeSpent onGetTotalTimeSpent){
        this.context = context;
        this.onGetTotalTimeSpent = onGetTotalTimeSpent;
    }

    @Override
    protected Integer doInBackground(UUID... userId) {
        HabitStepDao habitStepDao = ProddyDatabaseClient.getInstance(context).proddyDatabase.habitStepDao();
        return habitStepDao.getTotalTimeSpent(userId[0]);
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        onGetTotalTimeSpent.onSuccess(integer);
    }

    public interface OnGetTotalTimeSpent{
        void onSuccess(int time);
    }
}
