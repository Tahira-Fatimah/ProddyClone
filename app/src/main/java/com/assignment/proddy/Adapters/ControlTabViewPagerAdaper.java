package com.assignment.proddy.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.assignment.proddy.Fragments.AllHabitsFragment;
import com.assignment.proddy.Fragments.ReflectionFragment;
import com.assignment.proddy.Fragments.Insights;

public class ControlTabViewPagerAdaper extends FragmentStateAdapter {

    public ControlTabViewPagerAdaper(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: return new AllHabitsFragment();
            case 1: return new Insights();
            case 2: return new AllHabitsFragment();
            case 3: return new ReflectionFragment();
            case 4: return new AllHabitsFragment();
            default: return new AllHabitsFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
