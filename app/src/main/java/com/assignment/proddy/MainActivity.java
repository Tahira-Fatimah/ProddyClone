package com.assignment.proddy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        storeUserInfo();

        TabLayout tabLayout = findViewById(R.id.controltabLayout);
        inflateTabs(tabLayout);
        setTabLayoutOnClickListener(tabLayout);

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

//        TabLayout.Tab tabplus = tabLayout.getTabAt(2); // Tab at position 1
//        if (tabplus != null) {
//            View tabView = tabplus.view;
//            tabView.setClickable(false);
//            tabView.setEnabled(false);
//        }

//        ControlTabViewPagerAdaper adapter = new ControlTabViewPagerAdaper(this);
//        viewPager.setAdapter(adapter);

//        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
//            if (position == 2) { // Third tab
//                View customView = LayoutInflater.from(this).inflate(R.layout.control_tab_plus, null);
//                Button button = customView.findViewById(R.id.control_tab_plus_button);
//                button.setText("\uE945"); // Customize as needed
//                button.setOnClickListener(v -> {
//                    ControlTabBottomSheet bottomDrawerFragment = new ControlTabBottomSheet();
//                    bottomDrawerFragment.show(getSupportFragmentManager(), bottomDrawerFragment.getTag());
//                });
//                tab.setCustomView(customView);
//            } else {
//                switch (position) {
//                    case 0:
//                        tab.setText("\uE9CC");
//                        break;
//                    case 1:
//                        tab.setText("\uE9DD");
//                        break;
//                    case 3:
//                        tab.setText("\uE980");
//                        break;
//                    case 4:
//                        tab.setText("\uE981");
//                        break;
//                }
//            }
//        }).attach();
//    }
//}

//        new InsertUser(getApplicationContext()).execute(new User("1234", "Fatimah", "123"));


//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.fragment_container, new CreateHabit5());
//        transaction.commit();
//
//        Intent intent = new Intent(this, CreateHabit.class);
//        startActivity(intent);
//    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        System.out.println("Back in parenttttttttt");
//    }

//}

//FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.fragment_container, new CreateHabit2());
//        transaction.commit();


//        ViewPager2 viewPager = findViewById(R.id.viewPager);
//        List<Lesson> lessons = getLessons();
//        LessonPagerAdapter adapter = new LessonPagerAdapter(lessons);
//        viewPager.setAdapter(adapter);
//
//        // Apply zoom-out page transformer for pop-out effect
//        viewPager.setOffscreenPageLimit(3);
//        viewPager.setPageTransformer(new ZoomOutPageTransformer());
//    }
//

//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.fragment_container, new ReflectionFragment());
//        transaction.commit();


//        new InsertUser(getApplicationContext()).execute(new User("1234", "Fatimah", "123"));
//        new InsertHabit(getApplicationContext()).execute(new Habit("habit 2", "aise hi", "M,T,W", HabitType.FINANCES, 1));
//        new InsertHabitStep(getApplicationContext()).execute(
//                new HabitStep(1,1,"step 1", 30,"haha"),
//                new HabitStep(1,2,"step 2", 30,"haha")
//        );

//        new RetrieveHabitStack(getApplicationContext()).execute();
//    }

//
//    private List<Lesson> getLessons() {
//        // Generate or fetch a list of lessons
//        return Arrays.asList(
//                new Lesson(R.drawable.mindfulness, "Tiny Habits", "Why easy is better than difficult"),
//                new Lesson(R.drawable.learning, "Mindset", "Building a growth mindset"),
//                new Lesson(R.drawable.health, "Mindset", "Building a growth mindset")
//
//                // Add more lessons as needed
//        );
//    }

