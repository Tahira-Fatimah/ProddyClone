package com.assignment.proddy.Entity.reflection.asyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.assignment.proddy.Dao.ReflectionDao;
import com.assignment.proddy.DatabaseConfig.ProddyDatabaseClient;
import com.assignment.proddy.Entity.reflection.Reflection;

public class InsertReflectionTask extends AsyncTask<Reflection, Void, Long> {
    private Context context;

    public InsertReflectionTask(Context context){
        this.context = context;
    }

    @Override
    protected Long doInBackground(Reflection... reflections) {
        Log.d("ReflectionAsync", reflections[0].toString());
        ReflectionDao reflectionDao = ProddyDatabaseClient.getInstance(context).proddyDatabase.reflectionDao();
        return reflectionDao.insert(reflections[0]);
    }

    @Override
    protected void onPostExecute(Long result){
        if(result != -1){
            Toast.makeText(context, "Reflection Added", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(context, "SEDDD", Toast.LENGTH_SHORT).show();

        }
    }
}
