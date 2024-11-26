package com.assignment.proddy.Activities;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.assignment.proddy.R;
import com.assignment.proddy.Utils.AuthUtils;

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

    }

    private void setLogoutButtonListener() {
        logoutBtn.setOnClickListener(v -> {
            AuthUtils.clearUserRecord(this);
            finish();
        });
    }
}