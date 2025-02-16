package com.example.semesterproject.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.semesterproject.R;
import com.example.semesterproject.activities.Database;
import com.google.android.material.textfield.TextInputEditText;

public class ResetPasswordActivity extends AppCompatActivity {

    private TextInputEditText emailEditText, currentPasswordEditText, newPasswordEditText, confirmPasswordEditText;
    private Button resetPasswordButton;
    private ImageButton backButton;
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        emailEditText = findViewById(R.id.emailEditText);
        currentPasswordEditText = findViewById(R.id.currentPasswordEditText);
        newPasswordEditText = findViewById(R.id.newPasswordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        resetPasswordButton = findViewById(R.id.resetPasswordButton);
        backButton = findViewById(R.id.backButton);

        db = new Database(this);

        resetPasswordButton.setOnClickListener(view -> resetPassword());

        backButton.setOnClickListener(view -> finish());
    }

    private void resetPassword() {
        String email = emailEditText.getText().toString().trim();
        String currentPassword = currentPasswordEditText.getText().toString().trim();
        String newPassword = newPasswordEditText.getText().toString().trim();
        String confirmPassword = confirmPasswordEditText.getText().toString().trim();

        if (email.isEmpty() || currentPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isEmailValid(email)) {
            Toast.makeText(this, "Email not found!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isCurrentPasswordCorrect(email, currentPassword)) {
            Toast.makeText(this, "Current password is incorrect!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            Toast.makeText(this, "New password and confirm password do not match!", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean isUpdated = db.updatePassword(email, newPassword);
        if (isUpdated) {
            Toast.makeText(this, "Password reset successfully!", Toast.LENGTH_SHORT).show();
            finish(); // Close activity after success
        } else {
            Toast.makeText(this, "Failed to reset password. Try again!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isEmailValid(String email) {
        Cursor cursor = db.getUserByEmail(email);
        boolean isValid = cursor != null && cursor.moveToFirst();
        if (cursor != null) cursor.close();
        return isValid;
    }

    private boolean isCurrentPasswordCorrect(String email, String currentPassword) {
        Cursor cursor = db.getUserPasswordByEmail(email);
        boolean isCorrect = cursor != null && cursor.moveToFirst() && cursor.getString(0).equals(currentPassword);
        if (cursor != null) cursor.close();
        return isCorrect;
    }
}


//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.ImageButton;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.semesterproject.R;
//import com.google.android.material.textfield.TextInputEditText;
//
//public class ResetPasswordActivity extends AppCompatActivity {
//
//    private TextInputEditText currentPasswordEditText, newPasswordEditText, confirmPasswordEditText;
//
//    // Assume this is the literal string for the current password (to be replaced with a database check later)
//    private static final String CURRENT_PASSWORD_LITERAL = "password123"; // Literal current password
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_reset_password);
//
//        // Initialize UI components
//        currentPasswordEditText = findViewById(R.id.currentPasswordEditText);
//        newPasswordEditText = findViewById(R.id.newPasswordEditText);
//        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
//
//        // Set up the back button click listener
//        ImageButton backButton = findViewById(R.id.backButton);
//        backButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Close the current activity and return to the previous one
//                finish();
//            }
//        });
//
//        // Set up the reset password button click listener
//        findViewById(R.id.resetPasswordButton).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                resetPassword();
//            }
//        });
//    }
//
//    private void resetPassword() {
//        String currentPassword = currentPasswordEditText.getText().toString().trim();
//        String newPassword = newPasswordEditText.getText().toString().trim();
//        String confirmPassword = confirmPasswordEditText.getText().toString().trim();
//
//        // Validate input
//        if (TextUtils.isEmpty(currentPassword)) {
//            currentPasswordEditText.setError("Please enter your current password");
//            return;
//        }
//
//        if (!currentPassword.equals(CURRENT_PASSWORD_LITERAL)) {
//            currentPasswordEditText.setError("Current password is incorrect");
//            return;
//        }
//
//        if (TextUtils.isEmpty(newPassword)) {
//            newPasswordEditText.setError("Please enter a new password");
//            return;
//        }
//
//        if (TextUtils.isEmpty(confirmPassword)) {
//            confirmPasswordEditText.setError("Please confirm your new password");
//            return;
//        }
//
//        if (!newPassword.equals(confirmPassword)) {
//            confirmPasswordEditText.setError("Passwords do not match");
//            return;
//        }
//
//        // If all validations pass
//        Toast.makeText(this, "Password reset successful!", Toast.LENGTH_SHORT).show();
//
//        // You can add further functionality here, such as navigating back or clearing the fields
//        clearFields();
//    }
//
//    private void clearFields() {
//        currentPasswordEditText.setText("");
//        newPasswordEditText.setText("");
//        confirmPasswordEditText.setText("");
//    }
//}
