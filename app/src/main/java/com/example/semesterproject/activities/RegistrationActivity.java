package com.example.semesterproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.semesterproject.R;
import com.google.android.material.textfield.TextInputEditText;

public class RegistrationActivity extends AppCompatActivity {

    private TextInputEditText nameEditText;
    private TextInputEditText emailEditText;
    private TextInputEditText passwordEditText;
    private TextInputEditText confirmPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // Initialize views
        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        TextView loginLink = findViewById(R.id.loginLink);

        // Set click listener for register button
        findViewById(R.id.registerButton).setOnClickListener(v -> {
            if (validateInputs()) {
                // Registration logic here (e.g., saving data to database or API call)
                Toast.makeText(RegistrationActivity.this, "Registration Successful!", Toast.LENGTH_SHORT).show();

                // Redirect to LoginActivity
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                finish();
            }
        });

        // Set click listener for login link
        loginLink.setOnClickListener(v -> {
            // Redirect to LoginActivity
            startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            finish();
        });
    }

    // Validate input fields
    private boolean validateInputs() {
        String name = nameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String confirmPassword = confirmPasswordEditText.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            nameEditText.setError("Full name is required");
            return false;
        }

        if (TextUtils.isEmpty(email)) {
            emailEditText.setError("Email is required");
            return false;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Enter a valid email address");
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            passwordEditText.setError("Password is required");
            return false;
        }

        if (password.length() < 6) {
            passwordEditText.setError("Password must be at least 6 characters long");
            return false;
        }

        if (TextUtils.isEmpty(confirmPassword)) {
            confirmPasswordEditText.setError("Confirm your password");
            return false;
        }

        if (!password.equals(confirmPassword)) {
            confirmPasswordEditText.setError("Passwords do not match");
            return false;
        }

        return true;
    }
}
