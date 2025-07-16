package com.example.fixnbuy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignUpActivity extends AppCompatActivity {

    TextView signUpToLogin;
    Button signUpButton;
    EditText nameEditText, emailEditText, passwordEditText;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        signUpToLogin = findViewById(R.id.loginText);
        signUpToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUpToLogin = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(signUpToLogin);
                finish();
            }
        });

        signUpButton = findViewById(R.id.signupButton);
        nameEditText = findViewById(R.id.nameField);
        emailEditText = findViewById(R.id.emailOrNumberField);
        passwordEditText = findViewById(R.id.passwordField);

        signUpButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please provide the information.", Toast.LENGTH_SHORT).show();
            } else {
                Intent signupToHome = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(signupToHome);
            }
        });

    }
}