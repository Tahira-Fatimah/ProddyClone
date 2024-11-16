package com.assignment.proddy.Activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.assignment.proddy.Fragments.CreateHabit.CreateHabit1;
import com.assignment.proddy.Fragments.CreateHabit.CreateHabit2;
import com.assignment.proddy.Fragments.CreateHabit.CreateHabit3;
import com.assignment.proddy.Fragments.CreateHabit.CreateHabit4;
import com.assignment.proddy.Fragments.CreateHabit.CreateHabit5;
import com.assignment.proddy.Fragments.NameValidationBottomSheet;
import com.assignment.proddy.Listener.CreateHabitInteractionListener;
import com.assignment.proddy.MainActivity;
import com.assignment.proddy.R;
import com.assignment.proddy.SharedViewModel.HabitSharedViewModel;
import com.assignment.proddy.SharedViewModel.NavigationViewModel;
import com.assignment.proddy.Utils.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateHabit extends AppCompatActivity{

    Button continueBtn;
    ImageView backBtn;
    HabitSharedViewModel habitSharedViewModel;
    NavigationViewModel navigationViewModel;
    private int currentFragmentIndex = 0;


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

        navigationViewModel = new ViewModelProvider(this).get(NavigationViewModel.class);
        habitSharedViewModel = new ViewModelProvider(this).get(HabitSharedViewModel.class);

        navigationViewModel.getNavigateToNextFragment().observe(this, shouldNavigate -> {
            if (shouldNavigate != null && shouldNavigate) {
                goToNextFragment();
                navigationViewModel.resetNavigation();
            }
        });

        fillFragment(fragments[0], true);

        continueBtn = findViewById(R.id.continueBtn);
        backBtn = findViewById(R.id.backBtn);

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNextFragment();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToPreviousFragment();
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
                Intent intent = new Intent(CreateHabit.this, LoginActivity.class);
                startActivity(intent);
                if(habitDaysValidation()){
                    habitSharedViewModel.setHabitDays(StringUtils.getAllDays());
                }
                finish();
            } else {
                currentFragmentIndex++;
                fillFragment(fragments[currentFragmentIndex], true);
            }
        }
        System.out.println(habitSharedViewModel.toString());


    }

    private void goToPreviousFragment() {
        if (currentFragmentIndex == 0) {
            Intent intent = new Intent(CreateHabit.this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            currentFragmentIndex--;
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


    private void showCustomAlertDialog() {
        View dialogView = LayoutInflater.from(this)
                .inflate(R.layout.enter_habit_name_custom_dialog, null);

        AlertDialog alertDialog = new AlertDialog.Builder(this, R.style.BottomDialogAnimation)
                .setView(dialogView)
                .setCancelable(false)
                .create();

        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setGravity(Gravity.BOTTOM);
            alertDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            alertDialog.getWindow().getAttributes().windowAnimations = R.style.BottomDialogAnimation;
        }

        Button okButton = dialogView.findViewById(R.id.okButton);
        okButton.setOnClickListener(v -> alertDialog.dismiss());

        alertDialog.show();
    }

    private boolean habitNameValidation() {
        String habitName = habitSharedViewModel.getHabitName().getValue();
        return habitName == null || habitName.trim().isEmpty();
    }

    private boolean habitDaysValidation() {
        List<String> habitDays = habitSharedViewModel.getHabitDays().getValue();
        return habitDays == null || habitDays.isEmpty();
    }

}