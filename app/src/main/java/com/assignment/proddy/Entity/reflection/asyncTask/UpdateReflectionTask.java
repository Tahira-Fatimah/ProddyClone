package com.assignment.proddy.Entity.reflection.asyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.assignment.proddy.Dao.ReflectionDao;
import com.assignment.proddy.DatabaseConfig.ProddyDatabaseClient;
import com.assignment.proddy.Entity.reflection.Reflection;

public class UpdateReflectionTask extends AsyncTask<Reflection, Void, Void> {

    private Context context;

    public UpdateReflectionTask(Context context){
        this.context = context;
    }

    @Override
    protected Void doInBackground(Reflection... reflections) {
        Log.d("InTask", reflections[0].toString());
        ReflectionDao reflectionDao = ProddyDatabaseClient.getInstance(context).proddyDatabase.reflectionDao();
        reflectionDao.update(reflections[0]);
        return null;
    }

}
