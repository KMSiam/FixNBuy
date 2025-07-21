package com.example.fixnbuy.Activity;

import static android.app.ProgressDialog.show;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fixnbuy.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    EditText emailOrNumberSignup, passwordSignup, nameField, confirmPassword;
    Button signupButton;
    TextView loginText;
    ProgressBar signUpProgressBar;
    FirebaseAuth mAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        nameField = findViewById(R.id.nameField);
        emailOrNumberSignup = findViewById(R.id.emailOrNumberSignup);
        passwordSignup = findViewById(R.id.passwordSignup);
        confirmPassword = findViewById(R.id.confirmPassword);
        signupButton = findViewById(R.id.signupButton);
        loginText = findViewById(R.id.loginText);
        signUpProgressBar = findViewById(R.id.signUpProgressBar);

        signupButton.setOnClickListener(v -> {
            String name = nameField.getText().toString().trim();
            String email = emailOrNumberSignup.getText().toString().trim();
            String password = passwordSignup.getText().toString().trim();
            String confirmPass = confirmPassword.getText().toString().trim();

            if(TextUtils.isEmpty(name)){
                nameField.setError("Name is required");
                return;
            }

            if (TextUtils.isEmpty(email)) {
                emailOrNumberSignup.setError("Email is required");
                return;
            }

            if (TextUtils.isEmpty(password)) {
                passwordSignup.setError("Password is required");
                return;
            }

            if (password.length() < 6) {
                passwordSignup.setError("Password must be at least 6 characters");
                return;
            }

            if( !password.equals(confirmPass)) {
                confirmPassword.setError("Passwords do not match");
            }else {
                signUpProgressBar.setVisibility(View.VISIBLE);

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                            signUpProgressBar.setVisibility(View.GONE);
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(SignUpActivity.this, "Account created!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                                finish();
                            } else {
                                Toast.makeText(SignUpActivity.this, "Sign up failed: " , Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });

        loginText.setOnClickListener(v -> {
            startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            finish();
        });
    }
}