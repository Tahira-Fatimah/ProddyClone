package com.assignment.proddy.Entity.reflection.asyncTask;

import android.content.Context;
import android.os.AsyncTask;

import com.assignment.proddy.Dao.ReflectionDao;
import com.assignment.proddy.DatabaseConfig.ProddyDatabaseClient;
import com.assignment.proddy.ObjectMapping.ReflectionDateAndRate;

import java.util.List;

public class GetReflectionFeelingRateForLastMonthTask extends AsyncTask<Void, Void, List<ReflectionDateAndRate>> {

    private Context context;
    private onGetReflectionFeelingRateForLastMonthListener onGetReflectionFeelingRateForLastMonthListener;

    public GetReflectionFeelingRateForLastMonthTask(Context context, onGetReflectionFeelingRateForLastMonthListener onGetReflectionFeelingRateForLastMonthListener){
        this.context = context;
        this.onGetReflectionFeelingRateForLastMonthListener = onGetReflectionFeelingRateForLastMonthListener;
    }

    @Override
    protected List<ReflectionDateAndRate> doInBackground(Void... voids) {
        ReflectionDao reflectionDao = ProddyDatabaseClient.getInstance(context).proddyDatabase.reflectionDao();
        return reflectionDao.getReflectionFeelingRatesForLastMonth();
    }

    @Override
    protected void onPostExecute(List<ReflectionDateAndRate> res){
        if(res != null){
            onGetReflectionFeelingRateForLastMonthListener.onSuccess(res);
        } else{
            onGetReflectionFeelingRateForLastMonthListener.onFailure();
        }
    }

    public interface onGetReflectionFeelingRateForLastMonthListener{
        void onSuccess(List<ReflectionDateAndRate> res);
        void onFailure();
    }
}
