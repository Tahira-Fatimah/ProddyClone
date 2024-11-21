package com.assignment.proddy.Entity.reflection.asyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.assignment.proddy.Dao.ReflectionDao;
import com.assignment.proddy.DatabaseConfig.ProddyDatabaseClient;
import com.assignment.proddy.Entity.reflection.Reflection;

import java.sql.Ref;
import java.util.UUID;

public class GetReflectionByIdTask extends AsyncTask<UUID, Void, Reflection> {
    private Context context;

    public GetReflectionByIdTask(Context context){
        this.context = context;
    }

    @Override
    protected Reflection doInBackground(UUID... uuids) {
        ReflectionDao reflectionDao = ProddyDatabaseClient.getInstance(context).proddyDatabase.reflectionDao();
        return reflectionDao.findReflectionById(uuids[0]);
    }

    @Override
    protected void onPostExecute(Reflection reflection){
        if(reflection != null) {
            Log.d("Reflection", reflection.toString());
        }
        Log.d("Reflection", reflection.getReflectionCreationDate().toString());
    }
}
