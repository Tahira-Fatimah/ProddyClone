package com.assignment.proddy.DatabaseConfig;

import android.content.Context;

import androidx.room.Room;

public class ProddyDatabaseClient {
    public ProddyDatabase proddyDatabase;
    private Context context;
    public static ProddyDatabaseClient proddyDatabaseClient;

    private ProddyDatabaseClient(Context context){
        this.context = context;
        proddyDatabase = Room.databaseBuilder(context, ProddyDatabase.class, "Proddy_Database")
                .build();
    }

    public static synchronized ProddyDatabaseClient getInstance(Context context){
        if(proddyDatabaseClient == null){
            proddyDatabaseClient = new ProddyDatabaseClient(context);
        }
        return proddyDatabaseClient;
    }



}
