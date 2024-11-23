package com.assignment.proddy.Entity.habitStep.asyncTasks;

import android.content.Context;
import android.os.AsyncTask;

import com.assignment.proddy.Dao.HabitStepDao;
import com.assignment.proddy.DatabaseConfig.ProddyDatabaseClient;
import com.assignment.proddy.Entity.habitStep.HabitStep;

public class UpdateHabitStepTask extends AsyncTask<HabitStep, Void, Void> {

    private Context context;

    public UpdateHabitStepTask(Context context){
        this.context = context;
    }

    @Override
    protected Void doInBackground(HabitStep... habitSteps) {
        HabitStepDao habitStepDao = ProddyDatabaseClient.getInstance(context).proddyDatabase.habitStepDao();
        return habitStepDao.update(habitSteps[0]);
    }

}
