package com.assignment.proddy.SharedViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ReflectionNavigationViewModel extends ViewModel {
    private final MutableLiveData<Boolean> navigateToNextFragment = new MutableLiveData<>();
    private final MutableLiveData<Boolean> endActivity = new MutableLiveData<>();

    public MutableLiveData<Boolean> getNavigateToNextFragment() {
        return navigateToNextFragment;
    }

    public void triggerNavigation() {
        navigateToNextFragment.setValue(true);
    }

    public void resetNavigation() {
        navigateToNextFragment.setValue(false);
    }

    public MutableLiveData<Boolean> getEndActivity() {
        return endActivity;
    }

    public void triggerEndActivity(){
        this.endActivity.setValue(Boolean.TRUE);
    }
}
