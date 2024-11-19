package com.assignment.proddy.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.viewpager2.widget.ViewPager2;

import com.assignment.proddy.Adapters.HabitCategoryAdapter;
import com.assignment.proddy.Entity.habit.Habit;
import com.assignment.proddy.Fragments.BottomSheets.DeleteHabitBottomSheet;
import com.assignment.proddy.Models.HabitCategory;
import com.assignment.proddy.R;
import com.assignment.proddy.SharedViewModel.HabitSharedViewModel;
import com.assignment.proddy.Utils.StringUtils;
import com.assignment.proddy.ZoomOutPageTransformer;

import java.util.List;

public class EditHabit extends AppCompatActivity {

    ViewPager2 habitTypeViewPager;
    TextView deleteHabitBtn, saveChangesBtn, createReminder, editReminderTime, charCount;
    ImageView closeBtn, noReminder;
    EditText habitNameEdit, habitReasonEdit;
    LinearLayout reminderTime, reminderLayoutInitial, reminderLayoutCreateReminder;
    HabitSharedViewModel habitSharedViewModel;
    Habit habit;
    TextView [] daysTextViews;
    List<String> allDays = StringUtils.getAllDays();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_habit);
        habit = (Habit) getIntent().getSerializableExtra("Habit");
        initViews();
        defineValues();
        initViewHolder();
        defineViewModelValues();
        defineViewPager();
        defineDeleteHabitBtn();
        defineNoReminder();
        defineCreateReminder();
        defineEditHabitNameView();
        defineReminderTime();
        defineAllDaysButtons();


    }

    private void initViews(){
        habitTypeViewPager = findViewById(R.id.habitEditHabitTypeViewPager);
        deleteHabitBtn = findViewById(R.id.btn_delete_habit);
        saveChangesBtn = findViewById(R.id.btn_save_changes);
        closeBtn = findViewById(R.id.btn_close);
        noReminder = findViewById(R.id.noReminder);
        habitNameEdit = findViewById(R.id.edit_habit_name);
        habitReasonEdit = findViewById(R.id.edit_habit_reason);
        reminderTime = findViewById(R.id.edit_habit_reminder_time);
        reminderLayoutInitial = findViewById(R.id.reminderLayoutInitial);
        reminderLayoutCreateReminder = findViewById(R.id.reminderLayoutCreateReminder);
        createReminder = findViewById(R.id.createReminder);
        editReminderTime = findViewById(R.id.edit_reminder_time);
        charCount = findViewById(R.id.edit_habit_name_char_count);
        daysTextViews = new TextView[]{
                findViewById(R.id.monday),
                findViewById(R.id.tuesday),
                findViewById(R.id.wednesday),
                findViewById(R.id.thursday),
                findViewById(R.id.friday),
                findViewById(R.id.saturday),
                findViewById(R.id.sunday)
        };
    }

    private void defineValues(){
        habitNameEdit.setText(habit.getName());
        habitReasonEdit.setText(habit.getReason());
        editReminderTime.setText(habit.getReminderTime().toString());
        charCount.setText(habit.getName().length()+ "/40");
        for(int i=0; i<habit.getHabitDays().size(); i++){
           int index = allDays.indexOf(habit.getHabitDays().get(i));
            daysTextViews[index].setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.edit_habit_days_selected_bg));
           daysTextViews[index].setTextColor(getResources().getColor(R.color.white));
        }
    }

    private void initViewHolder(){
        habitSharedViewModel = new ViewModelProvider(
                this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())
        ).get(HabitSharedViewModel.class);
    }

    private void defineViewPager(){
        List<HabitCategory> habitCategories = StringUtils.getHabitCategories();
        HabitCategoryAdapter habitCategoryAdapter = new HabitCategoryAdapter(habitCategories, R.layout.edit_habit_habit_type_item);
        habitTypeViewPager.setAdapter(habitCategoryAdapter);
        habitTypeViewPager.setPageTransformer(new ZoomOutPageTransformer());
        habitTypeViewPager.setOffscreenPageLimit(3);
        habitTypeViewPager.setCurrentItem(StringUtils.getHabitCategoryIndex(habit.getHabitType()));
    }

    private void defineSaveChangeBtn(){
        saveChangesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String habitName = habitNameEdit.getText().toString();
                String habitReason = habitReasonEdit.getText().toString();

            }
        });
    }

    private void defineDeleteHabitBtn(){
        deleteHabitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteHabitBottomSheet bottomDrawerFragment = new DeleteHabitBottomSheet();
                bottomDrawerFragment.setHabit(habit);
                bottomDrawerFragment.show(getSupportFragmentManager(), bottomDrawerFragment.getTag());
            }
        });
    }

    private void defineCloseBtn(){
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void defineNoReminder(){
        noReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reminderLayoutInitial.setVisibility(View.GONE);
                reminderLayoutCreateReminder.setVisibility(View.VISIBLE);
            }
        });
    }

    private void defineCreateReminder(){
        createReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditHabit.this, SetReminder.class);
                startActivity(intent);            }
        });
    }

    private void defineEditHabitNameView(){
        habitNameEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                charCount.setText(habitNameEdit.getText().toString().length() + "/40");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void defineReminderTime(){
        reminderTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditHabit.this, SetReminder.class);
                startActivity(intent);
            }
        });
    }

    private void defineViewModelValues(){
        habitSharedViewModel.setHabitName(habit.getName());
        habitSharedViewModel.setHabitMotivationMessage(habit.getReason());
        habitSharedViewModel.setHabitDays(habit.getHabitDays());
        habitSharedViewModel.setHabitType(habit.getHabitType());
    }

    private void defineAllDaysButtons(){
        for(int i = 0; i <daysTextViews.length; i++){
            defineDayButton(daysTextViews[i]);
        }
    }

    private void defineDayButton(TextView textView){
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String day = ((TextView) v).getText().toString();
                List<String> selectedDays = habitSharedViewModel.getHabitDays().getValue();

                if(selectedDays.contains(day)){
                    selectedDays.remove(day);
                    textView.setBackgroundTintList(ContextCompat.getColorStateList(EditHabit.this, R.color.edit_habit_days_unselected_bg));
                    textView.setTextColor(getResources().getColor(R.color.reflection_bg));
                }
                else{
                    selectedDays.add(day);
                    textView.setBackgroundTintList(ContextCompat.getColorStateList(EditHabit.this, R.color.edit_habit_days_selected_bg));
                    textView.setTextColor(getResources().getColor(R.color.white));
                }

                habitSharedViewModel.setHabitDays(selectedDays);
                System.out.println("Selected day  " + habitSharedViewModel.getHabitDays().getValue());
            }
        });
    }
}