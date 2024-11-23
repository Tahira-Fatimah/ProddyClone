package com.assignment.proddy.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.assignment.proddy.Entity.habitStep.HabitStep;

import java.util.List;

@Dao
public interface HabitStepDao {
    @Insert
    public Long insert(HabitStep habitStep);

    @Delete
    public void delete(HabitStep habitStep);

    @Update
    public Void update(HabitStep habitStep);

    @Insert
    public Long[] insertAll(List<HabitStep> habitStepList);
}
