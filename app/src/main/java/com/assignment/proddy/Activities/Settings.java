package com.assignment.proddy.Activities;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.assignment.proddy.Entity.user.asyncTasks.DeleteUserByIdTask;
import com.assignment.proddy.R;
import com.assignment.proddy.Utils.AuthUtils;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import java.util.UUID;

public class Settings extends AppCompatActivity {
    ImageView backBtn;
    TextView userName;
    TextView userEmail;
    LinearLayout logoutBtn;
    LinearLayout deleteAccBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        backBtn = findViewById(R.id.settings_backbtn);
        userName = findViewById(R.id.userName);
        userEmail = findViewById(R.id.userEmail);
        logoutBtn = findViewById(R.id.logout_button);
        deleteAccBtn = findViewById(R.id.delete_acc_button);

        backBtn.setOnClickListener(v -> finish());
        userName.setText(AuthUtils.getLoggedInUserName(this));
        userEmail.setText(AuthUtils.getLoggedInUserEmail(this));

        setLogoutButtonListener();
        setDeleteAccountButtonListener();
    }

    private void setDeleteAccountButtonListener() {
        deleteAccBtn.setOnClickListener(v ->
            new DeleteUserByIdTask(this, new DeleteUserByIdTask.OnnDeleteUserListener() {
                @Override
                public void onSuccess() {
                    clearAuthState();
                    finish();
                }

            }).execute(UUID.fromString(AuthUtils.getLoggedInUser(this)))
        );
    }

    private void setLogoutButtonListener() {
        logoutBtn.setOnClickListener(v -> {
            clearAuthState();
            finish();
        });
    }

    private void clearAuthState(){
        AuthUtils.clearUserRecord(this);
        GoogleSignInOptions gso = AuthUtils.getGoogleSignInOptions();
        GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
            googleSignInClient.signOut();
        }
    }
}