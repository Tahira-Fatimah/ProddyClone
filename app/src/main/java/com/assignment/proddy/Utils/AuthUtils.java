package com.assignment.proddy.Utils;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

public class AuthUtils {

    public static void storeUserInfo(Context context){
        SharedPreferences userInfo = context.getSharedPreferences("ProddyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = userInfo.edit();

        editor.putString("userId", "1D3ED9A1-A65B-4423-8B54-90A07D6764E9");
        editor.putString("userName", "UserOne");
        editor.putBoolean("isLoggedIn", true);
        editor.apply();
    }

    public static String getLoggedInUser(Context context){
        SharedPreferences userInfo = context.getSharedPreferences("ProddyPrefs", MODE_PRIVATE);
        return userInfo.getString("userId","blank");
    }

}
