package com.assignment.proddy.Fragments.BottomSheets;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.assignment.proddy.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class NameValidationBottomSheet extends BottomSheetDialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for the bottom sheet
        View view = inflater.inflate(R.layout.name_validation_bottom_sheet, container, false);

        // Find the button and set a click listener
        view.findViewById(R.id.name_validation_btn).setOnClickListener(v -> dismiss());

        return view;
    }


}
