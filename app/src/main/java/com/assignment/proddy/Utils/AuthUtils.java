package com.assignment.proddy.Utils;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.UUID;

public class AuthUtils {

    public static void storeUserInfo(Context context, String userId){
        SharedPreferences userInfo = context.getSharedPreferences("ProddyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = userInfo.edit();

        editor.putString("userId", userId);
        editor.putString("userName", "UserOne");
        editor.putString("userEmail", "user@email.com");
        editor.putBoolean("isLoggedIn", true);
        editor.apply();
    }

    public static String getLoggedInUser(Context context){
        SharedPreferences userInfo = context.getSharedPreferences("ProddyPrefs", MODE_PRIVATE);
        return userInfo.getString("userId","blank");
    }

    public static String getLoggedInUserName(Context context){
        SharedPreferences userInfo = context.getSharedPreferences("ProddyPrefs", MODE_PRIVATE);
        return userInfo.getString("userName","John Doe");
    }

    public static String getLoggedInUserEmail(Context context){
        SharedPreferences userInfo = context.getSharedPreferences("ProddyPrefs", MODE_PRIVATE);
        return userInfo.getString("userEmail","JohnDoe@gmail.com");
    }
}
