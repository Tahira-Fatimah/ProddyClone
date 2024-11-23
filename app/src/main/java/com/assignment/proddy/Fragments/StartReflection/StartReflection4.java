package com.assignment.proddy.Fragments.StartReflection;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.assignment.proddy.R;
import com.assignment.proddy.SharedViewModel.ReflectionSharedViewModel;

public class StartReflection4 extends Fragment {
    ReflectionSharedViewModel reflectionSharedViewModel;
    EditText reflectionThoughts;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.create_reflection_page_4, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        reflectionSharedViewModel = new ViewModelProvider(requireActivity()).get(ReflectionSharedViewModel.class);
        initAndDefineEditText(view);
        if(reflectionSharedViewModel.getReflectionThoughts().getValue() != null){
            renderInitValues();
        }

    }

    private void initAndDefineEditText(View view){
        reflectionThoughts = view.findViewById(R.id.reflectionThoughts);
        reflectionThoughts.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String thoughts = s.toString();
                reflectionSharedViewModel.setReflectionThoughts(thoughts);
                System.out.println("Value Changed");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void renderInitValues(){
        reflectionThoughts.setText(reflectionSharedViewModel.getReflectionThoughts().getValue());
    }
}