package com.assignment.proddy.Fragments;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.assignment.proddy.Adapters.LessonPagerAdapter;
import com.assignment.proddy.Entity.Lesson;
import com.assignment.proddy.R;
import com.assignment.proddy.Utils.StringUtils;
import com.assignment.proddy.ZoomOutPageTransformer;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.List;

public class LessonsFragment extends Fragment {

    private ViewPager2 lessonsViewPager;
    private DotsIndicator dotsIndicator;
    private ImageView backgroundCircle, gridIcon;
    private int[] circleColors;
    private int[] gridColors;
    private int currentColor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lessons, container, false);

        lessonsViewPager = view.findViewById(R.id.lessonsViewPager);
        dotsIndicator = view.findViewById(R.id.dotsIndicator);
        backgroundCircle = view.findViewById(R.id.background_circle);
        gridIcon = view.findViewById(R.id.gridIcon);

        defineCircleColour();
        defineGridColours();
        defineLessonViewPager();
        currentColor = circleColors[0];

        dotsIndicator.setViewPager2(lessonsViewPager);

        return view;
    }

    private void defineGridColours() {
        gridColors = new int[]{
                getResources().getColor(R.color.lesson1_grid_color),
                getResources().getColor(R.color.lesson2_grid_color),
                getResources().getColor(R.color.lesson3_grid_color),
                getResources().getColor(R.color.lesson4_grid_color),
                getResources().getColor(R.color.lesson5_grid_color),
                getResources().getColor(R.color.lesson6_grid_color),
                getResources().getColor(R.color.lesson7_grid_color)
        };
        gridIcon.setColorFilter(gridColors[0]);
    }

    private void defineCircleColour() {
        circleColors = new int[]{
                getResources().getColor(R.color.lesson1_circle_color),
                getResources().getColor(R.color.lesson2_circle_color),
                getResources().getColor(R.color.lesson3_circle_color),
                getResources().getColor(R.color.lesson4_circle_color),
                getResources().getColor(R.color.lesson5_circle_color),
                getResources().getColor(R.color.lesson6_circle_color),
                getResources().getColor(R.color.lesson7_circle_color)
        };

        backgroundCircle.setColorFilter(circleColors[0]);
    }

    private void defineLessonViewPager() {
        List<Lesson> lessonList = StringUtils.getLessons();
        LessonPagerAdapter lessonPagerAdapter = new LessonPagerAdapter(lessonList, requireContext());
        lessonsViewPager.setAdapter(lessonPagerAdapter);
        lessonsViewPager.setOffscreenPageLimit(3);
        lessonsViewPager.setPageTransformer(new ZoomOutPageTransformer());
        lessonsViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                int colorIndex = position;
                int newColor = circleColors[colorIndex];
                int gridColor = gridColors[colorIndex];

                // Animate the tint color transition
                ObjectAnimator colorAnim = ObjectAnimator.ofObject(
                        backgroundCircle,
                        "colorFilter", // Property name for colorFilter (the tint of the image)
                        new ArgbEvaluator(),
                        currentColor, // Initial color (the current tint color)
                        newColor // Set the new tint color
                );

                colorAnim.setDuration(500); // 500ms for a smooth transition
                colorAnim.start();
                gridIcon.setColorFilter(gridColor);
                currentColor = newColor;
            }
        });
    }
}
