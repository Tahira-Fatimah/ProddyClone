package com.assignment.proddy.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.assignment.proddy.Fragments.GeneralInsightFragment;
import com.assignment.proddy.Fragments.IndividualInsightFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: return new GeneralInsightFragment();
            case 1: return new IndividualInsightFragment();
            default: return new GeneralInsightFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
