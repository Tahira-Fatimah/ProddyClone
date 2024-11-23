package com.assignment.proddy.Entity.reflection.asyncTask;

import android.content.Context;
import android.os.AsyncTask;

import com.assignment.proddy.Dao.ReflectionDao;
import com.assignment.proddy.DatabaseConfig.ProddyDatabaseClient;
import com.assignment.proddy.Entity.reflection.Reflection;

import java.util.Calendar;
import java.util.Date;

public class GetReflectionByDateTodayTask extends AsyncTask<Date, Void, Reflection> {

    private Context context;
    private onReflectionRetrievedListener onReflectionRetrievedListener;

    public GetReflectionByDateTodayTask(Context context, onReflectionRetrievedListener onReflectionRetrievedListener){
        this.context = context;
        this.onReflectionRetrievedListener = onReflectionRetrievedListener;
    }

    @Override
    protected Reflection doInBackground(Date... dates) {
        ReflectionDao reflectionDao = ProddyDatabaseClient.getInstance(context).proddyDatabase.reflectionDao();
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(dates[0]);
//        calendar.set(Calendar.HOUR_OF_DAY, 0);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//        calendar.set(Calendar.MILLISECOND, 0);
        return reflectionDao.findReflectionByDate(dates[0]);
    }

    @Override
    protected void onPostExecute(Reflection reflection){
        if(reflection != null){
            onReflectionRetrievedListener.onSuccess(reflection);
        }else{
            onReflectionRetrievedListener.onFailure();
        }
    }

    public interface onReflectionRetrievedListener{
        void onSuccess(Reflection reflection);
        void onFailure();
    }
}
