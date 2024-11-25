package com.assignment.proddy.Entity.reflection.asyncTask;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.assignment.proddy.Dao.ReflectionDao;
import com.assignment.proddy.DatabaseConfig.ProddyDatabaseClient;
import com.assignment.proddy.ObjectMapping.ReflectionDateAndRate;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class GetReflectionFeelingAndRateForDates extends AsyncTask<Void, Void, List<ReflectionDateAndRate>> {

    private Context context;
    private onGetReflectionFeelingRateForDatesListener onGetReflectionFeelingRateForDatesListener;
    private UUID userId;
    private Date startDate;
    private Date endDate;

    public GetReflectionFeelingAndRateForDates(Context context, UUID userId, Date startDate, Date endDate,
                                               onGetReflectionFeelingRateForDatesListener onGetReflectionFeelingRateAndDateForLastWeekListener){
        this.context = context;
        this.onGetReflectionFeelingRateForDatesListener = onGetReflectionFeelingRateAndDateForLastWeekListener;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
    }

    @Override
    protected List<ReflectionDateAndRate> doInBackground(Void... voids) {
        ReflectionDao reflectionDao = ProddyDatabaseClient.getInstance(context).proddyDatabase.reflectionDao();
        return reflectionDao.getReflectionFeelingRatesForDateBound(userId,startDate,endDate);
    }

    @Override
    protected void onPostExecute(List<ReflectionDateAndRate> res){
        if(res != null){
            onGetReflectionFeelingRateForDatesListener.onSuccess(res);
        } else{
            onGetReflectionFeelingRateForDatesListener.onFailure();
        }
    }

    public interface onGetReflectionFeelingRateForDatesListener {
        void onSuccess(List<ReflectionDateAndRate> res);
        void onFailure();
    }
}
