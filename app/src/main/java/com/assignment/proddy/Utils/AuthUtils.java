package com.assignment.proddy.Utils;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import com.assignment.proddy.Entity.user.User;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import java.util.UUID;
import java.util.regex.Pattern;

public class AuthUtils {

    public static void storeUserInfo(Context context, User user){
        SharedPreferences userInfo = context.getSharedPreferences("ProddyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = userInfo.edit();

        editor.putString("userId", user.getUserId().toString());
        editor.putString("userName", user.getUserName());
        editor.putString("userEmail", user.getUserEmail());
        editor.putBoolean("isLoggedIn", true);
        editor.apply();
    }

    public static void clearUserRecord(Context context){
        SharedPreferences userInfo = context.getSharedPreferences("ProddyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = userInfo.edit();
        editor.clear();
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

    public static Boolean checkEmailValidity(String email){
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    public static GoogleSignInOptions getGoogleSignInOptions(){
        return new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("661052001100-rlsrq5ponrnhkgqv80pd9ln5jh5nt6fl.apps.googleusercontent.com")
                .requestEmail()
                .build();
    }
}
