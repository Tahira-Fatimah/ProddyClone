package com.assignment.proddy.Entity.user.asyncTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.assignment.proddy.DatabaseConfig.ProddyDatabaseClient;
import com.assignment.proddy.Dao.UserDao;
import com.assignment.proddy.Entity.user.User;

import java.util.UUID;

public class GetUserHabitCount extends AsyncTask<UUID,Void, Integer> {

    private Context context;
    private onUserHabitCountRetrieved listener;
    public GetUserHabitCount(Context context, onUserHabitCountRetrieved listener){
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected Integer doInBackground(UUID... userId) {
        UserDao userDao = ProddyDatabaseClient.getInstance(context).proddyDatabase.userDao();
        return userDao.getUserHabitCount(userId[0]);
    }

    @Override
    protected void onPostExecute(Integer result){
        if(listener!=null){
            listener.userHabitCountRetrieved(result);
        }
    }
}
