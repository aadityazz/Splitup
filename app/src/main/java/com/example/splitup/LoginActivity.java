package com.example.splitup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        loginButton = findViewById(R.id.loginButton);
        username = findViewById(R.id.usernameText);
        password = findViewById(R.id.passwordText);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameInput = username.getText().toString().trim();
                String passwordInput = password.getText().toString().trim();

                if (usernameInput.isEmpty() || passwordInput.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please Enter Credentials", Toast.LENGTH_SHORT).show();
                    return;
                }

                Boolean result = checkCredentials(usernameInput, passwordInput);

                if (result) {
                    Intent intent = new Intent(LoginActivity.this, FullScreenActivity.class);
                    startActivity(intent);
                } else if (!result) {
                    Toast.makeText(LoginActivity.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(LoginActivity.this, RegisterUserActivity.class);
                    intent.putExtra("USERNAME", usernameInput);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Oops.. something went wrong, try again", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private Boolean checkCredentials(String usernameInput, String passwordInput) {
        if(usernameInput.equals("test") && passwordInput.equals("test")){
            return true;
        }
        return false;
    }
}