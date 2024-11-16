package com.assignment.proddy.Listener;

public interface CreateHabitInteractionListener {
    void onNextFragmentRequested();
    void onPreviousFragmentRequested();
    void onActionCompleted(String dataKey, Object dataValue);
}
