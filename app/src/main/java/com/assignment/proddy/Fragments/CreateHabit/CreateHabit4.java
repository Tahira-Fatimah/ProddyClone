package com.assignment.proddy.Fragments.CreateHabit;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.assignment.proddy.R;
import com.assignment.proddy.SharedViewModel.HabitSharedViewModel;

public class CreateHabit4 extends Fragment {

    private HabitSharedViewModel habitSharedViewModel;
    private EditText reasonEditText;
    private TextView characterCountTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.create_habit_page_4, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        habitSharedViewModel = new ViewModelProvider(requireActivity()).get(HabitSharedViewModel.class);
        reasonEditText = view.findViewById(R.id.editTextReason);
        characterCountTextView = view.findViewById(R.id.characterCountTextView);

        characterCountTextView.setText("0/300");
        setReasonEditText();
    }

    private void setReasonEditText(){
        reasonEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int after) {
                int currentLength = charSequence.length();

                characterCountTextView.setText(currentLength + "/300");

                habitSharedViewModel.setHabitMotivationMessage(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }
}
