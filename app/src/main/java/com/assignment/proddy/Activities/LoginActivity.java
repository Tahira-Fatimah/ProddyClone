package com.assignment.proddy.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.assignment.proddy.Entity.user.User;
import com.assignment.proddy.Entity.user.asyncTasks.GetUserRecord;
import com.assignment.proddy.Entity.user.asyncTasks.InsertUser;
import com.assignment.proddy.R;
import com.assignment.proddy.Utils.AuthUtils;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.util.UUID;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    TextView signUpOption;
    TextView loginButton;
    LinearLayout googleSignInBtn;
    TextView text;

    private static final int RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        signUpOption = findViewById(R.id.signupOption);
        loginButton = findViewById(R.id.loginButton);

        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);

        googleSignInBtn = findViewById(R.id.btnGoogleSignIn_login);
        text = findViewById(R.id.checkonly);

        setSignUpOptionButtonListener();
        setLoginButtonListener();
        setGoogleSinInButtonListener();
    }

    private void setGoogleSinInButtonListener(){
        googleSignInBtn.setOnClickListener(v -> {
            GoogleSignInOptions gso = AuthUtils.getGoogleSignInOptions();
            GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(this, gso);
            Intent signInIntent = googleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN);
        });
    }

    private void setLoginButtonListener() {
        loginButton.setOnClickListener(v -> {
            loginButton.setClickable(false);
            loginButton.setEnabled(false);

            String _password, _email;

            _email = email.getText().toString();
            _password = password.getText().toString();

            if(_password.isEmpty() || _email.isEmpty() || !AuthUtils.checkEmailValidity(_email)){
                Toast.makeText(this,"Please validate the incorrect fields",Toast.LENGTH_LONG).show();
                loginButton.setClickable(true);
                loginButton.setEnabled(true);
            } else {
                logInUser(_email,_password);
            }
        });
    }

    private void setSignUpOptionButtonListener() {
        signUpOption.setOnClickListener(v -> {
            Intent signup = new Intent(this, SignupActivity.class);
            startActivityForResult(signup,101);
        });
    }


    private void logInUser(String email, String password){
        new GetUserRecord(this, new GetUserRecord.OnUserRecordRetrievedListener() {
            @Override
            public void onSuccess(User user) {
                setAuthStateForReturn(user);
            }

            @Override
            public void onFailure() {
                Toast.makeText(LoginActivity.this,"Email or Password is incorrect",Toast.LENGTH_LONG).show();
                loginButton.setClickable(true);
                loginButton.setEnabled(true);
            }
        }, email, password).execute();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        super.onActivityResult(requestCode, resultCode, resultData);
        if (requestCode == 101) {  // Check the request code
            if (resultCode == RESULT_OK) {
                if (resultData != null) {
                    String email = resultData.getStringExtra("USER_EMAIL");
                    String password = resultData.getStringExtra("USER_PASSWORD");
                    logInUser(email,password);
                }
            } else {
                finish();
            }
        } else if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(resultData);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                logInUser(account.getEmail(),"");
            } catch (ApiException e) {
                Log.w("GoogleSignIn", "Google sign-in failed", e);
            }
        }
    }

    private void setAuthStateForReturn(User user){
        Intent intent = new Intent();
        intent.putExtra("USER_RECORD",user);
        setResult(RESULT_OK, intent);
        finish();
    }

}