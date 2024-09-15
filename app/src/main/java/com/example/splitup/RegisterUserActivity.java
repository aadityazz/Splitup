package com.example.splitup;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

public class RegisterUserActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText numberEditText;
    private EditText passwordEditText;
    private Button registerButton;
    private String selectedUpiID = "";
    private String[] upiIDs;
    private EditText usernameEditText;
    private TextView alertText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register_user);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nameEditText = findViewById(R.id.nameRegisterInput);
        numberEditText = findViewById(R.id.phoneRegisterInput);
        passwordEditText = findViewById(R.id.passwordRegisterInput);
        registerButton = findViewById(R.id.registerButton);
        usernameEditText = findViewById(R.id.usernameRegisterInput);
        alertText = findViewById(R.id.alertTextView);

        String userName = getIntent().getStringExtra("USERNAME");
        String password = getIntent().getStringExtra("PASSWORD");

        alertText.setText("Username : " + userName + " doesn't exist.");

        if (userName != null) usernameEditText.setText(userName);
        if (password != null) passwordEditText.setText(password);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the input values
                String nameValue = nameEditText.getText().toString().trim();
                String usernameValue = usernameEditText.getText().toString().trim();
                String passwordValue = passwordEditText.getText().toString().trim();
                String phoneNumberValue = numberEditText.getText().toString().trim();

                // Check for empty fields
                if (nameValue.isEmpty() || usernameValue.isEmpty() || passwordValue.isEmpty() || phoneNumberValue.isEmpty() ) {
                    Toast.makeText(RegisterUserActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else if (isUsernameExists(usernameValue)) {
                    alertText.setText("User : " + usernameValue + " already exists.");
                    alertText.setVisibility(View.VISIBLE);
                } else {
                    upiIDs = findUpiIdsByNumber(phoneNumberValue);
                    showUpiIdDialog(RegisterUserActivity.this, upiIDs);

                    registerUser(nameValue, usernameValue, passwordValue, phoneNumberValue, selectedUpiID);
                }
            }
        });
    }

    private String[] findUpiIdsByNumber(String phoneNumber) {
        return new String[]{"test@ytpay", "test@test"};
    }

    private boolean isUsernameExists(String username) {
        if(username.equals("tes")) return true;
        return false;
    }

    private void registerUser(String name, String username, String password, String phoneNumber, String upiID) {
        Toast.makeText(this, "User Registered: " + username, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, FullScreenActivity.class));
        finish();
    }

    private void showUpiIdDialog(Context context, String[] upiIDs) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Select UPI ID");

        final int[] selectedUpiIndex = {0};

        builder.setSingleChoiceItems(upiIDs, selectedUpiIndex[0], new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selectedUpiIndex[0] = which;
            }
        });

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selectedUpiID = upiIDs[selectedUpiIndex[0]];
                Toast.makeText(context, "Selected UPI: " + selectedUpiID, Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

}
