package com.example.fixnbuy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserProfileActivity extends AppCompatActivity {

    FirebaseAuth auth;
    Button signOutButton;
    TextView showEmailOrNumber, userName;
    FirebaseUser user;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        auth = FirebaseAuth.getInstance();
        signOutButton = findViewById(R.id.signOutButton);
        showEmailOrNumber = findViewById(R.id.showEmailOrNumber);
        userName = findViewById(R.id.userName);

        user = auth.getCurrentUser();
        if(user == null){
            Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(loginIntent);
            finish();
        }else {
            showEmailOrNumber.setText(user.getEmail());
            String email = user.getEmail();
            if (email != null && email.contains("@")) {
                String name = email.substring(0, email.indexOf('@'));
                userName.setText(name);
            } else {
                userName.setText("Unknown User");
            }
        }
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent signOutIntent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(signOutIntent);
                finish();
            }
        });
    }
}