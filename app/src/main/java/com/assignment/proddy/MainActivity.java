package com.assignment.proddy;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

//import com.assignment.proddy.Fragments.AllHabitsFragment;
//import com.assignment.proddy.Fragments.insights;

import com.assignment.proddy.Adapters.LessonPagerAdapter;
import com.assignment.proddy.Entity.Lesson;
import com.assignment.proddy.Fragments.AllHabitsFragment;

import java.util.Arrays;
import java.util.List;
import com.assignment.proddy.Fragments.ReflectionFragment;
import com.assignment.proddy.Fragments.insights;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ViewPager2 viewPager = findViewById(R.id.viewPager);
//        List<Lesson> lessons = getLessons();
//        LessonPagerAdapter adapter = new LessonPagerAdapter(lessons);
//        viewPager.setAdapter(adapter);
//
//        // Apply zoom-out page transformer for pop-out effect
//        viewPager.setPageTransformer(new ZoomOutPageTransformer());
//    }


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, new AllHabitsFragment());
        transaction.commit();


//        new InsertUser(getApplicationContext()).execute(new User("1234", "Fatimah", "123"));
//        new InsertHabit(getApplicationContext()).execute(new Habit("habit 2", "aise hi", "M,T,W", HabitType.FINANCES, 1));
//        new InsertHabitStep(getApplicationContext()).execute(
//                new HabitStep(1,1,"step 1", 30,"haha"),
//                new HabitStep(1,2,"step 2", 30,"haha")
//        );

//        new RetrieveHabitStack(getApplicationContext()).execute();
    }


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

}