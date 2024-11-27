package com.assignment.proddy.Entity.user.asyncTasks;

import android.content.Context;
import android.os.AsyncTask;

import com.assignment.proddy.Dao.UserDao;
import com.assignment.proddy.DatabaseConfig.ProddyDatabaseClient;
import com.assignment.proddy.Entity.user.User;

import java.util.UUID;

public class DeleteUserByIdTask extends AsyncTask<UUID,Void, Void> {

    private Context context;
    public DeleteUserByIdTask(Context context){
        this.context = context;
    }

    @Override
    protected Void doInBackground(UUID... uuids) {
        UserDao userDao = ProddyDatabaseClient.getInstance(context).proddyDatabase.userDao();
        return userDao.deleteUserById(uuids[0]);
    }
}
