package com.assignment.proddy.Entity.habitStep;

import android.content.Context;
import android.os.AsyncTask;

import com.assignment.proddy.DatabaseConfig.ProddyDatabaseClient;
import com.assignment.proddy.Dao.HabitStepDao;

import java.util.Arrays;

public class InsertHabitStep extends AsyncTask<HabitStep, Void, Long[]> {

    private Context context;

    public InsertHabitStep(Context context){
        this.context = context;
    }

    @Override
    protected Long[] doInBackground(HabitStep... habitSteps) {
        HabitStepDao habitStepDao = ProddyDatabaseClient.getInstance(context).proddyDatabase.habitStepDao();
        return habitStepDao.insertAll(Arrays.asList(habitSteps));
    }

}
