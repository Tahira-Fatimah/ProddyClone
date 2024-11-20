package com.assignment.proddy.Fragments.BottomSheets;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.assignment.proddy.Dao.HabitDao;
import com.assignment.proddy.DatabaseConfig.ProddyDatabaseClient;
import com.assignment.proddy.Entity.habit.Habit;
import com.assignment.proddy.Entity.habit.asyncTasks.DeleteHabit;
import com.assignment.proddy.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class DeleteHabitBottomSheet extends BottomSheetDialogFragment {

    private Habit habit;
    TextView deleteButtonFromAvtivity;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.delete_habit_bottom_sheet, container, false);
        TextView deleteHabitButton = view.findViewById(R.id.deleteHabitBtn);
        deleteHabitButton.setOnClickListener(v -> {
            deleteHabit();
            requireActivity().overridePendingTransition(android.R.anim.fade_in, 0);
            dismiss();
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        deleteButtonFromAvtivity.setClickable(true);
        deleteButtonFromAvtivity.setEnabled(true);
        super.onDestroyView();

    }

    public void setHabit(Habit habit){
        this.habit = habit;
    }

    public void setDeleteButtonFromAvtivity(TextView deleteButtonFromAvtivity) {
        this.deleteButtonFromAvtivity = deleteButtonFromAvtivity;
    }

    private void deleteHabit(){
        new DeleteHabit(getContext()).execute(habit);
    }
}
