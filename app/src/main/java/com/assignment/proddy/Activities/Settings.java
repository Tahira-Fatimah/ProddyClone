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
    ImageView imageView;
    TextView userName;
    TextView userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        imageView = findViewById(R.id.return_settings);
        userName = findViewById(R.id.userName);
        userEmail = findViewById(R.id.userEmail);

        imageView.setOnClickListener(v -> finish());
        userName.setText(AuthUtils.getLoggedInUserName(this));
        userEmail.setText(AuthUtils.getLoggedInUserEmail(this));

        LinearLayout changeOrderView = findViewById(R.id.change_habit_order);
        changeOrderView.setOnClickListener(v->{
            Intent intent = new Intent(this, ChangeHabitsOrder.class);
            startActivity(intent);
        });
    }
}