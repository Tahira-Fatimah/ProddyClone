package com.assignment.proddy.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.assignment.proddy.R;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        LinearLayout changeOrderView = findViewById(R.id.change_habit_order);
        changeOrderView.setOnClickListener(v->{
            Intent intent = new Intent(this, ChangeHabitsOrder.class);
            startActivity(intent);
        });
    }
}