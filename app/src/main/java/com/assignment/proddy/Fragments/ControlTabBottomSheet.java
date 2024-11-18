package com.assignment.proddy.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.assignment.proddy.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ControlTabBottomSheet extends BottomSheetDialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for the bottom sheet
        return inflater.inflate(R.layout.control_tab_bottom_sheet, container, false);
    }
}
