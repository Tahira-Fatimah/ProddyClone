package com.assignment.proddy.Activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.assignment.proddy.R;

public class ChangeHabitsOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_habits_order);

        RecyclerView incompleteHabitsView = findViewById(R.id.incomplete_habits_rv);
        RecyclerView completedHabitsView = findViewById(R.id.completed_habits_rv);

    }
}