package com.assignment.proddy.SharedViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NavigationViewModel extends ViewModel {

    private final MutableLiveData<Boolean> navigateToNextFragment = new MutableLiveData<>();

    public MutableLiveData<Boolean> getNavigateToNextFragment() {
        return navigateToNextFragment;
    }

    public void triggerNavigation() {
        navigateToNextFragment.setValue(true);
    }

    public void resetNavigation() {
        navigateToNextFragment.setValue(false);
    }
}
