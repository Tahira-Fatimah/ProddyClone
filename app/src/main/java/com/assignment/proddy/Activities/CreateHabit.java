package com.assignment.proddy.Activities;

import static java.lang.Integer.parseInt;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.assignment.proddy.Entity.habit.Habit;
import com.assignment.proddy.Entity.habit.asyncTasks.InsertHabit;
import com.assignment.proddy.Entity.habitStreak.asyncTasks.InsertHabitStreakTask;
import com.assignment.proddy.Fragments.CreateHabit.CreateHabit1;
import com.assignment.proddy.Fragments.CreateHabit.CreateHabit2;
import com.assignment.proddy.Fragments.CreateHabit.CreateHabit3;
import com.assignment.proddy.Fragments.CreateHabit.CreateHabit4;
import com.assignment.proddy.Fragments.CreateHabit.CreateHabit5;
import com.assignment.proddy.Fragments.BottomSheets.NameValidationBottomSheet;
import com.assignment.proddy.R;
import com.assignment.proddy.SharedViewModel.HabitSharedViewModel;
import com.assignment.proddy.SharedViewModel.NavigationViewModel;
import com.assignment.proddy.Utils.StringUtils;
import com.assignment.proddy.Utils.AuthUtils;

import java.sql.Time;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public class CreateHabit extends AppCompatActivity{

    Button continueBtn;
    ImageView backBtn, cancelBtn;
    HabitSharedViewModel habitSharedViewModel;
    NavigationViewModel navigationViewModel;
    private int currentFragmentIndex = 0;
    List<ImageView> doneList;

    private Fragment[] fragments = {
            new CreateHabit1(),
            new CreateHabit2(),
            new CreateHabit3(),
            new CreateHabit4(),
            new CreateHabit5()
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_habit);

        doneList = Arrays.asList(
                findViewById(R.id.done1),
                findViewById(R.id.done2),
                findViewById(R.id.done3),
                findViewById(R.id.done4),
                findViewById(R.id.done5)
        );

        initViews();
        initSharedViewModels();
        observeSharedViewModel();
        fillFragment(fragments[0], true);
        defineBackBtn();
        defineContinueBtn();
        defineCancelBtn();


    }

    private void initViews(){
        backBtn = findViewById(R.id.backBtn);
        continueBtn = findViewById(R.id.continueBtn);
        cancelBtn = findViewById(R.id.cancel);
    }

    private void initSharedViewModels(){
        navigationViewModel = new ViewModelProvider(this).get(NavigationViewModel.class);
        habitSharedViewModel = new ViewModelProvider(this).get(HabitSharedViewModel.class);
    }

    private void observeSharedViewModel(){
        navigationViewModel.getNavigateToNextFragment().observe(this, shouldNavigate -> {
            if (shouldNavigate != null && shouldNavigate) {
                goToNextFragment();
                navigationViewModel.resetNavigation();
            }
        });
    }

    private void defineContinueBtn(){
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNextFragment();
            }
        });
    }

    private void defineBackBtn(){
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToPreviousFragment();
            }
        });
    }

    private void defineCancelBtn(){
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(0,android.R.anim.fade_out);
            }
        });
    }

    private void goToNextFragment() {
        if(habitNameValidation()){
                NameValidationBottomSheet bottomDrawerFragment = new NameValidationBottomSheet();
                bottomDrawerFragment.show(getSupportFragmentManager(), bottomDrawerFragment.getTag());
        }
        else{
            if (currentFragmentIndex == fragments.length - 1) {
                if(habitDaysValidation()){
                    habitSharedViewModel.setHabitDays(StringUtils.getAllDays());
                }
                continueBtn.setClickable(false);
                continueBtn.setEnabled(false);
                Habit habit = getHabit();
                new InsertHabit(this).execute(habit);
                new InsertHabitStreakTask(this).execute(habit.getHabitId());
                finish();
                overridePendingTransition(0,android.R.anim.fade_out);
            } else {
                updateNextIndicator();
                currentFragmentIndex++;
                fillFragment(fragments[currentFragmentIndex], true);
            }
        }
    }

    private void goToPreviousFragment() {
        if (currentFragmentIndex == 0) {
            finish();
            overridePendingTransition(0,android.R.anim.fade_out);
        } else {
            currentFragmentIndex--;
            updatePreviousIndicator();
            fillFragment(fragments[currentFragmentIndex], false);
        }
    }

    private void fillFragment(Fragment fragment, boolean isNext) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (isNext) {
            transaction.setCustomAnimations(
                    R.anim.enter_from_bottom,  // Enter animation
                    R.anim.exit_to_top         // Exit animation
            );
        } else {
            transaction.setCustomAnimations(
                    R.anim.enter_from_top,     // Enter animation
                    R.anim.exit_to_bottom      // Exit animation
            );
        }

        transaction.replace(R.id.createHabitPageContainer, fragment);
        transaction.commitAllowingStateLoss();
    }

    private boolean habitNameValidation() {
        String habitName = habitSharedViewModel.getHabitName().getValue();
        return habitName == null || habitName.trim().isEmpty();
    }

    private boolean habitDaysValidation() {
        List<String> habitDays = habitSharedViewModel.getHabitDays().getValue();
        return habitDays == null || habitDays.isEmpty();
    }

    private void updateNextIndicator() {
        if (currentFragmentIndex < doneList.size()) {
            doneList.get(currentFragmentIndex).setImageResource(R.drawable.check_circle_green);
        }
    }

    private void updatePreviousIndicator() {
        if (currentFragmentIndex < doneList.size()) {
            doneList.get(currentFragmentIndex).setImageResource(R.drawable.check_circle_grey);
        }
    }

    private Habit getHabit(){
        return new Habit(UUID.randomUUID(),habitSharedViewModel.getHabitName().getValue(),
                habitSharedViewModel.getHabitMotivationMessage().getValue(),
                habitSharedViewModel.getHabitType().getValue(),
                UUID.fromString(AuthUtils.getLoggedInUser(this)),
                habitSharedViewModel.getReminderTime().getValue(),
                habitSharedViewModel.getHabitDays().getValue());
    }

}