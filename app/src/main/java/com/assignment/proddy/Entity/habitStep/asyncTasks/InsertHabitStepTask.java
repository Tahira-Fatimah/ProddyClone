package com.assignment.proddy.Entity.habitStep.asyncTasks;

import android.content.Context;
import android.os.AsyncTask;

import com.assignment.proddy.DatabaseConfig.ProddyDatabaseClient;
import com.assignment.proddy.Dao.HabitStepDao;
import com.assignment.proddy.Entity.habitStep.HabitStep;

public class InsertHabitStepTask extends AsyncTask<HabitStep, Void, Long> {

    private Context context;

    public InsertHabitStepTask(Context context){
        this.context = context;
    }

    @Override
    protected Long doInBackground(HabitStep... habitSteps) {
        HabitStepDao habitStepDao = ProddyDatabaseClient.getInstance(context).proddyDatabase.habitStepDao();
        return habitStepDao.insert(habitSteps[0]);
    }

}
