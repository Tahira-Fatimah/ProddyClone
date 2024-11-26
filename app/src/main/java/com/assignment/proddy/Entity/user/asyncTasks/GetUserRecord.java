package com.assignment.proddy.Entity.user.asyncTasks;

import android.content.Context;
import android.os.AsyncTask;

import com.assignment.proddy.Dao.HabitDao;
import com.assignment.proddy.Dao.UserDao;
import com.assignment.proddy.DatabaseConfig.ProddyDatabaseClient;
import com.assignment.proddy.Entity.user.User;

import java.util.UUID;

public class GetUserRecord extends AsyncTask<Void, Void, User> {

    private Context context;
    private OnUserRecordRetrievedListener onUserRecordRetrievedListener;
    private String email;
    private String password;

    public GetUserRecord(Context context, OnUserRecordRetrievedListener onUserRecordRetrievedListener, String email, String password) {
        this.context = context;
        this.onUserRecordRetrievedListener = onUserRecordRetrievedListener;
        this.email = email;
        this.password = password;
    }

    @Override
    protected User doInBackground(Void... Void) {
        UserDao userDao = ProddyDatabaseClient.getInstance(context).proddyDatabase.userDao();
        return userDao.getUserByEmailAndPassword(email,password);
    }

    @Override
    protected void onPostExecute(User user) {
        super.onPostExecute(user);
        if(user == null){
            this.onUserRecordRetrievedListener.onFailure();
        } else{
            this.onUserRecordRetrievedListener.onSuccess(user);
        }
    }

    public interface OnUserRecordRetrievedListener{
        void onSuccess(User user);
        void onFailure();
    }
}
