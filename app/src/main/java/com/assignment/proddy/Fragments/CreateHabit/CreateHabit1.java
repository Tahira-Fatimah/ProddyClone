package com.assignment.proddy.Fragments.CreateHabit;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.assignment.proddy.Activities.CreateHabit;
import com.assignment.proddy.Listener.CreateHabitInteractionListener;
import com.assignment.proddy.R;
import com.assignment.proddy.SharedViewModel.HabitSharedViewModel;

public class CreateHabit1 extends Fragment {

    private HabitSharedViewModel habitSharedViewModel;
    private EditText habitNameEditText;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.create_habit_page_1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        habitSharedViewModel = new ViewModelProvider(requireActivity()).get(HabitSharedViewModel.class);
        habitNameEditText = view.findViewById(R.id.habitNameEditText);
        TextView charCount = view.findViewById(R.id.charCount);

        habitNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                charCount.setText(s.length() + "/40");
                String habitName = habitNameEditText.getText().toString();
                habitSharedViewModel.setHabitName(habitName);
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });


    }

}
