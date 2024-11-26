package com.assignment.proddy.Entity.user.asyncTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.assignment.proddy.Dao.UserDao;
import com.assignment.proddy.DatabaseConfig.ProddyDatabaseClient;
import com.assignment.proddy.Entity.user.User;

import java.util.UUID;

public class DeleteUserByIdTask extends AsyncTask<UUID,Void, Void> {

    private Context context;
    private OnnDeleteUserListener onnDeleteUserListener;

    public DeleteUserByIdTask(Context context, OnnDeleteUserListener onnDeleteUserListener) {
        this.context = context;
        this.onnDeleteUserListener = onnDeleteUserListener;
    }

    @Override
    protected Void doInBackground(UUID... uuids) {
        UserDao userDao = ProddyDatabaseClient.getInstance(context).proddyDatabase.userDao();
        Log.e("DELETE USER", "USER DELETED");
        return userDao.deleteUserById(uuids[0]);
    }

    public interface OnnDeleteUserListener{
        void onSuccess();
    }
}
