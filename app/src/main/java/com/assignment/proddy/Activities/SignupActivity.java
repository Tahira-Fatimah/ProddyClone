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
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.assignment.proddy.Entity.user.User;
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

public class SignupActivity extends AppCompatActivity {

    LinearLayout signUpChoose;
    LinearLayout emailSignUp;
    TextView emailBtn;

    EditText name;
    EditText email;
    EditText password;
    TextView registerBtn;
    TextView signUpWithGoogle;

    private static final int RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);

        signUpChoose = findViewById(R.id.choose_signup);
        emailSignUp = findViewById(R.id.emailSignUp);
        emailBtn = findViewById(R.id.btnSignUpEmail);

        name = findViewById(R.id.etName);
        email = findViewById(R.id.etEmail_s);
        password = findViewById(R.id.etPassword_s);
        registerBtn = findViewById(R.id.register);
        signUpWithGoogle = findViewById(R.id.btnGoogleSignIn);

        setGoogleSignUpButtonListener();
        setEmailButtonListener();
        setRegisterButtonListener();
    }

    private void setGoogleSignUpButtonListener() {
        signUpWithGoogle.setOnClickListener(v->{
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken("661052001100-rlsrq5ponrnhkgqv80pd9ln5jh5nt6fl.apps.googleusercontent.com")
                    .requestEmail()
                    .build();

            GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(this, gso);
            Intent signInIntent = googleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN);
        });
    }

    private void setRegisterButtonListener() {
        registerBtn.setOnClickListener(v -> {
            registerBtn.setClickable(false);
            registerBtn.setEnabled(false);

            String _name, _email, _password;
            _name = name.getText().toString();
            _email = email.getText().toString();
            _password = password.getText().toString();

            if(_name.isEmpty() || _password.isEmpty() || _email.isEmpty() || !AuthUtils.checkEmailValidity(_email)){
                Toast.makeText(this,"Please validate the incorrect fields",Toast.LENGTH_LONG).show();
                registerBtn.setClickable(true);
                registerBtn.setEnabled(true);
            } else {
                signUpUser(_name, _email, _password);
            }
        });
    }

    private void setEmailButtonListener() {
        emailBtn.setOnClickListener(v -> {
            emailSignUp.setVisibility(View.VISIBLE);
            signUpChoose.setVisibility(View.GONE);
        });
    }


    private void signUpUser(String name, String email, String password){
        UUID userId = UUID.randomUUID();
        new InsertUser(this, new InsertUser.OnUserInsertListener() {
            @Override
            public void onSuccess() {
                finishSignUpActivity(email,password);
            }
        }).execute(new User(userId,name,email,password));
    }

    private void finishSignUpActivity(String email, String password) {
        Intent intent = new Intent();
        intent.putExtra("USER_EMAIL", email);
        intent.putExtra("USER_PASSWORD", password);
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        super.onActivityResult(requestCode, resultCode, resultData);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(resultData);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                signUpUser(account.getDisplayName(),account.getEmail(), "");
            } catch (ApiException e) {
                Log.w("GoogleSignIn", "Google sign-in failed", e);
            }
        }
    }


}