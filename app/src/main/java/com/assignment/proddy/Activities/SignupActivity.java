package com.assignment.proddy.Activities;

import android.content.Intent;
import android.os.Bundle;
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

        setEmailButtonListener();
        setRegisterButtonListener();
    }

    private void setRegisterButtonListener() {
        registerBtn.setOnClickListener(v -> {
            registerBtn.setClickable(false);
            registerBtn.setEnabled(false);

            String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
            Pattern pattern = Pattern.compile(emailRegex);

            String _name, _email, _password;

            _name = name.getText().toString();
            _email = email.getText().toString();
            _password = password.getText().toString();

            if(_name.isEmpty() || _password.isEmpty() || _email.isEmpty() || !pattern.matcher(_email).matches()){
                Toast.makeText(this,"Please validate the incorrect fields",Toast.LENGTH_LONG).show();
                registerBtn.setClickable(true);
                registerBtn.setEnabled(true);
            } else {
                UUID userId = UUID.randomUUID();
                new InsertUser(this).execute(new User(userId,_name,_email,_password));

                Intent intent = new Intent();
                intent.putExtra("USERID",userId.toString());
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    private void setEmailButtonListener() {
        emailBtn.setOnClickListener(v -> {
            emailSignUp.setVisibility(View.VISIBLE);
            signUpChoose.setVisibility(View.GONE);
        });
    }


}