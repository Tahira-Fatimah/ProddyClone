package com.assignment.proddy.Utils;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

public class AuthUtils {

    public static void storeUserInfo(Context context){
        SharedPreferences userInfo = context.getSharedPreferences("ProddyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = userInfo.edit();

        editor.putString("userId", "93BF1B3B-F5CE-44F6-A22A-B74EB554426B");
        editor.putString("userName", "UserOne");
        editor.putBoolean("isLoggedIn", true);
        editor.apply();
    }

    public static String getLoggedInUser(Context context){
        SharedPreferences userInfo = context.getSharedPreferences("ProddyPrefs", MODE_PRIVATE);
        return userInfo.getString("userId","blank");
    }

}
