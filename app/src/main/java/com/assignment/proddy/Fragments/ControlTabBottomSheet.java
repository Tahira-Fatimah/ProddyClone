package com.assignment.proddy.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.assignment.proddy.Activities.CreateHabit;
import com.assignment.proddy.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ControlTabBottomSheet extends BottomSheetDialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.control_tab_bottom_sheet, container, false);
        LinearLayout startHabitButton = view.findViewById(R.id.createHabitButton);
        startHabitButton.setOnClickListener(v -> {
            Intent intent = new Intent(requireActivity(), CreateHabit.class);
            startActivity(intent);
            requireActivity().overridePendingTransition(android.R.anim.fade_in, 0);
        });
        return view;
    }
}
