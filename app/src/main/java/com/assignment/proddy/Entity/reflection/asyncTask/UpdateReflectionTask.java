package com.assignment.proddy.Entity.reflection.asyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.assignment.proddy.Dao.ReflectionDao;
import com.assignment.proddy.DatabaseConfig.ProddyDatabaseClient;
import com.assignment.proddy.Entity.reflection.Reflection;

public class UpdateReflectionTask extends AsyncTask<Reflection, Void, Integer> {

    private Context context;
    private onUpdateReflectionListener onUpdateReflectionListener;

    public UpdateReflectionTask(Context context, onUpdateReflectionListener onUpdateReflectionListener){
        this.context = context;
        this.onUpdateReflectionListener = onUpdateReflectionListener;
    }

    @Override
    protected Integer doInBackground(Reflection... reflections) {
        Log.d("InTask", reflections[0].toString());
        ReflectionDao reflectionDao = ProddyDatabaseClient.getInstance(context).proddyDatabase.reflectionDao();
        return reflectionDao.update(reflections[0]);
    }

    @Override
    protected void onPostExecute(Integer res){
        if(res < 1){
            onUpdateReflectionListener.onFailure();
        }
        else{
            onUpdateReflectionListener.onSuccess();
        }
    }

    public interface onUpdateReflectionListener{
        void onSuccess();
        void onFailure();
    }

}
