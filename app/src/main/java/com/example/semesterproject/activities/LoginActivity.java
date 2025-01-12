package com.example.semesterproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.semesterproject.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText emailEditText = findViewById(R.id.emailEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);

        findViewById(R.id.loginButton).setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (validateCredentials(email, password)) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "Invalid credentials. Please try again.", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.resetPasswordButton).setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
        });

        findViewById(R.id.registerLink).setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
        });
        findViewById(R.id.backButton).setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        });
    }

    private boolean validateCredentials(String email, String password) {
        return email.equals("user@example.com") && password.equals("password123");
    }
}
