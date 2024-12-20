package com.assignment.proddy.Fragments;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.text.SpannableStringBuilder;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.assignment.proddy.Activities.StartReflection;
import com.assignment.proddy.Entity.reflection.Reflection;
import com.assignment.proddy.Entity.reflection.ReflectionActivities;
import com.assignment.proddy.Entity.reflection.ReflectionActivityEmojiEnum;
import com.assignment.proddy.Entity.reflection.ReflectionFeelings;
import com.assignment.proddy.Entity.reflection.ReflectionFeelingsColourEnum;
import com.assignment.proddy.Entity.reflection.asyncTask.GetAverageReflectionMoodTask;
import com.assignment.proddy.Entity.reflection.asyncTask.GetReflectionByDateTodayTask;
import com.assignment.proddy.Entity.reflection.asyncTask.GetReflectionFeelingAndRateForDates;
import com.assignment.proddy.Entity.reflection.asyncTask.GetReflectionFeelingRateAndDateForLastWeek;
import com.assignment.proddy.Entity.reflection.asyncTask.GetReflectionFeelingRateForLastMonthTask;
import com.assignment.proddy.ObjectMapping.ReflectionDateAndRate;
import com.assignment.proddy.R;
import com.assignment.proddy.Utils.AuthUtils;
import com.assignment.proddy.Utils.DateUtils;
import com.assignment.proddy.Utils.DrawableUtils;
import com.assignment.proddy.Utils.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;


public class ReflectionFragment extends Fragment {

    private ActivityResultLauncher<Intent> activityResultLauncher;
    SimpleDateFormat monthFormat = new SimpleDateFormat("MMM", Locale.getDefault());
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd", Locale.getDefault());
    SimpleDateFormat dayFormat = new SimpleDateFormat("EEE");
    LinearLayout reflectionHorizontalScrollViewContainer, tapToReflectButton;
    TextView moodDateView, moodTextView, moodNumView, thoughtsView, avgMoodTextView, avgMoodNumView;
    GridLayout feelingsGridView, activitiesGridView;
    LinearLayout allBarsView;
    ImageView moodEmojiView, avgMoodEmojiView;
    LinearLayout lastDateContainerClicked;
    TextView lastMonthClicked;
    TextView lastDateClicked;
    HorizontalScrollView dateContainerHorizontalScrollView;
    Reflection currentReflection;
    int avgMood;
    private boolean isComingBackFromAnotherActivity = false;
    String NOTHING_HERE_YET = "Nothing here yet. ";

