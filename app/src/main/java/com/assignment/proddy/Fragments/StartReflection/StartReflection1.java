package com.assignment.proddy.Fragments.StartReflection;

import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.assignment.proddy.Entity.reflection.ReflectionFeelingRate;
import com.assignment.proddy.R;
import com.assignment.proddy.SharedViewModel.HabitSharedViewModel;
import com.assignment.proddy.SharedViewModel.ReflectionNavigationViewModel;
import com.assignment.proddy.SharedViewModel.ReflectionSharedViewModel;
import com.assignment.proddy.Utils.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class StartReflection1 extends Fragment {
    ReflectionSharedViewModel reflectionSharedViewModel;
    ReflectionNavigationViewModel reflectionNavigationViewModel;
    List<LinearLayout> feelingRateLayouts;
    TextView reflectionQuote, reflectionQuoteAuthor, currentDate;
    ImageView closeBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.create_reflection_page_1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        initViewModels();
        defineCloseBtn();
        defineFeelingRateLayouts(view);
        currentDate.setText(StringUtils.getRequiredFormattedDate(reflectionSharedViewModel.getReflectionCreationDate().getValue()));
        
    }

    private void initViewModels(){
        reflectionSharedViewModel = new ViewModelProvider(requireActivity()).get(ReflectionSharedViewModel.class);
        reflectionNavigationViewModel = new ViewModelProvider(requireActivity()).get(ReflectionNavigationViewModel.class);
    }

    private void initViews(View view){
        reflectionQuote = view.findViewById(R.id.reflectionQuote);
        reflectionQuoteAuthor = view.findViewById(R.id.reflectionQuoteAuthor);
        closeBtn = view.findViewById(R.id.closeBtn);
        currentDate = view.findViewById(R.id.currentDate);

    }

    private void defineFeelingRateLayouts(View view){
        feelingRateLayouts = List.of(
                view.findViewById(R.id.terribleLayout),
                view.findViewById(R.id.mehLayout),
                view.findViewById(R.id.normalLayout),
                view.findViewById(R.id.goodLayout),
                view.findViewById(R.id.awesomeLayout)

        );

        for(int i = 0; i <feelingRateLayouts.size(); i++){
            defineFeelingLayoutOnClick(feelingRateLayouts.get(i));
        }
    }

    private void defineFeelingLayoutOnClick(LinearLayout layout){
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence contentDescription = layout.getContentDescription();
                if (contentDescription != null) {
                    String feelingName = contentDescription.toString();
                    ReflectionFeelingRate reflectionFeelingRate = ReflectionFeelingRate.valueOf(feelingName);
                    reflectionSharedViewModel.setReflectionFeelingRate(reflectionFeelingRate.getRate());
                    reflectionNavigationViewModel.triggerNavigation();
                }

                Log.d("ReflectionModel" ,reflectionSharedViewModel.toString());
            }
        });
    }

    private void defineCloseBtn(){
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reflectionNavigationViewModel.triggerEndActivity();
            }
        });
    }




}