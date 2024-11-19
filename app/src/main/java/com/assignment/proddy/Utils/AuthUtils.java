package com.assignment.proddy.Utils;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

public class AuthUtils {

    public static int getLoggedInUser(Context context){
        SharedPreferences userInfo = context.getSharedPreferences("ProddyPrefs", MODE_PRIVATE);
        return userInfo.getInt("userID",1);
    }

}
