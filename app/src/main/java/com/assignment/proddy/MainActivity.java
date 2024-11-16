package com.assignment.proddy;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

//import com.assignment.proddy.Fragments.AllHabitsFragment;
//import com.assignment.proddy.Fragments.insights;

import com.assignment.proddy.Activities.CreateHabit;
import com.assignment.proddy.Entity.user.InsertUser;
import com.assignment.proddy.Entity.user.User;
import com.assignment.proddy.Fragments.CreateHabit.CreateHabit2;
import com.assignment.proddy.Fragments.CreateHabit.CreateHabit5;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new InsertUser(getApplicationContext()).execute(new User("1234", "Fatimah", "123"));


//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.fragment_container, new CreateHabit5());
//        transaction.commit();
//
        Intent intent = new Intent(this, CreateHabit.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("Back in parenttttttttt");
    }

}


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

