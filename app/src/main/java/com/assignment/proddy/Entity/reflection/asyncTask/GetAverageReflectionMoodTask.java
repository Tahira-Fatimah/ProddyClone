package com.assignment.proddy.Entity.reflection.asyncTask;

import android.content.Context;
import android.os.AsyncTask;

import com.assignment.proddy.Dao.ReflectionDao;
import com.assignment.proddy.DatabaseConfig.ProddyDatabaseClient;

public class GetAverageReflectionMoodTask extends AsyncTask<Void, Void, Float> {

    private Context context;
    private onGetAverageReflectionMoodListener onGetAverageReflectionMoodListener;

    public GetAverageReflectionMoodTask(Context context, onGetAverageReflectionMoodListener onGetAverageReflectionMoodListener){
        this.context = context;
        this.onGetAverageReflectionMoodListener = onGetAverageReflectionMoodListener;
    }

    @Override
    protected Float doInBackground(Void... voids) {
        ReflectionDao reflectionDao = ProddyDatabaseClient.getInstance(context).proddyDatabase.reflectionDao();
        return reflectionDao.getAverageReflectionMoodForAMonth();
    }

    @Override
    protected void onPostExecute(Float res){
        if(res != null){
            onGetAverageReflectionMoodListener.onSuccess(res);
        }else{
            onGetAverageReflectionMoodListener.onFailure();
        }
    }

    public interface onGetAverageReflectionMoodListener{
        void onSuccess(Float avg);
        void onFailure();
    }
}
