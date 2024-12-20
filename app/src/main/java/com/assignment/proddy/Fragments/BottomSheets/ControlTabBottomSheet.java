package com.assignment.proddy.Fragments.BottomSheets;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.assignment.proddy.Activities.CreateHabit;
import com.assignment.proddy.Activities.StartReflection;
import com.assignment.proddy.Entity.reflection.Reflection;
import com.assignment.proddy.Entity.reflection.asyncTask.GetReflectionByDateTodayTask;
import com.assignment.proddy.R;
import com.assignment.proddy.Utils.DateUtils;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Calendar;

public class ControlTabBottomSheet extends BottomSheetDialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.control_tab_bottom_sheet, container, false);
        LinearLayout startHabitButton = view.findViewById(R.id.createHabitButton);
        LinearLayout startReflectionButton = view.findViewById(R.id.startReflectionButton);
        startHabitButton.setOnClickListener(v -> {
            Intent intent = new Intent(requireActivity(), CreateHabit.class);
            startActivity(intent);
            requireActivity().overridePendingTransition(android.R.anim.fade_in, 0);
        });

        startReflectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), StartReflection.class);
                new GetReflectionByDateTodayTask(getContext(), new GetReflectionByDateTodayTask.onReflectionRetrievedListener() {
                    @Override
                    public void onSuccess(Reflection reflection) {
                        intent.putExtra("Reflection", reflection);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure() {
                        Reflection newReflection = new Reflection();
                        newReflection.setReflectionCreationDate(DateUtils.getDateOnly(DateUtils.getToday()));
                        intent.putExtra("Reflection", newReflection);
                        startActivity(intent);
                    }
                }).execute(Calendar.getInstance().getTime());
                requireActivity().overridePendingTransition(android.R.anim.fade_in, 0);
            }
        });
        return view;
    }
}
