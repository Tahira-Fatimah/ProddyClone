package com.assignment.proddy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.assignment.proddy.Activities.LoginActivity;
import com.assignment.proddy.Activities.SignupActivity;
import com.assignment.proddy.Entity.user.User;
import com.assignment.proddy.Fragments.AllHabitsFragment;
import com.assignment.proddy.Fragments.BottomSheets.ControlTabBottomSheet;
import com.assignment.proddy.Fragments.LessonsFragment;
import com.assignment.proddy.Fragments.ReflectionFragment;
import com.assignment.proddy.Fragments.Insights;
import com.assignment.proddy.Utils.AuthUtils;

import com.assignment.proddy.Utils.DateUtils;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkAuthenticationState();
    }

    private void checkAuthenticationState() {
        if(Objects.equals(AuthUtils.getLoggedInUser(this), "blank")){
            Intent signIn = new Intent(this, LoginActivity.class);
            startActivityForResult(signIn,100);
        } else {
            launchApp();
        }
    }

    private void launchApp() {

        TabLayout tabLayout = findViewById(R.id.controltabLayout);
            inflateTabs(tabLayout);
            setTabLayoutOnClickListener(tabLayout);

            Fragment fragment = new AllHabitsFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container_main, fragment)
                    .commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        super.onActivityResult(requestCode, resultCode, resultData);
        if (requestCode == 100) {  // Check the request code
            if (resultCode == RESULT_OK) {
                if (resultData != null) {
                    User user = (User)resultData.getSerializableExtra("USER_RECORD");
                    AuthUtils.storeUserInfo(this, user);
                    launchApp();
                }
            } else {
                finish();
            }
        }
    }

    void inflateTabs(TabLayout tabLayout){
        View plusButtonView = LayoutInflater.from(this).inflate(R.layout.control_tab_plus, null);
        Button button = plusButtonView.findViewById(R.id.control_tab_plus_button);
        button.setOnClickListener(v -> {
            ControlTabBottomSheet bottomDrawerFragment = new ControlTabBottomSheet();
            bottomDrawerFragment.show(getSupportFragmentManager(), bottomDrawerFragment.getTag());
        });
        button.setText("\uE945");

        tabLayout.addTab(tabLayout.newTab().setText("\uE9CC"));
        tabLayout.addTab(tabLayout.newTab().setText("\uE9DD"));
        tabLayout.addTab(tabLayout.newTab().setCustomView(plusButtonView));
        tabLayout.addTab(tabLayout.newTab().setText("\uE980"));
        tabLayout.addTab(tabLayout.newTab().setText("\uE981"));

        TabLayout.Tab tabplus = tabLayout.getTabAt(2);
        if (tabplus != null) {
            View tabView = tabplus.view;
            tabView.setClickable(false);
            tabView.setEnabled(false);
        }
    }

    void setTabLayoutOnClickListener(TabLayout tabLayout){
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Fragment fragment;

                switch (position) {
                    case 0:
                        fragment = new AllHabitsFragment();
                        break;
                    case 1:
                        fragment = new Insights();
                        break;
                    case 3:
                        fragment = new ReflectionFragment();
                        break;
                    case 4:
                        fragment = new LessonsFragment();
                        break;
                    default:
                        fragment = new AllHabitsFragment();
                }

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container_main, fragment)
                        .commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
}
