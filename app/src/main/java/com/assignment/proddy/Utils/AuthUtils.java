package com.assignment.proddy.Utils;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

public class AuthUtils {

    public static void storeUserInfo(Context context){
        SharedPreferences userInfo = context.getSharedPreferences("ProddyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = userInfo.edit();

        editor.putString("userId", "E01396FF-AFBD-4587-BED7-B1134B5A0A8F");
        editor.putString("userName", "UserOne");
        editor.putBoolean("isLoggedIn", true);
        editor.apply();
    }

    public static String getLoggedInUser(Context context){
        SharedPreferences userInfo = context.getSharedPreferences("ProddyPrefs", MODE_PRIVATE);
        return userInfo.getString("userId","blank");
    }

}
