package com.assignment.proddy.Entity.reflection.asyncTask;


import android.content.Context;
import android.os.AsyncTask;

import com.assignment.proddy.Dao.ReflectionDao;
import com.assignment.proddy.DatabaseConfig.ProddyDatabaseClient;
import com.assignment.proddy.ObjectMapping.ReflectionDateAndRate;

import java.util.List;

public class GetReflectionFeelingRateAndDateForLastWeek extends AsyncTask<Void, Void, List<ReflectionDateAndRate>> {

    private Context context;
    private onGetReflectionFeelingRateAndDateForLastWeekListener onGetReflectionFeelingRateAndDateForLastWeekListener;

    public GetReflectionFeelingRateAndDateForLastWeek(Context context, onGetReflectionFeelingRateAndDateForLastWeekListener onGetReflectionFeelingRateAndDateForLastWeekListener){
        this.context = context;
        this.onGetReflectionFeelingRateAndDateForLastWeekListener = onGetReflectionFeelingRateAndDateForLastWeekListener;
    }

    @Override
    protected List<ReflectionDateAndRate> doInBackground(Void... voids) {
        ReflectionDao reflectionDao = ProddyDatabaseClient.getInstance(context).proddyDatabase.reflectionDao();
        return reflectionDao.getReflectionFeelingRatesForLastMonth();
    }

    @Override
    protected void onPostExecute(List<ReflectionDateAndRate> res){
        if(res != null){
            onGetReflectionFeelingRateAndDateForLastWeekListener.onSuccess(res);
        } else{
            onGetReflectionFeelingRateAndDateForLastWeekListener.onFailure();
        }
    }

    public interface onGetReflectionFeelingRateAndDateForLastWeekListener {
        void onSuccess(List<ReflectionDateAndRate> res);
        void onFailure();
    }
}
