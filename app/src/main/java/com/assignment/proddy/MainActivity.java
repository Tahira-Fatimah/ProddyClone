package com.assignment.proddy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

//import com.assignment.proddy.Fragments.AllHabitsFragment;
//import com.assignment.proddy.Fragments.insights;

//import com.assignment.proddy.Activities.Lessons;
import com.assignment.proddy.Activities.EditHabit;
import com.assignment.proddy.Dao.HabitDao;
import com.assignment.proddy.DatabaseConfig.ProddyDatabaseClient;
import com.assignment.proddy.Entity.habit.Habit;
import com.assignment.proddy.Entity.habit.HabitType;
import com.assignment.proddy.Entity.habit.asyncTasks.InsertHabit;
import com.assignment.proddy.Entity.user.InsertUser;
import com.assignment.proddy.Entity.user.User;
import com.assignment.proddy.Fragments.AllHabitsFragment;

import com.assignment.proddy.Fragments.BottomSheets.ControlTabBottomSheet;
import com.assignment.proddy.Fragments.LessonsFragment;
import com.assignment.proddy.Fragments.ReflectionFragment;
import com.assignment.proddy.Fragments.insights;
import com.assignment.proddy.Utils.StringUtils;
import com.google.android.material.tabs.TabLayout;

import java.sql.Time;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        storeUserInfo();

        TabLayout tabLayout = findViewById(R.id.controltabLayout);
        inflateTabs(tabLayout);
        setTabLayoutOnClickListener(tabLayout);
//        TabLayout tabLayout = findViewById(R.id.controltabLayout);
//        inflateTabs(tabLayout);
//        setTabLayoutOnClickListener(tabLayout);
        new InsertUser(getApplicationContext()).execute(new User("1234", "Fatimah", "123"));
        Habit habit = new Habit(UUID.randomUUID(), "good habit", "hahaha", HabitType.FINANCES, 1, new Time(1,45,0), StringUtils.getAllDays());
        new InsertHabit(getApplicationContext()).execute(habit);
        Log.d("HabitMain", "Habit " + habit.toString());
        Intent intent = new Intent(this, EditHabit.class);
        intent.putExtra("Habit",habit);
        startActivity(intent);

    }

    void storeUserInfo(){
        SharedPreferences userInfo = getSharedPreferences("ProddyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = userInfo.edit();

        editor.putInt("userId", 1);
        editor.putString("userName", "UserOne");
        editor.putBoolean("isLoggedIn", true);
        editor.apply();
    }

    void inflateTabs(TabLayout tabLayout){
        View plusButtonView = LayoutInflater.from(this).inflate(R.layout.control_tab_plus, null);
        Button button = plusButtonView.findViewById(R.id.control_tab_plus_button);
        button.setOnClickListener(v -> {
            ControlTabBottomSheet bottomDrawerFragment = new ControlTabBottomSheet();
            bottomDrawerFragment.show(getSupportFragmentManager(), bottomDrawerFragment.getTag());
        });
        button.setText("\uE945");

        tabLayout.addTab(tabLayout.newTab().setText("\uE9CC"));
        tabLayout.addTab(tabLayout.newTab().setText("\uE9DD"));
        tabLayout.addTab(tabLayout.newTab().setCustomView(plusButtonView));
        tabLayout.addTab(tabLayout.newTab().setText("\uE980"));
        tabLayout.addTab(tabLayout.newTab().setText("\uE981"));

        TabLayout.Tab tabplus = tabLayout.getTabAt(2);
        if (tabplus != null) {
            View tabView = tabplus.view;
            tabView.setClickable(false);
            tabView.setEnabled(false);
        }
    }

    void setTabLayoutOnClickListener(TabLayout tabLayout){
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Fragment fragment;

                switch (position) {
                    case 0:
                        fragment = new AllHabitsFragment();
                        break;
                    case 1:
                        fragment = new insights();
                        break;
                    case 3:
                        fragment = new ReflectionFragment();
                        break;
                    case 4:
                        fragment = new LessonsFragment();
                        break;
                    default:
                        fragment = new AllHabitsFragment();
                }

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container_main, fragment)
                        .commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
}
