package com.assignment.proddy.Entity.user.asyncTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.assignment.proddy.DatabaseConfig.ProddyDatabaseClient;
import com.assignment.proddy.Dao.UserDao;
import com.assignment.proddy.Entity.user.User;

public class InsertUser extends AsyncTask<User,Void, Long> {

    private Context context;
    private OnUserInsertListener onUserInsertListener;

    public InsertUser(Context context, OnUserInsertListener onUserInsertListener) {
        this.context = context;
        this.onUserInsertListener = onUserInsertListener;
    }

    @Override
    protected Long doInBackground(User... users) {
        UserDao userDao = ProddyDatabaseClient.getInstance(context).proddyDatabase.userDao();
        return userDao.insert(users[0]);
    }

    @Override
    protected void onPostExecute(Long result){
        if(result != -1){
            this.onUserInsertListener.onSuccess();
        }
    }

    public interface OnUserInsertListener{
        void onSuccess();
    }

}
