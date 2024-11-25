package com.assignment.proddy.Entity.habitStreak.asyncTasks;

import android.content.Context;
import android.os.AsyncTask;

import com.assignment.proddy.Dao.HabitStreakDao;
import com.assignment.proddy.DatabaseConfig.ProddyDatabaseClient;
import com.assignment.proddy.Entity.habitStreak.HabitStreak;
import com.assignment.proddy.Utils.DateUtils;
import java.util.UUID;

public class SetHabitStreakTask extends AsyncTask<UUID,Void,Void> {

    private Context context;
    private UUID habitId;

    public SetHabitStreakTask(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(UUID... habitId) {
        HabitStreakDao habitStreakDao = ProddyDatabaseClient.getInstance(context).proddyDatabase.habitStreakDao();
        HabitStreak existingStreak = habitStreakDao.getHabitStreak(habitId[0]);
        if(existingStreak.getHabitStreakLastCompletedDate() != null &&
                DateUtils.getDateOnly(existingStreak.getHabitStreakLastCompletedDate())
                        .equals(DateUtils.getDateOnly(DateUtils.getYesterdayForDB())))
        {
            existingStreak.setHabitStreakCrrStreak(existingStreak.getHabitStreakCrrStreak() + 1);
        } else {
            existingStreak.setHabitStreakCrrStreak(1);
        }
        if(existingStreak.getHabitStreakMaxStreak() < existingStreak.getHabitStreakCrrStreak()){
            existingStreak.setHabitStreakMaxStreak(existingStreak.getHabitStreakCrrStreak());
        }
        existingStreak.setHabitStreakLastCompletedDate(DateUtils.getTodayForInsertDB());
        habitStreakDao.update(existingStreak);
        return null;
    }
}
