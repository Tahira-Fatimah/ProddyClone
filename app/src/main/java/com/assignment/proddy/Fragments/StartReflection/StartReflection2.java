package com.assignment.proddy.Fragments.StartReflection;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import com.assignment.proddy.Entity.reflection.ReflectionFeelings;
import com.assignment.proddy.R;
import com.assignment.proddy.SharedViewModel.ReflectionSharedViewModel;

import java.util.ArrayList;
import java.util.List;

public class StartReflection2 extends Fragment {
    ReflectionSharedViewModel reflectionSharedViewModel;
    GridLayout gridLayout;
    List<TextView> feelingTextViews;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.create_reflection_page_2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        defineFeelingsTextView();
        reflectionSharedViewModel = new ViewModelProvider(requireActivity()).get(ReflectionSharedViewModel.class);

    }

    private void initViews(View view){
        gridLayout = view.findViewById(R.id.gridLayout);
        feelingTextViews = getTextViewsFromGridLayout(gridLayout);
    }

    private void defineFeelingsTextView(){
        for(TextView textView : feelingTextViews){
            defineFeelingsTextViewOnClick(textView);
        }
    }
    private List<TextView> getTextViewsFromGridLayout(GridLayout gridLayout) {
        List<TextView> textViewList = new ArrayList<>();
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            View child = gridLayout.getChildAt(i);
            if (child instanceof TextView) {
                textViewList.add((TextView) child);
            }
        }
        return textViewList;
    }

    private void defineFeelingsTextViewOnClick(TextView textView){
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = textView.getText().toString();
                ReflectionFeelings feeling = ReflectionFeelings.fromString(text);

                List<ReflectionFeelings> reflectionFeelings = reflectionSharedViewModel.getReflectionFeelingsList().getValue();

                if(reflectionFeelings == null){
                    reflectionFeelings = new ArrayList<>();
                }
                else{
                    if(reflectionFeelings.contains(feeling)){
                        reflectionFeelings.remove(feeling);
                        textView.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.reflection_feeling_bg_dark));
                        textView.setTextColor(getResources().getColor(R.color.white));
                    } else{
                        reflectionFeelings.add(feeling);
                        textView.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.reflection_feeling_bg_light));
                        textView.setTextColor(getResources().getColor(R.color.reflection_feeling_bg_light_font));
                    }
                }

                reflectionSharedViewModel.setReflectionFeelingsList(reflectionFeelings);
                Log.d("ReflectionFeeling ", reflectionSharedViewModel.toString());
            }
        });
    }
}