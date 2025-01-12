package com.example.semesterproject.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.semesterproject.R;
import com.google.android.material.textfield.TextInputEditText;

public class ResetPasswordActivity extends AppCompatActivity {

    private TextInputEditText currentPasswordEditText, newPasswordEditText, confirmPasswordEditText;

    // Assume this is the literal string for the current password (to be replaced with a database check later)
    private static final String CURRENT_PASSWORD_LITERAL = "password123"; // Literal current password

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        // Initialize UI components
        currentPasswordEditText = findViewById(R.id.currentPasswordEditText);
        newPasswordEditText = findViewById(R.id.newPasswordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);

        // Set up the back button click listener
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close the current activity and return to the previous one
                finish();
            }
        });

        // Set up the reset password button click listener
        findViewById(R.id.resetPasswordButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });
    }

    private void resetPassword() {
        String currentPassword = currentPasswordEditText.getText().toString().trim();
        String newPassword = newPasswordEditText.getText().toString().trim();
        String confirmPassword = confirmPasswordEditText.getText().toString().trim();

        // Validate input
        if (TextUtils.isEmpty(currentPassword)) {
            currentPasswordEditText.setError("Please enter your current password");
            return;
        }

        if (!currentPassword.equals(CURRENT_PASSWORD_LITERAL)) {
            currentPasswordEditText.setError("Current password is incorrect");
            return;
        }

        if (TextUtils.isEmpty(newPassword)) {
            newPasswordEditText.setError("Please enter a new password");
            return;
        }

        if (TextUtils.isEmpty(confirmPassword)) {
            confirmPasswordEditText.setError("Please confirm your new password");
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            confirmPasswordEditText.setError("Passwords do not match");
            return;
        }

        // If all validations pass
        Toast.makeText(this, "Password reset successful!", Toast.LENGTH_SHORT).show();

        // You can add further functionality here, such as navigating back or clearing the fields
        clearFields();
    }

    private void clearFields() {
        currentPasswordEditText.setText("");
        newPasswordEditText.setText("");
        confirmPasswordEditText.setText("");
    }
}