    public ReflectionFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerActivityResultLauncher();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_reflection, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        setInitialView();
        setReflectionRateWeekBarsAsyncTaskCall();
        inflateDateHorizontalScrollView();
        defineTapToReflectBtn();

    }

    @Override
    public void onResume() {
        super.onResume();
        if (isComingBackFromAnotherActivity) {
            if(currentReflection != null){
                reflectionHorizontalScrollViewContainer.removeAllViews();
                allBarsView.removeAllViews();
                setReflectionRateWeekBarsAsyncTaskCall();
                setAverageReflectionMood();
                inflateDateHorizontalScrollView();
                clearViews();
                tapToReflectButton.setVisibility(View.VISIBLE);
                setCustomReflection(currentReflection);
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        isComingBackFromAnotherActivity = true;
    }

    private void registerActivityResultLauncher(){
        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        currentReflection = (Reflection) result.getData().getSerializableExtra("Reflection");

                    }
                }
        );
    }

    private void initViews(View view){
        reflectionHorizontalScrollViewContainer = view.findViewById(R.id.reflectionHorizontalScrollViewContainer);
        moodDateView = view.findViewById(R.id.moodDate);
        moodTextView = view.findViewById(R.id.moodText);
        moodEmojiView = view.findViewById(R.id.moodEmoji);
        moodNumView = view.findViewById(R.id.moodNum);
        thoughtsView = view.findViewById(R.id.thoughts);
        feelingsGridView = view.findViewById(R.id.feelingsGrid);
        activitiesGridView = view.findViewById(R.id.activitiesGrid);
        allBarsView = view.findViewById(R.id.allBars);
        avgMoodTextView = view.findViewById(R.id.avgMoodText);
        avgMoodEmojiView = view.findViewById(R.id.avgMoodEmoji);
        avgMoodNumView = view.findViewById(R.id.avgMoodNum);
        tapToReflectButton = view.findViewById(R.id.tapToReflectButton);
        dateContainerHorizontalScrollView = view.findViewById(R.id.dateContainerHorizontalScrollView);
    }

    private void setInitialView(){
        new GetReflectionByDateTodayTask(getContext(), new GetReflectionByDateTodayTask.onReflectionRetrievedListener() {
            @Override
            public void onSuccess(Reflection reflection) {
                currentReflection = reflection;
                setTapToReflectBtnVisibility();
                defineDefaultReflection(DateUtils.getDateOnlyForToday());
                setCustomReflection(reflection);
            }

            @Override
            public void onFailure() {
                currentReflection = null;
                setTapToReflectBtnVisibility();
                defineDefaultReflection(DateUtils.getDateOnlyForToday());
            }
        }).execute(DateUtils.getDateOnlyForToday());
    }

    private void setTapToReflectBtnVisibility(){
        if(currentReflection == null){
            tapToReflectButton.setVisibility(View.GONE);
        } else{
            tapToReflectButton.setVisibility(View.VISIBLE);
        }
    }

    private void defineDefaultReflection(Date date){
        setReflectionMoodTopBar(null, date);
        setAverageReflectionMood();
    }

    private void inflateDateHorizontalScrollView() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -30);
        new GetReflectionFeelingAndRateForDates(getContext(), UUID.fromString(AuthUtils.getLoggedInUser(getContext())),
                calendar.getTime(),DateUtils.getToday(),
                new GetReflectionFeelingAndRateForDates.onGetReflectionFeelingRateForDatesListener() {
            @Override
            public void onSuccess(List<ReflectionDateAndRate> res) {
                inflateHorizontalView(res);
            }

            @Override
            public void onFailure() {
                List<ReflectionDateAndRate> res = new ArrayList<>();
                inflateHorizontalView(res);
            }
        }).execute();



    }

    private void inflateHorizontalView(List<ReflectionDateAndRate> res){
        LayoutInflater inflater = LayoutInflater.from(requireContext());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -30);
        Date todaysDate = DateUtils.getDateOnlyForToday();
        for (int i = 0; i <= 30; i++) {
            LinearLayout childView = (LinearLayout) inflater.inflate(
                    R.layout.reflection_horizontal_scroll_view_date_item,
                    reflectionHorizontalScrollViewContainer,
                    false
            );
            ImageView reflectionFeelingEmoji = childView.findViewById(R.id.reflectionFeelingEmoji);

            Date currentDate = calendar.getTime();
            currentDate = DateUtils.getDateOnly(currentDate);

            String month = monthFormat.format(currentDate);
            String day = dateFormat.format(currentDate);

            LinearLayout dateContainerLinearLayout = childView.findViewById(R.id.dateContainer);

            TextView monthTextView = childView.findViewById(R.id.reflectionMonth);
            monthTextView.setText(month);

            TextView dayTextView = childView.findViewById(R.id.reflectionDate);
            dayTextView.setText(day);

            childView.setContentDescription(currentDate.toString());
            dateContainerLinearLayout.setTag(currentDate);

            if(currentDate.equals(todaysDate)){
                lastDateContainerClicked = dateContainerLinearLayout;
                lastDateClicked = dayTextView;
                lastMonthClicked = monthTextView;
                dateContainerLinearLayout.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.reflection_date_font_purple));
                monthTextView.setTextColor(getResources().getColor(R.color.white));
                dayTextView.setTextColor(getResources().getColor(R.color.white));
            }

            int feelingRate = findFeelingRateByDate(res, currentDate);

            if(feelingRate != -1){
                reflectionFeelingEmoji.setImageResource(DrawableUtils.getReflectionEmojiDrawable(feelingRate));
            } else{
                reflectionFeelingEmoji.setImageResource(R.drawable.add_circle_purple);
                Date finalCurrentDate = currentDate;
                reflectionFeelingEmoji.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        createReflectionForSpecifiedDate(finalCurrentDate);
                    }
                });
            }
            setDateContainerOnClick(dateContainerLinearLayout, monthTextView, dayTextView);
            reflectionHorizontalScrollViewContainer.addView(childView);

            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        if(currentReflection!=null) {
            adjustSelectedDateColourOnResume(currentReflection.getReflectionCreationDate());
        }
        if(isComingBackFromAnotherActivity && currentReflection!= null){
            scrollHorizontalViewDateContainerToSpecificPosition();
        }else{
            scrollHorizontalViewDateContainerToEnd();
        }
        isComingBackFromAnotherActivity = false;

    }

    private void createReflectionForSpecifiedDate(Date finalCurrentDate) {
        Intent intent = new Intent(getContext(), StartReflection.class);
        Reflection reflectionToCreateProbably = new Reflection();
        reflectionToCreateProbably.setReflectionCreationDate(finalCurrentDate);
        intent.putExtra("Reflection", reflectionToCreateProbably);
        activityResultLauncher.launch(intent);
    }

    private void scrollHorizontalViewDateContainerToSpecificPosition() {
        int index = DateUtils.findDateIndex(currentReflection.getReflectionCreationDate());
        dateContainerHorizontalScrollView.postDelayed(new Runnable() {
            @Override
            public void run() {
                LinearLayout childContainer = (LinearLayout) dateContainerHorizontalScrollView.getChildAt(0);
                if (childContainer != null) {
                    int childWidth = childContainer.getChildAt(0).getWidth();
                    int scrollPosition = childWidth * (index - 1);
                    dateContainerHorizontalScrollView.smoothScrollTo(scrollPosition, 0);
                }
            }
        }, 100L);
    }

    private void scrollHorizontalViewDateContainerToEnd() {
        dateContainerHorizontalScrollView.postDelayed(new Runnable() {
            public void run() {
                dateContainerHorizontalScrollView.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
            }
        }, 100L);
    }

    public int findFeelingRateByDate(List<ReflectionDateAndRate> res, Date targetDate) {
        for (ReflectionDateAndRate reflection : res) {
            if (DateUtils.getDateOnly(targetDate).equals(DateUtils.getDateOnly(reflection.getReflectionCreationDate()))) {
                return reflection.getReflectionFeelingRate();
            }
        }
        return -1;
    }

    private void defineReflectionDateOnClick(Date selectedDate) {
        new GetReflectionByDateTodayTask(getContext(), new GetReflectionByDateTodayTask.onReflectionRetrievedListener() {
            @Override
            public void onSuccess(Reflection reflection) {
                currentReflection = reflection;
                clearViews();
                setTapToReflectBtnVisibility();
                setCustomReflection(reflection);
            }

            @Override
            public void onFailure() {
                currentReflection = null;
                clearViews();
                setTapToReflectBtnVisibility();
                defineDefaultReflection(selectedDate);
            }
        }).execute(selectedDate);
    }

    private void setAverageReflectionMood(){
        new GetAverageReflectionMoodTask(getContext(), new GetAverageReflectionMoodTask.onGetAverageReflectionMoodListener() {
            @Override
            public void onSuccess(Float avg) {
                setAverageReflectionMoodViews(avg);
            }

            @Override
            public void onFailure() {

            }
        }).execute();
    }

    private void setAverageReflectionMoodViews(Float avg) {
        float truncated =  (float) ((int) (avg * 10)) / 10;
        avgMood = Math.round(avg);
        avgMoodNumView.setText(String.valueOf(truncated));
        avgMoodEmojiView.setImageResource(DrawableUtils.getReflectionEmojiDrawable(avgMood));
        avgMoodTextView.setText(StringUtils.getAverageMoodTextFromRate(avgMood));
    }

    private void setReflectionRateWeekBarsAsyncTaskCall(){
        new GetReflectionFeelingRateAndDateForLastWeek(getContext(), new GetReflectionFeelingRateAndDateForLastWeek.onGetReflectionFeelingRateAndDateForLastWeekListener() {
            @Override
            public void onSuccess(List<ReflectionDateAndRate> res) {
                setReflectionRateWeekBars(res);
            }

            @Override
            public void onFailure() {
                Log.d("ReflectionDateAndRate", "");
            }
        }).execute();
    }

    private void setReflectionRateWeekBars(List<ReflectionDateAndRate> res){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -6);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                0 ,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        layoutParams.weight = 1;
        for (int i = 0; i < 7; i++) {
            Date currentDate = calendar.getTime();
            String day = dayFormat.format(currentDate);

            int feelingRate = findFeelingRateByDate(res, currentDate);
            LinearLayout daylinearLayout = new LinearLayout(requireContext());
            daylinearLayout.setOrientation(LinearLayout.VERTICAL);
            daylinearLayout.setGravity(Gravity.BOTTOM);
            daylinearLayout.setLayoutParams(layoutParams);

            // Create the Spacer View
            View spacerView = new View(requireContext());
            LinearLayout.LayoutParams spacerParams = new LinearLayout.LayoutParams(
                    0, 0 // 0dp width and height
            );
            spacerParams.weight = 1;
            spacerView.setLayoutParams(spacerParams);

            TextView moodNum = new TextView(requireContext());
            View bar = new View(requireContext());

            // Create TextView for mood number
            moodNum.setTypeface(null, Typeface.BOLD);
            moodNum.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16); // 16sp
            LinearLayout.LayoutParams moodNumParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            moodNumParams.gravity = Gravity.CENTER_HORIZONTAL;
            moodNum.setLayoutParams(moodNumParams);

            // Create the Bar View
            bar.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.rounded_background_1));
            LinearLayout.LayoutParams barParams;


            if(feelingRate != -1){
                barParams = new LinearLayout.LayoutParams(
                        (int) (12 * getResources().getDisplayMetrics().density), // 12dp in pixels
                        (int) (feelingRate * 16 * getResources().getDisplayMetrics().density)  // 80dp in pixels // calc height using mood num from db -------
                );
                moodNum.setText(String.valueOf(feelingRate));
                moodNum.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));
                bar.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.light_purple));
            } else{
                barParams = new LinearLayout.LayoutParams(
                        (int) (12 * getResources().getDisplayMetrics().density), // 12dp in pixels
                        (int) (80 * getResources().getDisplayMetrics().density)  // 80dp in pixels // calc height using mood num from db -------
                );
                moodNum.setTextColor(ContextCompat.getColor(requireContext(), R.color.your_week_bg_colour));
                bar.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.your_week_bg_colour));
            }

            barParams.gravity = Gravity.CENTER_HORIZONTAL;
            barParams.topMargin = (int) (4 * getResources().getDisplayMetrics().density); // 4dp top margin
            barParams.bottomMargin = (int) (20 * getResources().getDisplayMetrics().density); // 20dp bottom margin
            bar.setLayoutParams(barParams);


            TextView dayText = new TextView(requireContext());
            dayText.setText(day);
            dayText.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));
            LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            textParams.gravity = Gravity.CENTER_HORIZONTAL;
            dayText.setLayoutParams(textParams);

            daylinearLayout.addView(spacerView);
            daylinearLayout.addView(moodNum);
            daylinearLayout.addView(bar);
            daylinearLayout.addView(dayText);

            allBarsView.addView(daylinearLayout);
            calendar.add(Calendar.DAY_OF_MONTH, 1);

        }

    }

    private void setCustomReflection(Reflection reflection){
        String reflectionThoughts = reflection.getReflectionThoughts();
        List<ReflectionActivities> reflectionActivities = reflection.getReflectionActivitiesList();
        List<ReflectionFeelings> reflectionFeelings = reflection.getReflectionFeelingsList();

        setReflectionThoughts(reflectionThoughts);
        setReflectionMoodTopBar(reflection, null);
        setReflectionFeelingsGridView(reflectionFeelings);
        setActivitiesGridView(reflectionActivities);

    }

    private void setReflectionThoughts(String reflectionThoughts){
        thoughtsView.setText(reflectionThoughts);
    }

    private void setReflectionMoodTopBar(Reflection reflection, Date date){
        if(reflection == null){
            SpannableStringBuilder spannableBuilder = StringUtils.getSpannableString(date, "Your mood on ");
            moodDateView.setText(spannableBuilder);
            moodTextView.setText(NOTHING_HERE_YET);
            moodEmojiView.setImageResource(DrawableUtils.getReflectionEmojiDrawable(-1));
        } else {
            SpannableStringBuilder spannableBuilder = StringUtils.getSpannableString(reflection.getReflectionCreationDate(), "Your mood on ");
            moodDateView.setText(spannableBuilder);
            moodEmojiView.setImageResource(DrawableUtils.getReflectionEmojiDrawable(reflection.getReflectionFeelingRate()));
            moodTextView.setText(StringUtils.getMoodFromRate(reflection.getReflectionFeelingRate()));
            moodNumView.setText(String.valueOf(reflection.getReflectionFeelingRate()));
        }
    }

    private void setActivitiesGridView(List<ReflectionActivities> reflectionActivities){
        if(reflectionActivities == null){
            return;
        }
        for(int i = 0; i<reflectionActivities.size(); i++){
            ReflectionActivities reflectionActivity = reflectionActivities.get(i);
            String value = ReflectionActivityEmojiEnum.fromEnumName(reflectionActivity.getDisplayName()).getDisplayName();
            LinearLayout linearLayout = new LinearLayout(requireContext());
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            linearLayoutParams.setMargins(0, 5, 0, 5);


            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
            layoutParams.width = 0;
            layoutParams.height = GridLayout.LayoutParams.WRAP_CONTENT;
            layoutParams.columnSpec = GridLayout.spec(i % 3, 1f);
            layoutParams.rowSpec = GridLayout.spec(i / 3);

            TextView emojiTextView = new TextView(requireContext());
            emojiTextView.setText(value);
            emojiTextView.setTextSize(30);
            emojiTextView.setGravity(Gravity.CENTER_HORIZONTAL);
            emojiTextView.setPadding(0, 8, 0, 8);

            TextView activityTextView = new TextView(requireContext());
            activityTextView.setText(reflectionActivity.getDisplayName());
            activityTextView.setTextSize(15); // 15sp
            activityTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.reflection_date_font_purple));
            activityTextView.setGravity(Gravity.CENTER_HORIZONTAL);

            linearLayout.addView(emojiTextView);
            linearLayout.addView(activityTextView);
            linearLayout.setLayoutParams(linearLayoutParams);

            activitiesGridView.addView(linearLayout, layoutParams);
        }

    }

    private void setReflectionFeelingsGridView(List<ReflectionFeelings> reflectionFeelings){
        if(reflectionFeelings==null){
            return;
        }
        for(ReflectionFeelings reflectionFeeling: reflectionFeelings){
            int value = ReflectionFeelingsColourEnum.valueOf(reflectionFeeling.name()).getValue();

            TextView newTextView = new TextView(requireContext());
            newTextView.setText(reflectionFeeling.getDisplayName());
            if(value == 0){
                newTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.feeling_tag_negative_font));
                newTextView.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.feeling_tag_negative_bg));
            }
            else{
                newTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.feeling_tag_positive_font));
                newTextView.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.feeling_tag_positive_bg));
            }

            float density = getResources().getDisplayMetrics().density;
            int paddingHorizontal = (int) (8 * density);
            int paddingVertical = (int) (3 * density);
            newTextView.setPadding(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical);

            ViewGroup.MarginLayoutParams layoutParams = new ViewGroup.MarginLayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMargins((int) (4 * density), (int) (4 * density), (int) (4 * density), (int) (4 * density)); // 4dp margins
            newTextView.setLayoutParams(layoutParams);
            feelingsGridView.addView(newTextView);
        }

    }

    private void setDateContainerOnClick(LinearLayout layout, TextView month, TextView date){
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adjustDateContainerSelected(layout, month, date);
            }
        });
    }

    private void adjustDateContainerSelected(LinearLayout layout, TextView month, TextView date){
        Date selectedDate = (Date) layout.getTag();
        if(lastDateContainerClicked != null){
            lastDateContainerClicked.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.reflection_date_light_grey));
            lastDateClicked.setTextColor(getResources().getColor(R.color.reflection_date_font_purple));
            lastMonthClicked.setTextColor(getResources().getColor(R.color.reflection_date_font_purple));
        }
        layout.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.reflection_date_font_purple));
        month.setTextColor(getResources().getColor(R.color.white));
        date.setTextColor(getResources().getColor(R.color.white));

        lastDateContainerClicked = layout;
        lastMonthClicked = month;
        lastDateClicked = date;
        defineReflectionDateOnClick(selectedDate);
    }

    private void clearViews(){
        moodNumView.setText("");
        thoughtsView.setText("");
        feelingsGridView.removeAllViews();
        activitiesGridView.removeAllViews();
    }

    private void defineTapToReflectBtn(){
        tapToReflectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentReflection != null){
                    goToStartReflectionActivityForViewOrEdit();
                }
            }
        });
    }

    private void goToStartReflectionActivityForViewOrEdit() {
        Intent intent = new Intent(getContext(), StartReflection.class);
        intent.putExtra("Reflection", currentReflection);
        activityResultLauncher.launch(intent);
    }

    private void adjustSelectedDateColourOnResume(Date date){
        for (int i = 0; i < reflectionHorizontalScrollViewContainer.getChildCount(); i++) {
            View child = reflectionHorizontalScrollViewContainer.getChildAt(i);

            if(child instanceof LinearLayout) {
                LinearLayout linearLayout = (LinearLayout) child;
                if (linearLayout.getContentDescription() != null && linearLayout.getContentDescription().toString().equals(date.toString())){
                    if (lastDateContainerClicked != null) {
                        lastDateContainerClicked.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.reflection_date_light_grey));
                        lastDateClicked.setTextColor(getResources().getColor(R.color.reflection_date_font_purple));
                        lastMonthClicked.setTextColor(getResources().getColor(R.color.reflection_date_font_purple));
                    }
                    LinearLayout dateContainerLinearLayout =(LinearLayout) linearLayout.getChildAt(0);
                    TextView month, dateView;

                    for (int j = 0; j < dateContainerLinearLayout.getChildCount(); j ++){
                        View innerChild = dateContainerLinearLayout.getChildAt(j);
                        if(innerChild instanceof TextView && j == 0){
                            month = (TextView) innerChild;
                            month.setTextColor(getResources().getColor(R.color.white));
                            lastMonthClicked = month;
                        }
                        else if(innerChild instanceof TextView){
                            dateView = (TextView) innerChild;
                            dateView.setTextColor(getResources().getColor(R.color.white));
                            lastDateClicked = dateView;
                        }
                    }

                    dateContainerLinearLayout.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.reflection_date_font_purple));
                    lastDateContainerClicked = dateContainerLinearLayout;
                }
            }
        }

    }
}