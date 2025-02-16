package com.example.semesterproject.activities;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.semesterproject.R;
import com.example.semesterproject.activities.Database;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText emailEditText, passwordEditText;
    private Button loginButton, resetPasswordButton;
    private TextView registerLink;
    private ImageButton backButton;
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize UI elements
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        resetPasswordButton = findViewById(R.id.resetPasswordButton);
        registerLink = findViewById(R.id.registerLink);
        backButton = findViewById(R.id.backButton);

        db = new Database(this);

        loginButton.setOnClickListener(view -> loginUser());

        registerLink.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
            startActivity(intent);
        });

        resetPasswordButton.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, ResetPasswordActivity.class);
            startActivity(intent);
        });

       // backButton.setOnClickListener(view -> finish());
        backButton.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
         finish();
        });
    }

    private void loginUser() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show();
            return;
        }


        Cursor cursor = db.getUser(email, password);
        if (cursor != null && cursor.moveToFirst()) {
            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();


            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
        }

        if (cursor != null) {
            cursor.close();
        }
    }
}




//package com.example.semesterproject.activities;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.EditText;
//import android.widget.Toast;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.semesterproject.R;
//
//public class LoginActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//
//        EditText emailEditText = findViewById(R.id.emailEditText);
//        EditText passwordEditText = findViewById(R.id.passwordEditText);
//
//        findViewById(R.id.loginButton).setOnClickListener(v -> {
//            String email = emailEditText.getText().toString().trim();
//            String password = passwordEditText.getText().toString().trim();
//
//            if (validateCredentials(email, password)) {
//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(intent);
//                finish();
//            } else {
//                Toast.makeText(LoginActivity.this, "Invalid credentials. Please try again.", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        findViewById(R.id.resetPasswordButton).setOnClickListener(v -> {
//            startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
//        });
//
//        findViewById(R.id.registerLink).setOnClickListener(v -> {
//            startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
//        });
//        findViewById(R.id.backButton).setOnClickListener(v -> {
//            startActivity(new Intent(LoginActivity.this, MainActivity.class));
//            finish();
//        });
//    }
//
//    private boolean validateCredentials(String email, String password) {
//        return email.equals("user@example.com") && password.equals("password123");
//    }
//}
