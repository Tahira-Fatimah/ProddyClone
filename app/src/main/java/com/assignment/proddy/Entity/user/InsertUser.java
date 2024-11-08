package com.assignment.proddy.Entity.user;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.assignment.proddy.DatabaseConfig.ProddyDatabaseClient;
import com.assignment.proddy.Dao.UserDao;

public class InsertUser extends AsyncTask<User,Void, Long> {

    private Context context;
    public InsertUser(Context context){
        this.context = context;
    }

    @Override
    protected Long doInBackground(User... users) {
        UserDao userDao = ProddyDatabaseClient.getInstance(context).proddyDatabase.userDao();
        return userDao.insert(users[0]);
    }

    @Override
    protected void onPostExecute(Long result){
        if(result != -1){
            Toast.makeText(context,"User Inserted Successfully", Toast.LENGTH_LONG).show();
        }
    }

}
