package com.assignment.proddy.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.assignment.proddy.Entity.habitTracker.HabitTracker;
import com.assignment.proddy.ObjectMapping.HabitInsightData;
import com.assignment.proddy.ObjectMapping.HabitWithStreakAndTime;
import com.assignment.proddy.R;
import com.assignment.proddy.Utils.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndividualInsightsAdapter extends RecyclerView.Adapter<IndividualInsightsAdapter.IndividualInsightViewHolder> {

    private Context context;
    private List<HabitInsightData> habitInsightData;
    private HabitCalenderAdapter habitCalenderAdapter;

    public IndividualInsightsAdapter(Context context, List<HabitInsightData> habitInsightData) {
        this.context = context;
        this.habitInsightData = habitInsightData;
    }

    public void updateContent(List<HabitInsightData> _habitInsightData){
        habitInsightData.clear();
        this.habitInsightData = _habitInsightData;
        notifyDataSetChanged();
    }

    @Override
    public IndividualInsightViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_individual_insight_item, parent, false);
        return new IndividualInsightViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(IndividualInsightViewHolder holder, int position) {

        setGridViewLayout(holder.calendar);

        HabitWithStreakAndTime habitWithStreakAndTime = habitInsightData.get(position).getHabitWithStreak();

        habitCalenderAdapter = new HabitCalenderAdapter(context, new ArrayList<>());
        holder.calendar.setAdapter(habitCalenderAdapter);

        holder.dayTrack.setAdapter(new HabitTrackAdapter(context, habitWithStreakAndTime.getHabitDays().toArray(new String[0])));
        holder.habitName.setText(habitWithStreakAndTime.getHabitName());
        holder.streakCount.setText(String.valueOf(habitWithStreakAndTime.getHabitStreakCrrStreak()));
        holder.maxStreakCount.setText(String.format("Your longest streak overall is %d days",
                habitWithStreakAndTime.getHabitStreakMaxStreak()));
        holder.timeSpent.setText(String.format("You've spent %d minutes on this habit",
                habitWithStreakAndTime.getTimeSpent()));

        setCalendar(holder.calendar, habitInsightData.get(position).getHabitTrackerList(), habitWithStreakAndTime.getHabitDays());
    }

    public void setCalendar(GridView calendar, List<HabitTracker> habitTrackerList, List<String> habitDays){
        habitCalenderAdapter.empty();

        Map<Date, Integer> trackMap = new HashMap<>();
        for(HabitTracker ht : habitTrackerList){
            trackMap.put(DateUtils.getDateOnly(ht.getHabitTrackerDate()),1);
        }

        List<Date> monthDates = DateUtils.getMonth();
        for(int i = 0; i<monthDates.size(); i++){
            if(DateUtils.getDateOnly(monthDates.get(i)).equals(DateUtils.getDateOnly(DateUtils.getToday()))){
                habitCalenderAdapter.addItem(Map.entry(String.valueOf(i+1),"TODAY"));
                continue;
            }
            if(trackMap.get(DateUtils.getDateOnly(monthDates.get(i))) !=null){
                habitCalenderAdapter.addItem(Map.entry(String.valueOf(i+1),"COMPLETED"));
            } else {
                if(habitDays.contains(DateUtils.getDayOfWeek(monthDates.get(i)))){
                    habitCalenderAdapter.addItem(Map.entry(String.valueOf(i+1),"NOT COMPLETED"));
                } else {
                    habitCalenderAdapter.addItem(Map.entry(String.valueOf(i+1),"NOT TRACKED"));
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return habitInsightData.size();
    }

    static class IndividualInsightViewHolder extends RecyclerView.ViewHolder {
        public TextView habitName;
        public TextView streakCount;
        public TextView monthName;
        public GridView calendar;
        public GridView dayTrack;
        public TextView timeSpent;
        public TextView maxStreakCount;

        public IndividualInsightViewHolder(View itemView) {
            super(itemView);

            habitName = itemView.findViewById(R.id.habitName);
            streakCount = itemView.findViewById(R.id.streakCount);
            monthName = itemView.findViewById(R.id.monthName);
            calendar = itemView.findViewById(R.id.calendarIndividual);
            dayTrack = itemView.findViewById(R.id.habitTrack);
            timeSpent = itemView.findViewById(R.id.totaltime_II);
            maxStreakCount = itemView.findViewById(R.id.maxStreak);

        }
    }

    private void setGridViewLayout(GridView calendarGridView) {
        int dpWidth = 223;
        float scale = this.context.getResources().getDisplayMetrics().density;
        int pxWidth = (int) (dpWidth * scale + 0.5f);
        ViewGroup.LayoutParams params = calendarGridView.getLayoutParams();
        params.width = pxWidth;
        calendarGridView.setLayoutParams(params);
    }
}
