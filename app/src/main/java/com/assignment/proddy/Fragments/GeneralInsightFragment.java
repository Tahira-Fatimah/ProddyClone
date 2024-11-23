package com.assignment.proddy.Fragments;

import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.assignment.proddy.Adapters.HabitCalenderAdapter;
import com.assignment.proddy.Entity.habitTracker.HabitTracker;
import com.assignment.proddy.Entity.habitTracker.asyncTasks.getTrackersWithDateBoundTask;
import com.assignment.proddy.Entity.habitTracker.asyncTasks.onGetTrackersWithDateBoundListener;
import com.assignment.proddy.Entity.user.asyncTasks.GetUserHabitCount;
import com.assignment.proddy.Entity.user.asyncTasks.onUserHabitCountRetrieved;
import com.assignment.proddy.R;
import com.assignment.proddy.Utils.AuthUtils;
import com.assignment.proddy.Utils.DateUtils;
import com.assignment.proddy.Utils.DrawableUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class GeneralInsightFragment extends Fragment implements onGetTrackersWithDateBoundListener, onUserHabitCountRetrieved{

    HabitCalenderAdapter adapter;
    Map<Date, Integer> dateCountMap;
    int userHabitCount = 1;

    LinearLayout hasHabit;
    LinearLayout noHabit;

    TextView gen_ht_1;
    TextView gen_ht_2;
    TextView gen_ht_3;
    TextView gen_ht_4;
    TextView gen_ht_5;
    TextView gen_ht_6;
    TextView gen_ht_7;

    TextView gen_mt_1;
    TextView gen_mt_2;
    TextView gen_mt_3;
    TextView gen_mt_4;
    TextView gen_mt_5;
    TextView gen_mt_6;
    TextView gen_mt_7;

    GridView habitGridView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_habits_general, container, false);
        hasHabit = view.findViewById(R.id.hasHabitGI);
        noHabit = view.findViewById(R.id.noHabitGI);

        gen_ht_1 = view.findViewById(R.id.gen_ht_1);
        gen_ht_2 = view.findViewById(R.id.gen_ht_2);
        gen_ht_3 = view.findViewById(R.id.gen_ht_3);
        gen_ht_4 = view.findViewById(R.id.gen_ht_4);
        gen_ht_5 = view.findViewById(R.id.gen_ht_5);
        gen_ht_6 = view.findViewById(R.id.gen_ht_6);
        gen_ht_7 = view.findViewById(R.id.gen_ht_7);

        gen_mt_1 = view.findViewById(R.id.gen_mt_1);
        gen_mt_2 = view.findViewById(R.id.gen_mt_2);
        gen_mt_3 = view.findViewById(R.id.gen_mt_3);
        gen_mt_4 = view.findViewById(R.id.gen_mt_4);
        gen_mt_5 = view.findViewById(R.id.gen_mt_5);
        gen_mt_6 = view.findViewById(R.id.gen_mt_6);
        gen_mt_7 = view.findViewById(R.id.gen_mt_7);

        habitGridView = view.findViewById(R.id.calenderGeneral);
        setGridViewLayout();

        return view;
    }

    private void setGridViewLayout() {
        int dpWidth = 223;
        float scale = getResources().getDisplayMetrics().density;
        int pxWidth = (int) (dpWidth * scale + 0.5f);
        ViewGroup.LayoutParams params = habitGridView.getLayoutParams();
        params.width = pxWidth;
        habitGridView.setLayoutParams(params);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new HabitCalenderAdapter(requireContext(), new ArrayList<>());
        habitGridView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        new GetUserHabitCount(getContext(),this).execute(UUID.fromString(AuthUtils.getLoggedInUser(getContext())));
        new getTrackersWithDateBoundTask(getContext(),
                this, UUID.fromString(AuthUtils.getLoggedInUser(getContext())),
                DateUtils.getDateForFetchDB(DateUtils.getCurrentMonthStart()),DateUtils.getDateForFetchDB(DateUtils.getCurrentMonthEnd())).execute();
    }

    @Override
    public void getTrackersWithDateBoundListener(List<HabitTracker> trackersWithDateBound) {
        dateCountMap = new HashMap<>();
        for (HabitTracker ht : trackersWithDateBound) {
            Date trackerDate = DateUtils.getDateOnly(DateUtils.getDateForFetchDB(ht.getHabitTrackerDate()));

            if (dateCountMap.containsKey(trackerDate)) {
                dateCountMap.put(trackerDate, dateCountMap.get(trackerDate) + 1);
            } else {
                dateCountMap.put(trackerDate, 1);
            }
        }

        for (Map.Entry<Date, Integer> entry : dateCountMap.entrySet()) {
            Log.d("DATE_COUNT", entry.getKey() + ": " + entry.getValue());
        }
        setCurrentWeekHabitStatus();
        setCurrentWeekMoodStatus();
        setCalendar();
    }

    public void setCurrentWeekHabitStatus() {
        List<Date> latestWeek = DateUtils.getLatestWeek();
        TextView[] gen_hts = {gen_ht_1, gen_ht_2, gen_ht_3, gen_ht_4, gen_ht_5, gen_ht_6, gen_ht_7};

        for (int i = 0; i < latestWeek.size(); i++) {
            TextView gen_ht = gen_hts[i];

            if (dateCountMap.get(latestWeek.get(i)) != null) {
                int value = dateCountMap.get(latestWeek.get(i));
                gen_ht.setText(String.valueOf(value));
                gen_ht.setBackgroundTintList(ColorStateList.valueOf(
                        getResources().getColor(DrawableUtils.getCompletedHabitScaleColor(value, userHabitCount))
                ));
                gen_ht.setTextColor(getResources().getColor(R.color.completed_habit_font_light_yellow));
            } else {
                gen_ht.setText("0");
            }
            gen_hts[i] = gen_ht;
        }
    }

    public void setCurrentWeekMoodStatus(){
        List<Date> latestWeek = DateUtils.getLatestWeek();
        TextView[] gen_mts = {gen_mt_1, gen_mt_2, gen_mt_3, gen_mt_4, gen_mt_5, gen_mt_6, gen_mt_7};

        for (int i = 0; i < latestWeek.size(); i++) {
            TextView gen_mt = gen_mts[i];

            if (dateCountMap.get(latestWeek.get(i)) != null) {
                int value = dateCountMap.get(latestWeek.get(i));
                gen_mt.setText(String.valueOf(value));
                gen_mt.setBackgroundTintList(ColorStateList.valueOf(
                        getResources().getColor(DrawableUtils.getMoodScaleColor(value))
                ));
                gen_mt.setTextColor(getResources().getColor(R.color.white));
            } else {
                gen_mt.setText("0");
            }
            gen_mts[i] = gen_mt;
        }
    }

    public void setCalendar(){
        adapter.empty();
        List<Date> monthDates = DateUtils.getMonth();
        for (int i = 0; i < monthDates.size(); i++) {
            if(dateCountMap.get(monthDates.get(i)) != null){
                if(dateCountMap.get(monthDates.get(i)) == userHabitCount){
                    adapter.addItem(Map.entry(String.valueOf(i),"ALL"));
                } else {
                    adapter.addItem(Map.entry(String.valueOf(i),"SOME"));
                }
                continue;
            }
            adapter.addItem(Map.entry(String.valueOf(i),"NONE"));
        }
    }

    public void setViewState(int habitCount){
        if(habitCount == 0){
            hasHabit.setVisibility(View.GONE);
            noHabit.setVisibility(View.VISIBLE);
        } else {
            hasHabit.setVisibility(View.VISIBLE);
            noHabit.setVisibility(View.GONE);
        }
    }

    @Override
    public void userHabitCountRetrieved(int count) {
        userHabitCount = count;
        setViewState(count);
    }
}