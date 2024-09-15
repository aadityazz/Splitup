package com.example.splitup;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FullScreenActivity extends AppCompatActivity {

    private Button addGroupButton;
    private ImageButton profileButton;
    private LinearLayout groupShowLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_full_screen);

        // Applying window insets for padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addGroupButton = findViewById(R.id.addGroupButton);
        profileButton = findViewById(R.id.profileImageButton);
        groupShowLayout = findViewById(R.id.groupShowLayout);

        String[] groups = findAllGroups();

        for (String group : groups) {
            View groupView = LayoutInflater.from(this).inflate(R.layout.group_list, groupShowLayout, false);

            TextView groupNameTextView = groupView.findViewById(R.id.groupNameTextView);
            groupNameTextView.setText(group);

            TextView scoreTextView = groupView.findViewById(R.id.scoreTextView);
            scoreTextView.setText("You Owe " + calculateOwedAmount(group) + " rs");

            groupShowLayout.addView(groupView);

            groupView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(FullScreenActivity.this, GroupActivity.class);
                    startActivity(intent);
                }
            });
        }


        addGroupButton.setOnClickListener(v -> {
            addNewGroup();
        });

        profileButton.setOnClickListener(v -> {
            openProfile();
        });
    }

    private String calculateOwedAmount(String group) {
        return "40Rs";
    }

    private String[] findAllGroups() {
        return new String[]{"Group 1", "Group 2", "Group 3"};
    }

    // Method to add a new group
    private void addNewGroup() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Create Group");

        View dialogView = getLayoutInflater().inflate(R.layout.dialog_create_group, null);
        EditText groupNameEditText = dialogView.findViewById(R.id.groupNameEditText);

        builder.setView(dialogView);

        builder.setPositiveButton("Create", (dialog, which) -> {
            String groupName = groupNameEditText.getText().toString().trim();
            if (!groupName.isEmpty()) {
                addGroupToLayout(groupName);
            } else {
                Toast.makeText(FullScreenActivity.this, "Please enter a group name", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        builder.create().show();
    }

    private void addGroupToLayout(String groupName) {
        TextView groupTextView = new TextView(this);
        groupTextView.setText(groupName);
        groupTextView.setPadding(16, 16, 16, 16);
        groupTextView.setTextSize(18);
        groupTextView.setBackgroundResource(android.R.drawable.dialog_holo_light_frame);

        groupShowLayout.addView(groupTextView);
    }

    private void openProfile() {
        Toast.makeText(this, "Profile Button Clicked", Toast.LENGTH_SHORT).show();
    }
}
